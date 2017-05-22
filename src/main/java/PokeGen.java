
import java.util.concurrent.ThreadLocalRandom;
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
	
    @Override
    public Gene newGeneInternal() {
        try {
            return new PokeGen(getConfiguration(), this.listaDePokemones);
        } catch (InvalidConfigurationException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public void setAllele(Object pokemon) {
        this.pokemon = (Pokemon) pokemon;
    }

    @Override
    public Object getAllele() {
        return pokemon;
    }

    @Override
    public void applyMutation(int index, double percentage) {
        //se espera que index sea un valor entre 0 y (tamaño del cromosoma - 1)
        //representa el valor atómico que va a mutar
        double random = Math.random() * 2 - 1;
        if (random < percentage) {
            int randomInt = ThreadLocalRandom.current().nextInt(1, 250);

            switch (index) {
                case 0:
                    pokemon.setAtaque(randomInt);
                    break;
                case 1:
                    pokemon.setAtaqueEspecial(randomInt);
                    break;
                case 2:
                    pokemon.setDefensa(randomInt);
                    break;
                case 3:
                    pokemon.setDefensaEspecial(randomInt);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void setToRandomValue(RandomGenerator numberGenerator) {
        setAllele(listaDePokemones.getPokemon(numberGenerator.nextInt(this.listaDePokemones.cantidadDePokemones())));
    }

    @Override
    public int size() {
        //cantidad de valores atomicos 
        //del cromosoma, para aplicar mutacion
        return 4;
    }

    @Override
    public Object getInternalValue() {
        return pokemon;
    }

    @Override
    public int compareTo(Object pokeGen) {
        //esta implementado por id
        if (pokeGen == null) {
            return 1;
        }

        if (pokemon == null) {
            if (((PokeGen) pokeGen).pokemon == null) {
                return 0;
            } else {
                return -1;
            }
        }

        int dif = ((PokeGen) pokeGen).pokemon.getId() - pokemon.getId();
        if (dif == 0) {
            return 0;
        } else if (dif > 0) {
            return 1;
        }
        return -1;
    }

    @Override
    public String getPersistentRepresentation() {
        throw new UnsupportedOperationException("Unsupported getPersistentRepresentation");
    }

    @Override
    public void setValueFromPersistentRepresentation(String representation) {
        throw new UnsupportedOperationException("Unsupported setValueFromPersistentRepresentation");
    }
}
