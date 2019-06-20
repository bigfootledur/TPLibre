package edu.tplibre.api;

import java.util.Collection;

/**
 * Repr�sente un client du magasin
 *
 */
public interface Client {
	
	/**
	 * R�cup�re la commande du client � partir d'un catalogue de produits
	 * @param catalogue : catalogue � partir duquel la commande est faite
	 * @return : la commande r�sultante
	 */
	Collection<ProduitCommande> getCommande(Collection<Produit> catalogue);
}
