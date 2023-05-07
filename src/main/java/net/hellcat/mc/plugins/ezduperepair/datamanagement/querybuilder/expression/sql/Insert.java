package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.sql;

import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.Verb;

import java.util.Map;

public class Insert extends Verb
{
    @Override
    public String get()
    {
        int i;
        StringBuilder columns= new StringBuilder();
        StringBuilder values= new StringBuilder();

        columns.append("(");
        values.append("(");

        i=0;
        for(Map.Entry<String, String> entry : data.entrySet() )
        {
            if(i>0)
            {
                columns.append(", ");
                values.append(", ");
            }

            columns.append( entry.getKey() );
            values.append( entry.getValue() );

            i++;
        }

        columns.append(")");
        values.append(")");

        return "INSERT INTO " + table + " " + columns + " VALUES " + values;
    }
}
