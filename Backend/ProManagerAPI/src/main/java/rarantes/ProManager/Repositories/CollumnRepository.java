package rarantes.ProManager.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import rarantes.ProManager.Models.Collumn;

@Repository
public interface CollumnRepository extends JpaRepository<Collumn, Long>{

}
