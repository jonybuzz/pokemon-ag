package edu.utn.frba.ia.pokemonag.genotipo;

import org.jgap.Configuration;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;

/**
 *
 * @author Grupo 19 2017 1C
 */
public class Genotipo {

    public static Genotype crearYEvolucionar(Configuration config,
            Population poblacion, int numEvolutions)
            throws InvalidConfigurationException {

        Genotype genotipo = new Genotype(config, poblacion);
        int progreso = 0;
        int percentEvolution = numEvolutions / 100;
        for (int i = 0; i < numEvolutions; i++) {
            genotipo.evolve();
            // Print progress.
            // ---------------
            if (i % percentEvolution == 0) {
                progreso++;
                IChromosome fittest = genotipo.getFittestChromosome();
                System.out.println("Progreso: " + progreso + "%");
                System.out.println("Mas apto de la generacion " + i + ": " + fittest);
            }
        }
        return genotipo;
    }

}
