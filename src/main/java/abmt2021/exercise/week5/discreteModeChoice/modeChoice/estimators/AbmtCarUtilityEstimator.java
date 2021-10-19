package abmt2021.exercise.week5.discreteModeChoice.modeChoice.estimators;

import abmt2021.exercise.week5.discreteModeChoice.modeChoice.parameters.AbmtModeParameters;
import com.google.inject.Inject;
import org.eqasim.core.simulation.mode_choice.parameters.ModeParameters;
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

    //we can create our own method or overide the method with ours (if it is the same name)

    protected double estimateConstant() {
        return this.parameters.abmtCar.alpha_sex + parameters.car.alpha_u;
    }

    @Override
    protected double estimateTravelTimeUtility(CarVariables variables) {
        return this.parameters.car.betaTravelTime_u_min * variables.travelTime_min;
    }


    @Override
    protected double estimateMonetaryCostUtility(CarVariables variables) {
        return EstimatorUtils.interaction(variables.euclideanDistance_km, parameters.referenceEuclideanDistance_km,
                parameters.lambdaCostEuclideanDistance) * variables.cost_MU;
    }

    @Override
    public double estimateUtility(Person person, DiscreteModeChoiceTrip trip, List<? extends PlanElement> elements) {
        CarVariables carVariables = carPredictor.predict(person, trip, elements);

        double utility = 0.0;

        //use our constant estimator for male persons
        if (person.getAttributes().getAttribute("sex").equals("m")) {

            utility += estimateConstant();
        } else {
            utility += estimateConstantUtility();
        }

        utility += (estimateTravelTimeUtility(carVariables) + estimateMonetaryCostUtility(carVariables))
                * (parameters.betaCost_u_MU / 10) * parameters.betaCost_u_MU;

        return utility;
    }
}
