package licenta.backend.service;

import licenta.backend.helpers.ReservationHelper;
import licenta.backend.model.Rezervation;
import licenta.backend.repository.RezervationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service

public class RezervationService {
    @Resource
    public RezervationRepository rezervationRepository;

    public RezervationService(RezervationRepository rezrepository) {
        rezervationRepository = rezrepository;
    }

    public List<Rezervation> findAll() {
        return rezervationRepository.findAll();

    }

    public ReservationHelper saveHelper(ReservationHelper rezervation) {

        return rezervationRepository.saveHelper(rezervation);
    }

    public <T> Optional<Rezervation> findById(Long id) {
        return rezervationRepository.findById(id);
    }

    public void delete(Rezervation rezervation) {
        rezervationRepository.delete(rezervation);
    }



}

