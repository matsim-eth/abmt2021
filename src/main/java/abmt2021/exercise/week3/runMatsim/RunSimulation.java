package abmt2021.exercise.week3.runMatsim;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;

/**
 * @author kaghog created on 04.10.2021
 * @project abmt2021
 */
public class RunSimulation {
    public static void main (String[] args) {
        //Run without scenario
        /*Config config = ConfigUtils.createConfig();

        //We can change the config file directly here, change the iteration number or even the output directory
        config.controler().setLastIteration(1);
        config.controler().setOutputDirectory("output2");

        Controler controler = new Controler(config);
        controler.run();*/

        //Run with Scenario - Equil example project
        //Load the config
        String configPath = args[0]; //you can also directly add the config path here
        Config config = ConfigUtils.loadConfig(configPath);

        //what happens when we change the flow capacity
        //config.qsim().setFlowCapFactor(0.5);
        //config.qsim().setStorageCapFactor(0.5);


        Scenario scenario = ScenarioUtils.loadScenario(config);

        Controler controler = new Controler(scenario);

        controler.run();
    }
}
