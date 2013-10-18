import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Player {
	private Enums.Character character;
	private Location location = null;
	private List<Enums.Weapon> weaponCards;
	private List<Enums.RoomName> roomCards;
	private List<Enums.Character> characterCards;
	private boolean isControlled;

	public Player(Enums.Character character, boolean iC) {
		this.character = character;
		this.weaponCards = new LinkedList<Enums.Weapon>();
		this.roomCards = new LinkedList<Enums.RoomName>();
		this.characterCards = new LinkedList<Enums.Character>();
		isControlled = iC;
	}

	/**
	 * Returns whether this player is controlled by a player or not.
	 * 
	 * @return
	 */
	public boolean isControlled() {
		return isControlled;
	}

	/**
	 * Returns the location that this player is on
	 * 
	 * @return Location
	 */
	public Location getLocation() {
		return location;
	}

	public Enums.Character getCharacter() {
		return character;
	}

	public List<Enums.Weapon> getWeaponCards() {
		return weaponCards;
	}

	public List<Enums.RoomName> getRoomCards() {
		return roomCards;
	}

	public List<Enums.Character> getCharacterCards() {
		return characterCards;
	}

	/**
	 * Removes its current location's reference to the player and moves it to
	 * the specified location
	 * 
	 * @param nextLocation
	 */
	public void moveLocation(Location nextLocation) {
		if (location != null)
			location.setCharacter(null);
		location = nextLocation;
		nextLocation.setCharacter(character);
	}

	public void moveRoom(Room suggestedRoom) {
		for (Location l : suggestedRoom.getLocations()) {
			if (l.getCharacter() == null) {
				moveLocation(l);
				return;
			}
		}
	}

	/**
	 * Sets the state of whether this player is controlled or not.
	 * 
	 * @param b
	 */
	public void setControl(boolean b) {
		isControlled = false;
	}

	public String toString() {
		return character.toString();
	}

	public void drawGUI(Graphics g) {
		//Draws the image representing the player onto the GUI
		try {
			g.drawImage(ImageIO.read(new File("Images/"+character.toString()+".png")), getLocation().getPoint().x*27+3, getLocation().getPoint().y*27+3, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cant draw: " + character.toString());
			e.printStackTrace();
		}
	}
}
