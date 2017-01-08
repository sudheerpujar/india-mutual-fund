package in.co.pujar.sudheer.finance.mf.enums;

/**
 * Created by sudhe on 25-12-2016.
 */
public enum NAVLineType {
    HEADER(8), FUND_TYPE(1), FUND(1), SCHEME(8), EOS(0), EOF(0);
    private final int columnSize;
    NAVLineType(int columnSize){
        this.columnSize =columnSize;
    }

    /**
     *
      * @return
     */
    public final int getColumnSize() {
        return columnSize;
    }
}


