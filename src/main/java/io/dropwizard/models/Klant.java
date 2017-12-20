package io.dropwizard.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Klant{

    private int klantID;

    @NotEmpty
    @Length(max = 40)
    private String klantNaam;

    public int getKlantID(){return klantID; }

    public void setKlantID(int klantID){this.klantID = klantID;}

    public String getKlantNaam(){return klantNaam;}

    public void setKlantNaam(String klantNaam){this.klantNaam = klantNaam;}

    public Klant(int klantID, String klantNaam){

        this.klantID = klantID;

        this.klantNaam = klantNaam;
    }
}
