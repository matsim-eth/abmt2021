package abmt2021.lectures.week2.homework;

import java.util.Map;

public class Network {

	private final Map<String, Node> nodes;
	private final Map<String, Link> links;

	public Network(Map<String, Node> nodes, Map<String, Link> links) {
		this.nodes = nodes;
		this.links = links;
	}

	public Node findClosestNode(Node node) {

		double distance = Double.MAX_VALUE;
		Node closestNode = null;
		for (Node originNode : nodes.values()) {
			double currentDistance = distance(originNode, node);
			if (currentDistance < distance) {
				closestNode = originNode;
				distance = currentDistance;
			}
		}

		return closestNode;
	}

	private double distance(Node originNode, Node destinationNode) {
		return Math.sqrt(Math.pow(originNode.getXcoord() - destinationNode.getXcoord(), 2)
				+ Math.pow(originNode.getYcoord() - destinationNode.getYcoord(), 2));
	}

	public Map<String, Node> getNodes() {
		return nodes;
	}

	public Map<String, Link> getLinks() {
		return links;
	}

}
