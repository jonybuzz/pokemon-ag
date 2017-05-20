package edu.utn.frba.ia.pokemonag.funcionaptitud;

import edu.utn.frba.ia.pokemonag.gen.Pokemon;
import java.util.Vector;
import org.jgap.*;

/**
 * Funcion de aptitud de un equipo de Pokemons
 *
 * @author Grupo 19 2017 1C
 */
public class FuncionAptitudEquipo extends FitnessFunction {

    double aptitud;

    /*
   * @param a_subject the Chromosome to be evaluated
   * @return defect rate of our problem
   *
     */

    public FuncionAptitudEquipo() {
        this.aptitud = 0;
    }

    @Override
    public double evaluate(IChromosome equipoPokemon) {

        evaluarEquipo(equipoPokemon);
        return aptitud;
    }

    private void evaluarEquipo(IChromosome equipoPokemon) {

        Vector allelo1 = (Vector)equipoPokemon.getGene(0).getAllele();
        Vector allelo2 = (Vector)equipoPokemon.getGene(1).getAllele();
        Vector allelo3 = (Vector)equipoPokemon.getGene(2).getAllele();

        aptitud += aptitudDePokemon(allelo1);
        aptitud += aptitudDePokemon(allelo2);
        aptitud += aptitudDePokemon(allelo3);

    }

    private double aptitudDePokemon(Vector pokemonStats) {
        Pokemon pokemon = new Pokemon(pokemonStats);
        return pokemon.getAtaque() * pokemon.getTipo(); // placeholder
    }
}
