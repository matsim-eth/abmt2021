package abmt2021.lectures.week4.matsiminjection;

import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;

import com.google.inject.name.Names;

import abmt2021.lectures.week3.injection.Link;
import abmt2021.lectures.week3.injection.LinkProvider;
import abmt2021.lectures.week3.injection.NetworkGuice;

public class RunMatsimInjection {

	public static void main(String[] args) {

		// MATSim context
		
		// first we load the config
		Config config = ConfigUtils.loadConfig("C:\\Users\\balacm\\Downloads\\scenarios\\scenarios\\equil\\config.xml");
		// we only want to simulate one iteration to see if our code works
		config.controler().setLastIteration(1);
		// as we are testing our code we can always delete the output directory before starting the simulation
		config.controler().setOverwriteFileSetting(OverwriteFileSetting.deleteDirectoryIfExists);
		// we now create the Controler object
		Controler controler = new Controler(config);

		// MATSim uses an addOverridingModule() method in the controler
		// to set-up Guice dependency injections
		// this is a standard synthax that should be always used
		controler.addOverridingModule(new AbstractModule() {

			// one method should be implemented and that is install()
			// here we can set-up our bindings
			// the rest works the same as for the standard Guice injection
			@Override
			public void install() {
				// we want to create a String object that should be passed
				// to the injection framework
				String name = new String("X");
				bind(String.class).annotatedWith(Names.named("name")).toInstance(name);
				// we want to provide a Link object using a provider
				bind(Link.class).toProvider(LinkProvider.class);
				// we want to bind a NetworkGuice object and ensure that only one instance is created
				bind(NetworkGuice.class).asEagerSingleton();

			}
		});

		controler.addOverridingModule(new AbstractModule() {

			@Override
			public void install() {
				
				// in this way we tell MATSim that it should pass events to this event handler
				this.addEventHandlerBinding().to(MyEventHandler.class);
				// if we do not bind this as a Singleton MATSim will create two instances of this class
				// first time when it is added as EventHandler in MATSim
				// and second time when it is needed in the MyControlerListener
				// the second one will not be  registered as an EventHandler in MATSim and 
				// event will not be passed to it
				bind(MyEventHandler.class).asEagerSingleton();
				
				// as an alternative we can provide an EventHandler using a Provider
				// we should also ensure it is provided as a singleton
				// if we use this solution lines 58 and 64 should be commented out
				//this.addEventHandlerBinding().to(MyEventHandler.class);
				//bind(MyEventHandler.class).toProvider(MyEventHandlerProvider.class).asEagerSingleton();			
				
				// now we also need to tell MATSim that it should use our MyControlerListener
				this.addControlerListenerBinding().to(MyControlerListener.class);
				
				
				// another alternative would be the following (comment out all lines above)
				// bind the MyEventHandler either as a class or with a provider
				//bind(MyEventHandler.class).toProvider(MyEventHandlerProvider.class);
				// then just add a controler listener
				// what is here actually different can be seen within the listener itself
				//this.addControlerListenerBinding().to(MyControlerListenerV2.class);

			}
		});

		controler.run();
		// similarly like for default Guice we can obtain instances of different objects
		// that we injected using the Guice framework into MATSim
		NetworkGuice netM = controler.getInjector().getInstance(NetworkGuice.class);
		System.out.println(netM.getLink().getName());

	}

}
