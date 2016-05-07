package ch.makery.address.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Employe {
	private IntegerProperty Id;
    private StringProperty Prenom;
    private StringProperty Nom;
    private StringProperty Adresse;
    private StringProperty numero_tel;
    private FloatProperty HeuresTravail;
    private FloatProperty Prixheure;
    private FloatProperty Prime;
    private FloatProperty Charge;
    private FloatProperty SalaireBrut;
    private FloatProperty SalaireNet;


    

    /**
     * Default constructor.
     
     */

    public Employe(int id,String nom, String prenom,String adresse, String num) {
    	this.Id = new SimpleIntegerProperty(id);
        this.Nom = new SimpleStringProperty(nom);
        this.Prenom = new SimpleStringProperty(prenom);
        this.Adresse = new SimpleStringProperty(adresse);
        this.numero_tel = new SimpleStringProperty(num);
    }

    public Employe(int id,String nom, String prenom,String adresse, String num,
                   float HT, float PH, float prime, float charge) {
    	this.Id = new SimpleIntegerProperty(id);
        this.Nom = new SimpleStringProperty(nom);
        this.Prenom = new SimpleStringProperty(prenom);
        this.Adresse = new SimpleStringProperty(adresse);
        this.numero_tel = new SimpleStringProperty(num);
        this.HeuresTravail = new SimpleFloatProperty(HT);
        this.Prixheure = new SimpleFloatProperty(PH);
        this.Prime = new SimpleFloatProperty(prime);
        this.Charge = new SimpleFloatProperty(charge);
        this.SalaireBrut = new SimpleFloatProperty((HT * PH) + prime);
        this.SalaireNet = new SimpleFloatProperty((HT * PH) + prime - charge);
    }
    
    public int getIdEmploye() {
        return Id.get();
    }

    public IntegerProperty IdEmployeProperty() {
        return Id;
    }

    public void setIdEmploye(int id) {
        this.Id.set(id);
    }
    
    public float getSalaireN() {
        return SalaireNet.get();
    }

    public FloatProperty salaireNProperty() {
        return SalaireNet;
    }

    public void setSalaireN(float salaireNet) {
        this.SalaireNet.set(salaireNet);
    }

    public float getSalaireB() {
        return SalaireBrut.get();
    }

    public FloatProperty salaireBProperty() {
        return SalaireBrut;
    }

    public void setSalaireB(float salaireBrut) {
        this.SalaireBrut.set(salaireBrut);
    }

    public String getNom() {
        return Nom.get();
    }

    public void setNom(String nom) {
        this.Nom.set(nom);
    }

    public StringProperty NomProperty() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom.get();
    }

    public void setPrenom(String prenom) {
        this.Prenom.set(prenom);
    }

    public StringProperty PrenomProperty() {
        return Prenom;
    }

    public String getNumero() {
        return numero_tel.get();
    }

    public void setNumero(String numero) {
        this.numero_tel.set(numero);
    }

    public StringProperty NumeroProperty() {
        return numero_tel;
    }


    public String getAdresse() {
        return Adresse.get();
    }

    public void setAdresse(String adresse) {
        this.Adresse.set(adresse);
    }

    public StringProperty AdresseProperty() {
        return Adresse;
    }
    public float getHeuretravail() {
        return HeuresTravail.get();
    }

    public void setheureTravail(float HT) {
        this.HeuresTravail.set(HT);
    }

    public FloatProperty HeuresTravailProperty() {
        return HeuresTravail;
    }

    public float getPrixHeure() {
        return Prixheure.get();
    }

    public void setPrixheure(float PH) {
        this.Prixheure.set(PH);
    }

    public FloatProperty PrixheureProperty() {
        return Prixheure;
    }

    public float getPrime() {
        return Prime.get();
    }

    public void setPrime(float prime) {
        this.Prime.set(prime);
    }

    public FloatProperty PrimeProperty() {
        return Prime;
    }
    public float getCharge() {
        return Charge.get();
    }

    public void setCharge(float charge) {
        this.Charge.set(charge);
    }

    public FloatProperty ChargeProperty() {
        return Charge;
    }


}