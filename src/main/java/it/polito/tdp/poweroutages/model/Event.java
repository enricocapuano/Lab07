package it.polito.tdp.poweroutages.model;


import java.time.LocalDateTime;
import java.time.ZoneOffset; //converte in secondi una data in base ad un fusorario


public class Event {

	
	private int poId;
	private int eventTypeId;
	private int tagId;
	private int areaId;
	private int nercId;
	private int responsibleId;
	private int customersAffected;
	private LocalDateTime eventBegin;
	private LocalDateTime eventFinish;
	int durata;
	
	public Event(int poId, int eventTypeId, int tagId, int areaId, int nercId, int responsibleId, int customersAffected,
			LocalDateTime eventBegin, LocalDateTime eventFinish) {
		
		this.poId = poId;
		this.eventTypeId = eventTypeId;
		this.tagId = tagId;
		this.areaId = areaId;
		this.nercId = nercId;
		this.responsibleId = responsibleId;
		this.customersAffected = customersAffected;
		this.eventBegin = eventBegin;
		this.eventFinish = eventFinish;
	}

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

	public int getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getNercId() {
		return nercId;
	}

	public void setNercId(int nercId) {
		this.nercId = nercId;
	}

	public int getResponsibleId() {
		return responsibleId;
	}

	public void setResponsibleId(int responsibleId) {
		this.responsibleId = responsibleId;
	}

	public int getCustomersAffected() {
		return customersAffected;
	}

	public void setCustomersAffected(int customersAffected) {
		this.customersAffected = customersAffected;
	}

	public LocalDateTime getEventBegin() {
		return eventBegin;
	}

	public void setEventBegin(LocalDateTime eventBegin) {
		this.eventBegin = eventBegin;
	}

	public LocalDateTime getEventFinish() {
		return eventFinish;
	}

	public void setEventFinish(LocalDateTime eventFinish) {
		this.eventFinish = eventFinish;
	}

	@Override
	public String toString() {
		return poId+" "+eventTypeId+" "+customersAffected+" "+eventBegin+" "+eventFinish;
	}
	
	public int getDurata() {
		long f = this.eventFinish.toEpochSecond(ZoneOffset.UTC);
		long b = this.eventBegin.toEpochSecond(ZoneOffset.UTC);
		int diff = (int) (f - b);
		durata = diff/3600;
		return durata;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + poId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (poId != other.poId)
			return false;
		return true;
	}
	
	
}
