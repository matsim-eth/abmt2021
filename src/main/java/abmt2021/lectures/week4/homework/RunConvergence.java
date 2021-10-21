package abmt2021.lectures.week4.homework;

import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.core.controler.TerminationCriterion;

import com.google.inject.name.Names;

public class RunConvergence {

	public static void main(String[] args) {

		Config config = ConfigUtils
				.loadConfig(args[0]);
		config.controler().setOverwriteFileSetting(OverwriteFileSetting.deleteDirectoryIfExists);
		// as we will run simulations with different delta this is a convenient way
		// to distinguish our output folders
		config.controler().setOutputDirectory(config.controler().getOutputDirectory()+ "_" + args[1]);
		Controler controler = new Controler(config);

		controler.addOverridingModule(new AbstractModule() {

			@Override
			public void install() {
				// we are getting the delta value from the argument list
				Double delta = Double.valueOf(args[1]);
				bind(Double.class).annotatedWith(Names.named("delta")).toInstance(delta);
				// create an event handler instance and bind it to the ModeshareEventHandler class
				// like this we can access it through Injection in our Listener
				ModeshareEventHandler modeshareEventHandler = new ModeshareEventHandler();
				bind(ModeshareEventHandler.class).toInstance(modeshareEventHandler);
				// add a controler listener to the MATSim controler
				this.addControlerListenerBinding().to(ModeshareControlerListener.class);
				// tell Guice framework to only create one ModeshareControlerListener object
				bind(ModeshareControlerListener.class).asEagerSingleton();
				// bind our ConvergenceTerminationCriterio to the TerminationCriterion interface
				// this will override the default MATSim binding of TerminateAtFixedIterationNumber
				// to this interface, which stops the simulation when last iteration is reached
				
				bind(TerminationCriterion.class).to(ConvergenceTerminationCriterion.class);
				
				// it is the most frequent use of Injection to bind an implementation of an
				// interface to the interface itself. Then in the Injection constructors we
				// would always pass an interface (as we are doing in our listener). This allows
				// further flexibility of our code, which allows us to bind any implementation to 
				// the interface without needing to refactor our constructors downstream.
			}
		});
		controler.run();
	}

}
