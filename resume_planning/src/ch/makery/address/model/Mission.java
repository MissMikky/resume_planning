package ch.makery.address.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Mission {
	private IntegerProperty NumeroMission;
    private StringProperty DateMission;
    private StringProperty AdresseD_mission;
    private StringProperty AdresseA_mission;
    
    public Mission() {}
    
    public Mission(String date,int id,String AD,String AA){
    	this.NumeroMission = new SimpleIntegerProperty(id);
    	this.DateMission = new SimpleStringProperty(date);
    	this.AdresseD_mission = new SimpleStringProperty(AD);
    	this.AdresseA_mission = new SimpleStringProperty(AA);
    }
    
    public int getIdMission() {
    	return NumeroMission.get();
    }
    
    public void setIdMission(int id) {
    	this.NumeroMission.set(id);
    }
    
    public IntegerProperty IdProperty() {
    	return NumeroMission;
    }
    
    public String getDateMission() {
        return DateMission.get();
    }

    public void setDateMission(String date) {
        this.DateMission.set(date);
    }

    public StringProperty DateMissionProperty() {
        return DateMission;
    }   
    public String getAdresseDepart() {
        return AdresseD_mission.get();
    }

    public void setAdresseDepart(String AD) {
        this.AdresseD_mission.set(AD);
    }

    public StringProperty AdresseDepartProperty() {
        return AdresseD_mission;
    }
    
    public String getAdresseArrive() {
        return AdresseA_mission.get();
    }

    public void setAdresseArrive(String AD) {
        this.AdresseA_mission.set(AD);
    }

    public StringProperty AdresseAriveProperty() {
        return AdresseA_mission;
    }
    
}
