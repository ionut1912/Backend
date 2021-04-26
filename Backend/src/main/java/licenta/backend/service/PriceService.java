package licenta.backend.service;

import licenta.backend.helpers.TotalPrice;
import licenta.backend.model.Price;
import licenta.backend.repository.PriceRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class PriceService {
    @Resource
    PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Price> findAll() {
        return priceRepository.findAll();

    }

    public Price save(Price price) {

        return priceRepository.save(price);
    }

    public Optional<Price> findById(Long id) {
        return priceRepository.findById(id);
    }

    public void delete(Price price) {
        priceRepository.delete(price);
    }

    public TotalPrice getTotalPrice(Date checkin, Date checkout, int priceid) {
        return priceRepository.getFinalPrice(checkin, checkout, priceid);
    }
}
