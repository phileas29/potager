package com.example.Potager.bo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datePlantation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateRecolte;

}
