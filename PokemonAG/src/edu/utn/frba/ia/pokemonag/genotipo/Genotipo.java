package edu.utn.frba.ia.pokemonag.genotipo;

import org.jgap.Configuration;
import org.jgap.Genotype;
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
//        int progreso = 0;
//        int percentEvolution = numEvolutions / 100;
//        for (int i = 0; i < numEvolutions; i++) {
//            genotipo.evolve();
//            // Print progress.
//            // ---------------
//            if (percentEvolution > 0 && i % percentEvolution == 0) {
//                progreso++;
//                IChromosome fittest = genotipo.getFittestChromosome();
//                double fitness = fittest.getFitnessValue();
//                System.out.println("Progreso: " + progreso);
//                System.out.println("El cromosoma mas apto es: " + fitness);
//            }
//        }
        return genotipo;
    }

}
