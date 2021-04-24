package licenta.backend.repository;

import licenta.backend.helpers.UserData;
import licenta.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
@Query(value = "select name,email,userid from users where username=?1",nativeQuery = true)
UserData getUserDetails(String  username);
}

