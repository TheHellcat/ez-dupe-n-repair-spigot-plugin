package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.sql;

import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.Expression;

public class Equal extends Expression
{
    @Override
    public String get()
    {
        return leftSide + " = " + rightSide;
    }
}
