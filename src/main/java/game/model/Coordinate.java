package game.model;

public class Coordinate {
	private double x;
	private double y;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

	public Coordinate(){
		this.x = 0.0d;
		this.y = 0.0d;
	}
	
	public Coordinate(double x, Double y){
		this.x = x;
		this.y = y;
	}
}
