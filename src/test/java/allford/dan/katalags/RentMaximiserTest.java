package allford.dan.katalags;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class RentMaximiserTest {

    @Test
    public void singleRequest_isAccepted() {
        Request request = new Request("AF514", 0, 5, 10);
        assertEquals(maximalRequests(asList(request)), asList(request));
    }

    private List<Request> maximalRequests(List<Request> requests) {
        return requests;
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
