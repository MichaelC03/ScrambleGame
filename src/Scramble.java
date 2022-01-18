/*
 * Description: A game that scrambles words, and the player must unscramble as
 * many as possible before time runs out
 * Author:  Michael C.
 * Date: January 12th, 2019
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Timer;

public class Scramble extends JFrame implements ActionListener
{
        //Declaring (and initializing some) variables
    
        //Components
        private JFrame myFrame;
        private JLayeredPane mainPanel;
        private JPanel wordSpace, buttonSpace;
        private ImagePanel background;
        private JLabel clock, countdown, score;
        private JButton startButton, menuButton;
        
        //Arrays for the letters and the spaces to fill (12-letter words are the max)
        private JButton[] letters = new JButton[12];
        private JButton[] spaces = new JButton[12];
        
        //The correct word and the username
        private String correctWord = "", username;
        
        //Timer
        private Timer timer;
        
        //Number of points and difficulty level
        private int points = 0, difficulty = 0;
        
        //Word bank full of random words to choose from
        private final String[] wordBank = {"pole", "love", "area", "road", "tree", "junk", "quiz", "cash", "maze", "jury",
                                        "hero", "leaf", "whip", "zero", "twin", "king", "kick", "rice", "herd", "fuel",
                                        "water", "crown", "blank", "queen", "power", "crazy", "array", "sugar", "camel", "piano",
                                        "heart", "beach", "trust", "paper", "trunk", "brain", "joint", "novel", "queue", "quirk",
                                        "desert", "tricky", "thrust", "flower", "armory", "effect", "chorus", "minute", "tomato", "voyage",
                                        "couple", "museum", "animal", "theory", "volume", "divide", "virgin", "scheme", "attack", "galaxy",
                                        "reality", "animate", "grenade", "cottage", "stadium", "tension", "fantasy", "shelter", "verdict", "conduct",
                                        "zealous", "complex", "calorie", "harmony", "justice", "company", "uniform", "chapter", "package", "passage",
                                        "distance", "exchange", "activate", "negative", "relation", "commerce", "champion", "aviation", "recovery", "campaign",
                                        "orthodox", "elephant", "engineer", "hospital", "shoulder", "security", "momentum", "medicine", "infinite", "particle",
                                        "explosion", "chemistry", "beautiful", "rebellion", "interrupt", "exclusive", "satellite", "technique", "parachute", "highlight",
                                        "wilderness", "admiration", "investment", "stereotype", "compromise", "motorcycle", "democratic", "microphone", "conspiracy", "substitute",
                                        "achievement", "competition", "possibility", "coincidence", "stimulation", "firefighter", "transaction", "agriculture", "legislation", "concentrate",
                                        "circumstance", "refrigerator", "acquaintance", "transmission", "neighbourhood", "intelligence", "relationship", "discriminate", "civilization", "contemporary"};
        
        //Setting up the game (takes in the username)
        public Scramble(String n)
        {
                username = n; //Sets the username
                
                //Setting up JFrame
                myFrame = new JFrame("Scramble Game");
                myFrame.setLayout(null);
                myFrame.setSize(1000, 800);
                myFrame.setVisible(false);
                myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //Setting up the main panel
                mainPanel = new JLayeredPane();
                mainPanel.setBackground(Color.black);
                mainPanel.setLayout(new GridBagLayout());
                
                //Getting the background
                background = Format.setBackground("images/background.png");
                
                //Setting up the space for the word to be displayed (the spaces array)
                wordSpace = new JPanel();
                wordSpace.setBackground(Color.black);
                wordSpace.setOpaque(false);
                wordSpace.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 150), 2));
                wordSpace.setLayout(new GridLayout());
                
                //Setting up the space for the letters to be displayed (the letters array)
                buttonSpace = new JPanel();
                buttonSpace.setBackground(Color.black);
                buttonSpace.setOpaque(false);
                buttonSpace.setBorder(wordSpace.getBorder());
                
                //Getting image for the circle in which the timer will be placed
                ImageIcon icon = new ImageIcon("images/circle.png");
                Image iconImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
                ImageIcon imgIcon = new ImageIcon(iconImage);
                
                //The circle image in which the timer will be placed
                clock = new JLabel(imgIcon);
                clock.setPreferredSize(new Dimension(100, 100));
                
                //Setting up the timer display
                countdown = new JLabel("20", JLabel.CENTER);
                countdown.setFont(Format.createFont(70));
                countdown.setForeground(Color.green);
                countdown.setPreferredSize(new Dimension(100, 100));
                
                //Setting up the score label
                score = new JLabel("Score: 0", JLabel.CENTER);
                score.setFont(Format.createFont(70));
                score.setForeground(Color.green);
                score.setPreferredSize(new Dimension(600, 100));
                
                //Setting up the menu button
                menuButton = new JButton();
                menuButton.add(Format.setBackground("images/home.png"));
                menuButton.addActionListener(this);
                menuButton.setBackground(Color.black);
                menuButton.setBorder(wordSpace.getBorder());
                menuButton.setPreferredSize(new Dimension(100, 100));
                menuButton.setMinimumSize(new Dimension(100, 100));
                
                //Setting up the start button
                startButton = new JButton();
                startButton.add(Format.setBackground("images/start.png"));
                startButton.addActionListener(this);
                startButton.setBackground(Color.black);
                startButton.setBorder(wordSpace.getBorder());
                
                wordSpace.add(startButton); //Fills the wordSpace with the start button
                
                //A long series of setting constraints and placing all the components
                GridBagConstraints c = new GridBagConstraints();
                
                c = Format.setConstraints(0, 0, 1, 1, 1, 1, 10, 10);
                c.insets = new Insets(10, 10, 5, 10);
                
                mainPanel.add(wordSpace, c);
                mainPanel.setLayer(wordSpace, 2);
                
                c = Format.setConstraints(0, 1, 1, 2, 1, 1, 10, 10);
                c.insets = new Insets(5, 10, 10, 10);
                
                mainPanel.add(buttonSpace, c);
                mainPanel.setLayer(buttonSpace, 2);
                
                c = Format.setConstraints(0, 2, 1, 1, 1, 0, 14, 10);
                
                mainPanel.add(clock, c);
                mainPanel.setLayer(clock, 3);
                
                mainPanel.add(countdown, c);
                mainPanel.setLayer(countdown, 3);
                
                c = Format.setConstraints(0, 2, 1, 1, 1, 0, 16, 20);
                
                mainPanel.add(menuButton, c);
                mainPanel.setLayer(menuButton, 3);
                
                c = Format.setConstraints(0, 2, 1, 1, 1, 0, 15, 20);
                
                mainPanel.add(score, c);
                mainPanel.setLayer(score, 3);
                
                c = Format.setConstraints(0, 0, 1, 3, 1, 1, 10, 0);
                
                mainPanel.add(background, c);
                mainPanel.setLayer(background, 1);
                
                //Setting up the buttons for the letters
                for (int i=0; i<letters.length; i++)
                {
                        letters[i] = new JButton();
                        letters[i].setForeground(Color.black);
                        letters[i].setBorder(BorderFactory.createLineBorder(new Color(0, 150, 150), 2));
                        letters[i].setMargin(new Insets(0, 0, 0, 0));
                        letters[i].setPreferredSize(new Dimension(100, 100));
                        letters[i].addActionListener(this);
                }
                
                //Setting up the buttons for the spaces to be filled with the letters
                for (int i=0; i<spaces.length; i++)
                {
                        spaces[i] = new JButton(" ");
                        spaces[i].setForeground(Color.black);
                        spaces[i].add(Format.setBackground("images/letters/space.png"));
                        spaces[i].setBorder(wordSpace.getBorder());
                        spaces[i].setMargin(new Insets(0, 0, 0, 0));
                        spaces[i].setPreferredSize(new Dimension(50, 50));
                        spaces[i].addActionListener(this);
                }
                
                //Sets mainPanel as the content pane and allows the user to see the JFrame
                myFrame.setContentPane(mainPanel);
                myFrame.setVisible(true);
        }
        
        //What happens when the user takes an action
        public void actionPerformed(ActionEvent e)
        {
                //If the start button is pressed
                if (e.getSource() == startButton)
                {
                        newWord(); //Start the game by scrambling a word
                }
                
                //If the menu button is pressed
                if (e.getSource() == menuButton)
                {
                        new ScrambleTitle(); //Go back to the title screen
                        myFrame.dispose(); //Gets rid of the game screen
                }
                
                //For each button in the letters array
                for (int i=0; i<letters.length; i++)
                {
                        //If a letter button is pressed
                        if (e.getSource() == letters[i])
                        {
                                letters[i].setVisible(false); //Sets the pressed button to invisible
                                
                                //For each button in the spaces array
                                for (int n=0; n<spaces.length; n++)
                                {
                                        //Finds the next empty spaces button without a letter in it
                                        if (spaces[n].getText().equals(" "))
                                        {
                                                spaces[n].removeAll(); //Removes the image of the space
                                                spaces[n].add(Format.setBackground("images/letters/" + letters[i].getText() + ".png")); //Sets the background to the
                                                                                                                                        //appropriate letter
                                                spaces[n].setText(letters[i].getText()); //Set the (invisible) text to the letter that it is holding
                                                
                                                letters[i].setText(letters[i].getText() + "_false"); //Adds "_false" to the end of the letters button that was
                                                                                                     //pressed to differentiate it from non-pressed buttons
                                                
                                                break; //Breaks from the loop to stop checking for empty spaces
                                        }
                                }
                        }
                }
                
                //For each button in the spaces arraya
                for (int i=0; i<spaces.length; i++)
                {
                        //If a spaces button is pressed
                        if (e.getSource() == spaces[i])
                        {
                                //If the button has a letter in it
                                if (!spaces[i].getText().equals(" "))
                                {
                                        //For each button in the letters array
                                        for (int n=0; n<letters.length; n++)
                                        {
                                                //If the letter is the same as the one pressed and is invisible (has "_false" added on)
                                                if (letters[n].getText().equals(spaces[i].getText() + "_false"))
                                                {
                                                        letters[n].setVisible(true); //Make it visible
                                                        letters[n].setText(letters[n].getText().substring(0, 1)); //Removes "_false" from the text
                                                        break; //Breaks from the loop to stop checking for the invisible letter
                                                }
                                        }
                                        
                                        //Sets the space button pressed back to an empty space
                                        spaces[i].removeAll(); //Removes the letter image
                                        spaces[i].add(Format.setBackground("images/letters/space.png")); //Sets the background to the spaces image
                                        spaces[i].setText(" "); //Sets text back to a space
                                }
                        }
                }
                
                //If the word has been unscrambled correctly
                if (isCorrect(correctWord) == true)
                {
                        try
                        {
                                points += 500; //Adds 500 points
                                difficulty += 1; //Ups the difficulty
                                score.setText("<html><div style='text-align: center;'>Score: " + points + "</html>"); //Displays the new score
                                score.updateUI(); //Updates the display so it shows the new score
                                timer.stop(); //Stops the timer

                                wordSpace.removeAll(); //Remove the word from the display
                                wordSpace.setLayout(new GridLayout()); //Sets the layout again because it has been removed
                                wordSpace.add(Format.setBackground("images/correct.png")); //A "CORRECT" message will appear on the word display

                                //Small timer for how long the correct image is up for
                                Timer delay = new Timer(400, new ActionListener() //An action occurs after roughly 0.4 seconds,
                                                                                  //which is how long the correct message will stay
                                {
                                        //After approximately 0.4 seconds has passed
                                        public void actionPerformed(ActionEvent e)
                                        {
                                                newWord(); //Goes to the next word
                                                ((Timer) e.getSource()).stop(); //Stops the timer for the correct image
                                        }
                                });

                                delay.start(); //Starts the timer for the correct image
                        }
                        catch (NullPointerException ex) //To stop an error from occuring after the word bank has been exhausted
                        {
                                
                        }
                }
        }
        
        //Takes a random word from the wordBank array
        private String getRandomWord()
        {
                int randomIndex = 0; //Random index to get a word from the word bank
                String randomWord = null; //The string that will be returned
                
                while (randomWord == null) //If the word is null, then it has already been used, get another
                {
                        randomIndex = (int) (Math.random() * 10 + difficulty); //Gets a random index according to difficulty level
                        
                        //If the random index given is outside of the word bank
                        if (randomIndex >= wordBank.length)
                        {
                                //Makes sure the user has answered all of the final 10 words
                                for (int i=10; i>0; i--)
                                {
                                        randomIndex = wordBank.length-i; //Takes the index of one of the final 10
                                        randomWord = wordBank[randomIndex]; //Gets the corresponding word
                                        
                                        if (randomWord != null) //If the word has not yet been used
                                        {
                                                break; //Break out of the loop to stop looking for the next unused word in the final 10
                                        }
                                        else if (i == 1) //If the loop has gone on and checked for each word in the final 10
                                        {
                                                JOptionPane.showMessageDialog(myFrame, "Congratulations! You have exhausted our word bank!"); //Inform the user that the
                                                                                                                                              //word bank has been used up
                                                new GameOver(points, username); //Goes to the game over screen
                                                myFrame.dispose(); //Disposes of the game JFrame
                                                                   //Note: Since the dispose() method does not actually cease
                                                                   //all operations in the disposed JFrame, some places have a try
                                                                   //catch for a NullPointerException for when a null is passed in
                                                                   //(Because this method will return a null at this point)
                                        }
                                }
                                
                                if (randomWord == null) //If the random word is null in this after going through the if-statement, then the game is over
                                {
                                        break; //Break from the while loop seperately as randomWord will always return null at this point
                                }
                        }
                        else //Otherwise, if the endgame has not yet been reached, then simply take the corresponding word from the random index
                        {
                                randomWord = wordBank[randomIndex]; //Gets the corresponding word using the random index
                        }
                }
                
                wordBank[randomIndex] = null; //Sets the word in the array to null so it won't be repeated
                
                return randomWord; //Returns the random word
        }
        
        //Shuffles the word
        private String shuffleWord(String w)
        {
                char[] shuffledWord = w.toCharArray(); //Sets up array for the shuffled word
                String check; //Declaring string for checking if the word has remained the same after shuffling
                
                do
                {
                        check = ""; //Sets the check string to nothing
                        
                        //Will swap 2 characters in the array 20 times to shuffle the word
                        for (int n=0; n<20; n++)
                        {
                                //Declaring variables for random indexes to swap
                                int randomNum1;
                                int randomNum2;

                                do
                                {
                                        //Assigns both random numbers a random number
                                        randomNum1 = (int) (Math.random()*w.length());
                                        randomNum2 = (int) (Math.random()*w.length());
                                } while (randomNum1 == randomNum2); //Gets new numbers if the 2 random indexes to be swapped are the same

                                char temp = shuffledWord[randomNum1]; //Declaring variable to hold the char that will be replaced

                                //Swaps the 2 letters
                                shuffledWord[randomNum1] = shuffledWord[randomNum2];
                                shuffledWord[randomNum2] = temp;
                        }
                        
                        //Takes the shuffled word array and assigns it to the check variable
                        for (int c=0; c<shuffledWord.length; c++)
                        {
                                check += shuffledWord[c];
                        }
                        
                } while (check.compareToIgnoreCase(correctWord) == 0); //If the shuffled word and the original word are the same, reshuffle the word
                
                return check; //Returns the shuffled word
        }
        
        //Checks to see if the word on screen is the same as the correct word passed in
        private boolean isCorrect(String w)
        {
                //For each letter in the word
                for (int i=0; i<w.length(); i++)
                {
                        String character = "" + w.charAt(i);
                        
                        //If any of the characters on screen are not in the same place as the correct word
                        if (spaces[i].getText().compareToIgnoreCase(character) != 0)
                        {
                                return false; //Return false
                        }
                }
                
                return true; //Return true if all the characters are in the correct location
        }
        
        //Gets the buttons for both the letters and the spaces
        private void getButtons(String w)
        {
                //Removes everything from all the letter buttons and sets them to invisible
                for (int i=0; i<letters.length; i++)
                {
                        letters[i].removeAll();
                        letters[i] = new JButton();
                        letters[i].setVisible(false); //Set them all to invisible
                }
                
                //Sets up the buttons according to how many letters there are in the word
                for (int n=0; n<w.length(); n++)
                {
                        //Taking the letters from the string passed in and setting them to a button
                        String letter = "" + w.charAt(n);
                        
                        //Sets up the buttons
                        letters[n].setText(letter); //Sets the text to the letter it represents
                        letters[n].add(Format.setBackground("images/letters/" + letter + ".png")); //Gets the image of the letter
                        letters[n].setVisible(true);
                        letters[n].setForeground(Color.black);
                        letters[n].setBorder(wordSpace.getBorder());
                        letters[n].setMargin(new Insets(0, 0, 0, 0));
                        letters[n].setPreferredSize(new Dimension(100, 100)); //So the button will not resize
                        letters[n].setMinimumSize(new Dimension(100, 100)); //So even if there is not enough space, button will stay the same size
                        letters[n].addActionListener(this);
                }
                
                //Removes everything from all the space buttons and sets them to invisible
                for (int j=0; j<spaces.length; j++)
                {
                        spaces[j].removeAll();
                        spaces[j] = new JButton();
                        spaces[j].setVisible(false);
                }
                
                //Sets up the buttons according to how many letters there are in the word
                for (int k=0; k<w.length(); k++)
                {
                        spaces[k].setText(" ");
                        spaces[k].add(Format.setBackground("images/letters/space.png"));
                        spaces[k].setVisible(true);
                        spaces[k].setForeground(Color.black);
                        spaces[k].setBorder(wordSpace.getBorder());
                        spaces[k].setMargin(new Insets(0, 0, 0, 0));
                        spaces[k].setPreferredSize(new Dimension(100, 100));
                        spaces[k].setMinimumSize(new Dimension(100, 100));
                        spaces[k].addActionListener(this);
                }
        }
        
        //Timer for each word (Taken from StackOverflow) (https://stackoverflow.com/questions/26965049/java-gui-countdown)
        private void timer()
        {
                timer = new Timer(10, new ActionListener() //Each "tick" of this clock should be roughly 0.01 seconds long
                {
                        int count = correctWord.length() * 10 - 20; //The timer will be set to a different time depending on how many letters there are in the word
                        int delay = 0; //Variable to track how long I would like each second to be
                        
                        public void actionPerformed(ActionEvent e)
                        {
                                //If the timer has reached zero
                                if (count <= 0)
                                {
                                        timer.stop(); //Stops timer
                                        countdown.setText("0"); //Sets the countdown text to zero
                                        new GameOver(points, username); //Goes to game over screen with the number of points and username
                                        Leaderboard.newPoints(points, username); //Updates leaderboard
                                        myFrame.dispose(); //Disposes of the game JFrame
                                }
                                else //If the timer has not yet reached zero
                                {
                                        delay++; //Increase the delay
                                        countdown.setText("" + count); //Set the countdown to how much time is left
                                        
                                        if (delay%100 == 0) //Every 100 "ticks" will lower the countdown (roughly every second)
                                        {
                                                count--;
                                        }
                                }
                        }
                });
                
                timer.start(); //Starts the timer
        }
    
        //Places the letter and spaces buttons
        private void placeLetters(int l)
        {
                //Removes everything from the button space
                buttonSpace.removeAll();
                
                //Sets up the layout and constraints to place letter buttons
                buttonSpace.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                
                //Starting constraint for the button space
                c = Format.setConstraints(0, 0, 1, 1, 1, 0, 10, 10);
                
                //For each letter in the shuffled word array
                for (int i=0; i<letters.length; i++)
                {
                        //Adds the letter and creates a rigid area in the exact same space so when it is set
                        //to invisible, its place will not be taken
                        buttonSpace.add(letters[i], c);
                        buttonSpace.add(Box.createRigidArea(new Dimension(100, 100)), c);
                        
                        //If they have reached the end of the line
                        if ((i+1)%6 == 0)
                        {
                                c.gridy += 1; //Go down a line
                                c.gridx = 0; //Go back to the left
                        }
                        else //If they have not reached the end of the line
                        {
                                c.gridx += 1; //Go to the right by one
                        }
                }
                
                //Removes everything from the word space
                wordSpace.removeAll();
                
                //Sets up the layout
                wordSpace.setLayout(new GridBagLayout());
                
                //Starting constraint for the word space
                c = Format.setConstraints(0, 0, 1, 1, 1, 0, 10, 10);
                
                //For each letter in the word
                for (int j=0; j<l; j++)
                {
                        wordSpace.add(spaces[j], c); //Add a space
                        c.gridx += 1; //Move the next space button to the right
                }
                
                wordSpace.updateUI(); //Updates the display
        }
        
        //To get a new word
        private void newWord()
        {
                try
                {
                        String newWord = getRandomWord(); //Gets a random word
                        correctWord = newWord; //Sets the correct word as the random word that was returned
                        getButtons(shuffleWord(newWord)); //Gets the buttons according to the shuffled version of the random word
                        placeLetters(newWord.length()); //Places the buttons according to how many letters there are in the random word
                        timer(); //Starts a new timer
                }
                catch (NullPointerException e) //To stop an error from occuring after the word bank has been exhausted
                {
                        
                }
        }
}
