package rarantes.ProManager.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import rarantes.ProManager.Models.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

}
