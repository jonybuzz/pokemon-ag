
import org.jgap.*;

public class PokeFitnessFunction extends FitnessFunction {

    private static final long serialVersionUID = 1L;
    private double aptitud;
    private final Pokemon[] equipoDeGimnasio;
    private final double[][] matrizEfectividad;

    PokeFitnessFunction(Pokemon[] equipoDeGimnasio) {
        this.aptitud = 0;
        this.matrizEfectividad = inicializarMatriz();
        this.equipoDeGimnasio = equipoDeGimnasio;
    }

    @Override
    public double evaluate(IChromosome pokeEquipo) {
        aptitudDeEquipo(pokeEquipo);
        aptitud /= 10;
        if (aptitud < 0) {
            aptitud = 0;
        }
        if (aptitud > 100) {
            aptitud = 100;
        }
        return aptitud;
    }

    private void aptitudDeEquipo(IChromosome _equipoDesafiante) {

        // i equipo desafiante - j equipo de gimnasio

        /* Cada pokemon desafiante evalua como le va a ir con los 3 del gimnasio
	       aptitudesIndividuales[] tiene la aptitud de un desafiante contra los 3 del gimnasio
	       Se suman las aptitudes individuales para sacar la aptitud de ese pokemon desafiante total
	       Se guardan en aptitudesGrupales[] las aptitudes del equipo desafiante
	       Se dividen por 3 las aptitudes grupales de cada pokemon desafiante y se suman esos cocientes
         */
        int cant = _equipoDesafiante.getGenes().length;
        Pokemon[] equipoDesafiante = this.getEquipoDesafiante(_equipoDesafiante, cant);

        double[] aptitudesGrupales = new double[cant];

        for (int i = 0; i < cant; i++) {
            double[] aptitudesInviduales = new double[cant];
            for (int k = 0; k < cant; k++) {
                aptitudesInviduales[k] = 0;
            }

            for (int j = 0; j < cant; j++) {
                aptitudesInviduales[i] = peleaPokemon(equipoDesafiante[i], equipoDeGimnasio[j]);

                for (int k = 0; k < cant; k++) {
                    if (equipoDesafiante[i] == equipoDesafiante[k] && i != k) {
                        aptitudesInviduales[i] = 0;
                    }
                }

                aptitudesGrupales[i] += aptitudesInviduales[i];
            }
        }

        for (int k = 0; k < cant; k++) {
            aptitud += aptitudesGrupales[k];
        }

        //creo haber escuchado de que ibamos a penalizar
        //los equipos con pokemones del mismo tipo
        //si esto es asi hay que agregarlo
    }

    private Pokemon[] getEquipoDesafiante(IChromosome _equipoDesafiante, int cantidadDePokemones) {
        Pokemon[] equipoDesafiante = new Pokemon[cantidadDePokemones];
        for (int i = 0; i < cantidadDePokemones; i++) {
            equipoDesafiante[i] = (Pokemon) _equipoDesafiante.getGene(i).getAllele();
        }
        return equipoDesafiante;
    }

    private double peleaPokemon(Pokemon desafiante, Pokemon gimnasio) {
        double aptitudDesafiante = aptitud(desafiante, gimnasio);
        double aptitudGimnasio = aptitud(gimnasio, desafiante);

        return aptitudDesafiante - aptitudGimnasio;
    }

    private double aptitud(Pokemon pokemon1, Pokemon pokemon2) {
        double varianza = (Math.random() * (100 - 85)) + 85;
        return 0.01 * varianza
                * (efectividad(pokemon1.getTipo(), pokemon2.getTipo())
                * ((pokemon1.getAtaqueEspecial() * pokemon1.getAtaque()) / (25 * (pokemon2.getDefensa() + pokemon2.getDefensaEspecial()) / 2) + 2)
                - efectividad(pokemon2.getTipo(), pokemon1.getTipo())
                * ((pokemon2.getAtaqueEspecial() * pokemon2.getAtaque()) / (25 * (pokemon1.getDefensa() + pokemon1.getDefensaEspecial()) / 2) + 2));
    }

    private int tipoStringToInteger(String tipo) {

        if (tipo.compareTo("Normal") == 0) {
            return 0;
        } else if (tipo.compareTo("Fighting") == 0) {
            return 1;
        } else if (tipo.compareTo("Flying") == 0) {
            return 2;
        } else if (tipo.compareTo("Rock") == 0) {
            return 3;
        } else if (tipo.compareTo("Grass") == 0) {
            return 4;
        } else if (tipo.compareTo("Ghost") == 0) {
            return 5;
        } else if (tipo.compareTo("Fire") == 0) {
            return 6;
        } else if (tipo.compareTo("Water") == 0) {
            return 7;
        } else if (tipo.compareTo("Electric") == 0) {
            return 8;
        } else if (tipo.compareTo("Psychic") == 0) {
            return 9;
        } else if (tipo.compareTo("Ice") == 0) {
            return 10;
        } else if (tipo.compareTo("Dragon") == 0) {
            return 11;
        } else {
            //que rompa todo
            return -1;
        }
    }

    private double efectividad(String tipo1, String tipo2) {
        return this.matrizEfectividad[tipoStringToInteger(tipo1)][tipoStringToInteger(tipo2)];
    }

    private double[][] inicializarMatriz() {
        double[][] efectividad = new double[12][12];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                efectividad[i][j] = 1;
            }
        }
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
