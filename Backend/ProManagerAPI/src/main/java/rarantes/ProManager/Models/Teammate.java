package rarantes.ProManager.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;


@Entity
public class Teammate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Auto_increment id of teammate", name = "id", required = true)
	private Long id;
	
    @ManyToOne
	@ApiModelProperty(notes = "User 1", name = "user_1", required = true)
	private User user_1;
	
    @ManyToOne
	@ApiModelProperty(notes = "User 2", name = "user_2", required = true)
	private User user_2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser_1() {
		return user_1;
	}

	public void setUser_1(User user_1) {
		this.user_1 = user_1;
	}

	public User getUser_2() {
		return user_2;
	}

	public void setUser_2(User user_2) {
		this.user_2 = user_2;
	}

	/*@Override
	public String toString() {
		return "Teammate [id=" + id + ", user_1=" + user_1 + ", user_2=" + user_2 + "]";
	}*/
}
