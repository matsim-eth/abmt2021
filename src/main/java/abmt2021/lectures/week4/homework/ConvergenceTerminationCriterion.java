package abmt2021.lectures.week4.homework;

import java.util.ArrayList;
import java.util.Map;

import org.matsim.core.controler.TerminationCriterion;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ConvergenceTerminationCriterion implements TerminationCriterion {

	private final ModeshareControlerListener modeshareControlerListener;
	private final double delta;

	@Inject
	public ConvergenceTerminationCriterion(ModeshareControlerListener modeshareControlerListener,
			@Named("delta") Double delta) {
		this.modeshareControlerListener = modeshareControlerListener;
		this.delta = delta;
	}

	@Override
	public boolean mayTerminateAfterIteration(int iteration) {

		return true;
	}

	@Override
	public boolean doTerminate(int iteration) {
		if (iteration > 0) {
			ArrayList<Map<String, Double>> modeshares = modeshareControlerListener.getModeshares();
			int size = modeshares.size();
			// we stop the simulation if the change in the mode share of car is lower than delta
			if (Math.abs(modeshares.get(size - 1).get("car") - modeshares.get(size - 2).get("car")) < this.delta)
				return true;
		}
		return false;
	}

}
