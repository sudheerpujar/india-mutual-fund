package in.co.pujar.sudheer.finance.mf.builder.impl;

import in.co.pujar.sudheer.finance.mf.builder.Builder;
import in.co.pujar.sudheer.finance.mf.vo.SchemeVo;

/**
 * Created by sudhe on 25-12-2016.
 */
public class SchemeVoBuilder implements Builder<SchemeVo> {

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
     * @param code
     * @return
     */
    public SchemeVoBuilder code(String code) {
        this.code = code;
        return this;
    }

    /**
     *
     * @param name
     * @return
     */
    public SchemeVoBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @param gISIN
     * @return
     */
    public SchemeVoBuilder gISIN(String gISIN) {
        this.gISIN = gISIN;
        return this;
    }

    /**
     *
     * @param rISIN
     * @return
     */
    public SchemeVoBuilder rISIN(String rISIN) {
        this.rISIN = rISIN;
        return this;
    }

    /**
     *
     * @param nav
     * @return
     */
    public SchemeVoBuilder nav(String nav) {
        this.nav = nav;
        return this;
    }

    /**
     *
     * @param rPrice
     * @return
     */
    public SchemeVoBuilder rPrice(String rPrice) {
        this.rPrice = rPrice;
        return this;
    }

    public SchemeVoBuilder sPrice(String sPrice) {
        this.sPrice = sPrice;
        return this;
    }

    /**
     *
     * @param date
     * @return
     */
    public SchemeVoBuilder date(String date) {
        this.date = date;
        return this;
    }

    /**
     *
     * @return
     */
    public SchemeVo build(){
        return new SchemeVo(this);
    }
}
