/*
 * Description: Instructions screen for the Scramble Game
 * Author: Michael C.
 * Date: January 12th, 2018
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Instructions extends JFrame implements ActionListener
{
        //Declaring components
        private JFrame myFrame;
        private JLayeredPane mainPanel;
        private ImagePanel background;
        private JLabel header, instructions;
        private JButton menu;

        public Instructions()
        {
                //Setting up the JFrame
                myFrame = new JFrame("Scramble Game - Instructions");
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
                header = new JLabel("INSTRUCTIONS", JLabel.CENTER);
                header.setBackground(Color.black);
                header.setLayout(null);
                header.setForeground(Color.green);
                header.setFont(Format.createFont(100));
                header.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 150), 2));
                
                //Writing the instructions
                String instructionsText = "<html><div style='text-align: center;'>Welcome to the Scramble Game!"
                        + "<br/><br/>To play, you must unscramble the word within a given amount of time!"
                        + "<br/><br/>The game ends when you run out of time!</html>";
                
                //Setting up the instructions label
                instructions = new JLabel(instructionsText, JLabel.CENTER);
                instructions.setBackground(Color.black);
                instructions.setLayout(null);
                instructions.setForeground(Color.green);
                instructions.setFont(Format.createFont(45));
                instructions.setBorder(header.getBorder());
                
                //Setting up the menu button
                menu = new JButton();
                menu.add(Format.setBackground("images/home.png"));
                menu.setBorder(header.getBorder());
                menu.addActionListener(this);
                menu.setBackground(Color.black);
                menu.setPreferredSize(new Dimension(100, 100));
                menu.setMinimumSize(new Dimension(100, 100));
                
                //Placing all the components
                c = Format.setConstraints(1, 0, 4, 1, 1, 1, 10, 20);
                c.insets = new Insets(20, 10, 10, 20);
                
                mainPanel.add(header, c);
                mainPanel.setLayer(header, 2);
                
                c = Format.setConstraints(1, 1, 4, 4, 1, 1, 10, 20);
                c.insets = new Insets(10, 10, 20, 20);
                
                mainPanel.add(instructions, c);
                mainPanel.setLayer(instructions, 2);
                
                c = Format.setConstraints(0, 4, 1, 1, 0, 0, 16, 20);
                c.insets = new Insets(10, 20, 20, 10);
                
                mainPanel.add(menu, c);
                mainPanel.setLayer(menu, 2);
                
                background = Format.setBackground("images/background.png");
                
                c = Format.setConstraints(0, 0, 5, 5, 1, 1, 10, 0);
                
                mainPanel.add(background, c);
                mainPanel.setLayer(background, 1);
                
                //Setting the JFrame to visible
                myFrame.setContentPane(mainPanel);
                myFrame.setVisible(true);
        }

        public void actionPerformed (ActionEvent e)
        {
            if (e.getSource() == menu) //If the menu button was pressed
            {
                    new ScrambleTitle(); //Goes to title screen
                    myFrame.dispose(); //Disposes of instructions screen
            }
        }
}
