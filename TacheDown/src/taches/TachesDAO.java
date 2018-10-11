package taches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.apache.openjpa.lib.util.OrderedMap;

@Named
@ApplicationScoped
public class TachesDAO {

	//LIste de taches, a modifier pour persistance
	private  Map<Integer, ETache> tachesParPosition=new OrderedMap<Integer, ETache>();
	private  Map<Integer, ETache> tachesParId=new OrderedMap<Integer, ETache>();
	private  Map<Integer,Categorie> categories=new OrderedMap<Integer,Categorie>();

	
	public TachesDAO() {
		Categorie perso=new Categorie(0,"Perso" , "#FFDEAD");
		Categorie jardin=new Categorie(0,"Jardinage" , "#2E8B57");
		Categorie bricolage=new Categorie(0,"Bricolage" , "#A0522D");

		addCategorie(perso);
		addCategorie(jardin);	
		addCategorie(bricolage);
	}

	private  int getMaxPositionTache(){
		Integer index=0;
		Set<Integer> indexs=tachesParPosition.keySet();
		for(Integer i : indexs){
			index=(i>index)? i : index;
		}
		return index+10;
	}

	private  int getMaxIdTache(){
		Integer index=0;
		Set<Integer> indexs=tachesParId.keySet();
		for(Integer i : indexs){
			index=(i>index)? i : index;
		}
		return index+1;
	}

	private  int getMaxIdCategorie(){
		Integer index=0;
		Set<Integer> indexs=categories.keySet();
		for(Integer i : indexs){
			index=(i>index)? i : index;
		}
		return index+1;
	}

	public  List<Categorie> getCategories(){
		return new ArrayList<>(categories.values());
	}
	public static void setCategories(){
		
	}
	
	public  boolean creerTache(ETache tache){
		tache.setPosition(getMaxPositionTache());
		tache.setId(getMaxIdTache());
		tachesParPosition.put(tache.getPosition(), tache);
		tachesParId.put(tache.getId(), tache);
		return true;
	}

	public  boolean updateTache(ETache tache){
		//tache.setPosition(getMaxPositionTache());
		//tache.setId(getMaxIdTache());
		tachesParPosition.put(tache.getPosition(), tache);
		tachesParId.put(tache.getId(), tache);
		return true;
	}
	
	public  boolean addCategorie(Categorie cat) {
		cat.setId(getMaxIdCategorie());
		categories.put(cat.getId(), cat);
		return true;
	}

	public  List<ETache> getTaches(List<Categorie> categoriesSel){
		List<ETache> liste = new ArrayList<ETache>();
		for(ETache tache : tachesParPosition.values()){
			if(categoriesSel.contains(tache.getCategorie()) && tache.isOuvert()) liste.add(tache);

		}
		return liste;
	}

 public  Categorie getCategorie(Integer id) {
	 if(categories.containsKey(id)) return categories.get(id);
	 return null;
 }
 
 public  ETache getTache(Integer id) {
	 if(tachesParId.containsKey(id)) return tachesParId.get(id);
	 return null;
 }
}
