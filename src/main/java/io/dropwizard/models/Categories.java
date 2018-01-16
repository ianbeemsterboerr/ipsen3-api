package io.dropwizard.models;

public class Categories {
    private String klantnaam;
    private String projectnaam;
    private String onderwerpnaam;

    public Categories(String klantnaam, String projectnaam, String onderwerpnaam) {
        this.klantnaam = klantnaam;
        this.projectnaam = projectnaam;
        this.onderwerpnaam = onderwerpnaam;
    }

    public Categories(){

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
}
