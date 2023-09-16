package cinema.service;

import cinema.model.Hall;
import cinema.model.request.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnController {
    @Autowired
    private Hall hall;

    @PostMapping("/return")
    public ResponseEntity<Object> returnTicket(@RequestBody TokenDTO tokenDTO){
        return hall.returnTicket(tokenDTO.getToken());
    }
}
