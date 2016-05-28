package zdr;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zdr.dao.AggregateCurrentTimeRepository;
import zdr.dao.AggregateRepository;
import zdr.domain.Aggregate;
import zdr.dto.AggregateEntity;
import zdr.domain.TradeVolume;
import zdr.dto.AggregateEntityCurrentTime;
import zdr.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Component
public class Loader {
    private static final String HOST = "www.moex.com";
    private static final String SCHEMA = "http";
    private static final Set<Integer> WORK_DAYS = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
    private static final Logger log = LoggerFactory.getLogger(Loader.class);

    @Autowired
    private AggregateRepository aggregateRepository;

    @Autowired
    private AggregateCurrentTimeRepository aggregateCurrentTimeRepository;

    public static void main(String[] args) throws IOException, URISyntaxException {
//        Loader a = new Loader();

        /*long date = 1461766201305L;
        Instant instant = Instant.ofEpochMilli(date);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);
        System.out.println(formatter.format(zdt));*/

//        a.getSomething();
//        a.getVolume();
//        a.getTradeVolume("SBER", "2016-05-04");
    }

    public TradeVolume getTradeVolume(String ticker, String date) throws URISyntaxException, IOException {
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
        MoexTradeVolumeResponseHandler rh = new MoexTradeVolumeResponseHandler();
        TradeVolume tradeVolume = httpclient.execute(httpGet, rh);

        return tradeVolume;
    }

//    @Scheduled(fixedRate = 5000)
    public void loadTradeVolumeOnCurrentDate() {
        String ticker = "SBER";
        try {
            LocalDate timestamp = LocalDate.now();
//            TradeVolume tradeVolume = getTradeVolume("SBER", "today");
            TradeVolume tradeVolume = getTradeVolume("SBER", "2016-05-27");
            if (tradeVolume.getAggregator().isEmpty()) {
                log.warn("Trade volume for '{}' on '{}' is empty", ticker, timestamp);
            } else {
                for (int i = 0; i < tradeVolume.getAggregator().getAggregates().length; i++) {
                    Aggregate aggregate = tradeVolume.getAggregator().getAggregates()[i];
                    log.info("Trade Volume {} to save: {}", timestamp, aggregate);
                    AggregateEntityCurrentTime aggregateEntityCurrentTime = new AggregateEntityCurrentTime(aggregate);
                    aggregateCurrentTimeRepository.save(aggregateEntityCurrentTime);
                }
            }
        } catch (URISyntaxException | IOException e) {
            log.error("Could not load TradeVolume for {} on today. Exception: {}", ticker, e);
        }
    }

    public void loadTradeVolumes(String ticker, LocalDate startDate) {
        LocalDate end = LocalDate.now();

        for (LocalDate date = startDate; date.isBefore(end); date = date.plusDays(1)) {
            if (WORK_DAYS.contains(date.getDayOfWeek().getValue())) {
                try {
                    TradeVolume tradeVolume = getTradeVolume(ticker, date.format(Util.formatter));
                    if (tradeVolume.getAggregator().isEmpty()) {
                        log.warn("Trade volume for '{}' on '{}' is empty", ticker, date);
                    } else {
                        for (int i = 0; i < tradeVolume.getAggregator().getAggregates().length; i++) {
                            log.info("Trade Volume {} to save: {}", date, tradeVolume.getAggregator().getAggregates()[i].toString());
                            aggregateRepository.save(new AggregateEntity(tradeVolume.getAggregator().getAggregates()[i]));
                        }
                    }
                } catch (URISyntaxException | IOException e) {
                    log.error("Could not load TradeVolume for '{}' on '{}. Exception: {}", ticker, date, e);
                }
            }
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
