package iset.tp.tp7;

public class Resultat {
    private int id;
    private String nom;
    private String prenom;
    private Float moyenne;

    public Resultat(int id, String nom, String prenom, Float moyenne) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = moyenne;
    }
    public Resultat(String nom, String prenom, Float moyenne) {
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = moyenne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Float moyenne) {
        this.moyenne = moyenne;
    }
}
