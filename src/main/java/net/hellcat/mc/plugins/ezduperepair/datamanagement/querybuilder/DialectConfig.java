package net.hellcat.mc.plugins.ezduperepair.datamanagement.querybuilder;

public class DialectConfig
{
    private String columnNameOpen;
    private String columnNameClose;
    private String valueOpen;
    private String valueClose;
    private String groupOpen;
    private String groupClose;
    private String andTerm;
    private String orTerm;

    public String getColumnNameOpen() {
        return columnNameOpen;
    }

    public void setColumnNameOpen(String columnNameOpen) {
        this.columnNameOpen = columnNameOpen;
    }

    public String getColumnNameClose() {
        return columnNameClose;
    }

    public void setColumnNameClose(String columnNameClose) {
        this.columnNameClose = columnNameClose;
    }

    public String getValueOpen() {
        return valueOpen;
    }

    public void setValueOpen(String valueOpen) {
        this.valueOpen = valueOpen;
    }

    public String getValueClose() {
        return valueClose;
    }

    public void setValueClose(String valueClose) {
        this.valueClose = valueClose;
    }

    public String getGroupOpen() {
        return groupOpen;
    }

    public void setGroupOpen(String groupOpen) {
        this.groupOpen = groupOpen;
    }

    public String getGroupClose() {
        return groupClose;
    }

    public void setGroupClose(String groupClose) {
        this.groupClose = groupClose;
    }

    public String getAndTerm() {
        return andTerm;
    }

    public void setAndTerm(String andTerm) {
        this.andTerm = andTerm;
    }

    public String getOrTerm() {
        return orTerm;
    }

    public void setOrTerm(String orTerm) {
        this.orTerm = orTerm;
    }
}
