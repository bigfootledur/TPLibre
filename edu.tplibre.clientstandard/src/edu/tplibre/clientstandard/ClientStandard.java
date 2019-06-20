package edu.tplibre.clientstandard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import edu.tplibre.api.Client;
import edu.tplibre.api.Produit;
import edu.tplibre.api.ProduitCommande;

/**
 * Repr�sente un client qui commande moiti� moins de produit que le client gourmand
 *
 */
public class ClientStandard implements Client {

	// Probabilit� qu'un client ach�te un article lorsqu'il feuillette le catalogue 
	private final float PROBABILITE_ACHAT_ARTICLE = 0.5f;
	
	// Seuil en dessous duquel l'article est consid�r� comme indisponible
	private final int SEUIL_DISPONIBILITE_ARTICLE = 1;

	@Override
	public Collection<ProduitCommande> getCommande(Collection<Produit> catalogue) {
		Collection<ProduitCommande> commande = new ArrayList<ProduitCommande>();
		Random rand = new Random();
		for(Produit produit : catalogue){
			if (produit.estDisponible(SEUIL_DISPONIBILITE_ARTICLE)){
				if (rand.nextFloat() < PROBABILITE_ACHAT_ARTICLE){
					int quantiteDesiree = rand.nextInt(Math.round(produit.getQuantiteDisponible() / 2));
					commande.add(new ProduitCommande(produit.getId(), quantiteDesiree));
				}
			}
		}
		return commande;
	}
	
	@Override
	public String toString(){
		return "Client standard";
	}
}
