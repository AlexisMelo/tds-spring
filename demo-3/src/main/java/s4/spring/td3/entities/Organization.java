package s4.spring.td3.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Organization {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String domain;
	private String aliases;
	private String ville;
	
	@OneToMany(mappedBy="organization", cascade=CascadeType.ALL) 
	private List<Groupe> groupes;
	
	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void addGroupe(Groupe groupe) {
		groupes.add(groupe);
		groupe.setOrganization(this);
	}
	
	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public Organization() {
		groupes= new ArrayList<>();
	}
	
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getAliases() {
		return aliases;
	}
	public void setAliases(String aliases) {
		this.aliases = aliases;
	}
	
	@Override
	public String toString() {
		return name + " à " + ville;
	}
	
}
