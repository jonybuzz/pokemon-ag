package edu.utn.frba.ia.pokemonag.gen;

import java.util.Vector;

public class Pokemon {

    public int tipo;
    public int ataque;
    public int ataqueEspecial;
    public int defensa;
    public int defensaEspecial;

    public Pokemon(Vector stats) {
        this.tipo = (int)stats.get(0);
        this.ataque = (int)stats.get(1);
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
