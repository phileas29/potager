package com.example.Potager.bo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class Plantation {
	
	@Id
	@GeneratedValue
	private Integer idPlantation;
	
	@ManyToOne
	private Plante plante;
	
	public Plantation(Plante plante, Carre carre, Integer qte, Date datePlantation, Date dateRecolte) {
		super();
		this.plante = plante;
		this.carre = carre;
		this.qte = qte;
		this.datePlantation = datePlantation;
		this.dateRecolte = dateRecolte;
	}
	@ManyToOne
	private Carre carre;
	
	private Integer qte;
	private Date datePlantation;
	private Date dateRecolte;

}
