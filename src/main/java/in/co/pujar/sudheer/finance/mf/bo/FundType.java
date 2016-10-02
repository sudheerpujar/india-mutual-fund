package in.co.pujar.sudheer.finance.mf.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by sudhe on 12-07-2016.
 */
public class FundType {

    private String fundTypeName;
    private Set<Fund> funds;

    /**
     * @return
     */
    public String getFundTypeName() {
        return fundTypeName;
    }

    /**
     * @param fundTypeName
     */
    public void setFundTypeName(String fundTypeName) {
        this.fundTypeName = fundTypeName;
    }


    /**
     * @return
     */
    public Set<Fund> getFunds() {
        return funds==null ? new TreeSet<>() : funds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FundType)) return false;

        FundType fundType = (FundType) o;

        if (getFundTypeName() != null ? !getFundTypeName().equals(fundType.getFundTypeName()) : fundType.getFundTypeName() != null)
            return false;
        return !(getFunds() != null ? !getFunds().equals(fundType.getFunds()) : fundType.getFunds() != null);

    }

    @Override
    public int hashCode() {
        int result = getFundTypeName() != null ? getFundTypeName().hashCode() : 0;
        result = 31 * result + (getFunds() != null ? getFunds().hashCode() : 0);
        return result;
    }
}
