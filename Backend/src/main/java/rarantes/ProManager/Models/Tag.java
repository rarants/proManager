package rarantes.ProManager.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;


@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Auto_increment id of Tag", name = "id", required = true)
	private Long id;

	@ManyToOne
	@ApiModelProperty(notes = "Board that the tag is related", name = "board")
	private Board board;

	@Column(nullable = false)
	@ApiModelProperty(notes = "Title of the tag", name = "tag")
	private String title;

	@Column
	@ApiModelProperty(notes = "Color of the tag", name = "color")
	private String color;

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	/*@Override
	public String toString() {
		return "Tag [id=" + id + ", board=" + board + ", title=" + title + ", color=" + color + "]";
	}*/
}
