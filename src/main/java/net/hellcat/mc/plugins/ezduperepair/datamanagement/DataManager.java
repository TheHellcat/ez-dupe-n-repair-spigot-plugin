package net.hellcat.mc.plugins.ezduperepair.datamanagement;

import net.hellcat.mc.plugins.ezduperepair.magicchests.MagicChestBase;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is kind of a teeny-tiny-wannabe version of an ORM mapper.
 * For simple inserts, updates, selects and deletes, no SQL queries are needed,
 * just give the data manager a proper data entity object for persisting changes or
 * tell it what you want to get a neat data entity object with all the values back.
 */
public class DataManager
{
    /**
     * Inserts a data entity into it's associated database table, updates if it already exists.
     *
     * @param dataEntity Data entity object defining it's associated table and entry data.
     */
    public void persist(DataEntityBase dataEntity)
    {}

    /**
     * Deletes a data entities entry in it's associated database table from the table.
     * Does nothing if the entry doesn't exist.
     *
     * @param dataEntity Data entity object defining it's associated table and entry data.
     */
    public void delete(DataEntityBase dataEntity)
    {}

    /**
     * Finds all entries for the given data entity type that match the select criteria.
     *
     * @param conditions Map of values to query for, key of the map is the column name, value is the queried value.
     * @return Returns an array of the given data entity object, one object for each entry found, matching the criteria, in the database.
     * @param <T> Data entity object defining it's associated table.
     */
    public <T> ArrayList<DataEntityBase> find(HashMap<String, MagicChestBase> conditions)
    {
        return null;
    }

    /**
     * Find all entries for the given data entity type.
     *
     * @return Returns an array of the given data entity object, one object for each entry in the database.
     * @param <T> Data entity object defining it's associated table.
     */
    public <T> ArrayList<DataEntityBase> find()
    {
        return null;
    }

    /**
     * Creates a database table based on the definition of the data entity object.
     *
     * @param dataEntity Data entity object to create a database table for.
     */
    public void createTable(DataEntityBase dataEntity)
    {}
}
