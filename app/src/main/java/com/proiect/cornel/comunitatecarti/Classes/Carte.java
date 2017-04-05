package com.proiect.cornel.comunitatecarti.Classes;

public class Carte {
    private int idCarte;
    private String denumire;
    private String autor;
    private String categorie;
    private double rating;
    private boolean disponibilitate;
    private int idUtilizator;

    public Carte(int idCarte, String denumire, String autor, String categorie, double rating, boolean disponibilitate, int idUtilizator) {
        this.idCarte = idCarte;
        this.denumire = denumire;
        this.autor = autor;
        this.categorie = categorie;
        this.rating = rating;
        this.disponibilitate = disponibilitate;
        this.idUtilizator = idUtilizator;
    }

    public Carte(int idCarte, String denumire, String autor, String categorie, double rating, boolean disponibilitate) {
        this.idCarte = idCarte;
        this.denumire = denumire;
        this.autor = autor;
        this.categorie = categorie;
        this.rating = rating;
        this.disponibilitate = disponibilitate;
    }

    public Carte(String denumire, String autor, String categorie, double rating, boolean disponibilitate, int idUtilizator) {
        this.denumire = denumire;
        this.autor = autor;
        this.categorie = categorie;
        this.rating = rating;
        this.disponibilitate = disponibilitate;
        this.idUtilizator = idUtilizator;
    }

    public int getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(int idCarte) {
        this.idCarte = idCarte;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public boolean isDisponibilitate() {
        return disponibilitate;
    }

    public void setDisponibilitate(boolean disponibilitate) {
        this.disponibilitate = disponibilitate;
    }

    public int getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(int idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "idCarte=" + idCarte +
                ", denumire='" + denumire + '\'' +
                ", autor='" + autor + '\'' +
                ", categorie='" + categorie + '\'' +
                ", rating=" + rating +
                ", disponibilitate=" + disponibilitate +
                ", idUtilizator=" + idUtilizator +
                '}';
    }
}
