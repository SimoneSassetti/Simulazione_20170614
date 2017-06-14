package it.polito.tdp.artsmia.model;

public class Evento implements Comparable<Evento>{
	
	private Studente s;
	private Exibitions e;
	
	public Evento(Studente s, Exibitions e) {
		super();
		this.s = s;
		this.e = e;
	}
	
	public Studente getS() {
		return s;
	}

	public void setS(Studente s) {
		this.s = s;
	}

	public Exibitions getE() {
		return e;
	}
	public void setE(Exibitions e) {
		this.e = e;
	}
	@Override
	public int compareTo(Evento evento) {
		return this.e.getInizio().getValue()-evento.e.getInizio().getValue();
	}	
}
