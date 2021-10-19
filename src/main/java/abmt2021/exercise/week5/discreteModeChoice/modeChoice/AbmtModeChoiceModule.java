package abmt2021.exercise.week5.discreteModeChoice.modeChoice;

import abmt2021.exercise.week5.discreteModeChoice.modeChoice.estimators.AbmtCarUtilityEstimator;
import abmt2021.exercise.week5.discreteModeChoice.modeChoice.parameters.AbmtModeParameters;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.eqasim.core.components.config.EqasimConfigGroup;
import org.eqasim.core.simulation.mode_choice.AbstractEqasimExtension;
import org.eqasim.core.simulation.mode_choice.parameters.ModeParameters;
import org.eqasim.core.simulation.mode_choice.utilities.estimators.CarUtilityEstimator;
import org.matsim.contribs.discrete_mode_choice.model.mode_availability.ModeAvailability;
import org.matsim.core.config.CommandLine;

import java.io.IOException;

/**
 * @author kaghog created on 18.10.2021
 * @project abmt2021
 */
public class AbmtModeChoiceModule extends AbstractEqasimExtension {

    @Override
    protected void installEqasimExtension() {
        // bindUtilityEstimator is a method in AbstractEqasimExtension that make possible add
        // to a mapbinder objects of type UtilityEstimator with a specific key, in this case AbmtCarEstimator

        bindUtilityEstimator("AbmtCarEstimator").to(AbmtCarUtilityEstimator.class);
        //bind(CarUtilityEstimator.class).to(AbmtCarUtilityEstimator.class);
        bind(ModeParameters.class).to(AbmtModeParameters.class);
        bind(AbmtModeParameters.class).asEagerSingleton();
        bind(ModeAvailability.class).to(AbmtModeAvailability.class);
        //bind(ModeParameters.class).asEagerSingleton();


    }

    //@Provides is used as a factory for a new object. Every time a new object of
    // type AbmtModeParameters is required as parameter in a constructor implementing @Inject
    //this object will be created by using the following method

  /*  @Provides
    @Singleton
    public AbmtModeParameters provideModeChoiceParameters(EqasimConfigGroup config) throws IOException, CommandLine.ConfigurationException {
        AbmtModeParameters parameters = new AbmtModeParameters();

        return parameters;
    }*/

}
