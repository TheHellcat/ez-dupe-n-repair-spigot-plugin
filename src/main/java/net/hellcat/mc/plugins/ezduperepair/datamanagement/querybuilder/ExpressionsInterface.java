package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder;

import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.Expression;
import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.Verb;

public interface ExpressionsInterface
{
    public Expression equal();
    public Expression like();
    public Verb select();
    public Verb insert();
    public Verb update();
    public Verb delete();
}
