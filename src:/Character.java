/**
 * Class Character - a non-player entity (NPC) in the game.
 *
 * This class represents characters (like the cat) that have a name, description,
 * and location. They can move between rooms and interact with the player.
 *
 * @author  Renata Rusly
 * @version 2025.12.02
 */
public class Character
{
    // instance variables - replace the example below with your own
    private String characterName;
    private String characterEmoji;
    private String characterDescription;
    private Room currentRoom;


    /**
     * Create a new item.
     * @param name the name of the character.
     * @param emoji of the character.
     * @param description of the character.
     * @param current location of the charcter.
     */
    public Character(String name, String emoji, String description, Room roomNow){
        characterName = name;
        characterEmoji = emoji;
        characterDescription = description;
        currentRoom = roomNow;
    }
    
    /**
     * Get the name of the character.
     * @return the character's name as a string.
     */
    public String getCharacterName() 
    {
        return characterName;
    }
    
    /**
     * Get the emoji icon representing the character.
     * @return the string containing the character's emoji.
     */
    public String getCharacterEmoji() 
    {
        return characterEmoji;
    }
    
    /**
     * Get the character's decription.
     * @return the string character's description.
     */
    public String getCharacterDescription()
    {
        return characterDescription;
    }
    
    /**
     * Updates the character's current location reference.
     * @param newRoom the new Room object to move the character into.
     */
    public void setRoom(Room newRoom)
    {
        this.currentRoom = newRoom;
    }
    
    /**
     * Retrieves the room where the character is currently located.
     * @return the Room object representing the character's current location.
     */
    public Room getCurrentRoom()
    {
       return currentRoom;
    }
    
}