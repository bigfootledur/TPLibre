package edu.tplibre.magasin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

import edu.tplibre.api.Client;
import edu.tplibre.api.Fournisseur;
import edu.tplibre.api.MagasinCommande;
import edu.tplibre.api.Produit;
import edu.tplibre.api.ProduitCommande;
import edu.tplibre.api.ProduitIndisponibleException;

/**
 * Repr�sente le magasin chez qui les clients passent des commandes
 *
 */
public class Magasin extends Observable implements MagasinCommande{
	private Collection<Produit> catalogue;
	private Collection<Client> clients;
	private Collection<String> commandesRealisees;
	private Fournisseur fournisseur;

	public Magasin(){
		catalogue = new ArrayList<Produit>();
		clients = new ArrayList<Client>();
		commandesRealisees = new ArrayList<String>();
	}

	@Override
	public Collection<Produit> getCatalogue(){
		return catalogue;
	}

	@Override
	public Collection<String> getProduitsCommandes() {
		return commandesRealisees;
	}

	@Override
	public void passerCommande(Collection<ProduitCommande> commande) throws ProduitIndisponibleException {
		if(!estValide(commande)){
			throw new ProduitIndisponibleException();
		}
		realiserCommande(commande);
		seFournir();
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
			Iterator<Produit> iterateurCatalogue = catalogue.iterator();
			boolean presentDansLeCatalogue = false;
			while(iterateurCatalogue.hasNext()){
				Produit produit = iterateurCatalogue.next();
				if(produit.getId() == produitCommande.getNumeroProduit()){
					presentDansLeCatalogue = true;
					if(!produit.estDisponible(produitCommande.getQuantiteCommandee())){
						commandeValide = false;
					}
				}
			}
			if(!presentDansLeCatalogue){
				commandeValide = false;
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
			Iterator<Produit> iterateurCatalogue = catalogue.iterator();
			while(iterateurCatalogue.hasNext()){
				Produit produit = iterateurCatalogue.next();
				if(produit.getId() == produitCommande.getNumeroProduit()){
					int nouvelleQuantite = produit.getQuantiteDisponible() - produitCommande.getQuantiteCommandee();
					produit.setQuantiteDisponible(nouvelleQuantite);
				}
			}
		}
	}

	/**
	 * Parcours le catalogue et appelle le fournisseur pour s'approvisionner
	 */
	private void seFournir(){
		Collection<Produit> listeProduit = new ArrayList<Produit>();
		Iterator<Produit> iterateurCatalogue = catalogue.iterator();
		while(iterateurCatalogue.hasNext()){
			Produit produit = iterateurCatalogue.next();
			if(produit.getQuantiteDisponible() < 5){
				listeProduit.add(produit);
			}
		}
		if(!listeProduit.isEmpty()){
			this.fournisseur.approvisionner(listeProduit);
		}
	}
	
	public void setFournisseur(Fournisseur fournisseur, Map<String, ?> ref){
		this.fournisseur = fournisseur;
		
		// On initialise le catalogue avec le stock du fournisseur
		for(Produit produitFournisseur : fournisseur.getStock()){
			this.catalogue.add(new Produit(produitFournisseur.getId(),
										   produitFournisseur.getLibelle(),
										   produitFournisseur.getPrix(),
										   produitFournisseur.getQuantiteDisponible()));
		}
		
	}

	public void unsetFournisseur(Fournisseur fournisseur){
		this.fournisseur = null;
	}

	public void setClients(Client client, Map<String, ?> ref){
		// D�s qu'un service client est trouv� on le fait passer commande
		this.clients.add(client);
		Collection<ProduitCommande> commande = client.getCommande(catalogue);
		try {
			passerCommande(commande);
			for(ProduitCommande entree : commande){
				commandesRealisees.add(client.toString() + " : " + entree.toString());
			}
		} catch (ProduitIndisponibleException e) {
			System.out.println("Un des produits command�s est indisponible");			
		}
	}

	public void unsetClients(Client client){
		System.out.println("Removed client");
		this.clients.remove(client);
	}
}
