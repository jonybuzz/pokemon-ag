package edu.utn.frba.ia.pokemonag.gen;

public class Pokemon {

    public byte tipo;
    public byte ataque;
    public byte ataqueEspecial;
    public byte defensa;
    public byte defensaEspecial;

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
}
