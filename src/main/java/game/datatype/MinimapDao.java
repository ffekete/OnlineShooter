package game.datatype;

public class MinimapDao {
    private double x;
    private double y;
    private String color;

    public MinimapDao(double x, double y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getColor() {
        return color;
    }
}