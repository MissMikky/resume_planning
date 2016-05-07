package ch.makery.address;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import ch.makery.address.model.Employe;
import ch.makery.address.model.Mission;
//import ch.makery.address.view.ConnectOverviewController;
import ch.makery.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application {
	private static Connection con;
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Employe> EmployeData = FXCollections.observableArrayList();
    private ObservableList<Mission> MissionData = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TNI Resumé mensuel");

        initRootLayout();
        //showLogin();
        showPersonOverview();
    }
    
    public MainApp() {
    	try {
    		con = MySQLconnection.Connector();
        	
        	ResultSet rs = con.createStatement().executeQuery(
        			"SELECT employe.id_emp,name_employe,surname_employe,adresse,"
        			+ "numero_tel, salaire.id_emp,salaire.date, salaire.nbheureouvres, salaire.prixparheure, "
        			+ "salaire.prime, salaire.charge FROM employe,salaire WHERE statut = "
        			+ "'Actif'AND employe.id_emp = salaire.id_emp ORDER BY salaire.date");
        	ResultSet res = con.createStatement().executeQuery("SELECT idMission, idEmp, idDepart,mission.end,"
        			+ " idArriver,employe.id_emp FROM employe, mission WHERE employe.id_emp = mission.idEmp"
        			+ " ORDER BY mission.end LIMIT 30");
            // Initialize the person table with the two columns.
        	while (rs.next()&& res.next()) {
        		this.EmployeData.add(new Employe(rs.getInt("id_emp"),rs.getString("name_employe"), rs.getString("surname_employe"),
        				rs.getString("adresse"), rs.getString("numero_tel"), rs.getFloat("nbheureouvres"),
        				rs.getFloat("prixparheure"), rs.getFloat("prime"), rs.getFloat("charge")));
        		this.MissionData.add(new Mission(res.getString("end"),res.getInt("idMission"),res.getString("idDepart"), res.getString("idArriver")));
        	}
    	}catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Error on Building Data");

    	}	
    	
    }
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*public void showLogin () {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ConnectOverview.fxml"));
            AnchorPane connectOverview = (AnchorPane) loader.load();
            
            rootLayout.setCenter(connectOverview);
            
            ConnectOverviewController logcontroller = loader.getController();
            logcontroller.setMainApp(this);
    	} catch (IOException e) {
            e.printStackTrace();
    	}
    	
    }*/

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public ObservableList<Employe> getEmployeData() {
        return EmployeData;
    }

    public ObservableList<Mission> getMissionData() {
    	return MissionData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}