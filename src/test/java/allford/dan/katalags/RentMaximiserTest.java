package allford.dan.katalags;

import org.junit.Test;

import java.util.ArrayList;
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
        assertEquals(maximalRequests(asList(startingAtSameTimeRequest, highestPriceRequest)), asList(highestPriceRequest));
    }

    @Test
    public void whenTwoRequestsHaveNoOverlap_acceptBoth() {
        Request request1 = new Request("req1", 5, 5, 5);
        Request request2 = new Request("req2", 10, 5, 10);

        assertEquals(maximalRequests(asList(request1, request2)), asList(request1, request2));
    }

    private List<Request> maximalRequests(List<Request> requests) {
        Schedule result = new Schedule();
        for(Request request: requests) {
            if(request.startTime >= result.getEndTime()) {
                result.add(request);
            }
            else if (request.price > result.getLastBooking().price) {
                result.pop();
                result.add(request);
            }
        }
        return result.bookings;
    }

    private class Schedule {
        List<Request> bookings;

        public Schedule() {
            this.bookings = new ArrayList<>();
        }

        public void add(Request request) {
            bookings.add(request);
        }

        public Request getLastBooking () {
            return bookings.isEmpty() ? new Request("", 0, 0, 0) : bookings.get(bookings.size() - 1);
        }

        public void pop() {
            bookings.remove(bookings.size() - 1);
        }

        public int getEndTime() {
            return getLastBooking().getEndTime();
        }
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

        public int getPrice() {
            return price;
        }

        public int getEndTime() {
            return startTime + duration;
        }
    }
}
