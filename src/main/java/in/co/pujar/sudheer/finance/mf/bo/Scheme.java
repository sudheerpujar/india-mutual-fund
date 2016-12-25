package in.co.pujar.sudheer.finance.mf.bo;

import in.co.pujar.sudheer.finance.mf.builder.impl.SchemeBuilder;

import java.util.Date;

/**
 * Created by sudhe on 12-07-2016.
 */
public class Scheme {
    private int code;
    private String name;
    private String gISIN;
    private String rISIN;
    private float nav;
    private float rPrice;
    private float sPrice;
    private Date date;

    public Scheme(SchemeBuilder schemeBuilder) {
        this.code=schemeBuilder.getCode();
        this.name=schemeBuilder.getName();
        this.gISIN=schemeBuilder.getgISIN();
        this.rISIN=schemeBuilder.getrISIN();
        this.nav=schemeBuilder.getNav();
        this.rPrice=schemeBuilder.getrPrice();
        this.sPrice=schemeBuilder.getsPrice();
        this.date=schemeBuilder.getDate();
    }


    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getgISIN() {
        return gISIN;
    }

    public String getrISIN() {
        return rISIN;
    }

    public float getNav() {
        return nav;
    }

    public float getrPrice() {
        return rPrice;
    }

    public float getsPrice() {
        return sPrice;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scheme)) return false;

        Scheme scheme = (Scheme) o;

        if (getCode() != scheme.getCode()) return false;
        if (Float.compare(scheme.getNav(), getNav()) != 0) return false;
        if (Float.compare(scheme.getrPrice(), getrPrice()) != 0) return false;
        if (Float.compare(scheme.getsPrice(), getsPrice()) != 0) return false;
        if (!getName().equals(scheme.getName())) return false;
        if (!getgISIN().equals(scheme.getgISIN())) return false;
        if (!getrISIN().equals(scheme.getrISIN())) return false;
        return getDate().equals(scheme.getDate());

    }

    @Override
    public int hashCode() {
        int result = getCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getgISIN().hashCode();
        result = 31 * result + getrISIN().hashCode();
        result = 31 * result + (getNav() != +0.0f ? Float.floatToIntBits(getNav()) : 0);
        result = 31 * result + (getrPrice() != +0.0f ? Float.floatToIntBits(getrPrice()) : 0);
        result = 31 * result + (getsPrice() != +0.0f ? Float.floatToIntBits(getsPrice()) : 0);
        result = 31 * result + getDate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Scheme{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", gISIN='" + gISIN + '\'' +
                ", rISIN='" + rISIN + '\'' +
                ", nav=" + nav +
                ", rPrice=" + rPrice +
                ", sPrice=" + sPrice +
                ", date=" + date +
                '}';
    }
}
