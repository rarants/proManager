package rarantes.ProManager.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Collumn {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Auto_increment id of board", name = "id", required = true)
	private Long id;

	@JsonBackReference
	@ManyToOne
	@ApiModelProperty(notes = "Board that column is related", name = "board", required = true)
	private Board board;

	@Column(nullable = false)
	@ApiModelProperty(notes = "Column's title", name = "title", required = true)
	private String title;

	/*@OneToMany(mappedBy = "collumn", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@ApiModelProperty(notes = "Column's cards", name = "cards", required = true)
	private List<Card> cards = new ArrayList<Card>();
*/
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

	/*public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
*/
	/*
	 * @Override public String toString() { return "Collumn [id=" + id + ", board="
	 * + board + ", title=" + title + "]"; }
	 */
}
