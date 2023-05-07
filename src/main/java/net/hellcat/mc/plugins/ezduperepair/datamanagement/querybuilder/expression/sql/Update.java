package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.sql;

import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.expression.Verb;
import java.util.Map;

public class Update extends Verb
{
    @Override
    public String get()
    {
        int i;
        StringBuilder values= new StringBuilder();

        i=0;
        for(Map.Entry<String, String> entry : data.entrySet() )
        {
            if(i>0)
            {
                values.append(", ");
            }

            values.append( entry.getKey() );
            values.append( " = " );
            values.append( entry.getValue() );

            i++;
        }

        return "UPDATE " + table + " SET " + values;
    }
}
