package edu.utn.frba.ia.pokemonag;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;
import org.jgap.RandomGenerator;

/**
 * Poblacion de equipos Pokemon
 *
 * @author Grupo 19 2017 1C
 */
public class PoblacionPokemon {

    /**
     * Completely initialize the population with custom code.
     * Notice that we assign the double number of Genes to
     * each other Chromosome.
     * 
     * @param configuracionGA
     * @param cromosomaModelo
     * @return
     * @throws InvalidConfigurationException
     */
    public static Population crear(Configuration configuracionGA,
            IChromosome cromosomaModelo) throws InvalidConfigurationException {
        
        int populationSize = configuracionGA.getPopulationSize();
        Population poblacion = new Population(configuracionGA, populationSize);
        for (int i = 0; i < populationSize; i++) {
            int mult;
            // Every second Chromosome has double the number of Genes.
            // -------------------------------------------------------
            if (i % 2 == 0) {
                mult = 1;
            } else {
                mult = 2;
            }
            Gene[] sampleGenes = cromosomaModelo.getGenes();
            Gene[] newGenes = new Gene[sampleGenes.length * mult];
            RandomGenerator generator = configuracionGA.getRandomGenerator();
            for (int j = 0; j < newGenes.length; j = j + mult) {
                // We use the newGene() method on each of the genes in the
                // sample Chromosome to generate our new Gene instances for
                // the Chromosome we're returning. This guarantees that the
                // new Genes are setup with all of the correct internal state
                // for the respective gene position they're going to inhabit.
                // ----------------------------------------------------------
                newGenes[j] = sampleGenes[j / mult].newGene();
                // Set the gene's value (allele) to a random value.
                // ------------------------------------------------
                newGenes[j].setToRandomValue(generator);
                if (mult > 1) {
                    newGenes[j + 1] = sampleGenes[j / 2].newGene();
                    // Set the gene's value (allele) to a random value.
                    // ------------------------------------------------
                    newGenes[j + 1].setToRandomValue(generator);
                }
            }
            IChromosome cromosomaRandom = Chromosome.randomInitialChromosome(configuracionGA);
            cromosomaRandom.setGenes(newGenes);
            poblacion.addChromosome(cromosomaRandom);
        }
        return poblacion;
    }

}
