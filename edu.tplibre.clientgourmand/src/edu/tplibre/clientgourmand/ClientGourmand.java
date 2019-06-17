package edu.tplibre.clientgourmand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import edu.tplibre.api.Client;
import edu.tplibre.api.Produit;
import edu.tplibre.api.ProduitCommande;

public class ClientGourmand implements Client{

	@Override
	public Collection<ProduitCommande> getCommande(Collection<Produit> catalogue) {
		Collection<ProduitCommande> commande = new ArrayList<ProduitCommande>();
		Random rand = new Random();
		for(Produit produit : catalogue){
			if (produit.estDisponible(1)){
				if (rand.nextFloat() < 0.75f){
					int quantiteDesiree = rand.nextInt(produit.getQuantiteDisponible()) + 1;
					commande.add(new ProduitCommande(produit.getId(), quantiteDesiree));
				}
			}
		}
		return commande;
	}
}
