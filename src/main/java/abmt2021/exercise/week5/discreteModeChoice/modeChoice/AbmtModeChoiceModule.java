package abmt2021.exercise.week5.discreteModeChoice.modeChoice;


import org.eqasim.core.simulation.mode_choice.AbstractEqasimExtension;
import org.eqasim.core.simulation.mode_choice.parameters.ModeParameters;

import java.io.IOException;

/**
 * @author kaghog created on 18.10.2021
 * @project abmt2021
 */
public class AbmtModeChoiceModule extends AbstractEqasimExtension {

    @Override
    protected void installEqasimExtension() {

        //we make a bind to inject the Mode parameters required by the discrete mode choice.
        bind(ModeParameters.class).asEagerSingleton();


    }


}
