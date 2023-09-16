package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({
        "token",
        "ticket"
})
public class Ticket {
    private Seat seat;
    private UUID token;

    public Ticket(UUID token, Seat seat){
        this.seat = seat;
        this.token = token;
    }

    @JsonProperty("token")
    public UUID getToken() {
        return token;
    }

    @JsonProperty("ticket")
    public Seat getSeat() {
        return seat;
    }


}
