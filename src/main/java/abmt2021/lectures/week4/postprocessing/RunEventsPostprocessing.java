package abmt2021.lectures.week4.postprocessing;

import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.MatsimEventsReader;

import abmt2021.lectures.week4.matsiminjection.MyEventHandler;

public class RunEventsPostprocessing {

	public static void main(String[] args) {

		// Config config = ConfigUtils.createConfig();
		// Scenario scenario = ScenarioUtils.createScenario(config);
		EventsManager eventsManager = EventsUtils.createEventsManager();
		
		// we can provide null here for the scenario, as Scenario is not used
		// if we were actually using it we would need to create scenario as above
		MyEventHandler myEventHandler = new MyEventHandler(null);
		eventsManager.addHandler(myEventHandler);
		
		MatsimEventsReader matsimEventsReader = new MatsimEventsReader(eventsManager);
		// now we can read the events from our events file
		// the events manager will automatically process the read events 
		matsimEventsReader.readFile(args[0]);
		
		System.out.println(myEventHandler.getCounterEnter());
				
	}

}

