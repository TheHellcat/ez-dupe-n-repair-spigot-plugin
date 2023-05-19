package net.hellcat.mc.plugins.ezduperepair.datamanagement;

import net.hellcat.mc.plugins.ezduperepair.datamanagement.adapter.DataValueType;

public class TableField
{
    private String columnName;
    private DataValueType columnType;
    private boolean isId;
    private boolean isAutoincrement;
    private boolean isUnique;
    private boolean canBeNull;

    TableField()
    {
        setDefaults();
    }

    TableField( String name, DataValueType type )
    {
        setDefaults();
        columnName = name;
        columnType = type;
    }

    private void setDefaults()
    {
        isId = false;
        isAutoincrement = false;
        isUnique = false;
        canBeNull = true;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public DataValueType getColumnType() {
        return columnType;
    }

    public void setColumnType(DataValueType columnType) {
        this.columnType = columnType;
    }

    public boolean isId() {
        return isId;
    }

    public void setId(boolean id) {
        isId = id;
    }

    public boolean isAutoincrement() {
        return isAutoincrement;
    }

    public void setAutoincrement(boolean autoincrement) {
        isAutoincrement = autoincrement;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public void setUnique(boolean unique) {
        isUnique = unique;
    }

    public boolean isCanBeNull() {
        return canBeNull;
    }

    public void setCanBeNull(boolean canBeNull) {
        this.canBeNull = canBeNull;
    }
}
