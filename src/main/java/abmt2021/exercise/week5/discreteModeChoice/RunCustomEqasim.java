package abmt2021.exercise.week5.discreteModeChoice;

import abmt2021.exercise.week5.discreteModeChoice.modeChoice.AbmtModeChoiceModule;
import org.eqasim.core.components.config.EqasimConfigGroup;
import org.eqasim.core.simulation.EqasimConfigurator;
import org.eqasim.core.simulation.mode_choice.EqasimModeChoiceModule;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;

/**
 * @author kaghog created on 17.10.2021
 * @project abmt2021
 */
public class RunCustomEqasim {
    public static void main(String[] args)  {


        String configPath = args[0];

        //load the config file with the necessary modules (DMC module and eqasim...)
        //we can look the EqasimConfigurator class to see what methods are available and ehat the getConfigGroups method does
        Config config = ConfigUtils.loadConfig(configPath,
                EqasimConfigurator.getConfigGroups());

        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);

        //Now we have loaded the config, next is to load our scenario

        Scenario scenario = ScenarioUtils.loadScenario(config);

        //We need to define the names of the utility estimators we would use for the different modes
        //Now we use the default names

        EqasimConfigGroup eqasimConfig = (EqasimConfigGroup) config.getModules().get(EqasimConfigGroup.GROUP_NAME);

        eqasimConfig.setEstimator("walk", "WalkUtilityEstimator");
        eqasimConfig.setEstimator("pt", "PtUtilityEstimator");
        eqasimConfig.setEstimator("bike", "BikeUtilityEstimator");

        //now we change the estimator for car to the one we defined //use the name defined in the module
        eqasimConfig.setEstimator("car", "AbmtCarEstimator");


        //Create controller
        Controler controller = new Controler(scenario);

        //It adds all the discrete mode choice module and other modules we need to the controller. You can add them manually too
        EqasimConfigurator.configureController(controller);

     //Also add the eqasim mode choice module
        controller.addOverridingModule(new EqasimModeChoiceModule());

        //And add our own defined module
        controller.addOverridingModule(new AbmtModeChoiceModule());


        controller.run();
    }
}
