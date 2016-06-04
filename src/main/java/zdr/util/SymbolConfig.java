package zdr.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by yzadorozhnyy on 01.06.2016.
 */
public class SymbolConfig {
    private static final Map<String, String> config = new HashMap<>();
    private static final SymbolConfig instance = new SymbolConfig();

    private SymbolConfig() {
    }

    public static SymbolConfig getInstance() {
        return instance;
    }

    public String put(String key, String value) {
        return config.put(key, value);
    }

    public String get(String key) {
        return config.get(key);
    }

    public Set<String> getKeys() {
        return config.keySet();
    }
}
