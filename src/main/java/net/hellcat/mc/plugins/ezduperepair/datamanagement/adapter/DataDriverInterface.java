package net.hellcat.mc.plugins.ezduperepair.datamanagement.adapter;

import java.util.HashMap;

public interface DataDriverInterface
{
    public DataValueType getNativeTypeForType( DataValueType valueType );
    public String getNativeTermForType( DataValueType valueType );
    public void connect( String database, String host, String username, String password );
    public HashMap<Integer, HashMap<String, DataValue>> executeQuery(String query);
    public HashMap<Integer, HashMap<String, DataValue>> executeQuery(String query, HashMap<Integer, DataValue> parameters);
    public String getLastError();
    public void clearLastError();
}
