package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.RechercheException;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 *
 * @author DIGINAMIC
 */
public class RecherchePopulationBorneService extends MenuService {

    @Override
    public void traiter(Recensement rec, Scanner scanner) throws RechercheException {

        System.out.println("Quel est le code du département recherché ? ");
        String choix = scanner.nextLine();
        if (!choix.matches("([1-9])|([1-8]\\d)|(9[0-5])|(97[1-4])|(2[A-B])")) {
            throw new RechercheException("Le code de département n'existe pas");
        }

        System.out.println("Choississez une population minimum (en milliers d'habitants): ");
        String saisieMin = scanner.nextLine();
        if (!NumberUtils.isParsable(saisieMin)) {
            throw new RechercheException("Une lettre a été saisie au lieu d'un chiffre");
        }

        if (Integer.parseInt(saisieMin) < 0) {
            throw new RechercheException("Le minimum est plus grand que le maximum");
        }

        System.out.println("Choississez une population maximum (en milliers d'habitants): ");
        String saisieMax = scanner.nextLine();

        if (!NumberUtils.isParsable(saisieMax)) {
            throw new RechercheException("Une lettre a été saisie au lieu d'un chiffre");
        }

        if (Integer.parseInt(saisieMin) > Integer.parseInt(saisieMax)) {
            throw new RechercheException("Le minimum est plus grand que le maximum");
        }

        if (Integer.parseInt(saisieMax) < 0) {
            throw new RechercheException("Le maximum est inférieur a 0");
        }

        int min = Integer.parseInt(saisieMin) * 1000;
        int max = Integer.parseInt(saisieMax) * 1000;

        List<Ville> villes = rec.getVilles();
        for (Ville ville : villes) {
            if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
                if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
                    System.out.println(ville);
                }
            }
        }
    }

}
