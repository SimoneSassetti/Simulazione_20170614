/**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;
import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Model;
import it.polito.tdp.artsmia.model.Stat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ChoiceBox<Year> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldStudenti"
    private TextField txtFieldStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleCreaGrafo(ActionEvent event) {
    	Year y=boxAnno.getValue();
    	if(y==null){
    		txtResult.appendText("Selezionare un anno.");
    		return;
    	}
    	String s=model.creaGrafo(y);
    	txtResult.appendText(s);
    	txtResult.appendText("La mostra con il maggior numero di opere è: \n");
    	txtResult.appendText(model.getResult().toString()+"\n");	
    }

    @FXML
    void handleSimula(ActionEvent event) {
    	Year y=boxAnno.getValue();
    	if(y==null){
    		txtResult.appendText("Selezionare un anno");
    		return;
    	}
    	int studenti=0;
    	try{
    		studenti=Integer.parseInt(txtFieldStudenti.getText());
    	}catch(NumberFormatException e){
    		throw new NumberFormatException("Inserire un numero intero per gli studenti.\n");
    	}
    	
    	List<Stat> lista=model.simula(y,studenti);
    	Collections.sort(lista);
    	txtResult.appendText("Classifica studenti: \n");
    	for(Stat s: lista){
    		txtResult.appendText(s.getS().toString()+" --> "+s.getNumOpere()+"\n");
    	}
    	
    } 

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtFieldStudenti != null : "fx:id=\"txtFieldStudenti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }
    Model model;
	public void setModel(Model model) {
		this.model=model;
		
		boxAnno.getItems().addAll(model.getAnni());
	}
}
