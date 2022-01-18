/*
 * Description: Title page for the Scramble Game
 * Author: Michael C.
 * Date: January 12th, 2018
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ScrambleTitle extends JFrame implements ActionListener
{
        //Declaring components
        JFrame myFrame;
        JLayeredPane mainPanel;
        ImagePanel background, title;
        JButton play, instructions, leaderboard, quit;
        
        public static void main(String[] args)
        {
                new ScrambleTitle(); //Gets out of static main class
        }
        
        public ScrambleTitle()
        {
                //Setting up JFrame
                myFrame = new JFrame("Scramble Game - Title Screen");
                myFrame.setSize(895, 625);
                myFrame.setLayout(null);
                myFrame.setVisible(false);
                myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //Setting up main panel
                mainPanel = new JLayeredPane();
                mainPanel.setBackground(Color.black);
                mainPanel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                
                //Setting up the title label
                title = Format.setBackground("images/title.png");
                title.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 150), 2));
                
                //Setting up the background
                background = Format.setBackground("images/background.png");
                
                //Setting up the play button
                play = new JButton();
                play.add(Format.setBackground("images/play.png"));
                play.addActionListener(this);
                play.setBackground(Color.black);
                play.setBorder(title.getBorder());
                
                //Setting up the instructions button
                instructions = new JButton();
                instructions.add(Format.setBackground("images/instructions.png"));
                instructions.addActionListener(this);
                instructions.setBackground(Color.black);
                instructions.setBorder(title.getBorder());
                
                //Setting up the leaderboard button
                leaderboard = new JButton();
                leaderboard.add(Format.setBackground("images/leaderboardText.png"));
                leaderboard.addActionListener(this);
                leaderboard.setBackground(Color.black);
                leaderboard.setBorder(title.getBorder());
                
                //Setting up the quit button
                quit = new JButton();
                quit.add(Format.setBackground("images/quitText.png"));
                quit.addActionListener(this);
                quit.setBackground(Color.black);
                quit.setBorder(title.getBorder());
                
                //Placing all the components
                c = Format.setConstraints(0, 0, 2, 2, 1, 1, 10, 20);
                c.insets = new Insets(20, 20, 10, 20);
                
                mainPanel.add(title, c);
                mainPanel.setLayer(title, 2);
                
                c = Format.setConstraints(1, 2, 1, 3, 1, 1, 10, 10);
                c.insets = new Insets(10, 10, 20, 20);
                
                mainPanel.add(play, c);
                mainPanel.setLayer(play, 2);
                
                c = Format.setConstraints(0, 2, 1, 1, 1, 1, 10, 10);
                c.insets = new Insets(10, 20, 10, 10);
                
                mainPanel.add(instructions, c);
                mainPanel.setLayer(instructions, 2);
                
                c = Format.setConstraints(0, 3, 1, 1, 1, 1, 10, 10);
                c.insets = new Insets(10, 20, 10, 10);
                
                mainPanel.add(leaderboard, c);
                mainPanel.setLayer(leaderboard, 2);
                
                c = Format.setConstraints(0, 4, 1, 1, 1, 1, 10, 10);
                c.insets = new Insets(10, 20, 20, 10);
                
                mainPanel.add(quit, c);
                mainPanel.setLayer(quit, 2);
                
                c = Format.setConstraints(0, 0, 2, 5, 1, 1, 10, 0);
                
                mainPanel.add(background, c);
                mainPanel.setLayer(background, 1);
                
                //Sets the JFrame to visible
                myFrame.setContentPane(mainPanel);
                myFrame.setVisible(true);
        }
        
        public void actionPerformed(ActionEvent e)
        {
                //If the play button was pressed
                if (e.getSource() == play)
                {
                        //Ask for a username
                        String username = JOptionPane.showInputDialog("Please enter a username (Max 20 characters)", "Guest");
                        
                        //If user did not click cancel
                        if (username != null)
                        {
                                //If the entered username is within the given parameters
                                if (username.length() < 21)
                                {
                                        new Scramble(username); //Starts a new game with the username
                                        myFrame.dispose(); //Disposes of title screen JFrame
                                }
                                else //If the entered username is not within the given parameters
                                {
                                        //Display a message informing the user that they must follow the parameters
                                        JOptionPane.showMessageDialog(myFrame, "Username must be below 20 characters");
                                }
                        }
                }
                else if (e.getSource() == instructions) //If instructions button was pressed
                {
                        new Instructions(); //Goes to instructions frame
                        myFrame.dispose(); //Disposes of title screen JFrame
                }
                else if (e.getSource() == leaderboard) //If leaderboard button was pressed
                {
                        new Leaderboard(); //Goes to leaderboard frame
                        myFrame.dispose(); //Disposes of title screen JFrame
                }
                else if (e.getSource() == quit) //If quit button was pressed
                {
                        myFrame.dispose(); //Disposes of title screen JFrame
                }
        }
        
}
