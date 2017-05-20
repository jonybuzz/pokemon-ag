package edu.utn.frba.ia.pokemonag.gen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Pokemon {

    public int tipo;
    public int ataque;
    public int ataqueEspecial;
    public int defensa;
    public int defensaEspecial;

    public Pokemon(int idPokemon) {

        String stringStats;

        try (Stream<String> lines = Files.lines(Paths.get("pokemons.txt"))) {
            stringStats = lines.skip(idPokemon).findFirst().get();
            crearDesdeTexto(stringStats);
        } catch (IOException ex) {
            Logger.getLogger(Pokemon.class.getName()).log(Level.SEVERE, null, ex);
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

    private void crearDesdeTexto(String stringStats) {

        this.tipo = Integer.parseInt(stringStats.substring(0, 3));
        this.ataque = Integer.parseInt(stringStats.substring(3, 6));
        this.ataqueEspecial = Integer.parseInt(stringStats.substring(6, 9));
        this.defensa = Integer.parseInt(stringStats.substring(9, 12));
        this.defensaEspecial = Integer.parseInt(stringStats.substring(12, 15));
    }
}
