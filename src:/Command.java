/**
 * Class Command - holds information about a user command.
 * 
 * A command consists of up to three strings: a command word, a second word (target),
 * and a third word (object). This class holds these parts to be processed by the game logic.
 * 
 * @author  Renata Rusly
 * @version 2025.12.02
*/


public class Command
{
    private String commandWord;
    private String secondWord;
    private String thirdWord;

    /**
     * Create a command object with up to three words.
     * @param firstWord the first word of the command (null if not recognised).
     * @param secondWord the second word of the command.
     * @param thirdWord the third word of the command.
     */
    public Command(String firstWord, String secondWord, String thirdWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
    }

    /**
     * Return the command word (the first word) of this command.
     * @return the command word, or null if the command was not understood.
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * Return the second word of this command.
     * @return the second word of this command, or null if only one word was entered.
     */
    public String getSecondWord()
    {
        return secondWord;
    }
    
    /**
     * Return the third word of this command.
     * @return the third word of this command, or null if it was not entered.
     */
    public String getThirdWord()
    {
        return thirdWord;
    }

    /**
     * Check if the command word was understood.
     * @return true if this command was not understood (command word is null).
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * Check if the command includes a second word.
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
    
    /**
     * Check if the command includes a third word.
     * @return true if the command has a third word.
     */
    public boolean hasThirdWord()
    {
        return (thirdWord != null);
    }
}

