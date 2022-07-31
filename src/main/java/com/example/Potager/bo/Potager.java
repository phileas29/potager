package com.example.Potager.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Potager {

	@Id
	@GeneratedValue
	private Integer idPotager;

	@NotBlank
	private String location;
	@NotBlank
	private String nom;
	@NotNull(message= "salary may not be empty")
	@Range(min = 500)
	private int surface;
	@NotBlank
	private String ville;
	
	public Potager(String location, String nom, int surface, String ville) {
		super();
		this.location = location;
		this.nom = nom;
		this.surface = surface;
		this.ville = ville;
	}
	
}
