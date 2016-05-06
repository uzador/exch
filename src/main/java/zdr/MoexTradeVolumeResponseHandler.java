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
import org.apache.http.entity.ContentType;
import zdr.domain.Aggregator;
import zdr.domain.Info;
import zdr.domain.TradeVolume;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * Created by yzadorozhnyy on 06.05.2016.
 */
class MoexTradeVolumeResponseHandler implements ResponseHandler<TradeVolume> {

    @Override
    public TradeVolume handleResponse(HttpResponse httpResponse) throws IOException {
        StatusLine statusLine = httpResponse.getStatusLine();
        HttpEntity entity = httpResponse.getEntity();

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
}
