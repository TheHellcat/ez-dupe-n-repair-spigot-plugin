package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

public class SqliteBuilderTest
{
    @Test
    public void querySelectTest()
    {
        String query;
        ArrayList<String> selectColumns = new ArrayList<>();
        ArrayList<String> selectColumn = new ArrayList<>();
        HashMap<String, String> whereData1 = new HashMap<>();
        HashMap<String, String> whereData2 = new HashMap<>();
        HashMap<String, String> whereData3 = new HashMap<>();
        HashMap<String, String> whereData4 = new HashMap<>();
        Sqlite queryBuilder = new Sqlite();

        selectColumn.add("*");

        selectColumns.add("col1");
        selectColumns.add("col2");

        whereData1.put("id_col", "42");

        whereData2.put("col3", "test%");

        whereData3.put("col4", "1");
        whereData3.put("col5", "2");

        whereData4.put("col4", "3");
        whereData4.put("col5", "4");

        queryBuilder.reset();
        query = queryBuilder
                .select("test_table", selectColumn)
                .where( whereData1 )
                .getQuery();

        Assertions.assertEquals( "SELECT * FROM test_table WHERE id_col = '42';", query, "Sqlite simple select query failed" );

        queryBuilder.reset();
        query = queryBuilder
                .select("test_table", selectColumn)
                .where( whereData2 )
                .whereExpression( queryBuilder.getExpressions().like() )
                .getQuery();

        Assertions.assertEquals( "SELECT * FROM test_table WHERE col3 LIKE 'test%';", query, "Sqlite simple select-like query failed" );

        queryBuilder.reset();
        query = queryBuilder
                .select("test_table", selectColumns)
                .where( whereData1 )
                .getQuery();

        // as we can't be sure about the order of the array entries, for the select column, make sure we got them in the "correct" order, should they be the other way round
        query = query.replace("col2, col1", "col1, col2");

        Assertions.assertEquals( "SELECT col1, col2 FROM test_table WHERE id_col = '42';", query, "Sqlite explicit fetch columns select query failed" );

        queryBuilder.reset();
        query = queryBuilder
                .select("test_table", selectColumn)
                .where( whereData3 )
                .where( whereData1 )
                .orWhere( whereData4 )
                .getQuery();

        Assertions.assertEquals( "SELECT * FROM test_table WHERE ( col4 = '1' AND col5 = '2' ) AND id_col = '42' OR ( col4 = '3' AND col5 = '4' );", query, "Sqlite multi-where select query failed" );

        queryBuilder.reset();
        query = queryBuilder
                .select("test_table", selectColumn)
                .where( "col1", "1" )
                .orWhere( "col2", "2" )
                .getQuery();

        Assertions.assertEquals( "SELECT * FROM test_table WHERE col1 = '1' OR col2 = '2';", query, "Sqlite single column call where select query failed" );
    }

    @Test
    public void insertQueryTest()
    {
        String query;
        HashMap<String, String> insertData = new HashMap<>();
        Sqlite queryBuilder = new Sqlite();

        insertData.put("col1", "1");
        insertData.put("col2", "2");

        query = queryBuilder
                .insert( "test_table", insertData )
                .getQuery();

        query = query.replace("(col2, col1) VALUES ('2', '1')", "(col1, col2) VALUES ('1', '2')");

        Assertions.assertEquals( "INSERT INTO test_table (col1, col2) VALUES ('1', '2');", query, "Sqlite insert query failed" );
    }

    @Test
    public void updateQueryTest()
    {
        String query;
        HashMap<String, String> updateData = new HashMap<>();
        HashMap<String, String> whereData = new HashMap<>();
        Sqlite queryBuilder = new Sqlite();

        updateData.put("col1", "1");
        updateData.put("col2", "2");

        whereData.put("id_col", "42");

        query = queryBuilder
                .update( "test_table", updateData )
                .where( whereData )
                .getQuery();

        query = query.replace("col2 = '2', col1 = '1'", "col1 = '1', col2 = '2'");

        Assertions.assertEquals( "UPDATE test_table SET col1 = '1', col2 = '2' WHERE id_col = '42';", query, "Sqlite update query failed" );
    }

    @Test
    public void deleteQueryTest()
    {
        String query;
        HashMap<String, String> whereData = new HashMap<>();
        Sqlite queryBuilder = new Sqlite();

        whereData.put("id_col", "42");

        query = queryBuilder
                .delete( "test_table" )
                .where( whereData )
                .getQuery();

        Assertions.assertEquals( "DELETE FROM test_table WHERE id_col = '42';", query, "Sqlite delete query failed" );
    }
}
