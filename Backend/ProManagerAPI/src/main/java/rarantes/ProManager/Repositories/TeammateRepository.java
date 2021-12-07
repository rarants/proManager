package rarantes.ProManager.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import rarantes.ProManager.Models.Teammate;

@Repository
public interface TeammateRepository extends JpaRepository<Teammate, Long>{

}
