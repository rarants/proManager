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
import rarantes.ProManager.Models.Card;
import rarantes.ProManager.Repositories.CardRepository;

@RestController
@RequestMapping("/card")
public class CardController {
	@Autowired
	private CardRepository cardRepository;
	
	/* index route */
    @GetMapping
	@ApiOperation(value = "Show all user's cards", response = Iterable.class)
    public List<Card> index() {
        return cardRepository.findAll();
    }
    
    /* show route */
    @GetMapping("/{id}")
    @ApiOperation(value = "Show card by id", response = Iterable.class)
    public Optional<Card> show(@PathVariable Long id) {
        try {
            return cardRepository.findById(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    /* create route */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new card", response = Iterable.class)
    public Card post(@RequestBody Card card) {
        return cardRepository.save(card);
    }
	
    /* update route */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update card title", response = Iterable.class)
    public Card put(@RequestBody Card card, @PathVariable Long id) {
    	Card crd = cardRepository.getOne(id);
    	crd.setTitle(card.getTitle());
        return cardRepository.save(crd);
    }
	
    /* delete route */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete card", response = Iterable.class)
    public void delete(@PathVariable Long id) {
    	cardRepository.deleteById(id);
    }
}
