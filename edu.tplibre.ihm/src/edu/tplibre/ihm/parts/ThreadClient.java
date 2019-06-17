package edu.tplibre.ihm.parts;

import java.util.Random;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import edu.tplibre.api.Client;
import edu.tplibre.clientgourmand.ClientGourmand;
import edu.tplibre.clientstandard.ClientStandard;

public class ThreadClient extends Thread {
	// D�lai de pause du Thread
	public static final int delay=5000;
	
	public ThreadClient(){
		this.start();
	}
	
	/**
	 * M�thode run qui sera appel�e par le Thread.start()
	 */
	public void run(){
		BundleContext ctx = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		Random rand = new Random();
		Client client = null;
		while(true){
			if(rand.nextFloat() < 0.5f){
				client = new ClientGourmand();
			}
			else{
				client = new ClientStandard();
			}
			ctx.registerService(Client.class, client, null);
			
			try {
				sleep(delay);
			}
			catch (InterruptedException e2) {
				e2.printStackTrace();
			}
		}
	}
}
