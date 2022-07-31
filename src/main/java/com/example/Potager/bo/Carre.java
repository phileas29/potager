package com.example.Potager.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Carre {

	@Id
	@GeneratedValue
	private Integer idCarre;
	
	@NotNull(message= "must be 500 <")
	@Range(min = 500)
	private int surface;
	private EnumSol typeSol;
	private EnumExpo typeExpo;
	
	@ManyToOne
	private Potager potager;

	public Carre(int surface, EnumSol typeSol, EnumExpo typeExpo) {
		super();
		this.surface = surface;
		this.typeSol = typeSol;
		this.typeExpo = typeExpo;
	}


}
