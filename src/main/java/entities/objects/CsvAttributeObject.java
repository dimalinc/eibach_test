package entities.objects;

import java.util.Objects;

public class CsvAttributeObject implements Comparable<CsvAttributeObject> {

    final static String sep = ": ";
    final static String sepEnd = ";";

    String attributeName;
    String attributeValue;
    int number;
    final int customized = 0;

    public CsvAttributeObject(String attributeName, String attributeValue, int number) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.number = number;
    }

    @Override
    public String toString() {
        return "csvAttributeObject{" + /*"\r\n" +*/
                /*"attributeName='" +*/ attributeName + sep +/*'\'' "\r\n" +*/
                /*", attributeValue='" +*/ attributeValue + sep +/*'\''  +"\r\n" +*/
                /*", number=" +*/ number + sep +/*"\r\n" +*/
                /*", customized=" +*/ customized + sepEnd + "\r\n" + '}';
    }

    @Override
    public int compareTo(CsvAttributeObject o) {
        if (this.attributeName.compareTo(o.attributeName) != 0) {
            return this.attributeName.compareTo(o.attributeName);
        } else if (this.attributeValue.compareTo(o.attributeValue) != 0) {
            return this.attributeValue.compareTo(o.attributeValue);
        } else {
            return this.number - o.number;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CsvAttributeObject that = (CsvAttributeObject) o;
        return number == that.number && customized == that.customized && attributeName.equals(that.attributeName) && attributeValue.equals(that.attributeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeName, attributeValue, number, customized);
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public int getNumber() {
        return number;
    }
}
