package cinema.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@JsonPropertyOrder({
        "total_rows",
        "total_columns",
        "available_seats"
})
public class Hall {
    @JsonProperty("total_rows")
    private int totalRows;
    @JsonProperty("total_columns")
    private int totalСolumns;
    private CopyOnWriteArrayList<Seat> available_seats;
    private final ConcurrentHashMap<Seat, Boolean> purchasedSeats;

    private final  CopyOnWriteArrayList<Ticket> tickets;

    @JsonCreator
    public Hall(int totalRows,
                int totalColumns,
                CopyOnWriteArrayList<Seat> seats){
        this.totalRows = totalRows;
        this.totalСolumns = totalColumns;
        this.available_seats = seats;
        this.purchasedSeats = new ConcurrentHashMap <Seat, Boolean>();
        this.tickets = new CopyOnWriteArrayList<Ticket>();
        seatSeats();
    }

    @JsonProperty("available_seats")
    public CopyOnWriteArrayList<Seat> getAvailable_seats() {
        return available_seats;
    }

    private CopyOnWriteArrayList<Seat> seatSeats(){
        for (int i = 1; i< totalRows +1; i++){
            for (int j = 1; j< totalСolumns +1; j++){
                Seat seat;
                if(i<5){
                    seat = new Seat(i, j, 10);
                }else{
                    seat = new Seat(i, j, 8);
                }
                this.available_seats.add(seat);
                this.purchasedSeats.put(seat, false);
            }
        }
        return available_seats;
    }

    public Seat getSeat(int row, int column){
        for(Seat seat : available_seats){
            if(seat.getRow() == row && seat.getColumn() == column){
                return seat;
            }
        }
        return null;
    }

    public ResponseEntity<Object> collectStatistics(){
        int income = tickets
                .stream()
                .mapToInt(i->i.getSeat().getPrice())
                .sum();

        Map<String, Integer> body = new HashMap<>();
        body.put("current_income", income);
        body.put("number_of_available_seats", totalRows*totalСolumns-tickets.size());
        body.put("number_of_purchased_tickets", tickets.size());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }


    public ResponseEntity<Object> returnTicket(UUID token){
        for(Ticket ticket : tickets){
            if(ticket.getToken().equals(token)){
                tickets.remove(ticket);
                purchasedSeats.put(ticket.getSeat(), false);
                Map<String, Seat> body = new HashMap<>();
                body.put("returned_ticket", ticket.getSeat());
                return new ResponseEntity<>(body, HttpStatus.OK);
            }
        }
        Map<String, String> body = new HashMap<>();
        body.put("error", "Wrong token!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> buySeat(int row, int column) {
        Seat seat = getSeat(row, column);
        if(row> totalRows || column > totalСolumns ||row <= 0 || column <= 0){
            Map<String, String> body = new HashMap<>();
            body.put("error", "The number of a row or a column is out of bounds!");
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }else if(purchasedSeats.get(seat) == false){
            purchasedSeats.put(seat, true);
            Ticket ticket = new Ticket(UUID.randomUUID(), seat);
            tickets.add(ticket);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ticket);
        }else{
            Map<String, String> body = new HashMap<>();
            body.put("error", "The ticket has been already purchased!");
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }
}