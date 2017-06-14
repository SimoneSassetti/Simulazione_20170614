package it.polito.tdp.artsmia.model;

public class ExibitionsPair {
	private Exibitions e1;
	private Exibitions e2;
	public ExibitionsPair(Exibitions e1, Exibitions e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}
	public Exibitions getE1() {
		return e1;
	}
	public void setE1(Exibitions e1) {
		this.e1 = e1;
	}
	public Exibitions getE2() {
		return e2;
	}
	public void setE2(Exibitions e2) {
		this.e2 = e2;
	}
}
