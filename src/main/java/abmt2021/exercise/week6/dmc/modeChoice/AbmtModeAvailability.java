package abmt2021.exercise.week6.dmc.modeChoice;

import org.matsim.api.core.v01.population.Person;
import org.matsim.contribs.discrete_mode_choice.model.DiscreteModeChoiceTrip;
import org.matsim.contribs.discrete_mode_choice.model.mode_availability.ModeAvailability;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author kaghog created on 18.10.2021
 * @project abmt2021
 *
 * Here we define modes that will be available as a choice set for each person based on some defined constraints.
 * Here we place contraints on car drivers being above 17 and having a car available as part of their attribute
 */
public class AbmtModeAvailability implements ModeAvailability {

    @Override
    public Collection<String> getAvailableModes(Person person, List<DiscreteModeChoiceTrip> list) {
        //The ModeAvailability class we are implementing requires a collection of modes
        // using the HashSet data structure that extends Collections, we create an empty collection set
        // where we would add modes that a person can have available based on the constraints we define
        Collection<String> modes = new HashSet<>();

        // Modes that are always available to everyone
        modes.add("walk");
        modes.add("pt");
        modes.add("bike");

        //define constraints for using car
        //available only to those within driving age and have car available always
        int age = Integer.parseInt(person.getAttributes().getAttribute("age").toString());
        String carAvail = person.getAttributes().getAttribute("carAvail").toString();
        if(age > 17 && carAvail.equals("always")) {
            modes.add("car");
        }
        return modes;

        //For this code to work, we would need to bind it and for it to be used, we would need to specify that it should be used under the DiscreteModechoice module in the config file
        //either through the run script or directly on the config file. Check the config file to know what modeAvailability option is configured there
    }
}
