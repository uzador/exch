package zdr.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzadorozhnyy on 01.06.2016.
 */
public class AppConfig {
    private static final Map<String, String> config = new HashMap<>();
    private static final AppConfig instance = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return instance;
    }

    public String put(String key, String value) {
        return config.put(key, value);
    }

    public String get(String key) {
        return config.get(key);
    }
}
