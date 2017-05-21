
import java.io.*;
import java.util.*;

public class Pokemones {

    private Vector<Pokemon> pokemones;

    Pokemones(String pathFile) throws FileNotFoundException, IOException {

        pokemones = new Vector<Pokemon>();
        BufferedReader buffer = new BufferedReader(new FileReader(pathFile));

        String linea;
        while ((linea = buffer.readLine()) != null) {

            String word[] = linea.split("\\s+");
            pokemones.add(new Pokemon(
                    Integer.parseInt(word[0]), //id
                    word[1], //nombre
                    word[2], //tipo
                    Integer.parseInt(word[3]), //ataque
                    Integer.parseInt(word[5]), //ataque especial
                    Integer.parseInt(word[4]), //defensa
                    Integer.parseInt(word[6])) //defensa especial
            );
        }

        buffer.close();
    }

    public Pokemon getPokemon(int index) {
        return (index < 0 || index >= cantidadDePokemones()) ? pokemones.elementAt(0) : pokemones.elementAt(index);
    }

    public int cantidadDePokemones() {
        return pokemones.size();
    }
}
