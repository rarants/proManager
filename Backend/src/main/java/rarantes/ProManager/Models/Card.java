package rarantes.ProManager.Models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;


@Entity
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Auto_increment id of board", name = "id", required = true)
	private Long id;

	/*@ManyToOne
	@ApiModelProperty(notes = "Card's owner", name = "owner", required = true)
	private User owner;
	 */

	@JsonBackReference
	@ManyToOne
	@ApiModelProperty(notes = "Collumn that card is related", name = "collumn_card", required = true)
	private Collumn collumn;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Card's title", name = "title", required = true)
	private String title;

	@Column
	@ApiModelProperty(notes = "Card's description", name = "description")
	private String description;

	@Column
	@ApiModelProperty(notes = "Card's start date", name = "start_date")
	private Date start_date;

	@Column
	@ApiModelProperty(notes = "Card's end date", name = "end_date")
	private Date end_date;

	/*@OneToMany
	@ApiModelProperty(notes = "Card's tags", name = "card_tags", required = true)
	private List<CardTags> card_tags;
	*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}*/
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Collumn getCollumn() {
		return collumn;
	}

	public void setCollumn(Collumn collumn) {
		this.collumn = collumn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	

	/*@Override
	public String toString() {
		return "Card [id=" + id + ", owner=" + owner + ", responsible=" + responsible + ", title=" + title
				+ ", description=" + description + ", start_date=" + start_date + ", end_date=" + end_date + "]";
	}*/
}
