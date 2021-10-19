package abmt2021.exercise.week5.discreteModeChoice;

import abmt2021.exercise.week5.discreteModeChoice.modeChoice.AbmtModeChoiceModule;
import org.eqasim.core.components.config.EqasimConfigGroup;
import org.eqasim.core.simulation.EqasimConfigurator;
import org.eqasim.core.simulation.mode_choice.EqasimModeChoiceModule;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.CommandLine;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;

/**
 * @author kaghog created on 17.10.2021
 * @project abmt2021
 */
public class RunDefaultEqasimv2 {
    public static void main(String[] args) throws CommandLine.ConfigurationException {


        // This defines required and possible args needed to run the simulation. Can be defined in the
        // "Edit Configuration..." menu for intelliJ or "Run configuration for Eclipse
        //provide in the format --config-path "my/path/config.xml"
        CommandLine cmd = new CommandLine.Builder(args) //
                .requireOptions("config-path") //
                .allowPrefixes("mode-parameter", "cost-parameter") // note that we have the possibility of defining cost or mode parameters here
                .build();

        //Normally from the config file
        //how can we easily change things if we want

        //load the config file with the necessary modules (DMC module and eqasim...)
        //we can look the EqasimConfigurator class to see what methods are available and ehat the getConfigGroups method does
        Config config = ConfigUtils.loadConfig(cmd.getOptionStrict("config-path"),
                EqasimConfigurator.getConfigGroups());
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);

        cmd.applyConfiguration(config);

        //Now we have loaded the config, next is to load our scenario

        Scenario scenario = ScenarioUtils.loadScenario(config);

        //We need to define the names of the utility estimators we would use for the different modes
        //Now we use the default names

        EqasimConfigGroup eqasimConfig = (EqasimConfigGroup) config.getModules().get(EqasimConfigGroup.GROUP_NAME);

        eqasimConfig.setEstimator("walk", "WalkUtilityEstimator");
        eqasimConfig.setEstimator("pt", "PtUtilityEstimator");
        eqasimConfig.setEstimator("bike", "BikeUtilityEstimator");
        eqasimConfig.setEstimator("car", "CarUtilityEstimator");


        //Create controller
        Controler controller = new Controler(scenario);

        //It adds all the discrete mode choice module and other modules we need to the controller. You can add them manually too
        EqasimConfigurator.configureController(controller);



        //Also add the eqasim mode choice module
        controller.addOverridingModule(new EqasimModeChoiceModule());
        controller.addOverridingModule(new AbmtModeChoiceModule());


        controller.run();
    }
}
