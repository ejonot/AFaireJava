package taches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.apache.openjpa.lib.util.OrderedMap;

@Named
@ApplicationScoped
public class TachesDAO {

	//LIste de taches, a modifier pour persistance
	private  SortedMap<Integer, ETache> tachesParPosition=new TreeMap<Integer, ETache>();
	private  SortedMap<Integer, ETache> tachesParId=new TreeMap<Integer, ETache>();
	private  SortedMap<Integer,Categorie> categories=new TreeMap<Integer,Categorie>();

	private Map<Integer, List<Integer>> dependancesTaches=new HashMap<Integer, List<Integer>>();
	
	public TachesDAO() {
		Categorie perso=new Categorie(0,"Perso" , "#FFDEAD");
		Categorie jardin=new Categorie(0,"Jardinage" , "#2E8B57");
		Categorie bricolage=new Categorie(0,"Bricolage" , "#A0522D");

		addCategorie(perso);
		addCategorie(jardin);	
		addCategorie(bricolage);
		
		ETache tache1=new ETache("Suspendre etagère chambre", bricolage);
		ETache tache2=new ETache("Tailler la haie", jardin);
		ETache tache3=new ETache("Réinstaller PC", perso);
		
		creerTache(tache1);
		creerTache(tache2);
		creerTache(tache3);
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
	 if(id!=null && tachesParId.containsKey(id)) return tachesParId.get(id);
	 return null;
 }
 
 public boolean deplacerTacheAvant(ETache aDeplacer, ETache tacheAfter) {
	 Integer positionAprès=tacheAfter.getPosition();
	Integer positionAvant=0;
	for(Integer i : tachesParPosition.keySet()) {
		if(i<positionAprès) positionAvant=i;
		else break;
	}
	if(positionAprès==positionAvant) {System.out.println("erreur pas de position dispo pour le déplacement");
	return false;}
	Integer newPosition=Math.round((positionAprès+positionAvant)/2);
	Integer oldPosition=aDeplacer.getPosition();
	aDeplacer.setPosition(newPosition);
	tachesParPosition.remove(oldPosition);
	tachesParPosition.put(newPosition, aDeplacer);
	tachesParId.put(aDeplacer.getId(), aDeplacer);
	return true;
 }
 
 public boolean dependre(ETache estDependante, ETache depentDe) {
	 if(estDependante==null || depentDe==null) return false;
	 List<Integer> liste=null;
	 if(dependancesTaches.containsKey(estDependante.getId())) liste=dependancesTaches.get(estDependante.getId());
	 if(liste==null) liste=new ArrayList<Integer>();
	 liste.add(depentDe.getId());
	 dependancesTaches.put(estDependante.getId(), liste);
	 
	 return true;
 }
}
