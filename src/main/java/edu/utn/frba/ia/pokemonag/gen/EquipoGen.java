package edu.utn.frba.ia.pokemonag.gen;

import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.CompositeGene;
import org.jgap.impl.IntegerGene;

public class EquipoGen extends CompositeGene {

    public EquipoGen(Configuration config) throws InvalidConfigurationException {
        super(config, new IntegerGene(config, 0, 250));
    }

}
