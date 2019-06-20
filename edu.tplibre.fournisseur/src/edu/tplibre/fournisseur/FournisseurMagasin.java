package edu.tplibre.fournisseur;

import java.util.Collection;

import edu.tplibre.api.Fournisseur;
import edu.tplibre.api.Produit;
import java.util.Observable;

public class FournisseurMagasin extends Observable implements Fournisseur{

	Stock stock;
	
	public FournisseurMagasin() {
		stock = new Stock();
	}
	
	@Override
	public void livrer(Collection<Produit> produits) {
		stock.approvisionner(produits);
		this.setChanged();
		this.notifyObservers(getStock());
	}
	
	public Collection<Produit> getStock(){
		return stock.getCatalogue();
	}

}