package taches;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.faces.context.FacesContext;

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
		//System.out.println("new iTache");
		tacheActive=null;
		categoriesSel=new ArrayList<>(tachesDAO.getCategories());
		
	}
	
	public void cloreTache(Integer id) {
		ETache tache=tachesDAO.getTache(id);
		tache.setOuvert(false);
		tachesDAO.updateTache(tache);
		desactiverTache();
	}
	
	public void deplacerTache() {
		
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String idTacheAfter = params.get("idTache");
	
		try {
			Integer idTA=Integer.parseInt(idTacheAfter);
			ETache tacheAfter=tachesDAO.getTache(idTA);
			ETache aDeplacer=tachesDAO.getTache(tacheActive);
			tachesDAO.deplacerTacheAvant(aDeplacer, tacheAfter);
			desactiverTache();
		}catch(NumberFormatException e) {
			
		}	
	}
	
	public void dependre() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				String dependantDe = params.get("dependantDe");
		try {
			Integer idDependantDe=Integer.parseInt(dependantDe);
			ETache tacheDependante=tachesDAO.getTache(tacheActive);
			ETache tacheDepentDe=tachesDAO.getTache(idDependantDe);
			tachesDAO.dependre(tacheDependante, tacheDepentDe);
			desactiverTache();
			
		}catch(NumberFormatException e) {
			
		}	
	}
}
