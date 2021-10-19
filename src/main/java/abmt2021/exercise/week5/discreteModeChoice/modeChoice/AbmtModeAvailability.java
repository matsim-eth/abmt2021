package abmt2021.exercise.week5.discreteModeChoice.modeChoice;

import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.population.Person;
import org.matsim.contribs.discrete_mode_choice.model.DiscreteModeChoiceTrip;
import org.matsim.contribs.discrete_mode_choice.model.mode_availability.ModeAvailability;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author kaghog created on 18.10.2021
 * @project abmt2021
 */
public class AbmtModeAvailability implements ModeAvailability {
    @Override
    public Collection<String> getAvailableModes(Person person, List<DiscreteModeChoiceTrip> list) {
        Collection<String> modes = new HashSet<>();

        // Modes that are always available to everyone
        modes.add("walk");
        modes.add("pt");
        modes.add("bike");

        //available only to those within driving age and have car available always
        int age = Integer.parseInt(person.getAttributes().getAttribute("age").toString());
        String carAvail = person.getAttributes().getAttribute("carAvail").toString();
        if(age > 17 && carAvail.equals("always")) {
            modes.add("car");
        }
        return modes;
    }
}
