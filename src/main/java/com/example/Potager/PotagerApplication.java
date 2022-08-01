package com.example.Potager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Potager.bll.CarreManager;
import com.example.Potager.bll.PlantationManager;
import com.example.Potager.bll.PlanteManager;
import com.example.Potager.bll.PotagerManager;
import com.example.Potager.bo.Carre;
import com.example.Potager.bo.EnumExpo;
import com.example.Potager.bo.EnumSol;
import com.example.Potager.bo.EnumType;
import com.example.Potager.bo.Plantation;
import com.example.Potager.bo.Plante;
import com.example.Potager.bo.Potager;

@SpringBootApplication
public class PotagerApplication implements CommandLineRunner {
	@Autowired
	PotagerManager potagerManager;

	@Autowired
	CarreManager carreManager;

	@Autowired
	PlanteManager planteManager;
	
	@Autowired
	PlantationManager plantationManager;
	
	public static void main(String[] args) {
		SpringApplication.run(PotagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Random rnd = new Random();
		rnd.setSeed(System.currentTimeMillis());
		
		ArrayList<Potager> potagers = new ArrayList<Potager>();
		potagers.add(new Potager("Lesquidic", "Jardins Philéasiens", 250000, "Gouesnac'h"));
		potagers.add(new Potager("Toul ar C'Hoat", "Clément's Meadows", 800000, "Le Faou"));
		for (Potager potager : potagers)
			potagerManager.add(potager);
		
		ArrayList<Plante> plantes = new ArrayList<Plante>();
		plantes.add(new Plante("blette",EnumType.FEUILLE,"",50));
		plantes.add(new Plante("tomate",EnumType.FRUIT,"coeur de boeuf",75));
		plantes.add(new Plante("radis",EnumType.RACINE,"noir",25));
		plantes.add(new Plante("pinard",EnumType.FEUILLE,"géant d'hiver",20));
		plantes.add(new Plante("carotte",EnumType.RACINE,"chantenay",22));
		for (Plante plante : plantes)
			planteManager.add(plante);
		
		for (int i = 0; i < potagers.size(); i++) {
			for (int j = 0; j < rnd.nextInt(5,20); j++) {
				Carre c = new Carre(
					rnd.nextInt(1000,10000),
					EnumSol.values()[rnd.nextInt(0,EnumSol.values().length)],
					EnumExpo.values()[rnd.nextInt(0,EnumExpo.values().length)]
				);
				c.setPotager(potagers.get(i));
				carreManager.add(c);
			}
		}
		
		
		List<Carre> carres = carreManager.findAll();
		
		plantationManager.add(new Plantation(plantes.get(0), carres.get(0), 2, null, null));
		plantationManager.add(new Plantation(plantes.get(3), carres.get(4), 15, null, null));

		System.err.println("****************\nDEBUT AFFICHAGE\n****************");
		System.out.println("****************\nLISTE POTAGERS :\n****************");
		potagerManager.findAll().forEach(System.out::println);
		System.out.println("****************\nLISTE CARRES :\n****************");
		carreManager.findAll().forEach(System.out::println);
		System.out.println("****************\nLISTE PLANTES :\n****************");
		planteManager.findAll().forEach(System.out::println);
		
		System.err.println("****************\nFIN AFFICHAGE\n****************");
//		System.out.println(potagerManager.selectAllPotagerWithNumberCarres(potagerManager.findById(1)));
	}

}
