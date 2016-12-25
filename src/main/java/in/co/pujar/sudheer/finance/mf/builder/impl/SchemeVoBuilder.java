package in.co.pujar.sudheer.finance.mf.builder.impl;

import in.co.pujar.sudheer.finance.mf.builder.Builder;
import in.co.pujar.sudheer.finance.mf.vo.SchemeVo;

/**
 * Created by sudhe on 25-12-2016.
 */
public class SchemeVoBuilder implements Builder {

    private String code;
    private String name;
    private String gISIN;
    private String rISIN;
    private String nav;
    private String rPrice;
    private String sPrice;
    private String date;

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

    public SchemeVoBuilder code(String code) {
        this.code = code;
        return this;
    }

    public SchemeVoBuilder name(String name) {
        this.name = name;
        return this;
    }

    public SchemeVoBuilder gISIN(String gISIN) {
        this.gISIN = gISIN;
        return this;
    }

    public SchemeVoBuilder rISIN(String rISIN) {
        this.rISIN = rISIN;
        return this;
    }

    public SchemeVoBuilder nav(String nav) {
        this.nav = nav;
        return this;
    }

    public SchemeVoBuilder rPrice(String rPrice) {
        this.rPrice = rPrice;
        return this;
    }

    public SchemeVoBuilder sPrice(String sPrice) {
        this.sPrice = sPrice;
        return this;
    }

    public SchemeVoBuilder date(String date) {
        this.date = date;
        return this;
    }

    public SchemeVo build(){
        return new SchemeVo(this);
    }
}
