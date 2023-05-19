package com.sip.ams.model;

//import java.util.Date;

import jakarta.persistence.*;


@Entity
@Table(name = "livre")
public class Livre {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

    private String titre;
    private String description;
    private String dateEcheance;
    private String etat;
	public Livre(Long id, String titre, String description, String dateEcheance, String etat) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.dateEcheance = dateEcheance;
		this.etat = etat;
	}
	public Livre() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateEcheance() {
		return dateEcheance;
	}
	public void setDateEcheance(String dateEcheance) {
		this.dateEcheance = dateEcheance;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
}
