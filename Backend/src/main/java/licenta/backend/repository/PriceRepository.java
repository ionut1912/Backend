package licenta.backend.repository;

import licenta.backend.helpers.TotalPrice;
import licenta.backend.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query(value = "select datediff(?2,?1)*price as finalprice,type,priceid from prices where priceid=?3", nativeQuery = true)
    TotalPrice getFinalPrice(Date checkin, Date checkout, int priceid);
}
