package it.polito.tdp.borders.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		model.creaGrafo(1950);

		List<CountryAndNumber> list = model.getCountryAndNumber();
		
		for(CountryAndNumber c : list) {
			System.out.format("%s %d\n", c.getCountry().getStateName(), c.getNumber());
		}
	}

}
