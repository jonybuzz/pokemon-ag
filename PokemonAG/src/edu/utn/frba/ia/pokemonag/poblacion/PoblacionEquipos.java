package edu.utn.frba.ia.pokemonag.poblacion;

import edu.utn.frba.ia.pokemonag.cromosoma.Equipo;
import edu.utn.frba.ia.pokemonag.gen.PokemonIdGen;
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
public class PoblacionEquipos {

    /**
     * Completely initialize the population with custom code.
     *
     * @param config
     * @param equipoDeMuestra
     * @return
     * @throws InvalidConfigurationException
     */
    public static Population crearRandom(Configuration config,
            IChromosome equipoDeMuestra) throws InvalidConfigurationException {

        int tamanioPoblacion = config.getPopulationSize();
        Population poblacion = new Population(config, tamanioPoblacion);
        for (int i = 0; i < tamanioPoblacion; i++) {

            Gene[] genesDeMuestra = equipoDeMuestra.getGenes();
            PokemonIdGen[] nuevosGenes = new PokemonIdGen[genesDeMuestra.length];
            RandomGenerator generator = config.getRandomGenerator();
            
            for (int j = 0; j < nuevosGenes.length; j++) {

                nuevosGenes[j] = (PokemonIdGen) genesDeMuestra[j].newGene();

                // Set the gene's value (allele) to a random value.
                // ------------------------------------------------
                nuevosGenes[j].setToRandomValue(generator);

            }
            IChromosome cromosomaRandom = new Equipo(config, nuevosGenes);
            cromosomaRandom.setGenes(nuevosGenes);
            poblacion.addChromosome(cromosomaRandom);
        }
        return poblacion;
    }

}
