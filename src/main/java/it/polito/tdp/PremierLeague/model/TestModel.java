package it.polito.tdp.PremierLeague.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		m.creaGrafo(10, 5);
		System.out.println("#vertici "+ m.NumVertici()+ " #archi "+m.NumArchi());

	}

}
