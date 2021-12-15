package rarantes.ProManager.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import rarantes.ProManager.Models.Board;
import rarantes.ProManager.Models.Collumn;
import rarantes.ProManager.Repositories.BoardRepository;
import rarantes.ProManager.Repositories.CollumnRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/column")
public class CollumnController {

	@Autowired
	private CollumnRepository columnRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	/* index route */
    @GetMapping
	@ApiOperation(value = "Show all user's columns", response = Iterable.class)
    public List<Collumn> index() {
        return columnRepository.findAll();
    }
    
    /* show route */
    @GetMapping("/board/{id}")
    @ApiOperation(value = "Show column by id", response = Iterable.class)
    public List<Collumn> show(@PathVariable Long id) {
        try {
            return columnRepository.findByBoardId(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    /* create route */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new column", response = Iterable.class)
    public Collumn post(@RequestBody Collumn column) {
    	//System.out.println(column.getBoard().getId());
        //Optional<Board> board = boardRepository.findById(column.getBoard().getId());
        return columnRepository.save(column);
    }
	
    /* update route */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update column title", response = Iterable.class)
    public Collumn put(@RequestBody Collumn column, @PathVariable Long id) {
    	
    	Collumn col = columnRepository.getOne(id);
    	col.setTitle(column.getTitle());
    	
        return columnRepository.save(col);
    }
	
    /* delete route */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete column", response = Iterable.class)
    public void delete(@PathVariable Long id) {
    	columnRepository.deleteById(id);
    }
}
