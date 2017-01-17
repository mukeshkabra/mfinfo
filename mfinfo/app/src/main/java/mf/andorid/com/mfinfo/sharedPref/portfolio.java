package mf.andorid.com.mfinfo.sharedPref;

/**
 * Created by 8398 on 30/11/16.
 */
public class portfolio {
    private String mName;
    private String mcode;
    private Double mcurrentNav;
    private Double mdateNav;
    private Double mChange;
    private Double mAmountInvested;
    private Double mUnits;
    private String mCurrentDate;

    public portfolio(String name, Double datenav, Double currentNav,String mcode, Double change, String mdate,Double Units,Double amountInvested) {
        super();
        this.mName = name;
        this.mcurrentNav = currentNav;
        this.mcode = mcode;
        this.mChange=change;
        this.mCurrentDate=mdate;
        this.mdateNav=datenav;
        this.mAmountInvested=amountInvested;
        this.mUnits=Units;


    }
    public String getCode() {
        return mcode;
    }
    public Double getCurrentNav(){
        return mcurrentNav;
    }
    public String getName() {
        return mName;
    }
    public Double getdateNav() {
        return mdateNav;
    }
    public String getdate(){
        return mCurrentDate;
    }
    public Double getChange(){
        return mChange;
    }
    public Double getAmoutInv(){
        return mAmountInvested;
    }
    public Double getUnits(){
        return mUnits;
    }


    public void setName(String name) {
        this.mName = name;
    }
    public void setCode(String code) {
        this.mcode = code;
    }
    public void setCurrentNav(Double currentNav){
        this.mAmountInvested=currentNav;
    }





}
