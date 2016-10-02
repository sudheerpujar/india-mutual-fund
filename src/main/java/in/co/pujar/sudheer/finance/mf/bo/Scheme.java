package in.co.pujar.sudheer.finance.mf.bo;

import java.util.Date;

/**
 * Created by sudhe on 12-07-2016.
 */
public class Scheme {
    public static final String  SCHEME_DATE_FORMAT="DD-MMM-YYYY";
    private int code;
    private String name;
    private String gISIN;
    private String rISIN;
    private float nav;
    private float rPrice;
    private float sPrice;
    private Date date;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getgISIN() {
        return gISIN;
    }

    public void setgISIN(String gISIN) {
        this.gISIN = gISIN;
    }

    public String getrISIN() {
        return rISIN;
    }

    public void setrISIN(String rISIN) {
        this.rISIN = rISIN;
    }

    public float getNav() {
        return nav;
    }

    public void setNav(float nav) {
        this.nav = nav;
    }

    public float getrPrice() {
        return rPrice;
    }

    public void setrPrice(float rPrice) {
        this.rPrice = rPrice;
    }

    public float getsPrice() {
        return sPrice;
    }

    public void setsPrice(float sPrice) {
        this.sPrice = sPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scheme)) return false;

        Scheme scheme = (Scheme) o;

        return getCode() == scheme.getCode();

    }

    @Override
    public int hashCode() {
        return getCode();
    }
}
