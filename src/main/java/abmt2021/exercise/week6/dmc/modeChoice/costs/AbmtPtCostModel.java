package abmt2021.exercise.week6.dmc.modeChoice.costs;

import abmt2021.exercise.week6.dmc.modeChoice.parameters.AbmtCostParameters;
import com.google.inject.Inject;
import org.eqasim.core.simulation.mode_choice.cost.AbstractCostModel;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.contribs.discrete_mode_choice.model.DiscreteModeChoiceTrip;

import java.util.List;

/**
 * @author kaghog created on 25.10.2021
 * @project abmt2021
 */
public class AbmtPtCostModel extends AbstractCostModel {
    private final AbmtCostParameters costParameters;

    @Inject
    public AbmtPtCostModel(AbmtCostParameters costParameters) {
        super("pt");
        this.costParameters = costParameters;
    }

    @Override
    public double calculateCost_MU(Person person, DiscreteModeChoiceTrip discreteModeChoiceTrip, List<? extends PlanElement> list) {
        //now we just set a flat rate for PT fare which can be changed and customized here at anytime
        //The flat fare will be gotten from the cost parameters defined
        return costParameters.ptCost_USD;

        //we can make the cost model complicated. Try implementing a fancy one of your own that charges based on trip length
    }
}
