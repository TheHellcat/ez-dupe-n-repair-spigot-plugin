package net.hellcat.mc.plugins.ezduperepair.datamanagement;

import java.util.ArrayList;

public abstract class DataEntityBase
{
    protected String databaseTable;
    protected ArrayList<TableField> tableFields;

    DataEntityBase()
    {
        setup();
    }

    /**
     * Setting up of database table name and fields/columns for this data entity.
     * For each field defined, a matching pair of getFieldname/setFieldname methods is required,
     * for the data manager to be able to properly access the actual data.
     */
    protected abstract void setup();

    /**
     * @return Returns the table in the database this data entity is associated with.
     */
    public String getTable()
    {
        return databaseTable;
    }

    /**
     * @return Returns all field/column definitions for the database table of this data entity.
     */
    public ArrayList<TableField> getFields()
    {
        return tableFields;
    }
}
