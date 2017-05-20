package edu.utn.frba.ia.pokemonag.funcionaptitud;

import org.jgap.*;
import org.jgap.impl.*;

/**
 * Funcion de aptitud de un equipo de Pokemons
 *
 * @author Grupo 19 2017 1C
 */
public class FuncionAptitudEquipo extends FitnessFunction {

    /*
   * @param a_subject the Chromosome to be evaluated
   * @return defect rate of our problem
   *
     */
    @Override
    public double evaluate(IChromosome equipo) {
        int total = 0;

        for (int i = 0; i < equipo.size(); i++) {
            IntegerGene pokemon = (IntegerGene) equipo.getGene(i);
            total += pokemon.intValue();
        }

        return total;
    }
}
