package edu.tplibre.api;

import java.util.Collection;

/**
 * Approvisionne un catalogue de produits
 */
public interface Fournisseur {
	
	/**
	 * Renvoie le stock du fournisseur
	 * @return : le stock du fournisseur
	 */
	Collection<Produit> getStock();
	
	/**
	 * Renvoie l'historique des approvisionnements
	 * @return : l'historique des approvisionnements
	 */
	Collection<String> getHistoriqueApprovisionnement();
	
	/**
	 * Augmente la quantit� disponible des produits pr�sent dans le param�tre produits
	 * @param produits : la collection des produits � approvisionner
	 */
	void approvisionner(Collection<Produit> produits);
	
	/**
	 * Notifie les observers
	 */
	void stockChange();
}
