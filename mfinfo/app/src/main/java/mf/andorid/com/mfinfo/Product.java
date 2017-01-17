package mf.andorid.com.mfinfo;

/**
 * Created by 8398 on 30/11/16.
 */
public class Product {
    private String mName;
    private String mcode;
    private String mNav;
    private String mChange;
    private String mdate;

    public Product(String name, String mnav, String mcode,String change,String mdate) {
        super();
        this.mName = name;
        this.mNav = mnav;
        this.mcode = mcode;
        this.mChange=change;
        this.mdate=mdate;
    }
    public String getCode() {
        return mcode;
    }

    public void setCode(String id) {
        this.mcode = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getNav() {
        return mNav;
    }

    public void setNav(String Nav) {
        this.mNav = Nav;
    }
    public String gethowmuchChange(){
        return this.mChange;
    }
    public void setHowmuchChange(String change){
        this.mChange=change;
    }
    public String getDate(){
        return this.mdate;
    }
    public void setdate(String date){
        this.mdate=date;
    }



}
