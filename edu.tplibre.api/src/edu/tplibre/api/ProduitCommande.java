package edu.tplibre.api;

/**
 * Repr�sente une entr�e de la commande : un num�ro de produit associ� � la quantit�
 * d�sir�e par le client 
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
		return "Produit num�ro : " + numeroProduit + ", quantit� totale : " + quantiteCommandee;
	}
}
