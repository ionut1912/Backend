package licenta.backend.controller;

import licenta.backend.helpers.TotalPrice;
import licenta.backend.model.Price;
import licenta.backend.service.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/prices")
public class PriceController {
    @Resource
    private PriceService priceService;

    @GetMapping("/{checkin}/{checkout}/{id}")
    private TotalPrice getTotalPrice(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkin, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkout, @PathVariable int id) {
        return priceService.getTotalPrice(checkin, checkout, id);
    }
    @GetMapping("/one/{id}")
    public Price findById(@PathVariable long id){
        return  priceService.getOneById(id);
    }
}
