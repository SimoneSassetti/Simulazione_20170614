package it.polito.tdp.artsmia.model;

public class Result {
	private Exibitions e;
	private int numMostre;
	public Result(Exibitions e, int numMostre) {
		super();
		this.e = e;
		this.numMostre = numMostre;
	}
	public Exibitions getE() {
		return e;
	}
	public void setE(Exibitions e) {
		this.e = e;
	}
	public int getNumMostre() {
		return numMostre;
	}
	public void setNumMostre(int numMostre) {
		this.numMostre = numMostre;
	}
	@Override
	public String toString() {
		return e.getNome()+" "+numMostre;
	}
}
