package rarantes.ProManager.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import rarantes.ProManager.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
