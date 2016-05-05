package zdr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import zdr.domain.Aggregator;
import zdr.domain.Info;
import zdr.domain.TradeVolume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

public class Loader {
    private static final String HOST = "www.moex.com";
    private static final String SCHEMA = "http";

    public static void main(String[] args) throws IOException, URISyntaxException {
        Loader a = new Loader();

        /*long date = 1461766201305L;
        Instant instant = Instant.ofEpochMilli(date);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);
        System.out.println(formatter.format(zdt));*/

//        a.getSomething();
//        a.getVolume();
        a.getTradeVolume("SBER", "2016-05-04");
    }

    private void getTradeVolume(String ticker, String date) throws URISyntaxException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URI uri = new URIBuilder()
                .setScheme(SCHEMA)
                .setHost(HOST)
                .setPath("/iss/securities/" + ticker + "/aggregates.jsonp")
                .setParameter("iss.only", "aggregates")
                .setParameter("iss.meta", "off")
                .setParameter("iss.json", "extended")
                .setParameter("lang", "RU")
                .setParameter("date", date)
                .build();

        HttpGet httpGet = new HttpGet(uri);

        ResponseHandler rh = new ResponseHandler() {
            @Override
            public Object handleResponse(HttpResponse response) throws IOException {
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();

                if (statusLine.getStatusCode() >= 300) {
                    throw new HttpResponseException(
                            statusLine.getStatusCode(),
                            statusLine.getReasonPhrase());
                }

                if (entity == null) {
                    throw new ClientProtocolException("Response contains no content");
                }

                Gson gson = new GsonBuilder().create();
                ContentType contentType = ContentType.getOrDefault(entity);
                Charset charset = contentType.getCharset();
                Reader reader = new InputStreamReader(entity.getContent(), charset);

                JsonElement[] jsonArray = gson.fromJson(reader, JsonElement[].class);

                Info charsetinfo = gson.fromJson(jsonArray[0], Info.class);
                Aggregator aggregates = gson.fromJson(jsonArray[1], Aggregator.class);

                return new TradeVolume.Builder().setAggregator(aggregates).setInfo(charsetinfo).build();
            }
        };

        TradeVolume tradeVolume = (TradeVolume) httpclient.execute(httpGet, rh);
        if (tradeVolume.getAggregator().isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println(tradeVolume);
        }
    }

    private void getSomething() throws URISyntaxException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("moex.com")
                .setPath("/iss/engines/stock/markets/bonds/boards/TQOB/securities/SU25081RMFS9.jsonp")
                .setParameter("iss.only", "securities%2Cmarketdata")
                .setParameter("iss.meta", "off")
//                .setParameter("callback", "callback")
                .setParameter("lang", "RU")
                .setParameter("_", Long.toString(System.currentTimeMillis()))
                .build();

        HttpGet httpGet = new HttpGet(uri);
        System.out.println(httpGet.getURI());
        CloseableHttpResponse response = httpclient.execute(httpGet);

        read(response);
    }

    private void getVolume() throws URISyntaxException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("moex.com")
                .setPath("/iss/securities/SBER/aggregates.jsonp")
                .setParameter("iss.only", "aggregates")
                .setParameter("iss.meta", "off")
                .setParameter("iss.json", "extended")
//                .setParameter("callback", "")
                .setParameter("lang", "RU")
                .setParameter("date", "2016-05-04")
                .build();

        HttpGet httpGet = new HttpGet(uri);
        System.out.println(httpGet.getURI());
        CloseableHttpResponse response = httpclient.execute(httpGet);

        read(response);
    }

    private void read(CloseableHttpResponse response) {
        try {
            System.out.println(response.getProtocolVersion());
            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(response.getStatusLine().getReasonPhrase());
            System.out.println(response.getStatusLine().toString());

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            System.out.println("result: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


/*([
        {"charsetinfo": {"name": "utf-8"}},
        {"aggregates": [{"market_name": "bonds", "market_title": "Рынок облигаций", "engine": "stock", "tradedate": "2016-04-08", "secid": "SU26207RMFS9", "value": 1223168244.06, "volume": 1302645, "numtrades": 139},{"market_name": "ndm", "market_title": "Режим переговорных сделок", "engine": "stock", "tradedate": "2016-04-08", "secid": "SU26207RMFS9", "value": 3286226000.00, "volume": 3500000, "numtrades": 28},{"market_name": "otc", "market_title": "ОТС", "engine": "stock", "tradedate": "2016-04-08", "secid": "SU26207RMFS9", "value": 1048401080.00, "volume": 1117685, "numtrades": 3},{"market_name": "repo", "market_title": "Рынок сделок РЕПО", "engine": "stock", "tradedate": "2016-04-08", "secid": "SU26207RMFS9", "value": 27465748171.60, "volume": 32966738, "numtrades": 83}]}
])*/
