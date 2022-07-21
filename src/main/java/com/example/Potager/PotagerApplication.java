package com.example.Potager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Potager.bll.PotagerManager;
import com.example.Potager.bo.Carre;
import com.example.Potager.bo.EnumExpo;
import com.example.Potager.bo.EnumSol;
import com.example.Potager.bo.Potager;

@SpringBootApplication
public class PotagerApplication implements CommandLineRunner {
	@Autowired
	PotagerManager potagerManager;
	
	public static void main(String[] args) {
		SpringApplication.run(PotagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		
		System.err.println("****************\nDEBUT AFFICHAGE\n****************");
		
		Potager p1 = new Potager("Lesquidic", "Jardins Philéasiens", 250000, "Gouenac'h");
		Potager p2 = new Potager("Toul ar C'Hoat", "Clément's Meadows", 800000, "Le Faou");
		
		potagerManager.addPotager(p1);
		potagerManager.addPotager(p2);

		
//		Carre c1 = new Carre(10000, EnumSol.HUMIFERE, EnumExpo.SOLEIL);
//		Carre c1 = new Carre(6000, EnumSol.CAILLOUTEUX, EnumExpo.SOLEIL);
		
		potagerManager.addCarreToPotager(p1, 10000, EnumSol.HUMIFERE, EnumExpo.SOLEIL);
		potagerManager.addCarreToPotager(p2, 6000, EnumSol.CAILLOUTEUX, EnumExpo.SOLEIL);
		
		System.err.println("****************\nLISTE POTAGERS AVEC CARRES :\n****************");

		potagerManager.finAllCarre().forEach(System.out::println);
		

		
		
		
		
		
		
	}

}
