package net.hellcat.mc.plugins.ezduperepair;

import net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder.Sqlite;

import java.util.ArrayList;
import java.util.HashMap;

public class Main
{
    public static void main(String[] args)
    {
        Sqlite queryBuilder = new Sqlite();

        ArrayList<String> data = new ArrayList<>();
        data.add("column1");
        data.add("column2");
        data.add("column3");

        HashMap<String, String> data2 = new HashMap<>();
        data2.put( "column1", "blah" );
        data2.put( "column2", "bleh" );
        data2.put( "column3", "meep" );

        HashMap<String, String> whereData = new HashMap<>();
        whereData.put( "column1", "blah" );
        whereData.put( "column2", "bleh" );

        HashMap<String, String> whereData2 = new HashMap<>();
        whereData2.put( "column3", "meep%" );

        System.out.println();

        System.out.println( "Select query:" );

        queryBuilder.reset();
        queryBuilder
                .select("test_table", data)
                .where( whereData2 )
        ;
        System.out.println( queryBuilder.getQuery() );

        queryBuilder.reset();
        queryBuilder
                .select("test_table", data)
                .where( whereData )
                .where( whereData )
                .orWhere( whereData2 )
                .whereExpression( queryBuilder.getExpressions().like() )
        ;
        System.out.println( queryBuilder.getQuery() );

        System.out.println( "" );
        System.out.println( "Insert query:" );

        queryBuilder.reset();
        queryBuilder.insert("test_table", data2);
        System.out.println( queryBuilder.getQuery() );

        System.out.println();
        System.out.println( "Update query:" );

        queryBuilder.reset();
        queryBuilder
                .update("test_table", data2)
                .where( whereData2 )
                .whereExpression( queryBuilder.getExpressions().like() )
        ;
        System.out.println( queryBuilder.getQuery() );

        System.out.println();
        System.out.println( "Delete query:" );

        queryBuilder.reset();
        queryBuilder
                .delete("test_table")
                .where( whereData )
        ;
        System.out.println( queryBuilder.getQuery() );

        System.out.println();
    }
}
