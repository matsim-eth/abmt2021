package abmt2021.lectures.week2.homework;

import java.util.HashMap;
import java.util.Map;

public class MainProgram {
		
	public static void main(String[] args) {

		// you can use https://epsg.io/ to obtain the coordinates
		// using any coordinate system

		// first we create four nodes

		Node nodeZurich = new Node(2683229.36, 1247613.22, "Zurich");
		Node nodeLugano = new Node(2717168.21, 1095959.19, "Lugano");
		Node nodeBern = new Node(2601012.26, 1199875.32, "Bern");
		Node nodeLausanne = new Node(2538074.48, 1152247.11, "Lausanne");

		// we put them in a HashMap which creates a link between
		// a unique id and the object, ensuring fast object query

		// here Map is in interface and HashMap is an implementation
		// we can also use HashMap on both sides, but we cannot use Map
		Map<String, Node> nodes = new HashMap<>();

		nodes.put(nodeZurich.getId(), nodeZurich);
		nodes.put(nodeLugano.getId(), nodeLugano);
		nodes.put(nodeLausanne.getId(), nodeLausanne);
		nodes.put(nodeBern.getId(), nodeBern);

		// we now create six connections between cities
		// we only create one directional links as the distance does
		// not depend on the direction

		Link linkZurichLugano = new Link(nodeZurich, nodeLugano, "0001");
		Link linkZurichBern = new Link(nodeZurich, nodeBern, "0002");
		Link linkZurichLausanne = new Link(nodeZurich, nodeLausanne, "0003");
		Link linkLuganoBern = new Link(nodeLugano, nodeBern, "0004");
		Link linkLuganoLausanne = new Link(nodeLugano, nodeLausanne, "0005");
		Link linkBernLausanne = new Link(nodeBern, nodeLausanne, "0006");

		Map<String, Link> links = new HashMap<>();

		links.put(linkZurichLugano.getId(), linkZurichLugano);
		links.put(linkZurichBern.getId(), linkZurichBern);
		links.put(linkZurichLausanne.getId(), linkZurichLausanne);
		links.put(linkLuganoBern.getId(), linkLuganoBern);
		links.put(linkLuganoLausanne.getId(), linkLuganoLausanne);
		links.put(linkBernLausanne.getId(), linkBernLausanne);

		Network network = new Network(nodes, links);

		Node nodeZug = new Node(2681684.12, 1224847.58, "Zug");
		Node nodeThun = new Node(2614485.45, 1178555.23, "Thun");
		System.out.println("Closest city to Zug is " + network.findClosestNode(nodeZug).getId());
		System.out.println("Closest city to Thun is " + network.findClosestNode(nodeThun).getId());
		Link longestConnection = null;
		double distance = Double.MIN_VALUE;

		for (Link link : network.getLinks().values()) {
			if (link.linkLength() > distance) {
				distance = link.linkLength();
				longestConnection = link;
			}
		}
		// \n makes a new line
		// %s allows you to pass a String, %f lets you pass a float(double) value
		
		System.out.format("The longest crowflyy distance is between %s and %s with the distance %f \n",
				longestConnection.getOriginNode().getId(), longestConnection.getDestinationNode().getId(), distance);

		Node nLugano = network.getNodes().get("Lugano");
		System.out.println(nLugano.getId() + " " + nLugano.getXcoord() + " " + nLugano.getYcoord());
	
	}

}
