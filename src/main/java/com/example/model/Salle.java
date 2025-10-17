package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "salles")
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false)
    private String nom;

    @NotNull(message = "La capacité est obligatoire")
    @Min(value = 1, message = "La capacité minimum est de 1 personne")
    @Column(nullable = false)
    private Integer capacite;

    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    @Column(length = 500)
    private String description;

    @Column(name = "batiment")
    private String batiment;

    @Column(name = "etage")
    private Integer etage;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "salle_equipement",
            joinColumns = @JoinColumn(name = "salle_id"),
            inverseJoinColumns = @JoinColumn(name = "equipement_id")
    )
    private Set<Equipement> equipements = new HashSet<>();

    // --- Constructeurs ---
    public Salle() {}

    public Salle(String nom, Integer capacite, String description, String batiment, Integer etage) {
        this.nom = nom;
        this.capacite = capacite;
        this.description = description;
        this.batiment = batiment;
        this.etage = etage;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Integer getCapacite() { return capacite; }
    public void setCapacite(Integer capacite) { this.capacite = capacite; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getBatiment() { return batiment; }
    public void setBatiment(String batiment) { this.batiment = batiment; }

    public Integer getEtage() { return etage; }
    public void setEtage(Integer etage) { this.etage = etage; }

    public List<Reservation> getReservations() { return reservations; }
    public void setReservations(List<Reservation> reservations) { this.reservations = reservations; }

    public Set<Equipement> getEquipements() { return equipements; }
    public void setEquipements(Set<Equipement> equipements) { this.equipements = equipements; }

    // --- Méthodes utilitaires pour gérer les relations ---
    public void addEquipement(Equipement equipement) {
        this.equipements.add(equipement);
        equipement.getSalles().add(this);
    }

    public void removeEquipement(Equipement equipement) {
        this.equipements.remove(equipement);
        equipement.getSalles().remove(this);
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setSalle(this);
    }

    public void removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.setSalle(null);
    }
    public Salle(String nom, Integer capacite) {
        this.nom = nom;
        this.capacite = capacite;
    }

}
