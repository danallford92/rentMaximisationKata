package allford.dan.katalags;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class RentMaximiserTest {

    @Test
    public void singleRequest_isAccepted() {
        Request request = new Request("AF514", 0, 5, 10);
        assertEquals(maximalRequests(asList(request)), asList(request));
    }

    @Test
    public void whenTwoRequestsHaveSameStartTime_TakesTheOneWithTheHighestPrice() {
        Request highestPriceRequest = new Request("Req1", 0, 5, 20);
        Request startingAtSameTimeRequest = new Request("Req2", 0, 2, 10);

        assertEquals(maximalRequests(asList(highestPriceRequest, startingAtSameTimeRequest)), asList(highestPriceRequest));
    }

    private List<Request> maximalRequests(List<Request> requests) {
        return Arrays.asList(requests.get(0));
    }

    private class Request {
        String name;
        int startTime;
        int duration;
        int price;

        public Request(String name, int startTime, int duration, int price) {
            this.name = name;
            this.startTime = startTime;
            this.duration = duration;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Request{" +
                    "name='" + name + '\'' +
                    ", startTime=" + startTime +
                    ", duration=" + duration +
                    ", price=" + price +
                    '}';
        }
    }
}
