package edu.utn.frba.ia.pokemonag.poblacion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;
import org.jgap.UnsupportedRepresentationException;
import org.jgap.impl.CompositeGene;
import org.jgap.impl.IntegerGene;

/**
 * Poblacion de equipos Pokemon
 *
 * @author Grupo 19 2017 1C
 */
public class PoblacionEquipos {

    /**
     * Inicializa población con individuos al azar.
     *
     * @param config
     * @param equipoDeMuestra
     * @return
     * @throws InvalidConfigurationException
     * @throws org.jgap.UnsupportedRepresentationException
     */
    public static Population crearRandom(Configuration config, IChromosome equipoDeMuestra)
            throws InvalidConfigurationException, UnsupportedRepresentationException {

        int tamanioPoblacion = config.getPopulationSize();
        Population poblacion = new Population(config, tamanioPoblacion);

        //Crear poblacion leyendo lineas random del archivo txt
        for (int i = 0; i < tamanioPoblacion; i++) {

            CompositeGene[] nuevosGenes = new CompositeGene[3];

            for (int j = 0; j < nuevosGenes.length; j++) {

                nuevosGenes[j] = new CompositeGene(config, new IntegerGene(config));
                int randomNum = ThreadLocalRandom.current().nextInt(0, 16 + 1);
                
                try (final Stream<String> lines = Files.lines(Paths.get("pokemons.txt"))) {
                    String stringStats = lines.skip(randomNum).findFirst().get();
                    nuevosGenes[j].setValueFromPersistentRepresentation(stringStats);
                } catch (IOException ex) {
                }
            }

            IChromosome cromosomaRandom = new Chromosome(config, nuevosGenes);
            cromosomaRandom.setGenes(nuevosGenes);
            poblacion.addChromosome(cromosomaRandom);
        }
        return poblacion;
    }

}
