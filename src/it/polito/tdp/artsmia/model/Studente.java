package it.polito.tdp.artsmia.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Studente {
	
	private int id;
	
	private Set<ArtObject> opere; 
	
	public Studente(int id) {
		super();
		this.id = id;
		opere=new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void add(List<ArtObject> lista){
		opere.addAll(lista);
	}
	public Set<ArtObject> getOpere(){
		return opere;
	}
	

	@Override
	public String toString() {
		return ""+id;
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
		Studente other = (Studente) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}
