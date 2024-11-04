package fr.udl.acl.conteneur;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;



public class ConteneurImplTest {
    
   @Test
    public void testAjouterUnElement() throws ErreurConteneur {
        Conteneur conteneur = new ConteneurImpl(5);
        conteneur.ajouter("element1");
        assertTrue(conteneur.contient("element1"));
    }

    @Test
    public void testAjouterDeuxElementsDifferents() throws ErreurConteneur {
        Conteneur conteneur = new ConteneurImpl(5);
        conteneur.ajouter("element1");
        conteneur.ajouter("element2");
        assertTrue(conteneur.contient("element1") && conteneur.contient("element2"));
    }

    @Test
    public void testAjouterNull() {
        Conteneur conteneur = new ConteneurImpl(5);
        assertThrows(ErreurConteneur.class, () -> {
            conteneur.ajouter(null);
        });
    }

    @Test
    public void testAjouterMemeElementDeuxFois() throws ErreurConteneur {
        Conteneur conteneur = new ConteneurImpl(5);
        conteneur.ajouter("element1");
        assertThrows(ErreurConteneur.class, () -> {
            conteneur.ajouter("element1");
        });
    }

    @Test
    public void testAjouterElementsEgaux() throws ErreurConteneur {
        Conteneur conteneur = new ConteneurImpl(5);
        String element1 = new String("duplicate");
        String element2 = new String("duplicate");
        conteneur.ajouter(element1);
        assertThrows(ErreurConteneur.class, () -> {
            conteneur.ajouter(element2);
        });
    }

    @Test
    public void testAjouterDansConteneurCapaciteLimitee() throws ErreurConteneur {
        Conteneur conteneur = new ConteneurImpl(2);
        conteneur.ajouter("element1");
        conteneur.ajouter("element2");
        assertTrue(conteneur.contient("element1") && conteneur.contient("element2"));
    }

    @Test
    public void testAjouterTropDElements() throws ErreurConteneur {
        Conteneur conteneur = new ConteneurImpl(1);
        conteneur.ajouter("element1");
        assertThrows(ErreurConteneur.class, () -> {
            conteneur.ajouter("element2");
        });
    }

    @Test
    public void testAjouterElementsDansConteneurEtHashSet() throws ErreurConteneur {
        Conteneur conteneur = new ConteneurImpl(5);
        HashSet<String> hashSet = new HashSet<>();


        String[] elements = {"element1", "element2", "element3", "element4"};
        for (String element : elements) {
            conteneur.ajouter(element);
            hashSet.add(element);
        }


        for (String element : hashSet) {
            assertTrue(conteneur.contient(element), "Conteneur should contain " + element);
        }
    }

        @Test
    public void testRetirerElementPresent() throws ErreurConteneur {
        Conteneur conteneur = new ConteneurImpl(5);
        conteneur.ajouter("element1");
        conteneur.retirer("element1");
        assertFalse(conteneur.contient("element1"));
    }

    @Test
    public void testRetirerElementNonPresent() {
        Conteneur conteneur = new ConteneurImpl(5);
        assertThrows(ErreurConteneur.class, () -> {
            conteneur.retirer("element1");
        });
    }

    @Test
    public void testRetirerApresPlusieursAjouts() throws ErreurConteneur {
        Conteneur conteneur = new ConteneurImpl(5);
        conteneur.ajouter("element1");
        conteneur.ajouter("element2");
        conteneur.ajouter("element3");
        conteneur.retirer("element2");
        assertFalse(conteneur.contient("element2"));
        assertTrue(conteneur.contient("element1") && conteneur.contient("element3"));
    }

        @Test
    public void testRedimensionnerAugmenterCapacite() throws ErreurConteneur {
        Conteneur conteneur = new ConteneurImpl(2);
        conteneur.ajouter("element1");
        conteneur.redimensionner(5);
        assertEquals(5, conteneur.capacite());
    }

    @Test
    public void testRedimensionnerAvecCapaciteActuelle() throws ErreurConteneur {
        Conteneur conteneur = new ConteneurImpl(3);
        conteneur.redimensionner(3);
        assertEquals(3, conteneur.capacite());
    }

    @Test
    public void testRedimensionnerConteneurVide() throws ErreurConteneur {
        Conteneur conteneur = new ConteneurImpl(2);
        conteneur.redimensionner(4);
        assertEquals(4, conteneur.capacite());
    }

    @Test
    public void testRedimensionnerCapaciteInsuffisante() {
        Conteneur conteneur = new ConteneurImpl(3);
        assertThrows(ErreurConteneur.class, () -> {
            conteneur.redimensionner(1);
        });
    }

    @Test
    public void testRedimensionnerAugmenterGrandeCapacite() throws ErreurConteneur {
        Conteneur conteneur = new ConteneurImpl(2);
        conteneur.redimensionner(10);
        assertEquals(10, conteneur.capacite());
    }


}




