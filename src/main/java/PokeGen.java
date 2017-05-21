import org.jgap.*;

public class PokeGen extends BaseGene implements Gene, java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pokemon pokemon;
	private Pokemones listaDePokemones;
	
	public PokeGen(Configuration conf, Pokemones listaDePokemones) throws InvalidConfigurationException {
		super(conf);
		this.listaDePokemones = listaDePokemones;
	}
	
	public Gene newGeneInternal() {
		try {
			return new PokeGen(getConfiguration(), this.listaDePokemones);
		} catch(InvalidConfigurationException e) {
			throw new IllegalStateException(e.getMessage());
		}
	}
	
	public void setAllele(Object pokemon) {
		this.pokemon = (Pokemon)pokemon;
	}
	
	public Object getAllele() {
		return pokemon;
    }
	
	public void applyMutation(int index, double percentage) {
		//se espera que index sea un valor entre 0 y size -1
		//representa el bits que va a mutar		
		//en este caso 31 ya que solo mutan ataque, ataqueEspecial, defensa y defensaEspecial
		//donde cada uno es de 8 bits
		
		//ataque
		if (index >= 0 && index <= 7) {
			pokemon.setAtaque(pokemon.getAtaque() ^ (int)Math.pow((int)2,(int)index%8));
		} 
		//ataque especial
		else if (index >= 8 && index <= 15) {
			pokemon.setAtaqueEspecial(pokemon.getAtaqueEspecial() ^ (int)Math.pow((int)2,(int)index%8));
		}
		//defensa
		else if (index >= 16 && index <= 23) {
			pokemon.setDefensa(pokemon.getDefensa() ^ (int)Math.pow((int)2,(int)index%8));
		}
		//defensa especial
		else if (index >= 24 && index <= 31) {
			pokemon.setDefensaEspecial(pokemon.getDefensaEspecial() ^ (int)Math.pow((int)2,(int)index%8));
		}
	}
	
	public void setToRandomValue(RandomGenerator numberGenerator) {
		setAllele(listaDePokemones.getPokemon(numberGenerator.nextInt(this.listaDePokemones.cantidadDePokemones())));
	}
	
	public int size(){
		//4*8bits
		return 4*8;
	}
	
	public Object getInternalValue() {
		return pokemon;
	}
	
	public int compareTo(Object pokeGen) {
		//no se para que carajo usa esto
		//si se supone que es para ordenar por algun criterio, ahora
		//esta implementado por id
		if (pokeGen == null) {
			return 1;
		}
		
		if (pokemon == null) {
			if (((PokeGen)pokeGen).pokemon == null) {
				return 0;
			} else {
				return -1;
			}
		}
		
		int dif = ((PokeGen)pokeGen).pokemon.getId() - pokemon.getId();
		if (dif == 0) {
			return 0;
		} else if (dif > 0) {
			return 1;
		}
		return -1;
	}
	
	public String getPersistentRepresentation() {
		throw new UnsupportedOperationException("Unsupported getPersistentRepresentation");
	}
	
	public void setValueFromPersistentRepresentation(String representation) {
		throw new UnsupportedOperationException("Unsupported setValueFromPersistentRepresentation");
	}
}
