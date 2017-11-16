/**
 * Created with IntelliJ IDEA.
 * User: sbenner
 * Date: 10/19/17
 * Time: 3:28 AM
 */

import com.snowplowanalytics.snowplow.tracker.DevicePlatform;
import com.snowplowanalytics.snowplow.tracker.Subject;
import com.snowplowanalytics.snowplow.tracker.Tracker;
import com.snowplowanalytics.snowplow.tracker.emitter.BatchEmitter;
import com.snowplowanalytics.snowplow.tracker.emitter.Emitter;
import com.snowplowanalytics.snowplow.tracker.events.PageView;
import com.snowplowanalytics.snowplow.tracker.http.ApacheHttpClientAdapter;
import com.snowplowanalytics.snowplow.tracker.http.HttpClientAdapter;
import com.snowplowanalytics.snowplow.tracker.payload.SelfDescribingJson;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SPTest {

    public static void main(String[] args) {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setDefaultMaxPerRoute(50);

// Make the client
        CloseableHttpClient client = HttpClients.custom()
                .setConnectionManager(manager)
                .build();

// Build the adapter
        HttpClientAdapter adapter = ApacheHttpClientAdapter.builder()
                .url("http://localhost:8082/v1/import")
                .httpClient(client)
                .build();

        Subject s1 = new Subject.SubjectBuilder()
                .userId("Kevin Gleason")
                .language("en-gb")
                .screenResolution(1920, 1080)
                .build();

        Emitter batch = BatchEmitter.builder()
                .httpClientAdapter(adapter)
                .bufferSize(1)
                .build();

        Tracker tracker = new Tracker.TrackerBuilder(batch, "AF003", "cf")
                .subject(s1)
                .base64(true)
                .platform(DevicePlatform.Desktop)
                .build();

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("movie_name", "solaris");
        dataMap.put("poster_country", "JP");
        dataMap.put("poster_year", "1978");

// Now create your SelfDescribingJson object...
        SelfDescribingJson context1 = new SelfDescribingJson("iglu:com.acme/movie_poster/jsonschema/2.1.1", dataMap);

// Now add this JSON into a list of SelfDescribingJsons...
        List<SelfDescribingJson> contexts = new ArrayList<>();
        contexts.add(context1);

        tracker.track(PageView.builder().pageTitle("test").pageTitle("dasfa").pageUrl("asdfadf").customContext(contexts).build());
    }
}

