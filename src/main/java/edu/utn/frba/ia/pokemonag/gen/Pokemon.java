package edu.utn.frba.ia.pokemonag.gen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.stream.Stream;
import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;
import org.jgap.UnsupportedRepresentationException;

public class Pokemon {

    public int tipo;
    public int ataque;
    public int ataqueEspecial;
    public int defensa;
    public int defensaEspecial;

    public Pokemon(Vector stats) {
        this.tipo = (int) stats.get(0);
        this.ataque = (int) stats.get(1);
        this.ataqueEspecial = (int) stats.get(2);
        this.defensa = (int) stats.get(3);
        this.defensaEspecial = (int) stats.get(4);
    }

    public Pokemon(int idPokemon, Configuration config) {
        EquipoGen nuevoGen;

        try {
            nuevoGen = new EquipoGen(config);
            try (final Stream<String> lines = Files.lines(Paths.get("pokemons.txt"))) {
                String stringStats = lines.skip(idPokemon).findFirst().get();
                nuevoGen.setValueFromPersistentRepresentation(stringStats);
            } catch (IOException | UnsupportedRepresentationException ex) {
            }

            this.setTipo((int) nuevoGen.geneAt(0).getAllele());
            this.setAtaque((int) nuevoGen.geneAt(1).getAllele());
            this.setAtaqueEspecial((int) nuevoGen.geneAt(2).getAllele());
            this.setDefensa((int) nuevoGen.geneAt(3).getAllele());
            this.setDefensaEspecial((int) nuevoGen.geneAt(4).getAllele());

        } catch (InvalidConfigurationException e) {
        }
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(int ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(int defensaEspecial) {
        this.defensaEspecial = defensaEspecial;
    }

}
