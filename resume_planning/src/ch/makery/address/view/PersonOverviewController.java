package ch.makery.address.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;




//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;

import ch.makery.address.MainApp;
import ch.makery.address.MySQLconnection;
import ch.makery.address.model.Employe;
import ch.makery.address.model.Mission;

public class PersonOverviewController {
	
	//private static Statement stat;
	//private PreparedStatement prep;
	
    @FXML
    private TableView<Employe> EmployeTable;
    @FXML
    private TableView<Mission> MissionTable;
    @FXML
    private TableColumn<Employe, String> NomColumn;
    @FXML
    private TableColumn<Employe, String> PrenomColumn;
    
    @FXML
    private TableColumn<Mission, String> DateEndColumn;
    @FXML
    private TableColumn<Mission, Integer> numLivrColumn;
    @FXML
    private TableColumn<Mission, String> AdresseDepartColumn;
    @FXML
    private TableColumn<Mission, String> AdresseArriveColumn;

    @FXML
    private Label NomLabel;
    @FXML
    private Label PrenomLabel;
    @FXML
    private Label AdresseLabel;
    @FXML
    private Label NumeroLabel;
    @FXML
    private Label HeureLabel;
    @FXML
    private Label PrixLabel;
    @FXML
    private Label PrimeLabel;
    @FXML
    private Label ChargeLabel;
    @FXML
    private Label SalaireBLabel;
    @FXML
    private Label SalaireNLabel;
    

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
            this.NomColumn.setCellValueFactory(cellData -> cellData.getValue().NomProperty());
            this.PrenomColumn.setCellValueFactory(cellData -> cellData.getValue().PrenomProperty());
            
         // Clear person details.
            showPersonDetails(null);
            
            
            // Listen for selection changes and show the person details when changed.
            EmployeTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showPersonDetails(newValue));
           
            this.DateEndColumn.setCellValueFactory(cellData -> cellData.getValue().DateMissionProperty());
            this.numLivrColumn.setCellValueFactory(cellData -> cellData.getValue().IdProperty().asObject());
            this.AdresseDepartColumn.setCellValueFactory(cellData -> cellData.getValue().AdresseDepartProperty());
            this.AdresseArriveColumn.setCellValueFactory(cellData -> cellData.getValue().AdresseAriveProperty());
            
            
            
            
    	
    	
    }
    
    public ObservableList<Mission> getPersonMissions(Employe employe) {
    	Connection con = null;
    	ObservableList<Mission> PersonMission = FXCollections.observableArrayList();
    	try {
    		con = MySQLconnection.Connector();
    		int id = employe.getIdEmploye();
    		ResultSet res = con.createStatement().executeQuery("SELECT mission.end,idMission, idEmp, idDepart, idArriver,"
    				+ "employe.id_emp FROM employe, mission WHERE employe.id_emp = mission.idEmp AND "
    				+ "mission.idEmp = employe.id_emp AND employe.id_emp ="+id+"");
    		while (res.next()){
    			PersonMission.add(new Mission(res.getString("end"),res.getInt("idMission"),res.getString("idDepart"), res.getString("idArriver")));
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Error on Building Data");

    	}
    	return PersonMission;
    }
    
    

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        EmployeTable.setItems(this.mainApp.getEmployeData());
        
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showPersonDetails(Employe employe) {
        if (employe != null) {
            // Fill the labels with info from the person object.
            NomLabel.setText(employe.getNom());
            PrenomLabel.setText(employe.getPrenom());
            AdresseLabel.setText(employe.getAdresse());
            NumeroLabel.setText(employe.getNumero());
            HeureLabel.setText(Float.toString((employe.getHeuretravail())));
            PrixLabel.setText(Float.toString((employe.getPrixHeure())));
            PrimeLabel.setText(Float.toString((employe.getPrime())));
            ChargeLabel.setText(Float.toString((employe.getCharge())));
            SalaireBLabel.setText(Float.toString(employe.getSalaireB()));
            SalaireNLabel.setText(Float.toString(employe.getSalaireN()));
            MissionTable.setItems(getPersonMissions(employe));

        } else {
            // Person is null, remove all the text.
            this.NomLabel.setText("");
            this.PrenomLabel.setText("");
            this.AdresseLabel.setText("");
            this.NumeroLabel.setText("");
            this.HeureLabel.setText("");
            this.PrixLabel.setText("");
            this.PrimeLabel.setText("");
            this.ChargeLabel.setText("");
            this.SalaireBLabel.setText("");
            this.SalaireNLabel.setText("");
            this.SalaireBLabel.setText("");
            this.SalaireNLabel.setText("");
        }
    }
    
    
    
}