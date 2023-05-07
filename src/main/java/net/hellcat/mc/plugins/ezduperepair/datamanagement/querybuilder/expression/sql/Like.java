package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.sql;

import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.Expression;

public class Like extends Expression
{
    @Override
    public String get()
    {
        return leftSide + " LIKE " + rightSide;
    }
}
