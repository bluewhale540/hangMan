import java.util.ArrayList;
import java.util.Scanner;

public class HangmanServer {
    int movesRemaining = 6;
    public String hiddenWord = "community";
    public String newHiddenWord= "community";
    int hiddenWordLength = 9;
    int location = 0;
    String[] wordBank = {"country", "roads", "take", "me", "home", "to", "the", "place", "i", "belong"};
    ArrayList<String> blankWord = new ArrayList<>();
    ArrayList<String> usedGuesses = new ArrayList<>();
    int usedCheckVal = 0;
    int findMoreLocation = -1;
    Scanner scan = new Scanner(System.in);
    String message = "";

    public void gameInitializer() {
        //System.out.print("Welcome to Hangman. Please enter your name: ");
        //String name = scan.nextLine().toLowerCase();
        //System.out.println("Hello " + name +".");
        int randomizer = (int) (Math.random() * 9 ); //randomly selects hidden word
        hiddenWord = wordBank[randomizer];
        newHiddenWord = hiddenWord;
        hiddenWordLength = hiddenWord.length();
        for (int i = 0; i < hiddenWordLength; i++) { //adds - to blankWord for each letter in the hidden word
            blankWord.add("-");
        }
        //guessRetriever();
    }

    public void guessRetriever(String guess) { //accepts guess
        //printMan();
        //printArrayList(blankWord);
        if (!blankWord.contains("-")) { //win condition checker
            message = "You have guessed the word!";
        }
        //System.out.print("Please enter your guess: ");
        //guess = scan.next();
        usedGuesses.add(guess);
        int guessLength = guess.length();
        if (guessLength == 1) {
            guessCheckSingle(guess);
        }
        else if (guessLength == hiddenWordLength) {
            //guessCheckMultiple(guess);
            message = "That is not a valid guess.";
            usedCheckVal = 1;
        }
        else {
            message = "That is not a valid guess.";
            usedCheckVal = 1;
            //guessRetriever();
        }
    }

    public void usedChecker(String guess) { //check if guess was used
        message = "";
        if (usedGuesses.contains(guess)) {
            usedCheckVal = 1;
            message = "You already used that guess.";
        }
        else {
            usedGuesses.add(guess);
            usedCheckVal = 0;
            guessRetriever(guess);
        }
    }

    public int guessCheckSingle(String guess) { //Checks single character guesses
        location = newHiddenWord.indexOf(guess);
        if (location == -1) {
            message = "Letter is not in the hidden word. Try again.";
            movesRemaining--;
            //guessRetriever();
            return location;
        }
        else {
            blankWord.set(location, guess);
            newHiddenWord = hiddenWord.replaceFirst(guess, "-");
            gcsFindMore(guess);
            //guessRetriever();
            return location;
        }
    }

    public void gcsFindMore(String guess) { //Checks if guess is present twice
        findMoreLocation = newHiddenWord.indexOf(guess);
        if (findMoreLocation == -1) {}
        else {
            blankWord.set(findMoreLocation, guess);
            newHiddenWord = hiddenWord.replaceFirst(guess, "-");
        }
    }

    public void gcsFindThird(String guess) { //Checks if guess is present three times
        newHiddenWord = newHiddenWord.replaceFirst(guess, "-");
        findMoreLocation = newHiddenWord.indexOf(guess);
        if (findMoreLocation == -1) {}
        else {
            blankWord.set(findMoreLocation, guess);
        }
    }

    public void guessCheckMultiple(String guess) { //Checks entire word guesses
        if (hiddenWord.equals(guess)) {
            message = "You have guessed the word!";
        }
        else {
            movesRemaining--;
            message = "That is incorrect. Please try again.";
            //guessRetriever();
        }
    }

    public void printArrayList(ArrayList<String> list) { //prints elements of ArrayList
        for (String letter : list)
            System.out.print(letter);
        System.out.println("");
    }

    public void printMan() { //prints ASCII image with progress, deprecated
        switch (movesRemaining) {
            default: {
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    o ");
                System.out.println(" |   /|\\");
                System.out.println(" |   / \\");
                System.out.print("_|_  He ded. The word was: " +hiddenWord);
                System.exit(0);
            }
            case 1: {
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    o ");
                System.out.println(" |   /|\\");
                System.out.println(" |   / ");
                System.out.println("_|_");
                break;
            }
            case 2: {
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    o ");
                System.out.println(" |   /|\\");
                System.out.println(" |   ");
                System.out.println("_|_");
                break;
            }
            case 3: {
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    o ");
                System.out.println(" |   /|");
                System.out.println(" |");
                System.out.println("_|_");
                break;
            }
            case 4: {
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    o ");
                System.out.println(" |    |");
                System.out.println(" |");
                System.out.println("_|_");
                break;
            }
            case 5: {
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    o ");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|_");
                break;
            }
            case 6: {
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|_");
                break;
            }
        }
    }
}