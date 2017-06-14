package it.polito.tdp.artsmia.model;

import java.time.Year;
import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private List<Year> anni;
	ArtsmiaDAO dao;
	private SimpleDirectedGraph<Exibitions,DefaultEdge> grafo;
	private List<Exibitions> listaMostre;
	private Map<Integer,Exibitions> mappaMostre;
	private Map<Integer,ArtObject> mappaOpere;
	
	public Model(){
		dao=new ArtsmiaDAO();
		mappaMostre=new HashMap<>();
		mappaOpere=new HashMap<>();
	}
	
	public List<Year> getAnni(){
		if(anni==null){
			anni=dao.getAnni();
		}
		return anni;
	}
	
	

	public String creaGrafo(Year y) {
		grafo=new SimpleDirectedGraph<Exibitions,DefaultEdge>(DefaultEdge.class);
		
		dao.listObject(mappaOpere);
		listaMostre=dao.getMostre(y,mappaMostre);
		
		Graphs.addAllVertices(grafo, listaMostre);
		
		//archi
		for(ExibitionsPair e: dao.getCoppie(y, mappaMostre)){
			grafo.addEdge(e.getE1(), e.getE2());
		}
		
		ConnectivityInspector<Exibitions,DefaultEdge> ci=new ConnectivityInspector<Exibitions,DefaultEdge>(grafo);
		
		String result="";
		if(ci.isGraphConnected()){
			result="Il grafo è connesso.\n";
		}else{
			result="Il grafo NON è connesso.\n";
		}
		
		this.insersciOpere();
		
		return result;
	}
	
	public void insersciOpere(){
		for(Exibitions e: listaMostre){
			e.add(dao.getOpereMostra(e, mappaOpere));
		}
	}
	
	public Result getResult(){
		Exibitions best=null;
		int max=0;
		
		for(Exibitions e: listaMostre){
			if(e.getOpere().size()>max){
				max=e.getOpere().size();
				best=e;
			}
		}
		Result r=new Result(best,max);
		return r;
	}

	public List<Stat> simula(Year y, int studenti) {
		
		Simulatore sim=new Simulatore(grafo,studenti);
		
		sim.inserisci(y);
		sim.run();
		
		
		return sim.getClassifica();
	}
	
	
	
	
}
