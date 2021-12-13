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
import rarantes.ProManager.Models.Collumn;
import rarantes.ProManager.Repositories.CollumnRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/collumn")
public class CollumnController {

	@Autowired
	private CollumnRepository collumnRepository;
	
	/* index route */
    @GetMapping
	@ApiOperation(value = "Show all user's collumns", response = Iterable.class)
    public List<Collumn> index() {
        return collumnRepository.findAll();
    }
    
    /* show route */
    @GetMapping("/board/{id}")
    @ApiOperation(value = "Show collumn by id", response = Iterable.class)
    public List<Collumn> show(@PathVariable Long id) {
        try {
            return collumnRepository.findByBoardId(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    /* create route */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new collumn", response = Iterable.class)
    public Collumn post(@RequestBody Collumn collumn) {
    	System.out.println("Post");
    	System.out.println(collumn.toString());
        return collumnRepository.save(collumn);
    }
	
    /* update route */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update collumn title", response = Iterable.class)
    public Collumn put(@RequestBody Collumn collumn, @PathVariable Long id) {
    	Collumn col = collumnRepository.getOne(id);
    	col.setTitle(collumn.getTitle());
        return collumnRepository.save(col);
    }
	
    /* delete route */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete collumn", response = Iterable.class)
    public void delete(@PathVariable Long id) {
    	collumnRepository.deleteById(id);
    }
}
