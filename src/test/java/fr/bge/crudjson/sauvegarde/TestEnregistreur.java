package fr.bge.crudjson.sauvegarde;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.bge.crudjson.entites.Chaussure;
import fr.bge.crudjson.entites.Fabricant;
import fr.bge.crudjson.entites.Vetement;

public class TestEnregistreur {
	
	@Test
	public void testFonctionnementClassique() throws Exception {
		
		Enregistreur enregistreur = new Enregistreur();
		
		
		
		// On charge la liste actuelle des vetements
		List < Vetement> dressing1 = enregistreur.lireTous();
		
		// On instancie un nouveau vetement
		Chaussure chaussure = new Chaussure();
		chaussure.setTaille("42");
		chaussure.setCouleur("Rouge");
		chaussure.setLongeurLacet(25);
		
		Fabricant fabricant = new Fabricant();
		fabricant.setNom("Prada");
		 chaussure.setFabricant(fabricant);
				
	
		
		// On sauvegarde ce vetement 
		 
		 enregistreur.enregistrer(chaussure);
		
		// On verifie que la lsite a pris en compte ce vetement 
		 
		 List < Vetement > dressing2 = enregistreur.lireTous();
		
		 Assert.assertEquals("Le nouveau dressing a un element de plus ", dressing1.size() + 1, dressing2.size());
		// On modifie ce vetement on le sauvegarde
		 
		 
		 chaussure.setTaille("38");
		 enregistreur.enregistrer(chaussure);
		
		// On supprime le vetement
		
		 enregistreur.supprimer(chaussure);
		 
		// On verifie que la liste a pris en compte cette suppression
		 
		 List < Vetement> dressing3 = enregistreur.lireTous();
		 Assert.assertEquals("les deux dressing doivent avoir le meme nombre d'element", dressing1.size() , dressing3.size());
		
		
		
		
		
	}

}
