package taches;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ITache implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject  TachesDAO tachesDAO;
	
	protected Categorie categorie;
	protected String texte;
	protected Integer id;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		ETache tache=tachesDAO.getTache(id);
		if(tache!=null){
		this.id = tache.getId();
		this.categorie=tache.getCategorie();
		this.texte=tache.getTitre();
		}
		else {
			this.id = null;
			this.categorie=null;
			this.texte=null;
		}
		
	}

	public Categorie getCategorie() {
		return categorie;	
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	public void creerTache(){
		ETache tache=new ETache();
		tache.setCategorie(categorie);
		tache.setTitre(texte);
		tache.setDtCreation(new Date());

		if(tachesDAO.creerTache(tache)) {
			categorie=null;
			texte=null;
			id=null;
		}

	}
	
	public void updateTache(Integer id){
		ETache tache=tachesDAO.getTache(id);
		if(categorie!=null)tache.setCategorie(categorie);
		if(texte!=null)tache.setTitre(texte);
		//tache.setDtCreation(new Date());
		tachesDAO.updateTache(tache);
		

	}
	
	public List<Categorie> getCategories(){
		return tachesDAO.getCategories();
	}
	
	@PostConstruct
	protected void init() {
		System.out.println("new ITache");
	}
	

}
