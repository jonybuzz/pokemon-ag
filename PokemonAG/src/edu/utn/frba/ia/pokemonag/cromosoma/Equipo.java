package edu.utn.frba.ia.pokemonag.cromosoma;

import edu.utn.frba.ia.pokemonag.gen.Pokemon;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.IChromosomePool;
import org.jgap.IGeneConstraintChecker;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;

public class Equipo extends Chromosome {

    public Equipo()
            throws InvalidConfigurationException {
        super();
    }

    public Equipo(final Configuration a_configuration)
            throws InvalidConfigurationException {
        super(a_configuration);
    }

    public Equipo(final Configuration a_configuration,
            final int a_desiredSize)
            throws InvalidConfigurationException {
        super(a_configuration, a_desiredSize);
    }

    public Equipo(final Configuration a_configuration,
            final Pokemon a_sampleGene, final int a_desiredSize)
            throws InvalidConfigurationException {
        super(a_configuration, a_sampleGene, a_desiredSize);
    }

    public Equipo(final Configuration a_configuration,
            final Pokemon[] a_initialGenes)
            throws InvalidConfigurationException {
        super(a_configuration, a_initialGenes);
    }

    public Equipo(final Configuration a_configuration, Pokemon a_sampleGene,
            int a_desiredSize,
            IGeneConstraintChecker a_constraintChecker)
            throws InvalidConfigurationException {
        this(a_configuration, a_sampleGene, a_desiredSize);
    }

    @Override
    public boolean isHandlerFor(Object a_obj, Class a_class) {
        return a_class == Equipo.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object perform(Object a_obj, Class a_class, Object a_params)
            throws Exception {
        return randomInitialEquipo(getConfiguration());
    }

    public static IChromosome randomInitialEquipo(Configuration a_configuration)
            throws InvalidConfigurationException {
        // Sanity check: make sure the given configuration isn't null.
        // -----------------------------------------------------------
        if (a_configuration == null) {
            throw new IllegalArgumentException(
                    "Configuration instance must not be null");
        }
        // Lock the configuration settings so that they can't be changed
        // from now on.
        // -------------------------------------------------------------
        a_configuration.lockSettings();
        // First see if we can get a Chromosome instance from the pool.
        // If we can, we'll randomize its gene values (alleles) and then
        // return it.
        // -------------------------------------------------------------
        IChromosomePool pool = a_configuration.getChromosomePool();
        if (pool != null) {
            IChromosome randomChromosome = pool.acquireChromosome();
            if (randomChromosome != null) {
                Gene[] genes = randomChromosome.getGenes();
                RandomGenerator generator = a_configuration.getRandomGenerator();
                for (Gene gene : genes) {
                    gene.setToRandomValue(generator);
                }
                randomChromosome.setFitnessValueDirectly(FitnessFunction.NO_FITNESS_VALUE);
                return randomChromosome;
            }
        }
        // We weren't able to get a Chromosome from the pool, so we have to
        // construct a new instance and build it from scratch.
        // ------------------------------------------------------------------
        IChromosome sampleChromosome
                = a_configuration.getSampleChromosome();
        sampleChromosome.setFitnessValue(FitnessFunction.NO_FITNESS_VALUE);
        Gene[] genesDeMuestra = sampleChromosome.getGenes();
        Pokemon[] nuevosPokemon = new Pokemon[genesDeMuestra.length];
        RandomGenerator generator = a_configuration.getRandomGenerator();

        // ...
        // Finally, construct the new chromosome with the new random
        // genes values and return it.
        // ---------------------------------------------------------
        return new Equipo(a_configuration, nuevosPokemon);
    }
}
