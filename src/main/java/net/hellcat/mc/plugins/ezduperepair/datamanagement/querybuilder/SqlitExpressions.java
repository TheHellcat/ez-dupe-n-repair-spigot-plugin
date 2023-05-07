package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder;

import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.Expression;
import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.Verb;
import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.sql.*;

public class SqlitExpressions implements ExpressionsInterface
{
    @Override
    public Expression equal() {
        return new Equal();
    }

    @Override
    public Expression like()
    {
        return new Like();
    }

    @Override
    public Verb select() {
        return new Select();
    }

    @Override
    public Verb insert() {
        return new Insert();
    }

    @Override
    public Verb update() {
        return new Update();
    }

    @Override
    public Verb delete() {
        return new Delete();
    }
}
