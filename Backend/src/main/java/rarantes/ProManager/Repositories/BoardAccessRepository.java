package rarantes.ProManager.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import rarantes.ProManager.Models.BoardAccess;

@Repository
public interface BoardAccessRepository extends JpaRepository<BoardAccess, Long>{

}
