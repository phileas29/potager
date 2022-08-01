package com.example.Potager.bll;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.Potager.bo.Carre;
import com.example.Potager.bo.Potager;
import com.example.Potager.dal.CarreDAO;


@Service
public class CarreManager extends CRUDManager<Carre, CarreDAO> {
	public List<Carre> findByPotager(Potager potager) {
		return dao.findByPotager(potager);
	}
	
	public List<Integer> countAllPlantationsByCarre(Potager p) {
		return dao.countAllPlantationsByCarre(p);
	}

	@Override
	@Transactional
	public Carre add(Carre entity) throws PotagerException {
		// CT001 : insert le carré si il y a de la place dans le potager
		// utilisation de stream pour montrer les streams
		if (entity.getPotager().getIdPotager() != null) { // si le potager est déja en base
			int surfaceUtilise = dao.findByPotager(entity.getPotager()).stream()
			.mapToInt(c -> c.getSurface())
			.sum();
			if( entity.getPotager().getSurface() < surfaceUtilise + entity.getSurface() ) {
				throw new PotagerException("Un carre de "+entity.getSurface()+"m2 ne peut s'insérer "
						+ "dans un potager occupé dont "+surfaceUtilise+"m2 sont déjà utilisé sur "
						+entity.getPotager().getSurface()+"m2");
			}
		}
		return super.add(entity);
	}
}
