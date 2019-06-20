package edu.tplibre.fournisseur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;

import edu.tplibre.api.Fournisseur;
import edu.tplibre.api.Produit;

/**
 * Représente le fournisseur qui renfloue le stock du magasin
 *
 */
public class FournisseurMagasin extends Observable implements Fournisseur{

	// Seuil en dessous duquel l'article est considéré comme indisponible
	private final int SEUIL_DISPONIBILITE_ARTICLE = 1;
	
	StockFournisseur stock;
	Collection<String> historiqueApprovisionnement;
	
	public FournisseurMagasin() {
		stock = new StockFournisseur(this);
		historiqueApprovisionnement = new ArrayList<String>();
	}
	
	@Override
	public void approvisionner(Collection<Produit> catalogue) {
		Iterator<Produit> iterateurCatalogue = catalogue.iterator();
		Random rand = new Random();
		while(iterateurCatalogue.hasNext()){
			Produit produitCatalogue = iterateurCatalogue.next();
			Iterator<Produit> iterateurStock = stock.getProduitsEnStock().iterator();
			while(iterateurStock.hasNext()){
				Produit produitStock = iterateurStock.next();
				if(produitStock.estDisponible(SEUIL_DISPONIBILITE_ARTICLE)){
					if(produitCatalogue.getId() == produitStock.getId()){
						int quantiteApprovisionnement = rand.nextInt(produitStock.getQuantiteDisponible());
						produitCatalogue.setQuantiteDisponible(produitCatalogue.getQuantiteDisponible() + quantiteApprovisionnement);
						produitStock.setQuantiteDisponible(produitStock.getQuantiteDisponible() - quantiteApprovisionnement);
						historiqueApprovisionnement.add("Produit " + produitCatalogue.getId() + " : +" + quantiteApprovisionnement);
					}
				}
			}
		}
		stockChange();
	}
	
	public void stockChange(){
		this.setChanged();
		this.notifyObservers(getStock());
		this.notifyObservers(getHistoriqueApprovisionnement());
	}
	
	@Override
	public Collection<Produit> getStock(){
		return stock.getProduitsEnStock();
	}

	@Override
	public Collection<String> getHistoriqueApprovisionnement() {
		return historiqueApprovisionnement;
	}

}
