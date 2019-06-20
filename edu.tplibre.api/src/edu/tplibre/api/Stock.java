package edu.tplibre.api;

import java.util.Collection;

/**
 * Repr�sente un stock de produits
 *
 */
public interface Stock {
	/**
	 * Renvoie les produits en stock
	 * @return : les produits en stock
	 */
	Collection<Produit> getProduitsEnStock();
}
