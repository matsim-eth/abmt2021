package abmt2021.project;

import org.eqasim.switzerland.RunSimulation;
import org.matsim.core.config.CommandLine.ConfigurationException;

public class RunSwitzerland {

	public static void main(String[] args) throws ConfigurationException {

		// the argument that needs to be passed is of the following format:
		//--config-path "path-to-the-config-file"
		RunSimulation.main(args);
		
		// or you can copy paste everything contained in the main() method in the RunSumulation class
		// you will probably have to do this as you will need
		// to add your own code
		
	}

}
