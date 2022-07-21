package com.example.Potager.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Potager {
	
	@Id
	@GeneratedValue
	private Integer idPotager;
	
	private String location;
	private String nom;
	private int surface;
	private String ville;
	
	public Potager(String location, String nom, int surface, String ville) {
		super();
		this.location = location;
		this.nom = nom;
		this.surface = surface;
		this.ville = ville;
	}
	
}
