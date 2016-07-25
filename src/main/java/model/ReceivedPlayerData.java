package model;

/** This type of data will be received from client side during gameplay. */
public class ReceivedPlayerData {
	private Long id;
	private Long mouseX;
	private Long mouseY;
	

	/* Getters/setters and constructors */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMouseX() {
		return mouseX;
	}
	public void setMouseX(Long mouseX) {
		this.mouseX = mouseX;
	}
	public Long getMouseY() {
		return mouseY;
	}
	public void setMouseY(Long mouseY) {
		this.mouseY = mouseY;
	}

}
