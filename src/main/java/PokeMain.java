
import java.awt.Color;
import java.awt.image.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import org.jgap.*;
import org.jgap.impl.*;

public class PokeMain {

    public static void main(String[] args) throws InvalidConfigurationException {

        int CANTIDAD_DE_ITERACIONES = 500;
        int CANTIDAD_DE_POKEMONES_POR_EQUIPO = 5;
        int TAMANO_POBLACION = 300;
        int ALTURA_PNG = 512;
        float ALTURA_UTILIZABLE_DEL_PNG = 0.9f;
        int CANTIDAD_DE_CORRIDAS = 5;
        IChromosome mejorEquipo = null;
        double aptitudMejorEquipoGlobal = Double.MIN_VALUE;
        Vector<Double> aptitudesIteracionMejorEquipo = new Vector<>();

        try {
            //cargar pokemones
            Pokemones pokemones = new Pokemones("pokemones.txt");

            for (int c = 0; c < CANTIDAD_DE_CORRIDAS; c++) {
                Configuration.reset();
                Configuration conf = new DefaultConfiguration();
                conf.setPreservFittestIndividual(true);
                conf.setKeepPopulationSizeConstant(false);
                conf.setPopulationSize(TAMANO_POBLACION);

                conf.getGeneticOperators().clear();
                //cruza la mitad de los cromosomas
                conf.addGeneticOperator(new CrossoverOperator(conf, 2));
                //muta 1 gen de cada 10
                conf.addGeneticOperator(new MutationOperator(conf));

                conf.removeNaturalSelectors(true);
                conf.addNaturalSelector(new WeightedRouletteSelector(conf), false);
                
                //crear prototipo de chomosoma
                Gene[] genes = new PokeGen[CANTIDAD_DE_POKEMONES_POR_EQUIPO];
                for (int i = 0; i < CANTIDAD_DE_POKEMONES_POR_EQUIPO; i++) {
                    genes[i] = new PokeGen(conf, pokemones);
                }
                conf.setSampleChromosome(new Chromosome(conf, genes));

                //equipo rival
                Pokemon[] equipoRival = new Pokemon[CANTIDAD_DE_POKEMONES_POR_EQUIPO];
                //121	Starmie	Water	75	85	100	85
                equipoRival[0] = pokemones.getPokemon(121);
                //23	Ekans	Grass	60	44	40	54
                equipoRival[1] = pokemones.getPokemon(23);
                //75	Graveler	Rock	95	115	45	45
                equipoRival[2] = pokemones.getPokemon(75);
                //78	Rapidash	Fire	100	70	80	80
                equipoRival[3] = pokemones.getPokemon(78);
                //150	Mewtwo	Psychic	110	90	154	90
                equipoRival[4] = pokemones.getPokemon(150);

                //le paso el equipo a vencer a la funcion de aptitud
                FitnessFunction fitness = new PokeFitnessFunction(equipoRival);
                conf.setFitnessFunction(fitness);

                Population pop = new Population(conf, conf.getPopulationSize());
                RandomGenerator generator = conf.getRandomGenerator();

                //crear poblacion con equipos sin pokemones repetidos
                for (int i = 0; i < conf.getPopulationSize(); i++) {
                    Gene[] smpGenes = conf.getSampleChromosome().getGenes();
                    Gene[] newGenes = new Gene[smpGenes.length];

                    for (int j = 0; j < newGenes.length; j++) {
                        while (true) {
                            Gene newPokemon = smpGenes[j].newGene();
                            newPokemon.setToRandomValue(generator);
                            boolean repeat = false;
                            for (int k = 0; k < j; k++) {
                                if (((Pokemon) newPokemon.getAllele()).getId() == ((Pokemon) newGenes[k].getAllele()).getId()) {
                                    repeat = true;
                                    break;
                                }
                            }
                            if (!repeat) {
                                newGenes[j] = newPokemon;
                                break;
                            }
                        }
                    }

                    IChromosome chrom = Chromosome.randomInitialChromosome(conf);
                    chrom.setGenes(newGenes);
                    pop.addChromosome(chrom);
                }
                Genotype population = new Genotype(conf, pop);

                IChromosome mejorEquipoIteracion = null;
                double aptitudMejorEquipoIteracion = Double.MIN_VALUE;
                Vector<Double> aptitudesIteracion = new Vector<Double>();

                for (int i = 0; i < CANTIDAD_DE_ITERACIONES; i++) {
                    population.evolve();

                    double r = fitness.getFitnessValue(population.getFittestChromosome());
                    aptitudesIteracion.add(r);
                    if (r > aptitudMejorEquipoIteracion) {
                        aptitudMejorEquipoIteracion = r;
                        mejorEquipoIteracion = population.getFittestChromosome();
                    }
                }

                if (aptitudMejorEquipoIteracion > aptitudMejorEquipoGlobal) {
                    aptitudMejorEquipoGlobal = aptitudMejorEquipoIteracion;
                    aptitudesIteracionMejorEquipo = aptitudesIteracion;
                    mejorEquipo = mejorEquipoIteracion;
                }
            }

            System.out.println("Mejor equipo para enfrentar al rival: ");
            for (int i = 0; i < CANTIDAD_DE_POKEMONES_POR_EQUIPO; i++) {
                String output = ((Pokemon) ((PokeGen) mejorEquipo.getGene(i)).getAllele()).getNombre()
                        + ", ataque: " + ((Pokemon) ((PokeGen) mejorEquipo.getGene(i)).getAllele()).getAtaque()
                        + ", ataque especial: " + ((Pokemon) ((PokeGen) mejorEquipo.getGene(i)).getAllele()).getAtaqueEspecial()
                        + ", defensa: " + ((Pokemon) ((PokeGen) mejorEquipo.getGene(i)).getAllele()).getDefensa()
                        + ", defensa especial: " + ((Pokemon) ((PokeGen) mejorEquipo.getGene(i)).getAllele()).getDefensaEspecial();
                System.out.println(output);
            }
        } catch (Exception e) {
            System.out.println("JGAP -" + e.getMessage());
            throw new RuntimeException(e);
        }

        try {
            BufferedImage bi = new BufferedImage(aptitudesIteracionMejorEquipo.size(), ALTURA_PNG, BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < aptitudesIteracionMejorEquipo.size(); i++) {
                int altura = ALTURA_PNG - (int) (ALTURA_UTILIZABLE_DEL_PNG * ALTURA_PNG * (aptitudesIteracionMejorEquipo.elementAt(i) / aptitudMejorEquipoGlobal));
                int color = (aptitudesIteracionMejorEquipo.elementAt(i) == aptitudMejorEquipoGlobal) ? (Color.RED).getRGB() : (Color.BLUE).getRGB();

                for (int x = i - 2; x < i + 2; x++) {
                    for (int y = altura - 2; y < altura + 2; y++) {
                        if (y >= 0 && y < ALTURA_PNG && x >= 0 && x < aptitudesIteracionMejorEquipo.size()) {
                            bi.setRGB(x, y, color);
                        }
                    }
                }
            }
            ImageIO.write(bi, "png", new File("./mejorIteracion.png"));
        } catch (Exception e) {
            System.out.println("PNG - " + e.getMessage());
        }
    }
}
