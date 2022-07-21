package com.example.Potager.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

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
public class Carre {
	
	@Id
	@GeneratedValue
	private Integer idCarre;
	
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
