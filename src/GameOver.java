/*
 * Description: Game Over screen for the Scramble Game
 * Author: Michael C.
 * Date: January 12th, 2018
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameOver extends JFrame implements ActionListener
{
        //Declaring components
        private JFrame myFrame;
        private JLayeredPane mainPanel;
        private JPanel display;
        private JLabel points;
        private ImagePanel background;
        private JButton mainMenu, replay, leaderboard;
        private String username;
        
        public GameOver(int p, String n)
        {
                username = n; //Sets the username
                
                //Setting up the JFrame
                myFrame = new JFrame("Scramble Game - Game Over");
                myFrame.setLayout(null);
                myFrame.setSize(1000, 800);
                myFrame.setVisible(false);
                myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //Setting up the main panel
                mainPanel = new JLayeredPane();
                mainPanel.setBackground(Color.black);
                mainPanel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                
                //Setting up the background
                background = Format.setBackground("images/background.png");
                
                //Setting up the display
                display = new JPanel();
                display.setBackground(Color.black);
                display.setLayout(new BorderLayout(10, 10));
                display.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 150), 2));
                display.setOpaque(true);
                
                //Setting up the points label
                points = new JLabel("Score: " + p, JLabel.CENTER);
                points.setBackground(Color.black);
                points.setFont(Format.createFont(90));
                points.setForeground(Color.green);
                
                //Adds the game over image and the points label to the display
                display.add(Format.setBackground("images/gameOver.png"), BorderLayout.CENTER);
                display.add(points, BorderLayout.SOUTH);
                
                //Setting up the main menu button
                mainMenu = new JButton();
                mainMenu.add(Format.setBackground("images/home.png"));
                mainMenu.setBorder(display.getBorder());
                mainMenu.addActionListener(this);
                mainMenu.setBackground(Color.black);
                mainMenu.setPreferredSize(new Dimension(100, 100));
                
                //Setting up the replay button
                replay = new JButton();
                replay.add(Format.setBackground("images/replay.png"));
                replay.setBorder(display.getBorder());
                replay.addActionListener(this);
                replay.setBackground(Color.black);
                replay.setPreferredSize(new Dimension(100, 100));
                
                //Setting up the leaderboard button
                leaderboard = new JButton();
                leaderboard.add(Format.setBackground("images/leaderboard.png"));
                leaderboard.setBorder(display.getBorder());
                leaderboard.addActionListener(this);
                leaderboard.setBackground(Color.black);
                leaderboard.setPreferredSize(new Dimension(100, 100));
                
                //Placing all the components
                c = Format.setConstraints(0, 3, 1, 1, 1, 0, 17, 20);
                c.insets = new Insets(5, 20, 5, 20);
                
                mainPanel.add(mainMenu, c);
                mainPanel.setLayer(mainMenu, 2);
                
                c = Format.setConstraints(1, 3, 1, 1, 1, 0, 10, 20);
                c.insets = new Insets(5, 20, 5, 20);
                
                mainPanel.add(replay, c);
                mainPanel.setLayer(replay, 2);
                
                c = Format.setConstraints(2, 3, 1, 1, 1, 0, 13, 20);
                c.insets = new Insets(5, 20, 5, 20);
                
                mainPanel.add(leaderboard, c);
                mainPanel.setLayer(leaderboard, 2);
                
                c = Format.setConstraints(0, 0, 3, 3, 10, 1, 10, 20);
                c.insets = new Insets(20, 20, 5, 20);
                
                mainPanel.add(display, c);
                mainPanel.setLayer(display, 2);
                
                c = Format.setConstraints(0, 0, 3, 4, 1, 1, 10, 0);
                
                mainPanel.add(background, c);
                mainPanel.setLayer(background, 1);
                
                //Sets the JFrame to visible
                myFrame.setContentPane(mainPanel);
                myFrame.setVisible(true);
        }
        
        public void actionPerformed(ActionEvent e)
        {
                if (e.getSource() == mainMenu) //If the main menu button was pressed
                {
                        new ScrambleTitle(); //Goes to title screen
                        myFrame.dispose(); //Disposes of game over screen
                }
                else if (e.getSource() == replay) //If the replay button was pressed
                {
                        new Scramble(username); //Starts another game with the same username
                        myFrame.dispose(); //Disposes of game over screen
                }
                else if (e.getSource() == leaderboard) //If the leaderboard button was pressed
                {
                        new Leaderboard(); //Goes to leaderboard screen
                        myFrame.dispose(); //Disposes of game over screen
                }
        }
}