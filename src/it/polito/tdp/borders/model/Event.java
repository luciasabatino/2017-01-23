package it.polito.tdp.borders.model;

public class Event implements Comparable<Event>{

	private int t;
	private int num; //quante persone si spostano
	private Country destination; //nazione di destinazione
	
	@Override
	public int compareTo(Event arg0) {
		
		return this.t-arg0.t;
	}
	public Event(int t, int num, Country destination) {
		super();
		this.t = t;
		this.num = num;
		this.destination = destination;
	}
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Country getDestination() {
		return destination;
	}
	public void setDestination(Country destination) {
		this.destination = destination;
	}
	@Override
	public String toString() {
		return "Event [t=" + t + ", num=" + num + ", destination=" + destination + "]";
	}
	
	

}
