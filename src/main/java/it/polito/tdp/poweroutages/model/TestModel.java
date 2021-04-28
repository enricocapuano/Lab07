package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		for(Nerc n : model.getNercList()) {
			if(n.getValue().equals("MAAC")) {
				System.out.println(model.calcolaSottoinsiemeEventi(n.getId(), 200, 4));
			}
		}

	}

}
