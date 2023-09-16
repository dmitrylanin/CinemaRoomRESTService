package cinema.service;

import cinema.model.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class StatController {
    @Autowired
    private Hall hall;

    @GetMapping("/stats")
    public ResponseEntity<Object> purchaseSeat(@RequestParam Optional<String> password){
        if(password.isPresent()){
            return hall.collectStatistics();
        }else{
            Map<String, String> body = new HashMap<>();
            body.put("error", "The password is wrong!");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }
    }
}
