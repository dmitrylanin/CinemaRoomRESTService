package cinema.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({
        "row",
        "column",
        "price"
})
public class Seat {
    private int row;
    private int column;

    public int price;

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    @JsonProperty("row")
    public int getRow() {
        return row;
    }

    @JsonProperty("column")
    public int getColumn() {
        return column;
    }

    @JsonProperty("price")
    public int getPrice() {
        return price;
    }
}
