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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Board {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Auto_increment id of board", name = "id", required = true)
	private Long id;

	@Column(nullable = false)
	@ApiModelProperty(notes = "Board's title", name = "title", required = true)
	private String title;

	@Column
	@ApiModelProperty(notes = "Board's description", name = "description", required = false)
	private String description;

	@JsonManagedReference
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@ApiModelProperty(notes = "Board's columns", name = "columns", required = true)
	private List<Collumn> columns = new ArrayList<Collumn>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<Collumn> getColumns() {
		return columns;
	}

	public void setColumns(List<Collumn> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", description=" + description + "]";
	}	
}
