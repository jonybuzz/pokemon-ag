package edu.utn.frba.ia.pokemonag;

import edu.utn.frba.ia.pokemonag.funcionaptitud.FuncionAptitudEquipo;
import edu.utn.frba.ia.pokemonag.gen.EquipoGen;
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
        int cantidadGeneraciones = 1000;
        int tamanioCromosoma = 3;
        int tamanioPoblacion = 50;
        int aptitudMaximaDeCorte = 100;

        Configuration config = new DefaultConfiguration();
        config.setPreservFittestIndividual(true);
        config.setKeepPopulationSizeConstant(false);

        EquipoGen[] muestraGenesEquipo = new EquipoGen[tamanioCromosoma];

        for (int i = 0; i < tamanioCromosoma; i++) {
            muestraGenesEquipo[i] = new EquipoGen(config);
            muestraGenesEquipo[i].addGene(new IntegerGene(config, 0, 250));
            muestraGenesEquipo[i].addGene(new IntegerGene(config, 0, 250));
            muestraGenesEquipo[i].addGene(new IntegerGene(config, 0, 250));
            muestraGenesEquipo[i].addGene(new IntegerGene(config, 0, 250));
            muestraGenesEquipo[i].addGene(new IntegerGene(config, 0, 250));
        }
        IChromosome equipoDeMuestra = new Chromosome(config, muestraGenesEquipo);
        System.out.println("Equipo de muestra:" + equipoDeMuestra);
        config.setSampleChromosome(equipoDeMuestra);

        config.setPopulationSize(tamanioPoblacion);
        config.setFitnessFunction(new FuncionAptitudEquipo(config, aptitudMaximaDeCorte));

        //mejores resultados que en default
        config.removeNaturalSelectors(true);
        config.addNaturalSelector(new WeightedRouletteSelector(config), false);
        config.getGeneticOperators().clear();
        config.addGeneticOperator(new CrossoverOperator(config, 5));
//        la mutacion no funciona hasta que tenga la matriz de efectividad completa
//        config.addGeneticOperator(new GaussianMutationOperator(config));

        Population poblacion = PoblacionEquipos
                .crearRandom(config, equipoDeMuestra);

        Genotype genotipo = Genotipo
                .crearYEvolucionar(config, poblacion, cantidadGeneraciones);

        IChromosome fittest = genotipo.getFittestChromosome();
        System.out.println("Fin del algoritmo");
        System.out.println("El cromosoma mas apto es: " + fittest);

    }

}
