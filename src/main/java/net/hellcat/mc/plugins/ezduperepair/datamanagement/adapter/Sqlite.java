package net.hellcat.mc.plugins.ezduperepair.datamanagement.adapter;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Sqlite implements DataDriverInterface
{
    HashMap<DataValueType, DataValueType> typeMappings;
    HashMap<DataValueType, String> typeTermMappings;
    private Connection dbConnection = null;
    private String lastError = null;

    Sqlite()
    {
        typeMappings = new HashMap<>();
        typeTermMappings = new HashMap<>();

        typeMappings.put(DataValueType.INT, DataValueType.INT);
        typeMappings.put(DataValueType.FLOAT, DataValueType.FLOAT);
        typeMappings.put(DataValueType.STRING, DataValueType.STRING);
        typeMappings.put(DataValueType.TEXT, DataValueType.STRING);
        typeMappings.put(DataValueType.BOOL, DataValueType.INT);
        typeMappings.put(DataValueType.DATE, DataValueType.INT);
        typeMappings.put(DataValueType.DATETIME, DataValueType.INT);

        typeTermMappings.put(DataValueType.INT, "INTEGER");
        typeTermMappings.put(DataValueType.FLOAT, "REAL");
        typeTermMappings.put(DataValueType.STRING, "TEXT");
        typeTermMappings.put(DataValueType.TEXT, "TEXT");
        typeTermMappings.put(DataValueType.BOOL, "INTEGER");
        typeTermMappings.put(DataValueType.DATE, "INTEGER");
        typeTermMappings.put(DataValueType.DATETIME, "INTEGER");
    }

    @Override
    public DataValueType getNativeTypeForType(DataValueType valueType)
    {
        if( !typeMappings.containsKey(valueType) )
        {
            throw new RuntimeException("Unsupported / unknown value type");
        }

        return typeMappings.get( valueType );
    }

    @Override
    public String getNativeTermForType(DataValueType valueType)
    {
        if( !typeTermMappings.containsKey(valueType) )
        {
            throw new RuntimeException("Unsupported / unknown value type");
        }

        return typeTermMappings.get( valueType );
    }

    @Override
    public void connect(String database, String host, String username, String password)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection("jdbc:sqlite:" + database);
        }
        catch (ClassNotFoundException e)
        {
            lastError = e.getClass().getName() + ": " + e.getMessage();
        }
        catch ( Exception e )
        {
            lastError = e.getClass().getName() + ": " + e.getMessage();
        }
        finally
        {
        }
    }

    @Override
    public HashMap<Integer, HashMap<String, DataValue>> executeQuery(String query)
    {
        HashMap<Integer, DataValue> parameters = new HashMap<>();
        return executeQuery( query, parameters );
    }

    @Override
    public HashMap<Integer, HashMap<String, DataValue>> executeQuery(String query, HashMap<Integer, DataValue> parameters)
    {
        ResultSet rs;
        ResultSetMetaData rsMeta;
        int numCols;
        int i;
        String columnName;
        HashMap<Integer, HashMap<String, DataValue>> resultData = new HashMap<>();

        try
        {
            PreparedStatement ps = dbConnection.prepareStatement(query);

            for( Map.Entry<Integer, DataValue> parameter : parameters.entrySet() )
            {
                switch( parameter.getValue().getType() ) {
                    case INT:
                        ps.setInt( parameter.getKey(), parameter.getValue().getInt() );
                        break;

                    case FLOAT:
                        ps.setFloat( parameter.getKey(), parameter.getValue().getFloat() );
                        break;

                    case STRING:
                        ps.setString( parameter.getKey(), parameter.getValue().getString() );
                        break;

                    case BOOL:
                        int v = parameter.getValue().getBool() ? 1 : 0;
                        ps.setInt( parameter.getKey(), v );
                        break;

                    default:
                        throw new RuntimeException("Unsupported parameter value type " + parameter.getValue().getType().toString());
                }
            }

            if( !query.trim().substring(0, 6).equalsIgnoreCase("SELECT") )
            {
                ps.executeUpdate();
                return resultData;
            }

            rs = ps.executeQuery();

            while( rs.next() )
            {
                rsMeta = rs.getMetaData();

                HashMap<String, DataValue> rowData = new HashMap<>();
                numCols = rsMeta.getColumnCount();
                for( i=1; i<=numCols; i++ )
                {
                    DataValue columnData = new DataValue();
                    columnName = rsMeta.getColumnLabel(i);
                    switch( rsMeta.getColumnType(i) )
                    {
                        case Types.INTEGER:
                            columnData.set( rs.getInt(i) );
                            break;

                        case Types.FLOAT:
                        case Types.REAL:
                            columnData.set( rs.getFloat(i) );
                            break;

                        case Types.CHAR:
                        case Types.VARCHAR:
                        case Types.NVARCHAR:
                        case Types.LONGVARCHAR:
                        case Types.LONGNVARCHAR:
                        case Types.NCHAR:
                            columnData.set( rs.getString(i) );
                            break;

                        default:
                            throw new RuntimeException("Unsupported value type from SQL: " + rsMeta.getColumnType(i));
                    }

                    rowData.put( columnName, columnData );
                }

                resultData.put( resultData.size(), rowData);
            }

        } catch( Exception e ) {
            lastError = e.getClass().getName() + ": " + e.getMessage();
        }

        return resultData;
    }

    @Override
    public String getLastError()
    {
        return lastError;
    }

    @Override
    public void clearLastError()
    {
        lastError = null;
    }
}
