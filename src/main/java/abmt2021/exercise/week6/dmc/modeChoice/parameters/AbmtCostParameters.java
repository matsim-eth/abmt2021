package abmt2021.exercise.week6.dmc.modeChoice.parameters;

import org.eqasim.core.simulation.mode_choice.ParameterDefinition;

/**
 * @author kaghog created on 25.10.2021
 * @project abmt2021
 */
public class AbmtCostParameters implements ParameterDefinition {
    //Define the cost parameters that would be needed in the cost model for each mode
    //Later this class will need to be binded so it is injected into the cost model
    public double carCost_USD_km; //the cost can be named anything, we assume that its in USD per km, it all depends on how you use it in the cost model
    public double ptCost_USD;
}
