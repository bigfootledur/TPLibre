package edu.tplibre.api;

/**
 * Représente une entrée de la commande : un numéro de produit associé à la quantité
 * désirée par le client 
 *
 */
public class ProduitCommande {
	private int numeroProduit;
	private int quantiteCommandee;
	
	public ProduitCommande(int numeroProduit, int quantiteCommandee) {
		this.numeroProduit = numeroProduit;
		this.quantiteCommandee = quantiteCommandee;
	}

	public int getNumeroProduit() {
		return numeroProduit;
	}

	public void setNumeroProduit(int numeroProduit) {
		this.numeroProduit = numeroProduit;
	}

	public int getQuantiteCommandee() {
		return quantiteCommandee;
	}

	public void setQuantiteCommandee(int quantiteCommandee) {
		this.quantiteCommandee = quantiteCommandee;
	}
	
	@Override
	public String toString(){
		return "Produit numéro : " + numeroProduit + ", quantité totale : " + quantiteCommandee;
	}
}
