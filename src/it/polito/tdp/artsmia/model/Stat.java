package it.polito.tdp.artsmia.model;

public class Stat implements Comparable<Stat>{
	
	private Studente s;
	private int numOpere;
	public Stat(Studente s, int numOpere) {
		super();
		this.s = s;
		this.numOpere = numOpere;
	}
	public Studente getS() {
		return s;
	}
	public void setS(Studente s) {
		this.s = s;
	}
	public int getNumOpere() {
		return numOpere;
	}
	public void setNumOpere(int numOpere) {
		this.numOpere = numOpere;
	}
	@Override
	public int compareTo(Stat o) {
		return -(this.numOpere-o.numOpere);
	}
}
