package edu.utn.frba.ia.pokemonag.funcionaptitud;

import edu.utn.frba.ia.pokemonag.gen.Pokemon;
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

        int miembro1 = (int) equipoPokemon.getGene(0).getAllele();
        int miembro2 = (int) equipoPokemon.getGene(1).getAllele();
        int miembro3 = (int) equipoPokemon.getGene(2).getAllele();

        aptitud += aptitudDePokemon(miembro1);
        aptitud += aptitudDePokemon(miembro2);
        aptitud += aptitudDePokemon(miembro3);

    }

    private double aptitudDePokemon(int idPokemon) {
        Pokemon pokemon = new Pokemon(idPokemon);
        return pokemon.getAtaque() * pokemon.getAtaqueEspecial(); // placeholder
    }
}
