package cinema.service;

import cinema.model.Hall;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HallController {
    private final Hall hall;

    public HallController(Hall hall) {
        this.hall = hall;
    }

    @GetMapping("/seats")
    public Hall getSeats(){
        return hall;
    }
}
