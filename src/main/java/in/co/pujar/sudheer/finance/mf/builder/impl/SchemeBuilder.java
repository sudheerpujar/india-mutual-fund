package in.co.pujar.sudheer.finance.mf.builder.impl;

import in.co.pujar.sudheer.finance.mf.bo.Scheme;
import in.co.pujar.sudheer.finance.mf.builder.Builder;
import in.co.pujar.sudheer.finance.mf.vo.SchemeVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sudhe on 25-12-2016.
 */
public class SchemeBuilder implements Builder<Scheme> {
    public static final String  SCHEME_DATE_FORMAT="dd-MMM-yyyy";
    public static final String PRICE_NOT_AVAILABLE = "N.A.";
    public static final String PRICE_NOT_AVAILABLE_FLOAT = "-1.0";
    private int code;
    private String name;
    private String gISIN;
    private String rISIN;
    private float nav;
    private float rPrice;
    private float sPrice;
    private Date date;


    /**
     *
     * @param code
     * @return
     */
    public SchemeBuilder code(int code) {
        this.code = code;
        return this;
    }

    /**
     *
     * @param name
     * @return
     */
    public SchemeBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @param gISIN
     * @return
     */
    public SchemeBuilder gISIN(String gISIN) {
        this.gISIN = gISIN;
        return this;
    }

    /**
     *
     * @param rISIN
     * @return
     */
    public SchemeBuilder rISIN(String rISIN) {
        this.rISIN = rISIN;
        return this;
    }

    /**
     *
     * @param nav
     * @return
     */
    public SchemeBuilder nav(float nav) {
        this.nav = nav;
        return this;
    }

    /**
     *
     * @param rPrice
     * @return
     */
    public SchemeBuilder rPrice(float rPrice) {
        this.rPrice = rPrice;
        return this;
    }

    /**
     *
     * @param sPrice
     * @return
     */
    public SchemeBuilder sPrice(float sPrice) {
        this.sPrice = sPrice;
        return this;
    }

    /**
     *
     * @param date
     * @return
     */
    public SchemeBuilder date(Date date) {
        this.date = date;
        return this;
    }

    /**
     *
     * @param vo
     * @return
     */
    public SchemeBuilder schemeVo(SchemeVo vo){
        return code(Integer.parseInt(vo.getCode()))
                .name(vo.getName())
                .gISIN(vo.getgISIN())
                .rISIN(vo.getrISIN())
                .nav(parsePrice(vo.getNav()))
                .rPrice(parsePrice(vo.getrPrice()))
                .sPrice(parsePrice(vo.getsPrice()))
                .date( parseSchemeDate(vo.getDate()));
    }

    /**
     *
     * @return
     */
    public int getCode() {
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
    public float getNav() {
        return nav;
    }

    /**
     *
     * @return
     */
    public float getrPrice() {
        return rPrice;
    }

    /**
     *
     * @return
     */
    public float getsPrice() {
        return sPrice;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @return
     */
    @Override
    public Scheme build() {
        return new Scheme(this);
    }

    /**
     *
     * @param schemeDate
     * @return
     */
    private Date parseSchemeDate(String  schemeDate) {
        Date date=new Date();
        try {
            date= (new SimpleDateFormat(SchemeBuilder.SCHEME_DATE_FORMAT)).parse(schemeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     *
     * @param s
     * @return
     */
    private float parsePrice(String s) {
        return Float.parseFloat(s.equals(PRICE_NOT_AVAILABLE)? PRICE_NOT_AVAILABLE_FLOAT :s);
    }
}
