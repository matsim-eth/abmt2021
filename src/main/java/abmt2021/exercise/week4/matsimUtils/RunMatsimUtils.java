package abmt2021.exercise.week4.matsimUtils;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.*;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.geometry.CoordUtils;

/**
 * @author kaghog created on 11.10.2021
 * @project abmt2021 - modify population by adding a new person
 */
public class RunMatsimUtils {
    public static void main(String[] args){

        /*
        * We create a new agent and add this new agent to the Population data
        Use the Person class to create a person
        Add person attributes and a travel plan to this person
        Get the Population data from the scenario
        Add the new agent to the population file
        Run scenario

        * */

        //Creating a person and then adding the person to the equil population file

        //First load the equil scenario
        String configPath = args[0]; //you can also directly add the config path here
        Config config = ConfigUtils.loadConfig(configPath);

        Scenario scenario = ScenarioUtils.loadScenario(config);

        //Get the population from the scenario
        Population population = scenario.getPopulation();

        //use population factory to create a person
        PopulationFactory popFactory = population.getFactory();

        //To create person we need the person ID, create Id of type person
        Id<Person> personId = Id.create("101", Person.class);

        //Create person
        Person person = popFactory.createPerson(personId);

        //A person needs a plan so we create a plan container to take the activities and legs of a person
        Plan plan = popFactory.createPlan();

        //Create person activities
        //There are many ways to create activity, either from facility id, coordinates, or network link
        //Create activity from coordinates

        //Create Coordinate object
        Coord coord1 = CoordUtils.createCoord(-25000 ,0); //activities in the equil all start from same place so we do same here

        Activity act1 = popFactory.createActivityFromCoord("h", coord1);
        //there are many variables or fields that can be set for an activity
        act1.setEndTime(6*3600); //error if you don't set the activity end time. you can also set the start time

        Activity act2 = popFactory.createActivityFromCoord("w", CoordUtils.createCoord(10000, 0));
        act2.setEndTime(7*3600);
        act2.setMaximumDuration(3600);
        //another way to create coordinates
        Activity act3 = popFactory.createActivityFromCoord("h", new Coord(-25000, 0));

        //create person leg
        Leg leg1 = popFactory.createLeg("car");

        //We can also create a route but it is something you can go home to do on your own
        //RouteFactories routeFactories = popFactory.getRouteFactories();
        //routeFactories.createRoute()

        //add activities to plan
        plan.addActivity(act1);
        plan.addLeg(leg1);
        plan.addActivity(act2);
        plan.addLeg(leg1);
        plan.addActivity(act3);

        //add plan to person
        person.addPlan(plan);

        //we can add some person attributes too. Maybe age, etc
        person.getAttributes().putAttribute("age", "20");
        person.getAttributes().putAttribute("sex", "female");
        person.getAttributes().putAttribute("ptSubscription", "true");


        //add person to population
        population.addPerson(person);

        //After learning IO
        //new PopulationWriter(population).write("newPopulation.xml"); //or .xml.gz


        //Let's run our modified scenario with new population

        Controler controler = new Controler(scenario);

        //We can modify the config even after loading it in the scenario by calling it through the controler
        //it depends on where you are. You would not want to modify the config using the previously created config object
        //now because it has already been loaded into the scenario
        controler.getConfig().controler().setLastIteration(1);

        controler.run();


    }

}
