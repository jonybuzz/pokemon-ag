package edu.utn.frba.ia.pokemonag;

import edu.utn.frba.ia.pokemonag.funcionaptitud.FuncionAptitudEquipo;
import edu.utn.frba.ia.pokemonag.gen.PokemonIdGen;
import edu.utn.frba.ia.pokemonag.genotipo.Genotipo;
import edu.utn.frba.ia.pokemonag.poblacion.PoblacionEquipos;
import org.jgap.*;
import org.jgap.impl.*;

/**
 *
 * @author Grupo 19 2017 1C
 */
public class PokemonAG {

    public static void main(String[] args) throws InvalidConfigurationException {
        int cantidadGeneraciones = 500;
        int tamanioCromosoma = 4;
        int tamanioPoblacion = 50;

        Configuration config = new DefaultConfiguration();
        config.setPreservFittestIndividual(true);
        config.setKeepPopulationSizeConstant(false);

        Gene[] muestraGenesEquipo = new PokemonIdGen[tamanioCromosoma];

        PokemonIdGen pokemonIdGen = new PokemonIdGen(config);

        for (int i = 0; i < tamanioCromosoma; i++) {
            muestraGenesEquipo[i] = pokemonIdGen.newGene();
        }

        Chromosome equipoDeMuestra = new Chromosome(config, muestraGenesEquipo);
        config.setSampleChromosome(equipoDeMuestra);

        config.setPopulationSize(tamanioPoblacion);
        config.setFitnessFunction(new FuncionAptitudEquipo());

        Population poblacion = PoblacionEquipos
                .crearRandom(config, equipoDeMuestra);

        Genotype genotipo = Genotipo
                .crearYEvolucionar(config, poblacion, cantidadGeneraciones);

        IChromosome fittest = genotipo.getFittestChromosome();
        System.out.println("Fin del algoritmo");
        System.out.println("El cromosoma mas apto es: "
                + fittest.getFitnessValue());
    }

}
