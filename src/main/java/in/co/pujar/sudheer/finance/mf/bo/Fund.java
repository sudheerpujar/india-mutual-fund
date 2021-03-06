package in.co.pujar.sudheer.finance.mf.bo;

import in.co.pujar.sudheer.finance.mf.builder.impl.FundBuilder;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by sudhe on 12-07-2016.
 */
public class Fund {
    private String fundName;
    private Set<Scheme> schemes;

    public Fund(FundBuilder fundBuilder) {
        this.fundName=fundBuilder.getFundName();
        this.getSchemes().addAll(fundBuilder.getSchemes());
    }

    /**
     * @return
     */
    public String getFundName() {
        return fundName;
    }


    /**
     * @return
     */
    public Set<Scheme> getSchemes() {
        return schemes==null?new TreeSet<>():schemes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fund)) return false;

        Fund fund = (Fund) o;

        if (getFundName() != null ? !getFundName().equals(fund.getFundName()) : fund.getFundName() != null)
            return false;
        return !(getSchemes() != null ? !getSchemes().equals(fund.getSchemes()) : fund.getSchemes() != null);

    }

    @Override
    public int hashCode() {
        int result = getFundName() != null ? getFundName().hashCode() : 0;
        result = 31 * result + (getSchemes() != null ? getSchemes().hashCode() : 0);
        return result;
    }

}
