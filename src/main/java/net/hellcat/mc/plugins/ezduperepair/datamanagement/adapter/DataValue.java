package net.hellcat.mc.plugins.ezduperepair.datamanagement.adapter;

public class DataValue
{
    private DataValueType type = DataValueType.UNKNOWN;
    private int intValue;
    private float floatValue;
    private String stringValue;
    private boolean boolValue;

    public DataValueType getType()
    {
        return type;
    }

    public void set(int value)
    {
        type = DataValueType.INT;
        intValue = value;
    }

    public void set( float value )
    {
        type = DataValueType.FLOAT;
        floatValue = value;
    }

    public void set( String value )
    {
        type = DataValueType.STRING;
        stringValue = value;
    }

    public void set( boolean value )
    {
        type = DataValueType.BOOL;
        boolValue = value;
    }

    public int getInt()
    {
        return intValue;
    }

    public float getFloat()
    {
        return floatValue;
    }

    public String getString()
    {
        return stringValue;
    }

    public boolean getBool()
    {
        return boolValue;
    }
}
