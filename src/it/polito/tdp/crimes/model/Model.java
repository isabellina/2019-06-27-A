package it.polito.tdp.crimes.model;

import java.time.Year;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	private EventsDao eventsDao;
	private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> grafo;
	
	
	public Model() {
		this.eventsDao = new EventsDao();
	}

	public List<String> getCategorie() {
		List<String> cat = new LinkedList<String>(this.eventsDao.getCategorieReati());
		return cat;
	}
	
	public List<Year> getAnni(){
		List<Year> years = new LinkedList<Year>(this.eventsDao.getAnno());
		return years;
	}
	
	public void creaGrafo(Year y,String s) {
		this.grafo = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		for(String st: this.eventsDao.getTipoReati(y, s)) {
			grafo.addVertex(st);
		}
		
		for(String r: grafo.vertexSet()) {
			
		}
		
	}
	
	
	
}
