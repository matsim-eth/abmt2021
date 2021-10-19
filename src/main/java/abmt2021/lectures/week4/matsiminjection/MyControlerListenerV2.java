package abmt2021.lectures.week4.matsiminjection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.controler.events.IterationEndsEvent;
import org.matsim.core.controler.events.StartupEvent;
import org.matsim.core.controler.listener.IterationEndsListener;
import org.matsim.core.controler.listener.StartupListener;

import com.google.inject.Inject;

// we add StartupListener which notifies that the controler has been initialized
public class MyControlerListenerV2 implements StartupListener, IterationEndsListener{

	private final MyEventHandler eventHandler;
	private final int[] enterEvents;
	private final int[] leaveEvents;
	private final Scenario scenario;
	private final EventsManager eventsmanager;
	// we are additionally providing EventsManager, which is binded by default in MATSim
	// it is responsible to send events to all event handlers
	@Inject
	public MyControlerListenerV2(MyEventHandler eventHandler, Scenario scenario, EventsManager eventsManager) {
		this.eventHandler = eventHandler;
		this.eventsmanager = eventsManager;
		enterEvents = new int[scenario.getConfig().controler().getLastIteration() + 1];
		leaveEvents = new int[scenario.getConfig().controler().getLastIteration() + 1];
		this.scenario = scenario;

	}
	
	
	@Override
	public void notifyStartup(StartupEvent event) {
		// here we can then add the injected event handler to the events manager
		// this will make sure that both events manager and this controler lsitener
		// are using the same instance
		this.eventsmanager.addHandler(eventHandler);
		
	}
	
	public void notifyIterationEnds(IterationEndsEvent event) {		
		this.enterEvents[event.getIteration()] = this.eventHandler.getCounterEnter();
		this.leaveEvents[event.getIteration()] = this.eventHandler.getCounterLeave();	
		
		if (event.getIteration() == scenario.getConfig().controler().getLastIteration()) {
			// write all data gathered in csv files
			String path = event.getServices().getControlerIO().getOutputPath() + "\\events.csv";
			try {
				FileWriter fw = new FileWriter(new File(path));
				fw.write("iteration,numlinkenter,numlinkleave\n");
				for (int i = 0; i <= event.getIteration();i++) {
					fw.write(i + "," + this.enterEvents[i] + "," + this.leaveEvents[i] + "\n");
				}
				// flush() tells the FileWriter to output the stream
				fw.flush();
				
				// it is good practice to close the stream when no more output is expected
				fw.close();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}			
		}
	}

	public int[] getEnterEvents() {
		return enterEvents;
	}

	public int[] getLeaveEvents() {
		return leaveEvents;
	}

	

}

