package abmt2021.lectures.week2.homework;

public class Link {

	private final Node originNode;
	private final Node destinationNode;
	private final String id;

	public Link(Node originNode, Node destinationNode, String id) {
		this.originNode = originNode;
		this.destinationNode = destinationNode;
		this.id = id;
	}

	public Node getOriginNode() {
		return originNode;
	}

	public Node getDestinationNode() {
		return destinationNode;
	}

	public String getId() {
		return id;
	}

	public double linkLength() {
		return Math.sqrt(Math.pow(originNode.getXcoord() - destinationNode.getXcoord(), 2)
				+ Math.pow(originNode.getYcoord() - destinationNode.getYcoord(), 2));
	}

}
