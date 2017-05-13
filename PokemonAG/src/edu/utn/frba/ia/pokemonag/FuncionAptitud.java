package edu.utn.frba.ia.pokemonag;

import org.jgap.*;
import org.jgap.impl.*;

/**
 * Funcion de aptitud de un equipo de Pokemons
 *
 * @author Grupo 19 2017 1C
 */
public class FuncionAptitud extends FitnessFunction {

    
  /*
   * @param a_subject the Chromosome to be evaluated
   * @return defect rate of our problem
   *
   */
  @Override
  public double evaluate(IChromosome a_subject) {
    int total = 0;

    for (int i = 0; i < a_subject.size(); i++) {
      BooleanGene value = (BooleanGene) a_subject.getGene(a_subject.size() -
          (i + 1));
      if (value.booleanValue()) {
        total += Math.pow(2.0, (double) i);
      }
    }

    return total;
  }
}
