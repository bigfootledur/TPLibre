package edu.tplibre.api;

import java.util.Collection;

public interface MagasinCommande {
	Collection<Produit> getCatalogue();
	Collection<String> getProduitsCommandes();
	void passerCommande(Collection<ProduitCommande> commande)  throws ProduitIndisponibleException;
}
