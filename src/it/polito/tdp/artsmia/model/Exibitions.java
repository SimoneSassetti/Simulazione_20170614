package it.polito.tdp.artsmia.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Exibitions {
	
	private int id;
	private String dipartimento;
	private String nome;
	private Year inizio;
	private Year fine;
	
	private List<ArtObject> listaOpere;
	
	public Exibitions(int id, String dipartimento, String nome, Year inizio, Year fine) {
		super();
		this.id = id;
		this.dipartimento = dipartimento;
		this.nome = nome;
		this.inizio = inizio;
		this.fine = fine;
		listaOpere=new ArrayList<ArtObject>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDipartimento() {
		return dipartimento;
	}
	public void setDipartimento(String dipartimento) {
		this.dipartimento = dipartimento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Year getInizio() {
		return inizio;
	}
	public void setInizio(Year inizio) {
		this.inizio = inizio;
	}
	public Year getFine() {
		return fine;
	}
	public void setFine(Year fine) {
		this.fine = fine;
	}
	public void add(List<ArtObject> lista){
		listaOpere.addAll(lista);
	}
	
	public List<ArtObject> getOpere(){
		return listaOpere;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Exibitions other = (Exibitions) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
