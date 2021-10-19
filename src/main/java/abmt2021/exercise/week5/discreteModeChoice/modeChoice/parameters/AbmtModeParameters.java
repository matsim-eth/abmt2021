package abmt2021.exercise.week5.discreteModeChoice.modeChoice.parameters;

import org.eqasim.core.simulation.mode_choice.parameters.ModeParameters;

/**
 * @author kaghog created on 18.10.2021
 * @project abmt2021
 */
public class AbmtModeParameters extends ModeParameters {

    public class AbmtCarParameters {
        public double alpha_sex = 0.0;
    }

    //If we want to change all the parameters manually by code then we would have to define all of them here and change them

    public final AbmtCarParameters abmtCar = new AbmtCarParameters();
}
