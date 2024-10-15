package fr.diginamic.recensement.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.RechercheException;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Cas d'utilisation: affichage des N villes les plus peuplées d'une département
 * donné
 * 
 * @author DIGINAMIC
 *
 */
public class RechercheVillesPlusPeupleesDepartement extends MenuService {

	@Override
	public void traiter(Recensement recensement, Scanner scanner) throws RechercheException {

		System.out.println("Veuillez saisir un numéro de département:");
		String nomDept = scanner.nextLine();
		if (!nomDept.matches("([1-9])|([1-8]\\d)|(9[0-5])|(97[1-4])|(2[A-B])")) {
			throw new RechercheException("Le code de département n'existe pas");
		}

		System.out.println("Veuillez saisir un nombre de villes:");
		String nbVillesStr = scanner.nextLine();
		int nbVilles = Integer.parseInt(nbVillesStr);

		List<Ville> villesDept = new ArrayList<Ville>();

		List<Ville> villes = recensement.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(nomDept)) {
				villesDept.add(ville);
			}
		}

		Collections.sort(villesDept, new EnsemblePopComparateur(false));

		if (villesDept.size() > 0) {
			System.out.println("Les " + nbVilles + " villes les plus peuplées du département " + nomDept + " :");
			for (int i = 0; i < nbVilles; i++) {
				Ville ville = villesDept.get(i);
				System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
			}
		} 
	}

}
