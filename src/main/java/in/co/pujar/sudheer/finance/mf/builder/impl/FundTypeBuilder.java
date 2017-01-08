package in.co.pujar.sudheer.finance.mf.builder.impl;

import in.co.pujar.sudheer.finance.mf.bo.Fund;
import in.co.pujar.sudheer.finance.mf.bo.FundType;
import in.co.pujar.sudheer.finance.mf.builder.Builder;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by sudhe on 28-12-2016.
 */
public class FundTypeBuilder implements Builder<FundType> {
    private String fundTypeName;
    private Set<Fund> funds;

    /**
     *
     * @return
     */
    @Override
    public FundType build() {
        return new FundType(this);
    }

    /**
     *
     * @param fundTypeName
     * @return
     */
    public  FundTypeBuilder fundTypeName(String fundTypeName){
       this.fundTypeName=fundTypeName;
        return this;
    }

    /**
     *
     * @param funds
     * @return
     */
    public  FundTypeBuilder funds(Set<Fund> funds){
        this.getFunds().addAll(funds);
        return this;
    }

    /**
     *
     * @return
     */
    public String getFundTypeName() {
        return fundTypeName;
    }

    /**
     *
     * @return
     */
    public Set<Fund> getFunds() {
        return funds==null ? new TreeSet<>() : funds;
    }
}
