/*
 * Description: Leaderboard class for the Scramble Game, keeping track of the
 * names and scores
 * Author: Michael C.
 * Date: January 12th, 2018
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.IOException;

public class Leaderboard extends JFrame implements ActionListener
{
        //Declaring components
        private JFrame myFrame;
        private JLayeredPane mainPanel;
        private JLabel leaderboard, header;
        private ImagePanel background;
        private JButton back;
        
        public Leaderboard()
        {
                //Setting up the JFrame
                myFrame = new JFrame("Scramble Game - Leaderboard");
                myFrame.setLayout(null);
                myFrame.setSize(1000, 800);
                myFrame.setVisible(false);
                myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //Setting up the main panel
                mainPanel = new JLayeredPane();
                mainPanel.setBackground(Color.black);
                mainPanel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                
                //Setting up the header
                header = new JLabel("LEADERBOARD", JLabel.CENTER);
                header.setBackground(Color.black);
                header.setLayout(null);
                header.setForeground(Color.green);
                header.setFont(Format.createFont(100));
                header.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 150), 2));
                
                //Setting up the leaderboard label
                leaderboard = new JLabel("", JLabel.CENTER);
                leaderboard.setBackground(Color.black);
                leaderboard.setLayout(null);
                leaderboard.setForeground(Color.green);
                leaderboard.setFont(Format.createFont(45));
                leaderboard.setBorder(header.getBorder());
                
                //Seting up the back button
                back = new JButton();
                back.add(Format.setBackground("images/home.png"));
                back.setBorder(header.getBorder());
                back.addActionListener(this);
                back.setBackground(Color.black);
                back.setPreferredSize(new Dimension(100, 100));
                back.setMinimumSize(new Dimension(100, 100));
                
                //String that will be the leaderboard text
                String line = "";
                
                try
                {
                        IO.openInputFile("src/leaderboard.txt"); //Opens leaderboard.txt file
                        
                        //For each line in the leaderboard.txt file
                        for (int i=0; i<10; i++)
                        {
                                line += IO.readLine() + "<br/>"; //Adds the line to the string
                        }
                        
                        //Sets the text in the leaderboard label to the line string and centers it
                        leaderboard.setText("<html><div style='text-align: center;'>" + line + "</html>");
                        
                        IO.closeInputFile(); //Closes leaderboard.txt file
                }
                catch (IOException e) //To catch IOException errors
                {
                        System.out.println("Error");
                }
                
                //Placing all the components
                c = Format.setConstraints(1, 0, 4, 1, 1, 1, 10, 20);
                c.insets = new Insets(20, 10, 10, 20);
                
                mainPanel.add(header, c);
                mainPanel.setLayer(header, 2);
                
                c = Format.setConstraints(1, 1, 4, 4, 1, 1, 10, 20);
                c.insets = new Insets(10, 10, 20, 20);
                
                mainPanel.add(leaderboard, c);
                mainPanel.setLayer(leaderboard, 2);
                
                c = Format.setConstraints(0, 4, 1, 1, 0, 0, 16, 20);
                c.insets = new Insets(10, 20, 20, 10);
                
                mainPanel.add(back, c);
                mainPanel.setLayer(back, 2);
                
                background = Format.setBackground("images/background.png");
                
                c = Format.setConstraints(0, 0, 5, 5, 1, 1, 10, 0);
                
                mainPanel.add(background, c);
                mainPanel.setLayer(background, 1);
                
                //Sets the JFrame to visible
                myFrame.setContentPane(mainPanel);
                myFrame.setVisible(true);
        }
        
        public void actionPerformed(ActionEvent e)
        {
                if (e.getSource() == back) //If the back button was pressed
                {
                        new ScrambleTitle(); //Goes to title screen
                        myFrame.dispose(); //Disposes of leaderboard screen
                }
        }
        
        //Updates the leaderboard
        public static void newPoints(int points, String username)
        {
                //Declaring and initializing arrays for both the names and scores
                String[] names = new String[10];
                int[] scores = new int[10];
                
                try
                {
                        IO.openInputFile("src/leaderboard.txt"); //Opens leaderboard.txt file
                        
                        //For each line in the text file
                        for (int i=0; i<10; i++)
                        {
                                String line = IO.readLine(); //The line that was read from the file
                                int index = line.lastIndexOf(" "); //Finds the space that seperates the points from the name
                                
                                String name = line.substring(3, index); //Gets the name
                                int score = Integer.parseInt(line.substring(index + 1, line.length())); //Gets the points
                                
                                //Saves both to the arrays
                                names[i] = name;
                                scores[i] = score;
                        }
                        
                        //For each element in the array
                        for (int n=0; n<10; n++)
                        {
                                //If the points are greater than one of the points on the leaderboard
                                if (points > scores[n])
                                {
                                        //For all the remaining leaderboard entries
                                        for (int j=9; j>n; j--)
                                        {
                                                //Shifts everything else down
                                                scores[j] = scores[j-1];
                                                names[j] = names[j-1];
                                        }
                                        
                                        //Inserts the new entry
                                        scores[n] = points;
                                        names[n] = username;
                                        
                                        break; //Breaks from the loop
                                }
                        }
                        
                        IO.closeInputFile(); //Closes the leaderboard.txt file
                        
                        IO.createOutputFile("src/leaderboard.txt"); //Creates a new leaderboard.txt file to replace the old one
                        
                        //For each element in the names/scores array
                        for (int k=0; k<10; k++)
                        {
                                IO.println((k+1) + ". " + names[k] + " " + scores[k]); //Saves the new leaderboard
                        }
                        
                        IO.closeOutputFile(); //Closes the new modified leaderboard.txt file
                }
                catch (IOException e) //To catch any IOException errors
                {
                        System.out.println("Error");
                }
        }
}
