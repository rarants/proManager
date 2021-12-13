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
import rarantes.ProManager.Models.User;
import rarantes.ProManager.Repositories.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	/* index route */
    @GetMapping
	@ApiOperation(value = "Show all users", response = Iterable.class)
    public List<User> index() {
        return userRepository.findAll();
    }
    
    /* show route */
    @GetMapping("/{id}")
    @ApiOperation(value = "Show user by id", response = Iterable.class)
    public Optional<User> show(@PathVariable Long id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    /* create route */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Register new user", response = Iterable.class)
    public User post(@RequestBody User user) {
        return userRepository.save(user);
    }
	
    /* update route */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update user data", response = Iterable.class)
    public User put(@RequestBody  User user, @PathVariable Long id) {
    	User usr = userRepository.getOne(id);
    	usr.setUsername(user.getUsername());
    	usr.setEmail(user.getEmail());
        return userRepository.save(usr);
    }
	
    /* delete route */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user", response = Iterable.class)
    public void delete(@PathVariable Long id) {
    	userRepository.deleteById(id);
    }
}
