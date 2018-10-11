package taches;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/*Entite : Tache */

@Entity(name="Tache")
public class ETache {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int position;
	private String titre;
	private Etat etat;
	private boolean ouvert=true;
	
	public boolean isOuvert() {
		return ouvert;
	}
	public void setOuvert(boolean ouvert) {
		this.ouvert = ouvert;
	}
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="categorie")
	private Categorie categorie;
	private Date dtCreation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Date getDtCreation() {
		return dtCreation;
	}
	public void setDtCreation(Date dtCreation) {
		this.dtCreation = dtCreation;
	}
	
	

}
