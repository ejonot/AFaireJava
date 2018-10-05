package taches;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import config.Dao;

@ViewScoped
@ManagedBean
public class ITaches {
	
	//LIste de tache, a modifier pour persistance
	Map<Integer, ETache> taches=new HashMap<Integer, ETache>();
	
	protected Categorie categorieCreation;
	protected String texteCreation;
	
	protected  List<Categorie> categoriesSel=new ArrayList<Categorie>();
	protected List<Categorie> categories;
	
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
		Categorie perso=new Categorie(0,"Perso" , "#FFDEAD");
		Categorie jardin=new Categorie(0,"Jardinage" , "#2E8B57");
		Categorie bricolage=new Categorie(0,"Vricolage" , "#A0522D");
		categories=new ArrayList<Categorie>();
		categories.add(perso);
		Dao.addCategorie(perso);
		categories.add(jardin);
		Dao.addCategorie(jardin);
		categories.add(bricolage);
		Dao.addCategorie(bricolage);
		categoriesSel=new ArrayList<>(categories);
	}
	
	public List<Categorie> getCategories(){
		return categories;
	}
	
	public void creerTache(){
		ETache tache=new ETache();
		tache.setCategorie(categorieCreation);
		tache.setTitre(texteCreation);
		tache.setDtCreation(new Date());
		tache.setPosition(getMaxIndex());
		Dao.insertTache(tache);
		taches.put(tache.getPosition(), tache);
	}
	
	private int getMaxIndex(){
		Integer index=0;
		Set<Integer> indexs=taches.keySet();
		for(Integer i : indexs){
			index=(i>index)? i : index;
		}
		return index+10;
	}

	public List<ETache> getTaches(){
		return Dao.findTaches(getCategoriesSel());
		/*List<ETache> liste = new ArrayList<ETache>();
		for(ETache tache : taches.values()){
			if(categoriesSel.contains(tache.getCategorie())) liste.add(tache);
			
		}
		return liste;*/
	}
}
