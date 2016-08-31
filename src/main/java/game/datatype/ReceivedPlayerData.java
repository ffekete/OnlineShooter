package game.datatype;

/** This type of data will be received from client side during gameplay. */
public class ReceivedPlayerData {
    private Long id;
    private long mouseX;
    private long mouseY;
    private long canvasX;
    private long canvasY;
    private long canvasWidth;
    private long canvasHeight;
    private int weaponIndex;

    /* Getters/setters and constructors */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getMouseX() {
        return mouseX;
    }

    public void setMouseX(long mouseX) {
        this.mouseX = mouseX;
    }

    public long getMouseY() {
        return mouseY;
    }

    public void setMouseY(long mouseY) {
        this.mouseY = mouseY;
    }

    public long getCanvasX() {
        return canvasX;
    }

    public void setCanvasX(long canvasX) {
        this.canvasX = canvasX;
    }

    public long getCanvasY() {
        return canvasY;
    }

    public void setCanvasY(long canvasY) {
        this.canvasY = canvasY;
    }

    public long getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(long canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public long getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(long canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

	public int getWeaponIndex() {
		return weaponIndex;
	}

	public void setWeaponIndex(int weaponIndex) {
		this.weaponIndex = weaponIndex;
	}
}
