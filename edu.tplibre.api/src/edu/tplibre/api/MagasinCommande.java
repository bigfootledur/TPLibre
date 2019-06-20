package edu.tplibre.api;

import java.util.Collection;

/**
 * Repr�sente le magasin
 *
 */
public interface MagasinCommande {
	
	/**
	 * Renvoie le catalogue de produits
	 * @return : le catalogue de produits
	 */
	Collection<Produit> getCatalogue();
	
	/**
	 * Renvoie l'historique des produits command�s 
	 * @return : l'historique des produits command�s
	 */
	Collection<String> getProduitsCommandes();
	
	/**
	 * R�alise une commande apr�s validation, puis v�rifie que ses stocks sont � jour
	 * @param commande : la commande � r�aliser
	 * @throws ProduitIndisponibleException : si l'un des produits de la commande est indisponible
	 */
	void passerCommande(Collection<ProduitCommande> commande)  throws ProduitIndisponibleException;
}
