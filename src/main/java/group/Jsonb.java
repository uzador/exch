package group;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Created by yzadorozhnyy on 28.04.2016.
 */
public class Jsonb {
    public static void main(String[] args) {
        String message = "[{\"charsetinfo\": {\"name\": \"utf-8\"}},{\"aggregates\": [{\"market_name\": \"bonds\", \"market_title\": \"Рынок облигаций\", \"engine\": \"stock\", \"tradedate\": \"2016-04-08\", \"secid\": \"SU26207RMFS9\", \"value\": 1223168244.06, \"volume\": 1302645, \"numtrades\": 139},{\"market_name\": \"ndm\", \"market_title\": \"Режим переговорных сделок\", \"engine\": \"stock\", \"tradedate\": \"2016-04-08\", \"secid\": \"SU26207RMFS9\", \"value\": 3286226000.00, \"volume\": 3500000, \"numtrades\": 28},{\"market_name\": \"otc\", \"market_title\": \"ОТС\", \"engine\": \"stock\", \"tradedate\": \"2016-04-08\", \"secid\": \"SU26207RMFS9\", \"value\": 1048401080.00, \"volume\": 1117685, \"numtrades\": 3},{\"market_name\": \"repo\", \"market_title\": \"Рынок сделок РЕПО\", \"engine\": \"stock\", \"tradedate\": \"2016-04-08\", \"secid\": \"SU26207RMFS9\", \"value\": 27465748171.60, \"volume\": 32966738, \"numtrades\": 83}]}]";

        Gson gson = new Gson();
        JsonElement[] ar = gson.fromJson(message, JsonElement[].class);

        for (JsonElement jsonElement : ar) {
            System.out.println(jsonElement.getAsJsonObject());
        }
    }
}
