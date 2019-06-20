package edu.tplibre.fournisseur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import edu.tplibre.api.Produit;
import edu.tplibre.api.ProduitIndisponibleException;

public class Stock extends Thread{

	private final int delay = 5000;

	Collection<Produit> catalogue;

	public Stock() {
		catalogue=new ArrayList<Produit>();
		catalogue.add(new Produit(1, "cacao", 4.5f, 60));
		catalogue.add(new Produit(2, "lait", 10.3f, 60));

		this.start();
	}

	@Override
	public void run() {
		Random rand = new Random();
		while(true){
			try {
				sleep(delay);
				for (Produit produit : catalogue){
					int quantiteDisponible = produit.getQuantiteDisponible();
					produit.setQuantiteDisponible(quantiteDisponible + rand.nextInt(10));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void approvisionner(Collection<Produit> produits) {
		Iterator<Produit> iterProduits = produits.iterator();
		while(iterProduits.hasNext()){
			Produit produit=iterProduits.next();
			Iterator<Produit> iterCatalogue = catalogue.iterator();
			while(iterCatalogue.hasNext()){
				Produit produitCatalogue=iterCatalogue.next();
				if(produit.getId()==produitCatalogue.getId()){
					Random rand= new Random();
					int quantiteApprovisionnement = rand.nextInt(produitCatalogue.getQuantiteDisponible());
					produit.setQuantiteDisponible(quantiteApprovisionnement + produit.getQuantiteDisponible());
					produitCatalogue.setQuantiteDisponible(produitCatalogue.getQuantiteDisponible() - quantiteApprovisionnement);
					System.out.println("produit:"+produit.getQuantiteDisponible()+" prdouitCatalogue:"+produitCatalogue.getQuantiteDisponible());
				}
			}
		}
	}

	public Collection<Produit> getCatalogue() {
		return catalogue;
	}

}
