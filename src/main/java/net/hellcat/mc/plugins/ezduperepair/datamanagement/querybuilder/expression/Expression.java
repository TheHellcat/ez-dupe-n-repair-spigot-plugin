package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression;

public abstract class Expression
{
    protected String leftSide;
    protected String rightSide;

    public Expression values( String left, String right )
    {
        leftSide = left;
        rightSide = right;
        return this;
    }

    public abstract String get();
}
