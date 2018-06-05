/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryAndNumber;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxNazione"
    private ComboBox<Country> boxNazione; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    private Model model;
    
    public void setModel(Model model) {
    	this.model = model;
    }

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	try {
    	int anno = Integer.parseInt(txtAnno.getText());
    	
		model.creaGrafo(anno);

		List<CountryAndNumber> list = model.getCountryAndNumber();
		
		if(list.size()==0) {
			txtResult.appendText("Non ci sono stati corrispondenti\n");
		}
		else {
			txtResult.appendText("Stati dell'anno "+anno+"\n");
		for(CountryAndNumber c : list) {
			txtResult.appendText(String.format("%s %d\n", c.getCountry().getStateName(), c.getNumber()));
		}
		}
    	//aggiorna il menu a tendina con gli stati presenti nel grafo
		boxNazione.getItems().clear();
		boxNazione.getItems().setAll(model.getCountries());
		
    	}catch(NumberFormatException e){
    		txtResult.appendText("Errore di formattazione dell'anno\n");
    	}

    }

    @FXML
    void doSimula(ActionEvent event) {
    	Country partenza = boxNazione.getValue();
    	if(partenza==null) {
    		txtResult.setText("Errore : selezionare una nazione");
    		return;
    	}
    	model.simula(partenza);
    	int simT = model.getTsimulazione();
    	List<CountryAndNumber> stanziali = model.getCountriesStanziali();
    	txtResult.clear();
    	txtResult.appendText("*** RISULTATI simulazione dallo stato di partenza "+partenza+" ***\n");
    	txtResult.appendText("Durata : "+simT+" passi\n");
    	for(CountryAndNumber cn : stanziali) {
    		if(cn.getNumber()!=0)
    	txtResult.appendText("Nazione : "+cn.getCountry().getStateAbb()+"-"+cn.getCountry().getStateName()+",    Stanziali : "+cn.getNumber()+"\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
        assert boxNazione != null : "fx:id=\"boxNazione\" was not injected: check your FXML file 'Borders.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";

    }
}
