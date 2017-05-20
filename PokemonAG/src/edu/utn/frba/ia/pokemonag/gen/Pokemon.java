package edu.utn.frba.ia.pokemonag.gen;

import java.io.IOException;
import java.util.Vector;
import org.jgap.util.FileKit;

public class Pokemon {

    public byte tipo;
    public byte ataque;
    public byte ataqueEspecial;
    public byte defensa;
    public byte defensaEspecial;

    public Pokemon(int idPokemon) throws IOException {

        Vector pokemons = FileKit.readFile("pokemons.txt");

        String stringStats = (String) pokemons.get(idPokemon);

        crearDesdeTexto(stringStats);

    }

    public byte getTipo() {
        return tipo;
    }

    public void setTipo(byte tipo) {
        this.tipo = tipo;
    }

    public byte getAtaque() {
        return ataque;
    }

    public void setAtaque(byte ataque) {
        this.ataque = ataque;
    }

    public byte getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(byte ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public byte getDefensa() {
        return defensa;
    }

    public void setDefensa(byte defensa) {
        this.defensa = defensa;
    }

    public byte getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(byte defensaEspecial) {
        this.defensaEspecial = defensaEspecial;
    }

    private void crearDesdeTexto(String stringStats) {
        
        this.tipo = 50;
        this.ataque = 50;
        this.ataqueEspecial = 50;
        this.defensa = 50;
        this.defensaEspecial = 50;
    }
}
