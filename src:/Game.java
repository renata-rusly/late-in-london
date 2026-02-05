/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 * It initializes the game world (rooms, items, characters), processes user commands,
 * and manages the main game loop until the game is won or lost.
 * 
 * @author  Renata Rusly
 * @version 2025.12.02
 */

import java.util.Random;
import java.util.ArrayList;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private Character cat;
    private Item oysterCard;

    private Room livingroom; 
    private Room bedroom; 
    private Room bathroom; 
    private Room kitchen;
    
    private Room market;
    private Room street;
    private Room tubestation;
    
    private Room jubilee; 
    private Room bakerloo; 
    private Room northern;  
    private Room oxfordcircus;

    private ArrayList<Room> lastRoom;
    private int maxWeight;
    private int commandCount;
    private boolean catSatisfied;
    private boolean isGameOver;   
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        combine();
        parser = new Parser();
        player = new Player();
        maxWeight = 100;
        commandCount = 0;
        
        lastRoom = new ArrayList<>();
        catSatisfied = false;
        isGameOver = false;
    }

    /**
     * Calls all the helper methods together and setting up the starting room.
     */
    private void combine()
    {
        createRooms();   
        roomSetExits();     
        createItems();   
        cat = new Character("cat", "🐈", "your cat is a bit cranky in the morning and like to TAKE YOUR STUFFS", livingroom);  
        livingroom.addCharacter(cat);
        currentRoom = bedroom;
    }
    
    /**
     * Creates all the Room in the game map.
     */
    private void createRooms()
    {
        bedroom = new Room("in your bedroom");
        bathroom = new Room("in the bathroom");
        livingroom = new Room("in the livingroom");
        kitchen = new Room("in the kitchen"); 
        street = new Room("on the street in London");
        market = new Room("in Borough Market\nYou see a magic PORTAL in the corner of the market 🌀");
        tubestation = new Room ("in Waterloo Station");
        bakerloo = new Room ("your taking the Bakerloo Line");
        northern = new Room ("your taking the Northern Line");
        jubilee = new Room ("your taking the Jubilee Line");
        oxfordcircus = new Room ("you've arrived at Oxford Circus");
    }
    
    /**
     * Links every room with their exits to form the map.
     */
    private void roomSetExits()
    {
        bedroom.setExit("livingroom", livingroom);
        bathroom.setExit("livingroom", livingroom);        
        kitchen.setExit("livingroom", livingroom);
        livingroom.setExit("bedroom", bedroom);
        livingroom.setExit("bathroom", bathroom);
        livingroom.setExit("kitchen", kitchen);
        livingroom.setExit("street", street);
        street.setExit("livingroom", livingroom);
        street.setExit("market", market);
        street.setExit("tubestation", tubestation);  
        market.setExit("street", street);   
        tubestation.setExit("street", street);
        tubestation.setExit("bakerloo", bakerloo);
        tubestation.setExit("northern", northern);
        tubestation.setExit("jubilee", jubilee); 
        jubilee.setExit("tubestation", tubestation);
        bakerloo.setExit("tubestation", tubestation);
        bakerloo.setExit("oxfordcircus", oxfordcircus);
        northern.setExit("tubestation", tubestation);
    }
    
    /**
     * Creates all Item objects and places them into their starting rooms.
     */
    private void createItems()
    {
        Item phone, catFood, chocolate, snack, shampoo, houseKey;
        phone = new Item("phone", "📱", 30, true,"hm.., i wonder where's my friend is");
        catFood = new Item("catfood", "🥫", 50, true, "omg, i haven't fed the cat yet");
        chocolate = new Item("chocolate", "🍫", 20, true, "We need little sweet a day");
        snack = new Item ("chips", "🍟", 20, true, "My go-to snack");
        shampoo = new Item("shampoo", "🧴", 60, false, "do i need to take a morning shower before going out?");
        oysterCard = new Item("oystercard", "💳", 20, true,"will surely needs this to get into the station");
        houseKey = new Item ("housekey", "🔑", 30, true, " better not forget this when i left the house ");
        
        bathroom.addItem(shampoo);
        bathroom.addItem(phone);
        livingroom.addItem(houseKey);
        kitchen.addItem(catFood);
        kitchen.addItem(chocolate);
        kitchen.addItem(snack);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            
            if (! finished){
                commandCount++;
                if (commandCount % 3 == 0) {
                moveCatRandomly();
            }
            }
        }
        System.out.println("Thank you for playing! Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("\nWelcome to Late in London💂!");
        System.out.println("You woke up a bit late this morning \nand your friend already left to explore London 🏃");
        System.out.println("With 12 hours left to your flight 🛫,");
        System.out.println("Get all your ESSENTIALS to FIND YOUR FRIENDS before it’s too late!");
        System.out.println("Type 'help' to see your available commands 💡\n");
        outputFormat();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command the command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        if(command.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        }
        String commandWord = command.getCommandWord();
        switch (commandWord)
        {
            case "help":
                printHelp();
                break; 
        
            case "go":
                goRoom(command);
                break;
                
            case "back":
                back();
                break;
        
            case "quit":
                wantToQuit = quit(command);
                break;
            
            case "pick":
                pickItem(command);
                break; 
        
            case "drop":
                dropItem(command);
                break;
        
            case "check":
                check();
                break;
                
            case "feed":
                feedCat(command);
                break;
        }
        if (isGameOver)
        {
            return true;
        }
        return wantToQuit;
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Your command words are:");
        System.out.println("\n(NOTES: the 'feed' command needs 3 words, e.g., 'feed cat chocolate')");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        if (direction.equalsIgnoreCase("portal")) 
        {
            if (currentRoom == market) 
            {
                    portal(); 
            } else 
            {
                System.out.println("there is no portal here!");
                outputFormat();
            }
            return; 
        }
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null)
        {
            System.out.println("there is no such door!");
            return;
        }
        else if (nextRoom == street && currentRoom == livingroom)
        {
            if (!canEnterStreet(command))
            {
                return; 
            }
        }
        else if (nextRoom == livingroom && currentRoom == street)
        {
            if (!canEnterApartment()) 
            {
                return; 
            }
        }
        else if (nextRoom == tubestation) 
        {
            if (!canEnterStation()) 
            {
                return;
            }
        }
        else if (nextRoom == jubilee)
        {
            System.out.println("Bad news! the Jubilee Line is suspended for the day. You'll need to find another route.");
            return;
        }
        else if (nextRoom == northern)
        {
            if (!canEnterNorthern(command)) 
            {
                return;
            }
        }
        else if (nextRoom == oxfordcircus)
        {
            System.out.println("\nyou've arrived at Oxford Circus ");
            System.out.println("You go out of the tube and see your friend waving at you! 👋");
            System.out.println("You run over and give them a huge high-five.");
            System.out.println("'I thought you weren't going to make it!' they say.");
            System.out.println("'Me too,' you laugh, checking your watch.");
            System.out.println("You made it with plenty of time to spare before your flight.");
            System.out.println("\n 🎉🏆 CONGRATULATIONS! YOU WON! 🏆🎉");
            
            isGameOver = true; 
            return;
        }
        lastRoom.add(currentRoom);
        currentRoom = nextRoom;
        outputFormat();
    }
    
    /**
     * Checks if the player has the required oyster card to enter the station.
     * @return true if the player has the card, false otherwise.
     */
    private boolean canEnterStation() 
    {
        if (!player.hasItem ("oystercard"))
        {
            System.out.println("You can't get in because you don't bring your oyster card...");
            return false;
        }
        return true; 
    }
    
    /**
     * Checks if the player has all essential items before entering the street.
     * @param command the user command, checked for a confirmation string.
     * @return true if entry is allowed, false if blocked or confirmation is needed.
     */
    private boolean canEnterStreet(Command command) 
    {
        if (!player.hasItem ("phone")|| !player.hasItem ("oystercard")|| !player.hasItem ("housekey"))
        { 
            if (!command.hasThirdWord() || !command.getThirdWord().equalsIgnoreCase("yes"))
            {
                System.out.println("Hmm, you don't have all your ESSENTIALS yet. Are you sure you want to go out?");
                System.out.println("If you are sure, type 'go street yes'.");
                return false;
            } 
            if (!player.hasItem ("housekey"))
            { 
                System.out.println("You lock yourself out, and didn't bring your HOUSEKEY. You lost!");
                isGameOver = true;
            }
        }
        System.out.println("We're ready to go!");
        return true;
    }
    
    /**
     * Checks if the player has the required housekey to enter the housekey.
     * @return true if the player has the housekey, false otherwise.
     */
    private boolean canEnterApartment() 
    {
        if (!player.hasItem ("housekey"))
        { 
            System.out.println("You can't get in you don't have your HOUSEKEY with you!");
            return false;  
        }
        return true;
    }
    
    /**
     * Handles logic for the Northern line, warning the player before they lose.
     * @param command the user command, checked for a confirmation string.
     * @return true if the player confirms entry (triggering game over), false otherwise.
     */
    private boolean canEnterNorthern(Command command) 
    {
        if (!command.hasThirdWord() || !command.getThirdWord().equalsIgnoreCase("yes"))
            {
                System.out.println("Are you sure you're taking this line?");
                System.out.println("If you are sure, type 'go northern yes'.");
                return false;
            } 
        System.out.println("the doors hiss shut, and as the train pulls away, panic hits: wrong platform, wrong line. You know the search is over. You lost!");
        isGameOver = true;
        return true;
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) 
        {
            System.out.println("Quit what?");
            return false;
        }
        else 
        {
            return true; 
        }
    }
    
    /**
     * Moves the player back to the previously visited room using the last room log.
     */ 
    private void back()
    {
        if (lastRoom.isEmpty()) 
        {
            System.out.println("You can't go back any further!");
            return;
        }
        int index = lastRoom.size()-1;
        currentRoom = lastRoom.get(index);
        lastRoom.remove(index);  
        outputFormat();
    }
    
    /**
     * Removes an item from the player's inventory and adds it to the current room.
     * @param command the command containing the name of the item to drop.
     */
    private void dropItem(Command command)
    {
        if(!command.hasSecondWord()) 
        {
            System.out.println("Drop what?");
            return;
        }
        
        String itemName = command.getSecondWord();
        Item itemObject = player.getItemString(itemName);
        
        if (itemObject == null) 
        {
        System.out.println("That item is not in your inventory!");
        }
        else 
        {
            player.dropItem(itemObject);
            currentRoom.addItem(itemObject);
        }
        outputFormat();
    }
    
    /**
     * Adds an item from the current room to the player's inventory if carryable.
     * @param command the command containing the name of the item to pick up.
     */
    private void pickItem(Command command) 
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Pick what?");
            return;
        }
        
        String itemName = command.getSecondWord();
        Item itemObject = currentRoom.getItemCheck(itemName);
        
        if (itemObject == null)
        {
            System.out.println("That item is not in this room!");
            return;
        }
       
        if (!itemObject.canCarry()) 
        {
            System.out.println("I don't think I need this item right now");
            return;
        }
    
        int currentTotalWeight = player.getTotalWeight();
        int itemWeight = itemObject.getWeight();
        
        if (currentTotalWeight + itemWeight > maxWeight)
        {
            System.out.println("Oh no, no more space left to carry the item!");
            return;
        }
        
        else {
            player.pickItem(itemObject);
            currentRoom.removeItem(itemObject);
        }
        
        if (itemName.equalsIgnoreCase("phone"))
        {
            System.out.println("Notification from your friend pop ups:");
            System.out.println("Hey, meet me at OXFORD CIRCUS , with BAKERLOO LINE!xx");
            return;
        }
        outputFormat();
    }
    
    /**
     * Prints the current contents of the player's inventory and total weight.
     */
    private void check()
    {
        System.out.println(player.getInventoryString());
        System.out.println("Total Weight:"+player.getTotalWeight()+"/"+maxWeight);
    }
    
    /**
     * Processes the logic for feeding the cat a specific item from inventory.
     * @param command the command containing the target ('cat') and the item name.
     */
    private void feedCat(Command command)
    {
        if (!command.hasSecondWord() || !command.hasThirdWord())
        {
            System.out.println("Feed who with what? (Usage: feed [target] [item])");
            outputFormat();
            return;
        }

        String targetName = command.getSecondWord();
        String itemName = command.getThirdWord(); 
        
        if (!targetName.equalsIgnoreCase("cat"))
        {
            System.out.println("Huh");
            outputFormat();
            return;
        }
        
        Item itemToFeed = player.getItemString(itemName); 
        
        if (itemToFeed == null)
        {
            System.out.println("You don't have the food to feed the cat.");
            outputFormat();
            return;
        }
    
        if (itemName.equalsIgnoreCase("chocolate"))
        {
            player.dropItem(itemToFeed);
            System.out.println("You just fed your cat chocolate! It becomes sick and you lose time taking it to the hospital. You Lost!");
            isGameOver = true;
        }
        else if (itemName.equalsIgnoreCase("catfood"))
        {
            player.dropItem(itemToFeed);
            System.out.println("the cat happily eats the catfood, purrs, and moves away from the Oyster Card!");
            moveCharacter(cat, bedroom);
            currentRoom.addItem(oysterCard); 
            catSatisfied = true;
        } else
        {
            player.dropItem(itemToFeed);
            currentRoom.addItem(itemToFeed);
            System.out.println("the cat looks at the " + itemName + " and sniffs disdainfully. It clearly wants something else.");
        }
        outputFormat();
        
    }
    
    /**
     * Handles the coordinated movement of a character between two rooms.
     * @param character the character to move (e.g., 'cat').
     * @param newRoom the room the character is moving to.
     */
    private void moveCharacter(Character character, Room newRoom)
    {
        Room oldRoom = character.getCurrentRoom();
        oldRoom.removeCharacter(character);
        character.setRoom(newRoom);
        newRoom.addCharacter(character);
    }
    
    /**
     * Moves the cat character to a random adjacent room (excluding the street)
     */
    private void moveCatRandomly()
    {
        Room currentCatRoom = cat.getCurrentRoom();
        if (currentCatRoom == street) 
        {
            return;
        }

        if (catSatisfied)
        {
            return;
        }
    
        java.util.Set<String> exitDirections = currentCatRoom.getExitDirections();
        java.util.ArrayList<String> movableDirections = new java.util.ArrayList<>();
        for (String direction : exitDirections) 
        {
            Room potentialRoom = currentCatRoom.getExit(direction); 
        
            if (potentialRoom != null && potentialRoom != street) 
            {
                 movableDirections.add(direction);
            }
        }
        
        if (movableDirections.isEmpty())
        {
            moveCharacter(cat, livingroom);
            return;
        }
        
        Random random = new Random();
        String randomDirection = movableDirections.get(random.nextInt(movableDirections.size()));
        
        Room nextRoom = currentCatRoom.getExit(randomDirection);
        if (nextRoom != null) 
        {
            Room oldRoom = currentCatRoom;
            moveCharacter(cat, nextRoom);

            if (oldRoom == currentRoom) 
            {
                System.out.println("\n the cat just runs away to the another room!");
            } else if (nextRoom == currentRoom) 
            {
                System.out.println("\n the cat just wandered into your room.");
            }   
            outputFormat();
        }
    }  
    
    /**
     * Prints the long description, items, and characters in the current room.
     */
    private void outputFormat()
    {
        System.out.println(" ");
        System.out.println(currentRoom.getLongDescription());
        System.out.println(currentRoom.getItemString());
        System.out.println(currentRoom.getCharacterString());
    }

    /**
     * Creates and returns a list containing all rooms available to transport to in the game (except jubilee, northern, and bakerloo)
     * @return An ArrayList containing all valid Room objects.
     */
    private ArrayList<Room>getPortalRooms()
    {
        ArrayList<Room> portalRooms = new ArrayList<>();
        portalRooms.add(livingroom);
        portalRooms.add(bedroom);
        portalRooms.add(bathroom);
        portalRooms.add(kitchen);
        portalRooms.add(market);
        portalRooms.add(street);
        portalRooms.add(tubestation);
        portalRooms.add(oxfordcircus);
        return portalRooms;
    }
    
    /**
     * Teleports the player to a random room selected from the list of all rooms.
     */
    private void portal()
    {
        ArrayList<Room> possibleRooms = getPortalRooms();
        Random randomRoom = new Random();
        int index = randomRoom.nextInt(possibleRooms.size());  
        currentRoom = possibleRooms.get(index);
        System.out.println("Whoosh! You have been teleported to a random place!");
        outputFormat();
    }
}
