package abmt2021.exercise.week6.dmc;

import abmt2021.exercise.week6.dmc.modeChoice.AbmtModeChoiceModule;
import org.eqasim.core.components.config.EqasimConfigGroup;
import org.eqasim.core.simulation.EqasimConfigurator;
import org.eqasim.core.simulation.mode_choice.EqasimModeChoiceModule;
import org.eqasim.core.simulation.mode_choice.parameters.ModeParameters;
import org.matsim.api.core.v01.Scenario;
import org.matsim.contribs.discrete_mode_choice.modules.config.DiscreteModeChoiceConfigGroup;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;

import java.util.Arrays;

/**
 * @author kaghog created on 17.10.2021
 * @project abmt2021
 */
public class RunCustomEqasim {
    public static void main(String[] args)  {


        String configPath = "scenarios/siouxfalls-2014/config_default_eqasim.xml";//args[0]; //use the sioux fall scenario config_default_eqasim.xml file

        //load the config file with the necessary modules (DMC module and eqasim...)
        //we can look the EqasimConfigurator class to see what methods are available and ehat the getConfigGroups method does
        Config config = ConfigUtils.loadConfig(configPath, EqasimConfigurator.getConfigGroups());

        config.controler().setLastIteration(3);
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);

        //Now we have loaded the config, next is to load our scenario
        Scenario scenario = ScenarioUtils.loadScenario(config);


        //Now that we have chosen eqasimUtility estimator for setting up the dcm we need to identify for each mode what estimators we want to use
        EqasimConfigGroup eqasimConfig = (EqasimConfigGroup) config.getModules().get(EqasimConfigGroup.GROUP_NAME);

        eqasimConfig.setEstimator("walk", "WalkUtilityEstimator");
        eqasimConfig.setEstimator("bike", "BikeUtilityEstimator");
        eqasimConfig.setEstimator("pt", "PtUtilityEstimator");

        //now we change the estimator for car to the one we defined //use the name as defined in the module
        eqasimConfig.setEstimator("car", "AbmtCarUtilityEstimator");

        //we also need to specify the cost model --this can be done in the config file as well
        eqasimConfig.setCostModel("car", "AbmtCarCostModel");
        eqasimConfig.setCostModel("pt", "AbmtPtCostModel");

        //to define the mode and cost parameters path we can specify here or directly in the config file. Ensure this file exist
        eqasimConfig.setModeParametersPath("scenarios/siouxfalls-2014/mode_params.yml");
        eqasimConfig.setCostParametersPath("scenarios/siouxfalls-2014/cost_params.yml");

        //Here is how to add the AbmtModeAvailability to the dcm module directly, one can make changes to any of the parameterset this way
        //First we get the dcm config group
        //Comment it out because we already defined within the config file
        DiscreteModeChoiceConfigGroup dmcConfig = DiscreteModeChoiceConfigGroup.getOrCreate(config);
        dmcConfig.setModeAvailability("AbmtModeAvailability");

        //Create controller
        Controler controller = new Controler(scenario);

        //It adds all the discrete mode choice module and other modules we need to the controller. You can add them manually too
        EqasimConfigurator.configureController(controller);


        //Add the eqasim mode choice module
        controller.addOverridingModule(new EqasimModeChoiceModule());

        //for clean code and organization, we create a module for all our mode choice class injections
        controller.addOverridingModule(new AbmtModeChoiceModule());


        controller.run();
    }
}
