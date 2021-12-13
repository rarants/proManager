package rarantes.ProManager.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import rarantes.ProManager.Models.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

}
