package taches;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class ITaches {
	
	//LIste de tache, a modifier pour persistance
	Map<Integer, ETache> taches=new HashMap<Integer, ETache>();
	
	protected Categorie categorieCreation;
	protected String texteCreation;
	
	protected  List<Categorie> categoriesSel=Arrays.asList(Categorie.values());

	public List<Categorie> getCategoriesSel() {
		return categoriesSel;
	}

	public void setCategoriesSel(List<Categorie> categoriesSel) {
		this.categoriesSel = categoriesSel;
	}
	
	public Categorie getCategorieCreation() {
		return categorieCreation;
	}

	public void setCategorieCreation(Categorie categorieCreation) {
		this.categorieCreation = categorieCreation;
	}

	public String getTexteCreation() {
		return texteCreation;
	}

	public void setTexteCreation(String texteCreation) {
		this.texteCreation = texteCreation;
	}

	public ITaches(){
		
	}
	
	public Categorie[] getCategories(){
		return Categorie.values();
	}
	
	public void creerTache(){
		ETache tache=new ETache();
		tache.setCategorie(categorieCreation);
		tache.setTitre(texteCreation);
		tache.setDtCreation(new Date());
		tache.setIndex(getMaxIndex());
	}
	
	private int getMaxIndex(){
		Integer index=0;
		Set<Integer> indexs=taches.keySet();
		for(Integer i : indexs){
			index=(i>index)? i : index;
		}
		return index+10;
	}

}
