package edu.utn.frba.ia.pokemonag;

import edu.utn.frba.ia.pokemonag.funcionaptitud.FuncionAptitudEquipo;
import edu.utn.frba.ia.pokemonag.genotipo.Genotipo;
import edu.utn.frba.ia.pokemonag.poblacion.PoblacionEquipos;
import org.jgap.*;
import org.jgap.impl.*;

/**
 *
 * @author Grupo 19 2017 1C
 */
public class PokemonAG {

    public static void main(String[] args) throws InvalidConfigurationException, UnsupportedRepresentationException {
        int cantidadGeneraciones = 500;
        int tamanioCromosoma = 3;
        int tamanioPoblacion = 50;

        Configuration config = new DefaultConfiguration();
        config.setPreservFittestIndividual(true);
        config.setKeepPopulationSizeConstant(false);

        CompositeGene[] muestraGenesEquipo = new CompositeGene[tamanioCromosoma];

        for (int i = 0; i < tamanioCromosoma; i++) {
            muestraGenesEquipo[i] = new CompositeGene(config, new IntegerGene(config));
            muestraGenesEquipo[i].addGene(new IntegerGene(config));
            muestraGenesEquipo[i].addGene(new IntegerGene(config));
            muestraGenesEquipo[i].addGene(new IntegerGene(config));
        }
        IChromosome equipoDeMuestra = new Chromosome(config, muestraGenesEquipo);
        System.out.println("Equipo de muestra:" + equipoDeMuestra);
        config.setSampleChromosome(equipoDeMuestra);

        config.setPopulationSize(tamanioPoblacion);
        config.setFitnessFunction(new FuncionAptitudEquipo());

        //mejores resultados que en default
        config.removeNaturalSelectors(true);
        config.addNaturalSelector(new WeightedRouletteSelector(config), false);
        config.getGeneticOperators().clear();
        config.addGeneticOperator(new CrossoverOperator(config, 0.1));
        config.addGeneticOperator(new SwappingMutationOperator(config, new DefaultMutationRateCalculator(config)));

        Population poblacion = PoblacionEquipos
                .crearRandom(config, equipoDeMuestra);

        Genotype genotipo = Genotipo
                .crearYEvolucionar(config, poblacion, cantidadGeneraciones);

        IChromosome fittest = genotipo.getFittestChromosome();
        System.out.println("Fin del algoritmo");
        System.out.println("El cromosoma mas apto es: " + fittest);

    }

}
