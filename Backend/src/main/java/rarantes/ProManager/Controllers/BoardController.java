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
import rarantes.ProManager.Repositories.BoardRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardRepository boardRepository;
	
	/* index route */
    @GetMapping
	@ApiOperation(value = "Show all user's boards", response = Iterable.class)
    public List<Board> index() {
        return boardRepository.findAll();
    }
    
    /* show route */
    @GetMapping("/{id}")
    @ApiOperation(value = "Show board by id", response = Iterable.class)
    public Optional<Board> show(@PathVariable Long id) {
        try {
            return boardRepository.findById(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    /* create route */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new board", response = Iterable.class)
    public Board post(@RequestBody Board board) {
        return boardRepository.save(board);
    }
	
    /* update route */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update board title", response = Iterable.class)
    public Board put(@RequestBody Board board, @PathVariable Long id) {
    	Board brd = boardRepository.getOne(id);
    	brd.setTitle(board.getTitle());
    	brd.setDescription(board.getDescription());
        return boardRepository.save(brd);
    }
	
    /* delete route */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete board", response = Iterable.class)
    public void delete(@PathVariable Long id) {
    	boardRepository.deleteById(id);
    }
}
