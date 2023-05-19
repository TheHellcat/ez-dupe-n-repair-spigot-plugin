package net.hellcat.mc.plugins.ezduperepair.datamanagement.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

public class SqliteAdapterTest
{
    @Test
    public void executeQueryTest()
    {
        //String dbPath = System.getProperty("java.io.tmpdir");
        String dbPath = System.getProperty("user.dir");
        String dbFile;
        String table = "dm_driver_test_sqlite";
        long time = System.currentTimeMillis() / 1000L;
        HashMap<Integer, DataValue> paramData = new HashMap<>();
        DataValue a = new DataValue();
        DataValue b = new DataValue();
        DataValue c = new DataValue();
        HashMap<Integer, HashMap<String, DataValue>> r;

        if( dbPath.charAt( dbPath.length()-1 ) != '/' )
        {
            dbPath = dbPath + "/";
        }

        dbFile = dbPath + "test_" + time + ".db";

        Sqlite sqlite = new Sqlite();
        sqlite.connect(dbFile, "", "", "");

        Assertions.assertNull( sqlite.getLastError() );

        sqlite.clearLastError();

        String q = "CREATE TABLE IF NOT EXISTS \"" + table + "\" (" +
                " \"id\" INTEGER NOT NULL UNIQUE," +
                " \"a\" INTEGER NULL," +
                " \"b\" REAL NULL," +
                " \"c\" TEXT NOT NULL DEFAULT 'n/a'," +
                " PRIMARY KEY(\"id\")" +
                " );";
        sqlite.executeQuery(q);
        Assertions.assertNull( sqlite.getLastError() );

        q = "INSERT INTO " + table + " (a, b, c) VALUES (?, ?, ?);";

        a.set(42);
        b.set(13.37f);
        c.set("beep");
        paramData.put( 1, a );
        paramData.put( 2, b );
        paramData.put( 3, c );
        sqlite.executeQuery(q, paramData);
        Assertions.assertNull( sqlite.getLastError() );

        a.set(1337);
        b.set(4.2f);
        c.set("boop");
        paramData.put( 1, a );
        paramData.put( 2, b );
        paramData.put( 3, c );
        sqlite.executeQuery(q, paramData);
        Assertions.assertNull( sqlite.getLastError() );

        a.set(33);
        b.set(3.3f);
        c.set("three");
        paramData.put( 1, a );
        paramData.put( 2, b );
        paramData.put( 3, c );
        sqlite.executeQuery(q, paramData);
        Assertions.assertNull( sqlite.getLastError() );

        paramData.clear();
        a.set(2);
        paramData.put(1, a);
        q = "SELECT * FROM " + table + " WHERE id = ?;";
        r = sqlite.executeQuery(q, paramData);
        Assertions.assertNull( sqlite.getLastError() );
        Assertions.assertEquals( 1337, r.get(0).get("a").getInt() );
        Assertions.assertEquals( 4.2f, r.get(0).get("b").getFloat() );
        Assertions.assertEquals( "boop", r.get(0).get("c").getString() );

        q = "SELECT * FROM " + table + ";";
        r = sqlite.executeQuery(q);
        Assertions.assertNull( sqlite.getLastError() );
        Assertions.assertEquals( 3, r.size() );
    }
}
