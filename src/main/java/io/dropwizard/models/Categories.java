package io.dropwizard.models;

public class Categories {
    private String klantnaam;
    private String projectnaam;
    private String onderwerpnaam;
    private int klant_ID;
    private int project_ID;

    public Categories(String klantnaam, String projectnaam, String onderwerpnaam) {
        this.klantnaam = klantnaam;
        this.projectnaam = projectnaam;
        this.onderwerpnaam = onderwerpnaam;
    }

    public Categories(int klant_ID, String projectnaam, String onderwerpnaam) {
        this.klant_ID = klant_ID;
        this.projectnaam = projectnaam;
        this.onderwerpnaam = onderwerpnaam;
    }

    public Categories(int project_ID, String onderwerpnaam) {
        this.project_ID = project_ID;
        this.onderwerpnaam = onderwerpnaam;

    }

    public String getKlantnaam() {
        return klantnaam;
    }

    public void setKlantnaam(String klantnaam) {
        this.klantnaam = klantnaam;
    }

    public String getProjectnaam() {
        return projectnaam;
    }

    public void setProjectnaam(String projectnaam) {
        this.projectnaam = projectnaam;
    }

    public String getOnderwerpnaam() {
        return onderwerpnaam;
    }

    public void setOnderwerpnaam(String onderwerpnaam) {
        this.onderwerpnaam = onderwerpnaam;
    }

    public int getKlant_ID() {
        return klant_ID;
    }

    public void setKlant_ID(int klant_ID) {
        this.klant_ID = klant_ID;
    }

    public int getProject_ID() {
        return project_ID;
    }

    public void setProject_ID(int project_ID) {
        this.project_ID = project_ID;
    }
}
