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
    Pokemon[] equipoDeGimnasio;

    /*
   * @param a_subject the Chromosome to be evaluated
   * @return defect rate of our problem
   *
     */

    public FuncionAptitudEquipo() {
        this.aptitud = 0;
        this.equipoDeGimnasio = this.crearEquipoDeGimnasio(3);
    }

    @Override
    public double evaluate(IChromosome equipoPokemon) {
        aptitudDePokemon(equipoPokemon);
        return aptitud;
    }


    private Pokemon[] crearEquipoDeGimnasio(Integer tamanioCromosoma){
        Pokemon lvl1 = new Pokemon(56);
        Pokemon lvl2 = new Pokemon(121);
        Pokemon lvl3 = new Pokemon(23);

        Pokemon[] equipo = new Pokemon[tamanioCromosoma];
        equipo[0] = lvl1;
        equipo[1] = lvl2;
        equipo[2] = lvl3;

        return equipo;
    }
    private void aptitudDePokemon(IChromosome _equipoDesafiante) {
        Pokemon[] equipoDesafiante = this.getEquipoDesafiante(_equipoDesafiante);

        // i equipo desafiante - j equipo de gimnasio

        /* Cada pokemon desafiante evalúa como le va a ir con los 3 del gimnasio
            aptitudesIndividuales[] tiene la aptitud de un desafiante contra los 3 del gimnasio
            Se suman las aptitudes individuales para sacar la aptitud de ese pokemon desafiante total
            Se guardan en aptitudesGrupales[] las aptitudes del equipo desafiante

            Se dividen por 3 las aptitudes grupales de cada pokemon desafiante y se suman esos cocientes
            Cada pokemon representa  1/3 de la aptitud final del equipo
         */

         double[] aptitudesGrupales = new double[3];

         for(int i=0; i<3 ; i++) {
             double[] aptitudesInviduales = new double[3];
             for (int k = 0; k < 3; k++) {
                 aptitudesInviduales[k] = 0;
             }

             for (int j = 0; j < 3; j++) {
                 aptitudesInviduales[i] = peleaPokemon(equipoDesafiante[i], equipoDeGimnasio[j]);
                 aptitudesGrupales[i] += aptitudesInviduales[i];
             }
             aptitudesGrupales[i] /= 3;
         }

           for (int k = 0; k < 3; k++) {
              aptitud += aptitudesGrupales[k];
           }
    }

    private double peleaPokemon(Pokemon desafiante, Pokemon gimnasio) {
        double aptitudDesafiante = aptitud(desafiante,gimnasio);
        double aptitudGimnasio = aptitud(gimnasio,desafiante);

        return aptitudDesafiante-aptitudGimnasio;
    }

    private double aptitud(Pokemon pokemon1, Pokemon pokemon2) {
       return pokemon1.ataque * pokemon2.defensa * 0.25 +
               pokemon1.ataqueEspecial * pokemon2.defensaEspecial * 0.25 +
               pokemon1.tipo * pokemon2.tipo;
    }

    private Pokemon[] getEquipoDesafiante(IChromosome _equipoDesafiante) {
        Pokemon[] equipoDesafiante = new Pokemon[3];

        for(int i=0; i<3 ; i++){
          int idPokemon = (int) _equipoDesafiante.getGene(i).getAllele();
          Pokemon pokemon = new Pokemon(idPokemon);
          equipoDesafiante[i] = pokemon;
        }
        return equipoDesafiante;
    }
}
