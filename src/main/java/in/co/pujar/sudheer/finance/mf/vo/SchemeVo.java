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

    /**
     *
     * @param schemeVoBuilder
     */
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

    /**
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getgISIN() {
        return gISIN;
    }

    /**
     *
     * @return
     */
    public String getrISIN() {
        return rISIN;
    }

    /**
     *
     * @return
     */
    public String getNav() {
        return nav;
    }

    /**
     *
     * @return
     */
    public String getrPrice() {
        return rPrice;
    }

    /**
     *
     * @return
     */
    public String getsPrice() {
        return sPrice;
    }

    /**
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param o
     * @return
     */
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

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
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
