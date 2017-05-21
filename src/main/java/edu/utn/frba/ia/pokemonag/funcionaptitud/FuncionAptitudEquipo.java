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

    private double aptitud;
    private final int aptitudMaximaDeCorte;
    public static Pokemon[] EQUIPO_DE_GIMNASIO;
    public static double[][] MATRIZ_EFECTIVIDAD = inicializarMatriz();

    public FuncionAptitudEquipo(Configuration config, int aptitudMaximaDeCorte) {
        this.aptitud = 0;
        this.aptitudMaximaDeCorte = aptitudMaximaDeCorte;
        EQUIPO_DE_GIMNASIO = this.crearEquipoDeGimnasio(3, config);
    }

    @Override
    public double evaluate(IChromosome equipoPokemon) {
        aptitudDeEquipoPokemon(equipoPokemon);
        aptitud /= 10;
        if(aptitud<0) aptitud = 0;
        if(aptitud>aptitudMaximaDeCorte) aptitud = aptitudMaximaDeCorte;
        return aptitud;
    }

    private Pokemon[] crearEquipoDeGimnasio(Integer tamanioCromosoma, Configuration config){
        Pokemon lvl1 = new Pokemon(121, config);
        Pokemon lvl2 = new Pokemon(23, config);
        Pokemon lvl3 = new Pokemon(75, config);

        Pokemon[] equipo = new Pokemon[tamanioCromosoma];
        equipo[0] = lvl1;
        equipo[1] = lvl2;
        equipo[2] = lvl3;

        return equipo;
    }
    private void aptitudDeEquipoPokemon(IChromosome _equipoDesafiante) {
        Pokemon[] equipoDesafiante = this.getEquipoDesafiante(_equipoDesafiante);

        // i equipo desafiante - j equipo de gimnasio

        /* Cada pokemon desafiante evalúa como le va a ir con los 3 del gimnasio
            aptitudesIndividuales[] tiene la aptitud de un desafiante contra los 3 del gimnasio
            Se suman las aptitudes individuales para sacar la aptitud de ese pokemon desafiante total
            Se guardan en aptitudesGrupales[] las aptitudes del equipo desafiante

            Se dividen por 3 las aptitudes grupales de cada pokemon desafiante y se suman esos cocientes
         */

         double[] aptitudesGrupales = new double[3];

         for(int i=0; i<3 ; i++) {

             for (int j = 0; j < 3; j++) {
                 double aptitudIndividual = peleaPokemon(equipoDesafiante[i], EQUIPO_DE_GIMNASIO[j]);
                 aptitudesGrupales[i] += aptitudIndividual;
             }
         }

           for (int k = 0; k < 3; k++) {
              aptitud += aptitudesGrupales[k];
           }
    }

    private double peleaPokemon(Pokemon desafiante, Pokemon gimnasio) {
        double aptitudDesafiante = daño(desafiante,gimnasio);
        double aptitudGimnasio = daño(gimnasio,desafiante);

        return aptitudDesafiante - aptitudGimnasio;
    }

    private double daño(Pokemon pokemon1, Pokemon pokemon2) {
        double varianza = (Math.random() * (100 - 85)) + 85;
        return 0.01 * varianza
                * ((efectividadDelAtaque(pokemon1.getTipo(), pokemon2.getTipo()) * ((pokemon1.getAtaqueEspecial() * pokemon1.getAtaque()) / (25 * (pokemon2.getDefensa() + pokemon2.getDefensaEspecial()) / 2) + 2))
                - (efectividadDelAtaque(pokemon2.getTipo(), pokemon1.getTipo()) * ((pokemon2.getAtaqueEspecial() * pokemon2.getAtaque()) / (25 * (pokemon1.getDefensa() + pokemon1.getDefensaEspecial()) / 2) + 2)));
    }

    private double efectividadDelAtaque(int tipoDelAtacante, int tipoDelDefensor) {
        return MATRIZ_EFECTIVIDAD[tipoDelAtacante][tipoDelDefensor];
    }

    private Pokemon[] getEquipoDesafiante(IChromosome _equipoDesafiante) {
        Pokemon[] equipoDesafiante = new Pokemon[3];

        for(int i=0; i<3 ; i++){
            Vector idPokemon = (Vector) _equipoDesafiante.getGene(i).getAllele();
            Pokemon pokemon = new Pokemon(idPokemon);
          equipoDesafiante[i] = pokemon;
        }
        return equipoDesafiante;
    }


    private static double[][] inicializarMatriz() {
        double[][] efectividad = new double[12][12];
        for(int i=0; i<12 ; i++){
            for(int j=0 ; j<12 ; j++){
                efectividad[i][j] = 1;
            }
        }
        
        //efectividad[tipoDef][tipoAtk]
        efectividad[0][3] = 0.8;
        efectividad[0][5] = 0.8;
        efectividad[1][0] = 1.25;
        efectividad[1][2] = 0.8;
        efectividad[1][3] = 1.25;
        efectividad[1][5] = 0.8;
        efectividad[1][9] = 0.8;
        efectividad[1][10] = 1.25;
        efectividad[2][1] = 1.25;
        efectividad[2][3] = 0.8;
        efectividad[2][4] = 1.25;
        efectividad[2][8] = 0.8;
        efectividad[3][1] = 0.8;
        efectividad[3][2] = 1.25;
        efectividad[3][7] = 1.25;
        efectividad[3][10] = 1.25;
        efectividad[4][2] = 0.8;
        efectividad[4][3] = 1.25;
        efectividad[4][4] = 0.8;
        efectividad[4][6] = 0.8;
        efectividad[4][7] = 1.25;
        efectividad[4][11] = 1.25;
        efectividad[5][0] = 0.8;
        efectividad[5][4] = 1.25;
        efectividad[5][9] = 1.25;
        efectividad[6][3] = 0.8;
        efectividad[6][4] = 1.25;
        efectividad[6][6] = 0.8;
        efectividad[6][7] = 0.8;
        efectividad[6][10] = 1.25;
        efectividad[6][11] = 0.8;
        efectividad[7][3] = 1.25;
        efectividad[7][4] = 0.8;
        efectividad[7][6] = 1.25;
        efectividad[7][11] = 0.8;
        efectividad[8][2] = 1.25;
        efectividad[8][3] = 0.8;
        efectividad[8][4] = 0.8;
        efectividad[8][7] = 1.25;
        efectividad[8][8] = 0.8;
        efectividad[8][11] = 0.8;
        efectividad[9][1] = 1.25;
        efectividad[9][5] = 1.25;
        efectividad[9][9] = 1.25;
        efectividad[10][2] = 1.25;
        efectividad[10][4] = 1.25;
        efectividad[10][6] = 0.8;
        efectividad[10][7] = 0.8;
        efectividad[10][10] = 0.8;
        efectividad[10][11] = 1.25;
        efectividad[11][11] = 1.25;
        return efectividad;
    }

}
