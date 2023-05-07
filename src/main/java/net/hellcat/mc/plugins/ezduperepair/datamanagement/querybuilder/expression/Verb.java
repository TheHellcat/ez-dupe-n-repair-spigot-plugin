package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression;

import java.util.HashMap;

public abstract class Verb
{
    protected String table;
    protected HashMap<String, String> data;

    public Verb data( String table, HashMap<String, String> data )
    {
        this.table = table;
        this.data = data;

        return this;
    }

    public abstract String get();
}
