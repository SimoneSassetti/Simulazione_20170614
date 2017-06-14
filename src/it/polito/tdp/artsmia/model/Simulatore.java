package it.polito.tdp.artsmia.model;

import java.time.Year;
import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public class Simulatore {
	
	private SimpleDirectedGraph<Exibitions,DefaultEdge> grafo;
	private Map<Studente,Integer> studentiOpere;
	private PriorityQueue<Evento> coda;
	private int studenti;
	private Set<Studente> listaStudenti;
	
	
	public Simulatore(SimpleDirectedGraph<Exibitions,DefaultEdge> grafo, int studenti){
		this.grafo=grafo;
		this.studenti=studenti;
		
		studentiOpere=new HashMap<>();
		listaStudenti=new HashSet<Studente>();
		
		for(int i=0;i<this.studenti;i++){
			Studente s=new Studente(i);
			listaStudenti.add(s);
			studentiOpere.put(s, 0);
		}
		
		coda=new PriorityQueue<Evento>();
	}
	
	public void inserisci(Year y){
		Random r=new Random();
		
		List<Exibitions> lista=new ArrayList<Exibitions>();
		for(Exibitions e: grafo.vertexSet()){
			if(e.getInizio().compareTo(y)==0){
				lista.add(e);
			}
		}
		
		boolean trovato=false;
		Exibitions e = null;
		while(!trovato){
			int val=r.nextInt(lista.size());
			e=lista.get(val);
			if(e.getInizio().compareTo(y)==0 && Graphs.successorListOf(grafo, e).size()!=0){
				trovato=true;
			}
		}
		for(Studente s: listaStudenti){
			Evento evento=new Evento(s,e);
			coda.add(evento);
		}
	}
	
	public void run(){
		
		while(!coda.isEmpty()){
			
			Evento e=coda.poll();
			
			List<ArtObject> mostra=e.getE().getOpere();
			Studente s=e.getS();
			s.add(mostra);
			studentiOpere.put(s, s.getOpere().size());
			
			Random r=new Random();
			List<Exibitions> mostreSuccessive=Graphs.successorListOf(grafo,e.getE());
			
			if(!mostreSuccessive.isEmpty()){
				int val=r.nextInt(mostreSuccessive.size());
				Exibitions nuova=mostreSuccessive.get(val);
				
				coda.add(new Evento(s,nuova));
			}
			
		}
	}
	
	public List<Stat> getClassifica(){
		List<Stat> temp=new ArrayList<>();
		
		for(Studente s: studentiOpere.keySet()){
			temp.add(new Stat(s,studentiOpere.get(s)));
		}
		return temp;
	}
	
}
