import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.serverside.ActionSource;
import com.facebook.ads.sdk.serverside.Event;
import com.facebook.ads.sdk.serverside.EventRequest;
import com.facebook.ads.sdk.serverside.UserData;

import java.util.Arrays;

public class FacebookConversionsAPI {
    private static final String ACCESS_TOKEN = "YOUR_ACCESS_TOKEN";
    private static final String PIXEL_ID = "YOUR_PIXEL_ID";

    public static void main(String[] args) {
        APIContext context = new APIContext(ACCESS_TOKEN).enableDebug(true);

        UserData userData = new UserData()
                .email("user@example.com")
                .phone("1234567890");

        Event event = new Event()
                .eventName("Purchase")
                .eventTime(System.currentTimeMillis() / 1000L)
                .userData(userData)
                .actionSource(ActionSource.WEBSITE);

        EventRequest eventRequest = new EventRequest(PIXEL_ID, context)
                .events(Arrays.asList(event));

        try {
            eventRequest.execute();
            System.out.println("Event sent successfully.");
        } catch (APIException e) {
            e.printStackTrace();
            System.out.println("Failed to send event.");
        }
    }
}
