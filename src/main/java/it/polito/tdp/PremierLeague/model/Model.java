package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	PremierLeagueDAO dao;
	Map<Integer,Match> idMap;
	Graph<Match, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao = new  PremierLeagueDAO();
	}
	
	public void creaGrafo(int min, int mese) {
		idMap = new HashMap<>();
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		dao.getVertici(mese, idMap);
		Graphs.addAllVertices(grafo, idMap.values());
		
		List<Adiacenza> archi = dao.getArchi(min, mese, idMap);
		
		for(Adiacenza ad: archi) {
			if(grafo.containsVertex(ad.getM1()) && grafo.containsVertex(ad.getM2())) {
				//if(!grafo.containsEdge(ad.getM1(), ad.getM2()))
				Graphs.addEdgeWithVertices(grafo, ad.getM1(), ad.getM2(), ad.getPeso());
			}
		}
	}
	
	public int NumVertici() {
		return grafo.vertexSet().size();
	}
	public int NumArchi() {
		return grafo.edgeSet().size();
	}
	
	public List<Adiacenza> getMaggioreNumGiocatori(){
		double max = 0;
		List<Adiacenza> result = new ArrayList<>();
		
		for(DefaultWeightedEdge edge: grafo.edgeSet()) {
			if(grafo.getEdgeWeight(edge) >= max) {
			max = grafo.getEdgeWeight(edge);
			Adiacenza a = new 	Adiacenza(grafo.getEdgeSource(edge), grafo.getEdgeTarget(edge), grafo.getEdgeWeight(edge));
			result.add(a);
			}
		}
		return result;
	}
	
	public List<Match> getAllMatch(){
		 List<Match> list = new ArrayList<>(idMap.values());
		 return list;
	}
	
}
