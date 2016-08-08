package game.model;

public class Canvas {
    private double x;
    private double y;
    private double height;
    private double width;

    public double getHalfWidth(){
        return width / 2;
    }
    
    public double getHalfHeight(){
        return height / 2;
    }
    
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
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public Canvas(double x, double y, double height, double width) {
        super();
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }
}
