package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<Event> partenza;
	private List<Event> soluzioneMigliore;
	private int sommaCustomers;
	private int sommaOre;
	private int sommaAnni;
	
	public Model() {
		podao = new PowerOutageDAO();
		sommaCustomers = 0;
		sommaOre = 0;
		sommaAnni = 1;
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Event> calcolaSottoinsiemeEventi(int id, int Y, int X) {
		List<Event> parziale = new ArrayList<Event>();
		partenza = podao.getEventListFromNerc(id);
		soluzioneMigliore = null;
		cerca(parziale, 0, Y, X);
		return soluzioneMigliore;
	}
	
	private void cerca(List<Event> parziale, int livello, int oreMax, int anniMax) {
		
		sommaCustomers = calcolaTotaleCustomers(parziale);
			
		if(sommaCustomers > calcolaTotaleCustomers(soluzioneMigliore) || soluzioneMigliore == null) {
			soluzioneMigliore = new ArrayList<Event>(parziale);
		}
				
		
		for(Event e : partenza) {
			if(soluzioneAmmisibile(e, parziale, oreMax, anniMax)) {
				parziale.add(e);
				cerca(parziale, livello+1, oreMax, anniMax);
				parziale.remove(e);
			}	
		}
		
	}

	private boolean soluzioneAmmisibile(Event e, List<Event> parziale, int oreMax, int anniMax) {
		
		
				
		if(parziale.size() == 0) { 
			return true;
		}
	
		sommaAnni = e.getEventBegin().getYear() - parziale.get(0).getEventBegin().getYear();
		
			
		for(Event ee : parziale) {
			sommaOre += ee.getDurata() + e.getDurata();
		}
			
		if(sommaAnni > anniMax || sommaOre > oreMax) {
			return false;
		}
		
		return true;
		
	
	}

	private int calcolaTotaleCustomers(List<Event> parziale) {
		int somma = 0;
		if(parziale != null) {
			for(Event e : parziale) {
				somma += e.getCustomersAffected();
			}			
		}
		return somma;
	}

}
