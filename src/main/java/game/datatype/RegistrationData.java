package game.datatype;

public class RegistrationData {
    private String name;
    private String color;
    private String shipType;

    private boolean isAI = false;

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getIsAI() {
        return this.isAI;
    }

    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }
}