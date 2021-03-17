import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HangmanGraphics extends Application {
    HangmanServer game = new HangmanServer();
    FlowPane pane = new FlowPane();
    Pane hangMan = new Pane();
    GridPane blanks = new GridPane();
    GridPane messagePane = new GridPane();
    String guess = "";
    Text message = new Text("");

    GridPane letters = new GridPane();
    GridPane incorrectLetters = new GridPane();
    Text a = new Text("a");
    Text b = new Text("b");
    Text c = new Text("c");
    Text d = new Text("d");
    Text e = new Text("e");
    Text f = new Text("f");
    Text g = new Text("g");
    Text h = new Text("h");
    Text i = new Text("i");
    Text j = new Text("j");
    Text k = new Text("k");
    Text l = new Text("l");
    Text m = new Text("m");
    Text n = new Text("n");
    Text o = new Text("o");
    Text p = new Text("p");
    Text q = new Text("q");
    Text r = new Text("r");
    Text s = new Text("s");
    Text t = new Text("t");
    Text u = new Text("u");
    Text v = new Text("v");
    Text w = new Text("w");
    Text x = new Text("x");
    Text y = new Text("y");
    Text z = new Text("z");
    Text guessText = new Text();

    Circle head = new Circle();
    Line line5 = new Line(250, 120, 250, 210);
    Line leg1 = new Line(225, 250, 250, 210);
    Line leg2 = new Line(275, 250, 250, 210);
    Line arm1 = new Line(225, 140, 250, 170);
    Line arm2 = new Line(275, 140, 250, 170);
    TextField guessField = new TextField();
    Button OKButton = new Button("OK");

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        game.gameInitializer();

        pane.setOrientation(Orientation.VERTICAL);
        pane.getChildren().add(getTop());
        pane.getChildren().add(getGuessBox());
        pane.getChildren().add(getLetters());
        pane.getChildren().add(getIncorrectLetters());
        pane.getChildren().add(getBlanks());
        pane.getChildren().add(getMessage());

        Scene scene = new Scene(pane, 350, 580);
        primaryStage.setTitle("Hangman"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private Pane getTop() { //Hangman graphic
        head.setCenterX(250);
        head.setCenterY(100);
        head.setRadius(20);
        head.setStroke(Color.BLACK);
        head.setFill(Color.WHITE);
        Line line1 = new Line(50, 300, 200, 300);
        Line line2 = new Line(125, 300, 125, 50);
        Line line3 = new Line(125, 50, 250, 50);
        Line line4 = new Line(250, 50, 250, 80);
        Line separator = new Line(0, 360, 350, 360);
        Rectangle top = new Rectangle(0,0,350,360);
        top.setFill(Color.WHITE);

        hangMan.getChildren().add(top);
        hangMan.getChildren().add(line1);
        hangMan.getChildren().add(line2);
        hangMan.getChildren().add(line3);
        hangMan.getChildren().add(line4);
        hangMan.getChildren().add(separator);
        return hangMan;
    }

    private GridPane getGuessBox(){ //Input area and OK button
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setPadding(new Insets(10, 0, 0, 10));
        pane.add(new Label("Your Guess:"),0,0);
        pane.add(guessField,1,0);
        pane.add(OKButton,2,0);
        OKButton.setOnAction(new GuessHandler());
        pane.add(new Text("Available Letters:"),0,1);
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        OKButton.setDisable(true);

        //disables button unless text in field
        guessField.textProperty().addListener((ov, text, currentText) -> {
            OKButton.setDisable(currentText.equals(""));
        });

        //restricts field to letters
        guessField.textProperty().addListener((observable, text, currentText) -> {
            if (!currentText.matches("a-zA-Z*")) {
                guessField.setText(currentText.replaceAll("[^a-zA-Z]", ""));
            }
        });

        return pane;
    }

    private GridPane getLetters() { //Available letters
        letters.setAlignment(Pos.TOP_LEFT);
        letters.setPadding(new Insets(5, 0, 0, 10)); //+55

        letters.add(a,0,0);
        letters.add(b,1,0);
        letters.add(c,2,0);
        letters.add(d,3,0);
        letters.add(e,4,0);
        letters.add(f,5,0);
        letters.add(g,6,0);
        letters.add(h,7,0);
        letters.add(i,8,0);
        letters.add(j,9,0);
        letters.add(k,10,0);
        letters.add(l,11,0);
        letters.add(m,12,0);
        letters.add(n,13,0);
        letters.add(o,14,0);
        letters.add(p,15,0);
        letters.add(q,16,0);
        letters.add(r,17,0);
        letters.add(s,18,0);
        letters.add(t,19,0);
        letters.add(u,20,0);
        letters.add(v,21,0);
        letters.add(w,22,0);
        letters.add(x,23,0);
        letters.add(y,24,0);
        letters.add(z,25,0);

        letters.setHgap(5.5);
        letters.setVgap(5.5);
        return letters;
    }

    private GridPane getIncorrectLetters() { //Incorrect letters
        incorrectLetters.setPadding(new Insets(5,0,0,10));
        incorrectLetters.add(new Text("Incorrect Letters: "),0,0);
        incorrectLetters.setHgap(5.5);
        return incorrectLetters;
    }

    private GridPane getBlanks(){ //Hidden word area
        blanks.setPadding(new Insets(20, 0, 0, 0));
        blanks.setAlignment(Pos.CENTER);
        for (int i = 0; i < game.hiddenWordLength; i++) { //adds - for each letter in the hidden word
            Text blank = new Text("__ ");
            blank.setFont(Font.font(20));
            blanks.add(blank,i,0);
        }
        return blanks;
    }

    private GridPane getMessage(){ //pane for output
        messagePane.setPadding(new Insets(30,0,0,0));
        messagePane.setAlignment(Pos.CENTER);
        return messagePane;
    }

    public void guessToText(String guess){ //converts string guess to Text object
        switch (guess){
            case "a": guessText = a;
                break;
            case "b": guessText = b;
                break;
            case "c": guessText = c;
                break;
            case "d": guessText = d;
                break;
            case "e": guessText = e;
                break;
            case "f": guessText = f;
                break;
            case "g": guessText = g;
                break;
            case "h": guessText = h;
                break;
            case "i": guessText = i;
                break;
            case "j": guessText = j;
                break;
            case "k": guessText = k;
                break;
            case "l": guessText = l;
                break;
            case "m": guessText = m;
                break;
            case "n": guessText = n;
                break;
            case "o": guessText = o;
                break;
            case "p": guessText = p;
                break;
            case "q": guessText = q;
                break;
            case "r": guessText = r;
                break;
            case "s": guessText = s;
                break;
            case "t": guessText = t;
                break;
            case "u": guessText = u;
                break;
            case "v": guessText = v;
                break;
            case "w": guessText = w;
                break;
            case "x": guessText = x;
                break;
            case "y": guessText = y;
                break;
            case "z": guessText = z;
                break;
            default: break;
        }
    }

    public Text duplicate(Text original){ //clones guess in case of duplicate letters to avoid error
        String originalString = original.getText();
        Text clone = new Text("" +originalString);
        clone.setFont(Font.font(20));
        return clone;
    }

    public void blankReplacer(){ //places guess in position if correct and tracks incorrect guesses
        if (game.location >= 0) {
            guessText.setFont(Font.font(20));
            blanks.add(guessText, game.location,0);
            game.gcsFindMore(guess);
            if (game.findMoreLocation != -1) {
                Text newGuess = duplicate(guessText);
                blanks.add(newGuess, game.findMoreLocation,0);
                game.gcsFindThird(guess);
                if (game.findMoreLocation != -1) {
                    Text newGuess1 = duplicate(guessText);
                    blanks.add(newGuess1, game.findMoreLocation,0);
                }
            }
        }
        else {
            int movesRemaining = game.movesRemaining;
            incorrectLetters.add(guessText, 6 - movesRemaining,0);
            gameProgress(movesRemaining);
        }
    }

    public void gameProgress(int movesRemaining){ //controls hangman graphic
        switch (movesRemaining) {
            case 5: hangMan.getChildren().add(head);
                break;
            case 4: hangMan.getChildren().add(line5);
                break;
            case 3: hangMan.getChildren().add(arm1);
                break;
            case 2: hangMan.getChildren().add(arm2);
                break;
            case 1: hangMan.getChildren().add(leg1);
                break;
            case 0: {
                hangMan.getChildren().add(leg2);
                OKButton.setDisable(true);
                guessField.setDisable(true);
                game.message = "You lose. The word was " +game.hiddenWord +".";
                break;
            }
        }
    }

    public void printMessage() { //adds output to message pane
        message = new Text("" +game.message);
        messagePane.add(message,0,0);
    }

    class GuessHandler implements EventHandler<ActionEvent> { //event handler
        @Override // Override the handle method
        public void handle(ActionEvent e) {
            guess = guessField.getText().toLowerCase();
            guessField.clear();
            messagePane.getChildren().remove(message);
            message = new Text("");
            guessToText(guess);
            game.usedChecker(guess);

            if (game.usedCheckVal == 1) {}
            else {
                letters.getChildren().remove(guessText);
                blankReplacer();
            }

            if (!game.blankWord.contains("-")) { //win condition checker
                game.message = "You have guessed the word!";
                OKButton.setDisable(true);
                guessField.setDisable(true);
            }
            printMessage();
        }
    }
}