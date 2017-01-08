package in.co.pujar.sudheer.finance.mf.builder.impl;

import in.co.pujar.sudheer.finance.mf.bo.Fund;
import in.co.pujar.sudheer.finance.mf.bo.Scheme;
import in.co.pujar.sudheer.finance.mf.builder.Builder;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by sudhe on 27-12-2016.
 */
public class FundBuilder implements Builder<Fund> {
    private String fundName;
    private Set<Scheme> schemes;

    /**
     *
     * @return
     */
    @Override
    public Fund build() {
        return new Fund(this);
    }

    /**
     *
     * @param fundName
     * @return
     */
    public FundBuilder fundName(String fundName){
        this.fundName=fundName;
        return this;
    }

    /**
     *
     * @param schemes
     * @return
     */
    public FundBuilder schemes(Set<Scheme> schemes){
        this.getSchemes().addAll(schemes);
        return this;
    }

    /**
     *
     * @return
     */
    public String getFundName() {
        return fundName;
    }

    /**
     *
     * @return
     */
    public Set<Scheme> getSchemes() {
        return schemes==null ? new TreeSet<>() : schemes;
    }
}
