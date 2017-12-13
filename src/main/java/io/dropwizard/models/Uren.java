package io.dropwizard.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class Uren {


    @NotEmpty
    private int uurId;

    @NotNull
    private int personeelID;

    @NotNull
    private String beginDatum;

    @NotNull
    private String beginTijd;

    @NotNull
    private String eindDatum;

    @NotNull
    private String eindTijd;

    @NotNull
    private String klantNaam;

    @NotNull
    private String projectNaam;

    @NotNull
    private String onderwerpNaam;

    @NotNull
    private int klantId;

    @NotNull
    private int projectId;

    @NotNull
    private int onderwerpId;

    @NotNull
    private String commentaar;

    @NotNull
    private boolean goedgekeurd;

    @NotNull
    private String persoonNaam;

    @NotNull
    private boolean isChanged = false;

    public Uren(int uurId, int personeelID, String beginDatum, String beginTijd, String eindDatum, String eindTijd, String klantNaam, String projectNaam, String onderwerpNaam,int klantId, int projectId, int onderwerpId, String commentaar, boolean goedgekeurd, String persoonNaam, boolean isChanged) {
        this.uurId = uurId;
        this.personeelID = personeelID;
        this.beginDatum = beginDatum;
        this.beginTijd = beginTijd;
        this.eindDatum = eindDatum;
        this.eindTijd = eindTijd;
        this.klantNaam = klantNaam;
        this.projectNaam = projectNaam;
        this.onderwerpNaam = onderwerpNaam;
        this.commentaar = commentaar;
        this.goedgekeurd = goedgekeurd;
        this.persoonNaam = persoonNaam;
        this.isChanged = isChanged;
        this.projectId = projectId;
        this.klantId = klantId;
        this.onderwerpId = onderwerpId;
    }

    public Uren(int uurID, String begindatum, String einddatum, String begintijd, String eindtijd, String commentaar, boolean goedgekeurd, int persoonID, String klantnaam, String projectnaam, String onderwerpnaam, String personeelsnaam) {
        this.uurId = uurID;
        this.personeelID = persoonID;
        this.beginDatum = begindatum;
        this.beginTijd = begintijd;
        this.eindDatum = einddatum;
        this.eindTijd = eindtijd;
        this.klantNaam = klantnaam;
        this.projectNaam = projectnaam;
        this.onderwerpNaam = onderwerpnaam;
        this.commentaar = commentaar;
        this.goedgekeurd = goedgekeurd;
        this.persoonNaam = personeelsnaam;
    }

    public int getUurId() {
        return uurId;
    }

    public void setUurId(int uurId) {
        this.uurId = uurId;
    }

    public int getPersoneelID() {
        return personeelID;
    }

    public void setPersoneelID(int personeelID) {
        this.personeelID = personeelID;
    }

    public String getBeginDatum() {
        return beginDatum;
    }

    public void setBeginDatum(String beginDatum) {
        this.beginDatum = beginDatum;
    }

    public String getBeginTijd() {
        return beginTijd;
    }

    public void setBeginTijd(String beginTijd) {
        this.beginTijd = beginTijd;
    }

    public String getEindDatum() {
        return eindDatum;
    }

    public void setEindDatum(String eindDatum) {
        this.eindDatum = eindDatum;
    }

    public String getEindTijd() {
        return eindTijd;
    }

    public void setEindTijd(String eindTijd) {
        this.eindTijd = eindTijd;
    }

    public String getKlantNaam() {
        return klantNaam;
    }

    public void setKlantNaam(String klantNaam) {
        this.klantNaam = klantNaam;
    }

    public String getProjectNaam() {
        return projectNaam;
    }

    public void setProjectNaam(String projectNaam) {
        this.projectNaam = projectNaam;
    }

    public String getOnderwerpNaam() {
        return onderwerpNaam;
    }

    public void setOnderwerpNaam(String onderwerpNaam) {
        this.onderwerpNaam = onderwerpNaam;
    }

    public String getCommentaar() {
        return commentaar;
    }

    public void setCommentaar(String commentaar) {
        this.commentaar = commentaar;
    }

    public boolean isGoedgekeurd() {
        return goedgekeurd;
    }

    public void setGoedgekeurd(boolean goedgekeurd) {
        this.goedgekeurd = goedgekeurd;
    }

    public String getPersoonNaam() {
        return persoonNaam;
    }

    public void setPersoonNaam(String persoonNaam) {
        this.persoonNaam = persoonNaam;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }



}


