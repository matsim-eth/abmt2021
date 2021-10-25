package abmt2021.lectures.week4.homework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.controler.events.IterationEndsEvent;
import org.matsim.core.controler.events.StartupEvent;
import org.matsim.core.controler.listener.IterationEndsListener;
import org.matsim.core.controler.listener.StartupListener;

import com.google.inject.Inject;

public class ModeshareControlerListener implements IterationEndsListener, StartupListener {

	private final EventsManager eventsManager;
	private final ModeshareEventHandler modeshareEventHandler;
	// we use an ArrayList to store mode counts for each iteration.
	// we use this structure as it is more flexible than a simple array 
	// as we do not know beforehand how many iteration we will have before we converge with a
	// given delta
	private final ArrayList<Map<String, Double>> modeshares;
	private final Scenario scenario;

	@Inject
	public ModeshareControlerListener(ModeshareEventHandler modeshareEventHandler, EventsManager eventsManager,
			Scenario scenario) {
		this.eventsManager = eventsManager;
		this.modeshareEventHandler = modeshareEventHandler;
		this.modeshares = new ArrayList<>();
		this.scenario = scenario;
	}

	@Override
	public void notifyStartup(StartupEvent event) {
		this.eventsManager.addHandler(this.modeshareEventHandler);

	}

	@Override
	public void notifyIterationEnds(IterationEndsEvent event) {

		// we need to create mode shares from the mode counts
		this.modeshareEventHandler.getModeCounts();
		double totalCount = 0;
		for (int i : this.modeshareEventHandler.getModeCounts().values()) {
			totalCount += i;
		}

		Map<String, Double> newEntry = new HashMap<>();
		for (String key : this.modeshareEventHandler.getModeCounts().keySet()) {
			newEntry.put(key, this.modeshareEventHandler.getModeCounts().get(key) / totalCount);
		}

		this.modeshares.add(newEntry);

		// now let's output this information to a csv file

		String path = event.getServices().getControlerIO().getIterationPath(event.getIteration()) + "\\modeshare.csv";
		try {
			FileWriter fw = new FileWriter(new File(path));
			fw.write("iteration");
			Set<String> keySet = newEntry.keySet();
			// first we write out the modes as a header
			for (String key : keySet)
				fw.write("," + key);
			fw.write("\n");
			fw.write(Integer.toString(event.getIteration()));
			// then we use the same keySet above to access the information
			// about the mode share
			
			for (String key : keySet) {
				fw.write("," + newEntry.get(key));
			}

			// flush() tells the FileWriter to output the stream
			fw.flush();

			// it is good practice to close the stream when no more output is expected
			fw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

		// this will not work when we have our convergence termination criterion
		// as in that case we do not know which is the last iteration.
		// any ideas how we can output this info in the output folder when
		// termination criterion is used? try to implement it.
		if (event.getIteration() == scenario.getConfig().controler().getLastIteration()) {
			// write all data gathered in csv files
			path = event.getServices().getControlerIO().getOutputPath() + "\\modeshare.csv";
			try {
				FileWriter fw = new FileWriter(new File(path));
				fw.write("iteration");
				Set<String> keySet = newEntry.keySet();
				for (String key : keySet)
					fw.write("," + key);
				fw.write("\n");

				for (int i = 0; i <= event.getIteration(); i++) {
					fw.write(Integer.toString(i));
					for (String key : keySet) {
						fw.write("," + this.modeshares.get(i).get(key));
					}
					fw.write("\n");
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

	public ArrayList<Map<String, Double>> getModeshares() {
		return modeshares;
	}
}
