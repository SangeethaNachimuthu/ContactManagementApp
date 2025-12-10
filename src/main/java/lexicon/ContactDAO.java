package lexicon;

/*
This class holds the dataTypes of the application.
 */

public class ContactDAO {

    String name;
    String mobileNo;
    String combineNameMobileNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCombineNameMobileNo() {
        return combineNameMobileNo;
    }

    public void setCombineNameMobileNo(String combineNameMobileNo) {
        this.combineNameMobileNo = combineNameMobileNo;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Mobile: " + mobileNo;
    }
}
