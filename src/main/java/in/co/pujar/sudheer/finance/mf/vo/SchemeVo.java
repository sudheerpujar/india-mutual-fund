package in.co.pujar.sudheer.finance.mf.vo;

import in.co.pujar.sudheer.finance.mf.builder.impl.SchemeVoBuilder;

/**
 * Created by sudhe on 25-12-2016.
 */
public class SchemeVo {
    private String code;
    private String name;
    private String gISIN;
    private String rISIN;
    private String nav;
    private String rPrice;
    private String sPrice;
    private String date;

    public SchemeVo(SchemeVoBuilder schemeVoBuilder) {
        this.code=schemeVoBuilder.getCode();
        this.name=schemeVoBuilder.getName();
        this.gISIN=schemeVoBuilder.getgISIN();
        this.rISIN=schemeVoBuilder.getrISIN();
        this.nav=schemeVoBuilder.getNav();
        this.rPrice=schemeVoBuilder.getrPrice();
        this.sPrice=schemeVoBuilder.getsPrice();
        this.date=schemeVoBuilder.getDate();
    }

    public String getCode() {
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

    public String getNav() {
        return nav;
    }

    public String getrPrice() {
        return rPrice;
    }

    public String getsPrice() {
        return sPrice;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchemeVo)) return false;

        SchemeVo schemeVo = (SchemeVo) o;

        if (!getCode().equals(schemeVo.getCode())) return false;
        if (!getName().equals(schemeVo.getName())) return false;
        if (!getgISIN().equals(schemeVo.getgISIN())) return false;
        if (!getrISIN().equals(schemeVo.getrISIN())) return false;
        if (!getNav().equals(schemeVo.getNav())) return false;
        if (!getrPrice().equals(schemeVo.getrPrice())) return false;
        if (!getsPrice().equals(schemeVo.getsPrice())) return false;
        return getDate().equals(schemeVo.getDate());

    }

    @Override
    public int hashCode() {
        int result = getCode().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getgISIN().hashCode();
        result = 31 * result + getrISIN().hashCode();
        result = 31 * result + getNav().hashCode();
        result = 31 * result + getrPrice().hashCode();
        result = 31 * result + getsPrice().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SchemeVo{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", gISIN='" + gISIN + '\'' +
                ", rISIN='" + rISIN + '\'' +
                ", nav='" + nav + '\'' +
                ", rPrice='" + rPrice + '\'' +
                ", sPrice='" + sPrice + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
