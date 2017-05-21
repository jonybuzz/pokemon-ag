package edu.utn.frba.ia.pokemonag.poblacion;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;
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

        BufferedReader reader = null;
        List<String> linesArray = new ArrayList<String>();
        try {
            File file = new File("pokemons.txt");
            reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                linesArray.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Crear poblacion leyendo lineas random del archivo txt
        for (int i = 0; i < tamanioPoblacion; i++) {

            CompositeGene[] nuevosGenes = new CompositeGene[3];

            for (int j = 0; j < nuevosGenes.length; j++) {
                nuevosGenes[j] = new CompositeGene(config, new IntegerGene(config));
                int randomNum = ThreadLocalRandom.current().nextInt(0, 16 + 1);
                String randomNumberLine = linesArray.get(randomNum);
                nuevosGenes[j].setValueFromPersistentRepresentation(randomNumberLine);
            }

            IChromosome cromosomaRandom = new Chromosome(config, nuevosGenes);
            cromosomaRandom.setGenes(nuevosGenes);
            poblacion.addChromosome(cromosomaRandom);
        }
        return poblacion;
    }

}
