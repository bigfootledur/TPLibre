package edu.tplibre.api;

import java.util.Collection;

/**
 * Représente le magasin
 *
 */
public interface MagasinCommande {
	
	/**
	 * Renvoie le catalogue de produits
	 * @return : le catalogue de produits
	 */
	Collection<Produit> getCatalogue();
	
	/**
	 * Renvoie l'historique des produits commandés 
	 * @return : l'historique des produits commandés
	 */
	Collection<String> getProduitsCommandes();
	
	/**
	 * Réalise une commande après validation, puis vérifie que ses stocks sont à jour
	 * @param commande : la commande à réaliser
	 * @throws ProduitIndisponibleException : si l'un des produits de la commande est indisponible
	 */
	void passerCommande(Collection<ProduitCommande> commande)  throws ProduitIndisponibleException;
}
