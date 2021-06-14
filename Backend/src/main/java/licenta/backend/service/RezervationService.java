package licenta.backend.service;


import licenta.backend.helpers.UserReservationHelper;
import licenta.backend.model.Rezervation;
import licenta.backend.repository.RezervationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
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

  public Rezervation save(Rezervation rezervation) {

        return rezervationRepository.save(rezervation);
    }
    public <T> Optional<Rezervation> findById(Long id) {
        return rezervationRepository.findById(id);
    }

    public void delete(Rezervation rezervation) {
        rezervationRepository.delete(rezervation);
    }
public  Rezervation getOneById(long id){
        return  rezervationRepository.getOne(id);
}

public List<UserReservationHelper> findByUserId(Long user)
{
        return  rezervationRepository.findRezervationByuserid(user);
}

public  List<Rezervation> findByDate()
{LocalDate date=LocalDate.now().plusDays(7);
return  rezervationRepository.findCheckin(date);

}
}

