package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder;

import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.Expression;
import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.Verb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class QueryBuilderBase
{
    protected QueryTypeEnum queryType;
    protected Verb queryVerb;
    protected String table;
    protected HashMap<String, String> data;
    protected HashMap< Integer, HashMap< String, String > > wheres;
    protected HashMap< Integer,  HashMap< String, String > > orWheres;
    protected HashMap< Integer, Expression> whereExpressions;
    protected int whereCount;
    protected DialectConfig dialectConfig;

    protected ExpressionsInterface expressions;

    QueryBuilderBase()
    {
        dialectConfig = new DialectConfig();

        initExpressions();
        initDialectConfig();

        reset();
    }

    public void reset()
    {
        queryType = QueryTypeEnum.NONE;
        queryVerb = null;
        table = "";
        data = new HashMap<>();
        whereCount = 0;
        wheres = new HashMap<>();
        orWheres = new HashMap<>();
        whereExpressions = new HashMap<>();
    }

    public QueryBuilderBase select(String table, ArrayList<String> columns)
    {
        queryType = QueryTypeEnum.SELECT;
        queryVerb = expressions.select();
        this.table = table;

        for( String column : columns )
        {
            data.put(dialectConfig.getColumnNameOpen() + column + dialectConfig.getColumnNameClose(), "" );
        }

        return this;
    }

    public QueryBuilderBase insert(String table, HashMap<String, String> data)
    {
        queryType = QueryTypeEnum.INSERT;
        queryVerb = expressions.insert();
        this.table = table;

        for( Map.Entry<String, String> entry : data.entrySet() )
        {
            this.data.put(
                    dialectConfig.getColumnNameOpen() + entry.getKey() + dialectConfig.getColumnNameClose(),
                    dialectConfig.getValueOpen() + entry.getValue() + dialectConfig.getValueClose()
            );
        }

        return this;
    }

    public QueryBuilderBase update(String table, HashMap<String, String> data)
    {
        queryType = QueryTypeEnum.UPDATE;
        queryVerb = expressions.update();
        this.table = table;

        for( Map.Entry<String, String> entry : data.entrySet() )
        {
            this.data.put(
                    dialectConfig.getColumnNameOpen() + entry.getKey() + dialectConfig.getColumnNameClose(),
                    dialectConfig.getValueOpen() + entry.getValue() + dialectConfig.getValueClose()
            );
        }

        return this;
    }

    public QueryBuilderBase delete(String table)
    {
        queryType = QueryTypeEnum.DELETE;
        queryVerb = expressions.delete();
        this.table = table;
        return this;
    }

    public QueryBuilderBase where( HashMap< String, String > criteria )
    {
        whereCount++;
        wheres.put( whereCount, criteria );
        return this;
    }

    public QueryBuilderBase orWhere( HashMap< String, String > criteria )
    {
        whereCount++;
        orWheres.put( whereCount, criteria );
        return this;
    }

    public QueryBuilderBase whereExpression( Expression operator )
    {
        whereExpressions.put( whereCount, operator );
        return this;
    }

    protected String mergeWhere()
    {
        int i;
        int j;
        Expression expression;
        StringBuilder whereClause = new StringBuilder();
        StringBuilder column = new StringBuilder();
        StringBuilder value = new StringBuilder();
        HashMap< String, String > currentWhereSet;

        currentWhereSet = new HashMap<>();

        for( i=1; i<=whereCount; i++ )
        {
            //currentWhereSet.clear();
            currentWhereSet = new HashMap<>();

            if( wheres.containsKey( i ) )
            {
                if (i > 1) {
                    whereClause.append(" ").append(dialectConfig.getAndTerm());
                }

                currentWhereSet = wheres.get(i);
            }

            if( orWheres.containsKey( i ) )
            {
                if (i > 1) {
                    whereClause.append(" ").append(dialectConfig.getOrTerm());
                }

                currentWhereSet = orWheres.get(i);
            }

            if( currentWhereSet.size() > 0 )
            {
                if( currentWhereSet.size() > 1 )
                {
                    whereClause.append( " " ).append( dialectConfig.getGroupOpen() );
                }

                j = 0;
                for( Map.Entry<String, String> entry : currentWhereSet.entrySet() )
                {
                    if( j > 0 )
                    {
                        whereClause.append( " " ).append( dialectConfig.getAndTerm() );
                    }
                    j++;

                    column.setLength(0);
                    column
                            .append( dialectConfig.getColumnNameOpen() )
                            .append( entry.getKey() )
                            .append( dialectConfig.getColumnNameClose() );

                    value.setLength(0);
                    value
                            .append( dialectConfig.getValueOpen() )
                            .append( entry.getValue() )
                            .append( dialectConfig.getValueClose() );

                    expression = expressions.equal();
                    if( whereExpressions.containsKey( i ) )
                    {
                        expression = whereExpressions.get(i);
                    }

                    whereClause
                            .append( " " )
                            .append( expression.values( column.toString(), value.toString() ).get() );
                }

                if( currentWhereSet.size() > 1 )
                {
                    whereClause.append( " " ).append( dialectConfig.getGroupClose() );
                }
            }
        }

        return whereClause.toString();
    }

    public ExpressionsInterface getExpressions()
    {
        return expressions;
    }

    protected abstract void initExpressions();
    protected abstract void initDialectConfig();
    public abstract String getQuery();
}
