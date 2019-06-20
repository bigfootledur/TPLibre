package edu.tplibre.fournisseur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import edu.tplibre.api.Fournisseur;
import edu.tplibre.api.Produit;
import edu.tplibre.api.Stock;

/**
 * Représente les produits en stock du fournisseur
 *
 */
public class StockFournisseur extends Thread implements Stock {

	// Le stock est augmenter de cette quantité à chaque fois
	private final int QUANTITE_A_AUGMENTER = 10;
	
	// Délai entre les augmentations du stock
	private final int DELAY = 3000;

	Collection<Produit> produitsEnStock;
	Fournisseur fournisseur;

	public StockFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
		produitsEnStock=new ArrayList<Produit>();
		produitsEnStock.add(new Produit(1, "cacao", 4.5f, 20));
		produitsEnStock.add(new Produit(2, "lait", 10.3f, 20));

		this.start();
	}

	@Override
	public void run() {
		while(true){
			try {
				sleep(DELAY);
				augmenterQuantiteProduits();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void augmenterQuantiteProduits(){
		Random rand = new Random();
		for (Produit produit : produitsEnStock){
			int quantiteDisponible = produit.getQuantiteDisponible();
			produit.setQuantiteDisponible(quantiteDisponible + QUANTITE_A_AUGMENTER);
			fournisseur.stockChange();
		}
	}

	@Override
	public Collection<Produit> getProduitsEnStock() {
		return produitsEnStock;
	}

}
