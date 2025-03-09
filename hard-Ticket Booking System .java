import java.util.*;

class TicketBookingSystem {
    private final int totalSeats;
    private final Set<Integer> bookedSeats;

    public TicketBookingSystem(int seats) {
        this.totalSeats = seats;
        this.bookedSeats = new HashSet<>();
    }

    public synchronized boolean bookSeat(int seatNumber, String customerType) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            System.out.println(customerType + " booking failed: Invalid seat number " + seatNumber);
            return false;
        }
        if (!bookedSeats.contains(seatNumber)) {
            bookedSeats.add(seatNumber);
            System.out.println(customerType + " successfully booked seat " + seatNumber);
            return true;
        } else {
            System.out.println(customerType + " booking failed: Seat " + seatNumber + " is already booked");
            return false;
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String customerType;

    public BookingThread(TicketBookingSystem system, int seatNumber, String customerType, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerType = customerType;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customerType);
    }
}

public class TicketBookingMain {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(10);

        Thread vip1 = new BookingThread(system, 5, "VIP", Thread.MAX_PRIORITY);
        Thread vip2 = new BookingThread(system, 3, "VIP", Thread.MAX_PRIORITY);
        Thread regular1 = new BookingThread(system, 5, "Regular", Thread.NORM_PRIORITY);
        Thread regular2 = new BookingThread(system, 7, "Regular", Thread.NORM_PRIORITY);
        Thread regular3 = new BookingThread(system, 3, "Regular", Thread.NORM_PRIORITY);

        vip1.start();
        vip2.start();
        regular1.start();
        regular2.start();
        regular3.start();
    }
}
