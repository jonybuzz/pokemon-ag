package edu.utn.frba.ia.pokemonag.cromosoma;

import edu.utn.frba.ia.pokemonag.gen.PokemonIdGen;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;

public class Equipo extends Chromosome {

    public Equipo(Configuration configuration, PokemonIdGen[] genesIniciales) throws InvalidConfigurationException {
        super(configuration, genesIniciales);
    }

}
