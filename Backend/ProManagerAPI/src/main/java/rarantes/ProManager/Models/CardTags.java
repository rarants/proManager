package rarantes.ProManager.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class CardTags {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Auto_increment id of board", name = "id", required = true)
	private Long id;

	@ManyToOne
	@ApiModelProperty(notes = "Card that tag is related", name = "card", required = true)
	private Card card;

	@ManyToOne
	@ApiModelProperty(notes = "Tag that card is related", name = "tag", required = true)
	private Tag tag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	/*@Override
	public String toString() {
		return "CardTags [id=" + id + ", card=" + card + ", tag=" + tag + "]";
	}*/
}
