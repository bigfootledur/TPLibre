package edu.tplibre.magasin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

import edu.tplibre.api.Client;
import edu.tplibre.api.MagasinCommande;
import edu.tplibre.api.Produit;
import edu.tplibre.api.ProduitCommande;
import edu.tplibre.api.ProduitIndisponibleException;

public class Magasin extends Observable implements MagasinCommande{
	private Collection<Produit> catalogue;
	private Collection<Client> clients;
	private Collection<ProduitCommande> commandesRealisees;
	
	public Magasin(){
		System.out.println("Hello world");
		catalogue = new ArrayList<Produit>();
		catalogue.add(new Produit(1, "cacao", 4.5f, 59));
		catalogue.add(new Produit(2, "lait", 10.3f, 48));
		
		clients = new ArrayList<Client>();
		commandesRealisees = new ArrayList<ProduitCommande>();
	}
	
	@Override
	public Collection<Produit> getCatalogue(){
		return catalogue;
	}
	
	@Override
	public Collection<ProduitCommande> getProduitsCommandes() {
		return commandesRealisees;
	}
	
	public void setClients(Client client, Map<String, ?> ref){
		this.clients.add(client);
		try {
			passerCommande(client.getCommande(catalogue));
		} catch (ProduitIndisponibleException e) {
			System.out.println("Un des produits command�s est indisponible");
		}
	}
	
	public void unsetClients(Client client){
		this.clients.remove(client);
	}

	@Override
	public void passerCommande(Collection<ProduitCommande> commande) throws ProduitIndisponibleException {
		if(!estValide(commande)){
			throw new ProduitIndisponibleException();
		}
		realiserCommande(commande);
		this.setChanged();
		this.notifyObservers(catalogue);
		this.notifyObservers(commandesRealisees);
	}
	
	/**
	 * V�rifie que tous les produits de la commande sont disponibles par rapport
	 * � la quantit� demand�e
	 * @param commande : la commande � v�rifier
	 * @return vrai si tous les produits sont disponible, faux sinon
	 */
	private boolean estValide(Collection<ProduitCommande> commande){
		boolean commandeValide = true;
		Iterator<ProduitCommande> iterateurCommande = commande.iterator();
		while(iterateurCommande.hasNext()){
			ProduitCommande produitCommande = iterateurCommande.next();
			Iterator<Produit> iterateurProduits = catalogue.iterator();
			while(iterateurProduits.hasNext()){
				Produit produit = iterateurProduits.next();
				if(produit.getId() == produitCommande.getNumeroProduit()){
					if(!produit.estDisponible(produitCommande.getQuantiteCommandee())){
						commandeValide = false;
					}
				}
			}
		}
		return commandeValide;
	}
	
	/**
	 * Enregistre la commande dans la liste des commandes r�alis�es et
	 * sort les produits command�s
	 * @param commande : la commande � r�aliser
	 */
	private void realiserCommande(Collection<ProduitCommande> commande) {
		Iterator<ProduitCommande> iterateurCommande = commande.iterator();
		while(iterateurCommande.hasNext()){
			ProduitCommande produitCommande = iterateurCommande.next();
			commandesRealisees.add(new ProduitCommande(produitCommande.getNumeroProduit(),
					  produitCommande.getQuantiteCommandee()));
			Iterator<Produit> iterateurProduits = catalogue.iterator();
			while(iterateurProduits.hasNext()){
				Produit produit = iterateurProduits.next();
				if(produit.getId() == produitCommande.getNumeroProduit()){
					int nouvelleQuantite = produit.getQuantiteDisponible()
										   - produitCommande.getQuantiteCommandee();
					produit.setQuantiteDisponible(nouvelleQuantite);
				}
			}
		}
	}
}