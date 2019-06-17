package edu.tplibre.api;

public class Produit {
	private int id;
	private String libelle;
	private float prix;
	private int quantiteDisponible;
	
	public Produit(int id, String libelle, float prix, int quantiteDisponible) {
		this.id = id;
		this.libelle = libelle;
		this.prix = prix;
		this.quantiteDisponible = quantiteDisponible;
	}
	
	public boolean estDisponible(int quantite){
		return quantiteDisponible >= quantite;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getQuantiteDisponible() {
		return quantiteDisponible;
	}
	public void setQuantiteDisponible(int quantiteDisponible) {
		this.quantiteDisponible = quantiteDisponible;
	}
	
	@Override
	public String toString(){
		return "ID : " + id + "; Libelle : " + libelle+ " ; Prix : " + prix + " ; Quantit� : " + quantiteDisponible;
	}
}
