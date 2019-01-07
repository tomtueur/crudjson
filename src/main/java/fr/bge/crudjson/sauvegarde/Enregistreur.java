package fr.bge.crudjson.sauvegarde;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bge.crudjson.entites.Chaussure;
import fr.bge.crudjson.entites.Chemise;
import fr.bge.crudjson.entites.Pantalon;
import fr.bge.crudjson.entites.Vetement;

public class Enregistreur {

	private final static String NOM_REPERTOIRE_STOCKAGE = "c:/tempobge/crudjson";
	private File repertoireStockage;

	public Enregistreur() {

		repertoireStockage = new File(NOM_REPERTOIRE_STOCKAGE);
		if (!repertoireStockage.exists()) {
			repertoireStockage.mkdirs();

		}
	}

	public void enregistrer(Vetement vetement) throws Exception {

		String nomFichier = "";

		if (vetement instanceof Chaussure) {
			nomFichier = "Chaussure_";
		}

		if (vetement instanceof Pantalon) {
			nomFichier = "Pantalon_";
		}
		if (vetement instanceof Chemise) {
			nomFichier = "Chemise_";
		}

		if (vetement.getId() == null) {
			vetement.setId(UUID.randomUUID().toString());
		}

		nomFichier = nomFichier + vetement.getId();

		File fichierJson = new File(repertoireStockage, nomFichier + ".json");
		ObjectMapper mapper = new ObjectMapper();

		mapper.writeValue(fichierJson, vetement);
	}

	public void supprimer(Vetement vetement) {
		if (vetement.getId() == null) {
			return;
		} else {
			File[] listFiles = repertoireStockage.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				File fichier = listFiles[i];
				if (fichier.getName().contains(vetement.getId())) {
					fichier.delete();
				}
			}
		}
	}

	public List<Vetement> lireTous() throws Exception {
		List<Vetement> resultat = new ArrayList<Vetement>();

		// On parcout les fichiers .json et on les convertit en java
		File[] fichiers = repertoireStockage.listFiles();
		for (int i = 0; i < fichiers.length; i++) {
			File fichier = fichiers[i];
			if (fichier.getName().endsWith(".json")) {
				ObjectMapper mapper = new ObjectMapper();
				if (fichier.getName().startsWith("Chaussure_")) {
					resultat.add(mapper.readValue(fichier, Chaussure.class));
				} else if (fichier.getName().startsWith("Chemise_")) {
					resultat.add(mapper.readValue(fichier, Chemise.class));
				} else if (fichier.getName().startsWith("Pantalon_")) {
					resultat.add(mapper.readValue(fichier, Pantalon.class));
				}

			}
		}

		return resultat;
	}

}
