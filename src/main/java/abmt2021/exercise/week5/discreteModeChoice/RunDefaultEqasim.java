package abmt2021.exercise.week5.discreteModeChoice;

import abmt2021.exercise.week5.discreteModeChoice.modeChoice.AbmtModeChoiceModule;
import org.eqasim.core.simulation.EqasimConfigurator;
import org.eqasim.core.simulation.mode_choice.EqasimModeChoiceModule;
import org.eqasim.core.simulation.mode_choice.parameters.ModeParameters;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;

/**
 * @author kaghog created on 17.10.2021
 * @project abmt2021
 */
public class RunDefaultEqasim {
    public static void main(String[] args)  {


        String configPath = args[0]; //use the sioux fall scenario config_default_eqasim.xml file

        //load the config file with the necessary modules (DMC module and eqasim...)
        //we can look the EqasimConfigurator class to see what methods are available and ehat the getConfigGroups method does
        Config config = ConfigUtils.loadConfig(configPath, EqasimConfigurator.getConfigGroups());

        config.controler().setLastIteration(3);
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);

        //Now we have loaded the config, next is to load our scenario
        Scenario scenario = ScenarioUtils.loadScenario(config);

        //Create controller
        Controler controller = new Controler(scenario);

        //It adds all the discrete mode choice module and other modules we need to the controller. You can add them manually too
        EqasimConfigurator.configureController(controller);



        //Add the eqasim mode choice module
        controller.addOverridingModule(new EqasimModeChoiceModule());

        //Add an injection for mode parameters for the code to work
        controller.addOverridingModule(new AbstractModule() {
            @Override
            public void install() {
                bind(ModeParameters.class).asEagerSingleton();
            }
        });

        //for clean code and organization, we create a module for all our mode choice class injections and put the above there
        //controller.addOverridingModule(new AbmtModeChoiceModule());


        controller.run();
    }
}
