package zdr.util;

/**
 * Created by yzadorozhnyy on 03.06.2016.
 */
public enum MarketName {
    SHARES("shares"),
    NDM("ndm"),
    REPO("repo"),
    STANDARD("standard"),
    CLASSICA("classica"),
    MOEXBOARD("moexboard"),
    OTC("otc"),
    CCP("ccp");

    private String value;

    MarketName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
