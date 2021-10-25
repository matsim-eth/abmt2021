package abmt2021.lectures.week4.homework;

import java.util.HashMap;
import java.util.Map;

import org.matsim.api.core.v01.events.PersonDepartureEvent;
import org.matsim.api.core.v01.events.handler.PersonDepartureEventHandler;

public class ModeshareEventHandler implements PersonDepartureEventHandler{

	// we will store the counts of the modes in a Map
	// since we do not know which modes exist in the simulation 
	// we need to use a flexible structure
	private Map<String, Integer> modeCounts;
	
	public ModeshareEventHandler() {
		modeCounts = new HashMap<>();
	}
	
	@Override
	public void handleEvent(PersonDepartureEvent event) {

		if (this.modeCounts.containsKey(event.getLegMode()))
			this.modeCounts.put(event.getLegMode(), this.modeCounts.get(event.getLegMode()) + 1);
		else {
			this.modeCounts.put(event.getLegMode(), 1);
		}
		
	}
	@Override
	public void reset (int iteration) {
		this.modeCounts.clear();
	}

	public Map<String, Integer> getModeCounts() {
		return modeCounts;
	}

}
