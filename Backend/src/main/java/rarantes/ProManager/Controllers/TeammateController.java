package rarantes.ProManager.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import rarantes.ProManager.Models.Teammate;
import rarantes.ProManager.Repositories.TeammateRepository;

@RestController
@RequestMapping("/teammate")
public class TeammateController {
	@Autowired
	private TeammateRepository teammateRepository;
	
	/* index route */
    @GetMapping
	@ApiOperation(value = "Show all user's teammates", response = Iterable.class)
    public List<Teammate> index() {
        return teammateRepository.findAll();
    }
    
    /* show route */
    @GetMapping("/{id}")
    @ApiOperation(value = "Show teammate by id", response = Iterable.class)
    public Optional<Teammate> show(@PathVariable Long id) {
        try {
            return teammateRepository.findById(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    /* create route */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new teammate", response = Iterable.class)
    public Teammate post(@RequestBody Teammate teammate) {
        return teammateRepository.save(teammate);
    }
	
    /* update route */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update teammate title", response = Iterable.class)
    public Teammate put(@RequestBody Teammate teammate, @PathVariable Long id) {
    	Teammate mat = teammateRepository.getOne(id);
    	mat.setUser_2(teammate.getUser_2());
        return teammateRepository.save(mat);
    }
	
    /* delete route */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete teammate", response = Iterable.class)
    public void delete(@PathVariable Long id) {
    	teammateRepository.deleteById(id);
    }
}
