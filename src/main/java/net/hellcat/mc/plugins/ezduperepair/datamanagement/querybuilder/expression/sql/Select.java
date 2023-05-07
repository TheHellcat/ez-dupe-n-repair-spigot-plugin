package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.sql;

import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.Verb;

import java.util.Map;

public class Select extends Verb
{
    @Override
    public String get()
    {
        int i;
        StringBuilder columns= new StringBuilder();

        i=0;
        for(Map.Entry<String, String> entry : data.entrySet() )
        {
            if(i>0)
            {
                columns.append(", ");
            }

            columns.append( entry.getKey() );

            i++;
        }

        return "SELECT " + columns + " FROM " + table;
    }
}
