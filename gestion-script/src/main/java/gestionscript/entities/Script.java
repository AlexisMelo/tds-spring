package gestionscript.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Script {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String title;
	private String description;
	private String content;
	private Date creationDate;
	
	@ManyToOne
	private User createur;
	
	@ManyToOne 
	private Category categorie;
	
	@ManyToOne
	private Language langage;
	
	@OneToMany(mappedBy = "script")
	private List<History> history;
	
	public List<History> getHistory() {
		return history;
	}

	public void setHistory(List<History> history) {
		this.history = history;
	}

	public Script() {
		history = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getCreateur() {
		return createur;
	}
	public void setCreateur(User createur) {
		this.createur = createur;
	}
	public Category getCategorie() {
		return categorie;
	}
	public void setCategorie(Category categorie) {
		this.categorie = categorie;
	}
	public Language getLangage() {
		return langage;
	}
	public void setLangage(Language langage) {
		this.langage = langage;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
