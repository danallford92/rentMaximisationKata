package allford.dan.katalags;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class RentMaximiserTest {


    @Test
    public void noRequests() {
        assertEquals(emptyList(), maximalRequests(emptyList()));
    }

    @Test
    public void singleRequest_isAccepted() {
        Request request = new Request("AF514", 0, 5, 10);
        assertEquals(asList(request), maximalRequests(asList(request)));
    }

    @Test
    public void whenTwoRequestsHaveSameStartTime_TakesTheOneWithTheHighestPrice() {
        Request highestPriceRequest = new Request("Req1", 0, 5, 20);
        Request startingAtSameTimeRequest = new Request("Req2", 0, 2, 10);

        assertEquals(asList(highestPriceRequest), maximalRequests(asList(highestPriceRequest, startingAtSameTimeRequest)));
        assertEquals(asList(highestPriceRequest), maximalRequests(asList(startingAtSameTimeRequest, highestPriceRequest)));
    }

    @Test
    public void whenTwoRequestsHaveNoOverlap_acceptBoth() {
        Request request1 = new Request("req1", 5, 5, 5);
        Request request2 = new Request("req2", 10, 5, 10);

        assertEquals(asList(request1, request2), maximalRequests(asList(request1, request2)));
    }

    private List<Request> maximalRequests(List<Request> requests) {
        Schedule schedule = new Schedule();
        for(Request request: requests) {
            if(request.startTime >= schedule.getEndTime()) {
                schedule.add(request);
            }
            else if (request.price > schedule.getLastBooking().price) {
                schedule.pop();
                schedule.add(request);
            }
        }
        return schedule.bookings;
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
