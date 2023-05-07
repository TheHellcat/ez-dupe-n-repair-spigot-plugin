package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder;

public class Sqlite extends QueryBuilderBase
{
    @Override
    protected void initExpressions()
    {
        expressions = new SqlitExpressions();
    }

    @Override
    protected void initDialectConfig()
    {
        dialectConfig.setColumnNameOpen("");
        dialectConfig.setColumnNameClose("");
        dialectConfig.setValueOpen("'");
        dialectConfig.setValueClose("'");
        dialectConfig.setGroupOpen("(");
        dialectConfig.setGroupClose(")");
        dialectConfig.setAndTerm("AND");
        dialectConfig.setOrTerm("OR");
    }

    @Override
    public String getQuery()
    {
        String where = "";

        if( whereCount > 0 )
        {
            where = " WHERE" + mergeWhere();
        }

        return queryVerb.data(table, data).get() + where + ";";
    }
}
