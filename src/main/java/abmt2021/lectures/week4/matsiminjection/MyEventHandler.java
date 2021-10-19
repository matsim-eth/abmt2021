package abmt2021.lectures.week4.matsiminjection;

import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;
import org.matsim.api.core.v01.events.handler.LinkLeaveEventHandler;

import com.google.inject.Inject;

// we want to analyse link enter and link leave events
public class MyEventHandler implements LinkEnterEventHandler, LinkLeaveEventHandler{

	// we want to have two counters of these events
	// we also add a Scenario object in case we need it
	private final Scenario scenario;
	private int counterEnter = 0;
	private int counterLeave = 0;

	@Inject
	public MyEventHandler(Scenario scenario) {
		this.scenario = scenario;
	}
	
	@Override
	public void handleEvent(LinkEnterEvent event) {
		if (event.getTime() > 0.0)
			counterEnter++;
		
	}
	
	@Override
	public void handleEvent(LinkLeaveEvent event) {
		if (event.getTime() > 0.0)
			counterLeave++;
		
	}
	
	@Override
	public void reset(int iteration) {
		this.counterEnter = 0;
		this.counterLeave = 0;
	}
	
	public int getCounterEnter() {
		return counterEnter;
	}

	public int getCounterLeave() {
		return counterLeave;
	}

}

