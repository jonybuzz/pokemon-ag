package edu.utn.frba.ia.pokemonag.gen;

import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.FixedBinaryGene;

public class PokemonIdGen extends FixedBinaryGene {

    private static final int BITS_PARA_150_POKEMONS = 8;

    public PokemonIdGen(Configuration a_config) throws InvalidConfigurationException {
        super(a_config, BITS_PARA_150_POKEMONS);
        this.setConstraintChecker(new PokemonIdContraintChecker());
    }


}
