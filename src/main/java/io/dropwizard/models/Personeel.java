package io.dropwizard.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

public class Personeel implements Principal{
    @NotEmpty
    private int personeelID;

    @NotEmpty
    @Length(min = 3, max = 40)
    private String voornaam;

    private String tussenvoegsel;

    @NotEmpty
    @Length(min = 3, max = 40)
    private String achternaam;

    @NotEmpty
    private String email;

    @NotEmpty
    @Length(min = 8)
    private String wachtwoord;

    @NotEmpty
    private String rechten;

    @NotEmpty
    private String werkzaam;

    public Personeel(int personeelID, String voornaam, String tussenvoegsel, String achternaam, String email, String wachtwoord, String rechten, String werkzaam){
        this.personeelID = personeelID;
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.rechten = rechten;
        this.werkzaam = werkzaam;
    }

    public Personeel(int personeelID, String voornaam, String achternaam, String email, String wachtwoord, String rechten, String werkzaam){
        this.personeelID = personeelID;
        this.voornaam = voornaam;
        this.tussenvoegsel = null;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.rechten = rechten;
        this.werkzaam = werkzaam;

    }

    @Override
    public String getName() {
        return null;
    }

    public String getPassword() {
        return wachtwoord;
    }
}
