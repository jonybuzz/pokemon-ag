package edu.utn.frba.ia.pokemonag;

import org.jgap.Configuration;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;

/**
 * 
 *
 * @author Grupo 19 2017 1C
 */
public class Genotipo {

    public static Genotype crear(Configuration gaConf, Population pop, int numEvolutions) throws InvalidConfigurationException {
        // Now we need to construct the Genotype. This could otherwise be
        // accomplished more easily by writing
        // "Genotype genotype = Genotype.randomInitialGenotype(...)"
        Genotype genotype = new Genotype(gaConf, pop);
        int progress = 0;
        int percentEvolution = numEvolutions / 100;
        for (int i = 0; i < numEvolutions; i++) {
            genotype.evolve();
            // Print progress.
            // ---------------
            if (percentEvolution > 0 && i % percentEvolution == 0) {
                progress++;
                IChromosome fittest = genotype.getFittestChromosome();
                double fitness = fittest.getFitnessValue();
                System.out.println("El cromosoma mas apto es: " + fitness);
            }
        }
        return genotype;
    }

}
