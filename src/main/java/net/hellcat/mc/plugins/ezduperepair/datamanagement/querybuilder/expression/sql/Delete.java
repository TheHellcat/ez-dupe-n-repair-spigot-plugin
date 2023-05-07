package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.sql;

import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.Verb;

public class Delete extends Verb
{
    @Override
    public String get()
    {
        return "DELETE FROM " + table;
    }
}
