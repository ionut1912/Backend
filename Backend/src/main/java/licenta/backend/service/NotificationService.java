package licenta.backend.service;

import licenta.backend.model.Notifications;

import licenta.backend.model.Rezervation;
import licenta.backend.repository.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;


@Service
public class NotificationService {
    @Resource
    NotificationsRepository repository;
    List<Rezervation> rezervations;
    Map<String, String> rezervationsEmails;
    @Resource
    RezervationService service;
    Notifications notifications;
    public Notifications save(Notifications notifications) {
        return repository.save(notifications);
    }
List<Rezervation> findAll(){
        return service.findByDate();
}

    public Boolean existByUserEmail(String email) {
        return repository.existsByuseremail(email);
    }

    @Scheduled(fixedRate = 5000)
    public void emailWorker() {
        this.rezervations = service.findByDate();
        rezervationsEmails = new HashMap<>();
        String message = "Salut!Nu uita de rezervarile urmatoare:\n";

        for (Rezervation element : rezervations) {
            if (!rezervationsEmails.containsKey(element.getEmail())) {
                rezervationsEmails.put(element.getEmail(), message + formatEmail(element.getCheckin(),element.getRoomtype(),element.getName()));


            }
            else
                {
                rezervationsEmails.put(element.getEmail(),rezervationsEmails.get(element.getEmail()).concat(formatEmail(element.getCheckin(),element.getRoomtype(),element.getName())));
            }
        }

        for(Map.Entry<String,String> mapRezervation:rezervationsEmails.entrySet())
        {
         if(!existByUserEmail(mapRezervation.getKey()))
         {
             notifications=new Notifications(mapRezervation.getKey(),mapRezervation.getValue(),new Date(),false);
             save(notifications);
         }
        }
    }

    public String formatEmail(LocalDate date, String roomtype, String name) {
        String emailMessage="";
        emailMessage+="Tipul camerei:" + roomtype.toLowerCase() + ",  Numele :" + name + ",Data rezervarii:" +date;
      emailMessage+="\n";
        return  emailMessage;
    }

}
