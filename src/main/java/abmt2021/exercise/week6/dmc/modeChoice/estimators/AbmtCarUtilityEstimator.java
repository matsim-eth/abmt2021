package abmt2021.exercise.week6.dmc.modeChoice.estimators;

import abmt2021.exercise.week6.dmc.modeChoice.parameters.AbmtModeParameters;
import com.google.inject.Inject;
import org.eqasim.core.simulation.mode_choice.utilities.estimators.CarUtilityEstimator;
import org.eqasim.core.simulation.mode_choice.utilities.estimators.EstimatorUtils;
import org.eqasim.core.simulation.mode_choice.utilities.predictors.CarPredictor;
import org.eqasim.core.simulation.mode_choice.utilities.variables.CarVariables;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.contribs.discrete_mode_choice.model.DiscreteModeChoiceTrip;

import java.util.List;

/**
 * @author kaghog created on 18.10.2021
 * @project abmt2021
 */
public class AbmtCarUtilityEstimator extends CarUtilityEstimator {
    private final AbmtModeParameters parameters;
    private final CarPredictor carPredictor;

    // the constructor must support the annotation @Inject in order to construct its
    // parameters using Guice. This makes AbmtModeParameters available by its @Provider
    // defined in AbmtModeChoiceModule

    @Inject
    public AbmtCarUtilityEstimator(AbmtModeParameters parameters, CarPredictor carPredictor) {
        super(parameters, carPredictor);
        this.carPredictor = carPredictor;
        this.parameters = parameters;
    }

    //we can create our own method or override the method with ours (if it is the same name)

    //defining our own method for estimating the constant parameters when the person is male to give them preference
    //for using car
    protected double estimateConstantMale() {
        //Here we use the additional alpha value for male from our defined AbmtCarParameter
        return this.parameters.abmtCar.alpha_male + parameters.car.alpha_u;
    }



    //Here we override the estimateUtility method defined in the CarUtilityEstimator class we are extending,
    // we customize it to take into account the constant for Male drivers.
    //Notice how we use other methods (e.g estimateConstantUtility(), estimateTravelTimeUtility(),..)  from the CarUtilityEstimator class even though we have not written them here.
    // Ctrl+Click on the methods to see what they do
    @Override
    public double estimateUtility(Person person, DiscreteModeChoiceTrip trip, List<? extends PlanElement> elements) {
        CarVariables carVariables = carPredictor.predict(person, trip, elements);

        double utility = 0.0;

        //use our constant estimator for male persons
        if (person.getAttributes().getAttribute("sex").equals("m")) {

            utility += estimateConstantMale();
        } else {
            utility += estimateConstantUtility();
        }

        utility += (estimateTravelTimeUtility(carVariables) + estimateMonetaryCostUtility(carVariables))
                * (parameters.betaCost_u_MU / 10) * parameters.betaCost_u_MU;

        return utility;
    }
}
