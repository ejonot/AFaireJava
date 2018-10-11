package taches;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@Named
public class ITaches implements Serializable{


	private static final long serialVersionUID = 1L;


	protected Integer tacheActive;
	@Inject TachesDAO tachesDAO;
	@Inject ITache tache;

	protected  List<Categorie> categoriesSel=new ArrayList<Categorie>();


	public List<Categorie> getCategoriesSel() {
		return categoriesSel;
	}

	public void setCategoriesSel(List<Categorie> categoriesSel) {
		this.categoriesSel = categoriesSel;
	}


	public List<Categorie> getCategories(){
		return tachesDAO.getCategories();
	}

	

	public List<ETache> getTaches(){
		return tachesDAO.getTaches(getCategoriesSel());
	}

	public void activerTache(ETache tache) {
	 tacheActive=tache.getId();
	}
	public Integer getTacheActive() {
		return tacheActive;
	}
	public boolean isTacheActive(ETache tache) {
		if(tache!=null && tacheActive!=null && tache.getId()==tacheActive) return true;
		return false;
	}
	public void setTacheActive(Integer tacheActive) {
		this.tacheActive = tacheActive;
		tache.setId(tacheActive);
	}
	public void desactiverTache() {
		this.tacheActive =null;
		tache.setId(null);
	}
	@PostConstruct
	public void init() {
		System.out.println("new iTache");
		tacheActive=null;
		categoriesSel=new ArrayList<>(tachesDAO.getCategories());
		
	}
	
	public void cloreTache(Integer id) {
		ETache tache=tachesDAO.getTache(id);
		tache.setOuvert(false);
		tachesDAO.updateTache(tache);
		desactiverTache();
	}
}
