package rarantes.ProManager.Models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import io.swagger.annotations.ApiModelProperty;


@Entity
public class Collumn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Auto_increment id of board", name = "id", required = true)
	private Long id;

	@ManyToOne
	@ApiModelProperty(notes = "Board that column is related", name = "board", required = true)
	private Board board;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Column's title", name = "title", required = true)
	private String title;

	/*@OneToMany
	@ApiModelProperty(notes = "Column's cards", name = "cards", required = true)
	private Set<Card> cards;*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}*/

	/*@Override
	public String toString() {
		return "Collumn [id=" + id + ", board=" + board + ", title=" + title + "]";
	}*/
}
