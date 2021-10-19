package abmt2021.exercise.week5.discreteModeChoice.modeChoice;

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

    static public final String MODE_AVAILABILITY_NAME = "AbmtModeAvailability";

    @Override
    protected void installEqasimExtension() {

        //we make a bind to inject the Mode parameters required by the discrete mode choice.
        bind(ModeParameters.class).asEagerSingleton();


    }


}
