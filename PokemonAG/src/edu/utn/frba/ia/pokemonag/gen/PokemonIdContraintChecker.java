package edu.utn.frba.ia.pokemonag.gen;

import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.IGeneConstraintChecker;

public class PokemonIdContraintChecker implements IGeneConstraintChecker {

    @Override
    public boolean verify(Gene a_gene, Object a_alleleValue, IChromosome a_chromosome, int a_geneIndex) {

        return (int) a_alleleValue < 150;
    }

}
