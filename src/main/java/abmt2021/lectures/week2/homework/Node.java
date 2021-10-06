package abmt2021.lectures.week2.homework;

public class Node {
	
	private final double xcoord;
	private final double ycoord;
	private final String id;
	public Node(double xcoord, double ycoord, String id) {
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		this.id = id;
	}
	public double getXcoord() {
		return xcoord;
	}
	public double getYcoord() {
		return ycoord;
	}
	public String getId() {
		return id;
	}

}
