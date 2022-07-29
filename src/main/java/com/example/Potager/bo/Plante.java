package com.example.Potager.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class Plante {
	@Id
	@GeneratedValue
	private Integer idPlante;
	
	public Plante(String nom, EnumType type, String variete, Integer surface) {
		super();
		this.nom = nom;
		this.type = type;
		this.variete = variete;
		this.surface = surface;
	}
	private String nom;
	private EnumType type;
	private String variete;
	private Integer surface;

}
