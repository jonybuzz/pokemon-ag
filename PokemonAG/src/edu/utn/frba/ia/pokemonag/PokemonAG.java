package edu.utn.frba.ia.pokemonag;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;
import org.jgap.impl.BooleanGene;
import org.jgap.impl.DefaultConfiguration;

/**
 *
 * @author Grupo 19 2017 1C
 */
public class PokemonAG {

    public static void main(String[] args) throws InvalidConfigurationException {
        int cantidadGeneraciones = 500;
        int tamanioCromosoma = 4;
        int tamanioPoblacion = 50;
        
        Configuration configuracionGA = new DefaultConfiguration();
        configuracionGA.setPreservFittestIndividual(true);
        configuracionGA.setKeepPopulationSizeConstant(false);
        IChromosome cromosomaEquipoPokemon = new Chromosome(configuracionGA,
                new BooleanGene(configuracionGA), tamanioCromosoma);
        configuracionGA.setSampleChromosome(cromosomaEquipoPokemon);
        configuracionGA.setPopulationSize(tamanioPoblacion);
        configuracionGA.setFitnessFunction(new FuncionAptitud());


        Population pop = PoblacionPokemon.crear(configuracionGA, cromosomaEquipoPokemon);

        Genotype genotipo = Genotipo.crear(configuracionGA, pop, cantidadGeneraciones);
        
        IChromosome fittest = genotipo.getFittestChromosome();
        System.out.println("El cromosoma mas apto es: "
                + fittest.getFitnessValue());
    }


}
