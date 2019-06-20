package edu.tplibre.api;

import java.util.Collection;

/**
 * Représente un client du magasin
 *
 */
public interface Client {
	
	/**
	 * Récupère la commande du client à partir d'un catalogue de produits
	 * @param catalogue : catalogue à partir duquel la commande est faite
	 * @return : la commande résultante
	 */
	Collection<ProduitCommande> getCommande(Collection<Produit> catalogue);
}
