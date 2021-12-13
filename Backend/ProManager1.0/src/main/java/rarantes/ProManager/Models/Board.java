package rarantes.ProManager.Models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import io.swagger.annotations.ApiModelProperty;

//import io.swagger.annotations.ApiModelProperty;

@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Auto_increment id of board", name = "id", required = true)
	private Long id;
	
	/*@ManyToOne
	@ApiModelProperty(notes = "Owner user", name = "owner", required = true)
	private User owner;
	 */
	@Column(nullable = false)
	@ApiModelProperty(notes = "Board's title", name = "title", required = true)
	private String title;

	@Column
	@ApiModelProperty(notes = "Board's description", name = "description", required = false)
	private String description;
	
	@OneToMany
	@ApiModelProperty(notes = "Board's collumns", name = "collumns", required = true)
	private Set<Collumn> collumns;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Collumn> getCollumns() {
		return collumns;
	}

	public void setCollumns(Set<Collumn> collumns) {
		this.collumns = collumns;
	}

	/*@Override
	public String toString() {
		return "Board [id=" + id + ", owner=" + owner + ", title=" + title + "]";
	}*/
}
