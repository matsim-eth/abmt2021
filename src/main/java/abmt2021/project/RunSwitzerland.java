package abmt2021.project;

import org.eqasim.core.simulation.analysis.EqasimAnalysisModule;
import org.eqasim.core.simulation.mode_choice.EqasimModeChoiceModule;
import org.eqasim.switzerland.RunSimulation;
import org.eqasim.switzerland.SwitzerlandConfigurator;
import org.eqasim.switzerland.mode_choice.SwissModeChoiceModule;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.CommandLine;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.CommandLine.ConfigurationException;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;

public class RunSwitzerland {

	public static void main(String[] args) throws ConfigurationException {

		// the argument that needs to be passed is of the following format:
		//--config-path "path-to-the-config-file"
		RunSimulation.main(args);
		
		// or you can copy paste everything contained in the main() method in the RunSumulation class
		// you will probably have to do this as you will need
		// to add your own code
//		CommandLine cmd = new CommandLine.Builder(args) //
//				.requireOptions("config-path") //
//				.allowPrefixes("mode-parameter", "cost-parameter") //
//				.build();
//
//		Config config = ConfigUtils.loadConfig(cmd.getOptionStrict("config-path"),
//				SwitzerlandConfigurator.getConfigGroups());
//		cmd.applyConfiguration(config);
//
//		Scenario scenario = ScenarioUtils.createScenario(config);
//
//		SwitzerlandConfigurator.configureScenario(scenario);
//		ScenarioUtils.loadScenario(scenario);
//		SwitzerlandConfigurator.adjustScenario(scenario);
//
//		Controler controller = new Controler(scenario);
//		SwitzerlandConfigurator.configureController(controller);
//		controller.addOverridingModule(new EqasimAnalysisModule());
//		controller.addOverridingModule(new EqasimModeChoiceModule());
//		controller.addOverridingModule(new SwissModeChoiceModule(cmd));
//
//		controller.run();
	}

}
