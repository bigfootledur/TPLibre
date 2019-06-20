package edu.tplibre.api;

import java.util.Collection;

/**
 * Représente un stock de produits
 *
 */
public interface Stock {
	/**
	 * Renvoie les produits en stock
	 * @return : les produits en stock
	 */
	Collection<Produit> getProduitsEnStock();
}
