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
	private int totaleOre;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Event> calcolaSottoinsiemeEventi(int id, int Y, int X) {
		List<Event> parziale = new ArrayList<Event>();
		partenza = podao.getEventListFromNerc(id);
		sommaCustomers = 0;
		totaleOre = 0;
		soluzioneMigliore = null;
		cerca(parziale, Y, X);
		return soluzioneMigliore;
	}
	
	private void cerca(List<Event> parziale, int oreMax, int anniMax) {
		
		
			
		if(this.calcolaTotaleCustomers(parziale) > sommaCustomers) {
			sommaCustomers = calcolaTotaleCustomers(parziale);
			totaleOre = this.sommaOre(parziale);
			soluzioneMigliore = new ArrayList<>(parziale);
		}
				
		
		for(Event e : partenza) {
			if(!parziale.contains(e)) {
				parziale.add(e);
				if(soluzioneAmmisibile(parziale, oreMax, anniMax)) {	
					cerca(parziale, oreMax, anniMax);		
				}	
			parziale.remove(e);
			}
		}
		
	}

	private boolean soluzioneAmmisibile(List<Event> parziale, int oreMax, int anniMax) {
		
		if(parziale.get(parziale.size()-1).getEventBegin().getYear() - parziale.get(0).getEventBegin().getYear() >= anniMax) {
			return false;
		}
		if(sommaOre(parziale) >= oreMax) {
			return false;
		}
		
		return true;
			
	}
	
	private int sommaOre(List<Event> parziale) {
		int somma = 0;
		for(Event ee : parziale) {
			somma += ee.getDurata();
		}
		return somma;
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

	public int getSommaCustomers() {
		return sommaCustomers;
	}

	public int getTotaleOre() {
		return totaleOre;
	}
	
	

}
