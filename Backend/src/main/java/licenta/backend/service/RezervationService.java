package licenta.backend.service;

import licenta.backend.helpers.ReservationHelper;
import licenta.backend.model.Rezervation;
import licenta.backend.repository.RezervationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

   public ReservationHelper saveReservation(String name, String email, String roomtype, Date checkin,Date checkout,boolean deleted,Long userid )
   {
       return  rezervationRepository.saveReservation(name,email,roomtype,checkin,checkout,deleted,userid);
   }
    public <T> Optional<Rezervation> findById(Long id) {
        return rezervationRepository.findById(id);
    }

    public void delete(Rezervation rezervation) {
        rezervationRepository.delete(rezervation);
    }



}

