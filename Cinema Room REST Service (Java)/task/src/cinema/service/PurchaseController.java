package cinema.service;

import cinema.model.Hall;
import cinema.model.exceptions.BusinessException;
import cinema.model.exceptions.NotFoundException;
import cinema.model.request.SeatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PurchaseController {
    @Autowired
    private Hall hall;

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseSeat(@RequestBody SeatDTO seatDTO) throws BusinessException, NotFoundException{
        return hall.buySeat(seatDTO.getRow(), seatDTO.getColumn());
    }
}
