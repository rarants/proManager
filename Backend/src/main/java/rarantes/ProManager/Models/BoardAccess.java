package rarantes.ProManager.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class BoardAccess {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Auto_increment id of Accesses", name = "id", required = true)
	private Long id;
	
	@ManyToOne
	@ApiModelProperty(notes = "User that has access permition in the board", name = "user")
	private User user;
	
	@ManyToOne
	@ApiModelProperty(notes = "Board that has access permition", name = "board")
	private Board board;

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*@Override
	public String toString() {
		return "BoardAccess [id=" + id + ", user=" + user + "]";
	}*/
}
