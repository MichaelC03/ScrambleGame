/*
 * Description: Class with methods to make things more convenient/efficient
 * Author: Michael C.
 * Date: January 12th, 2018
 */

import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Format
{
        //Setting a background to an image (Taken from StackOverflow)
        public static ImagePanel setBackground(String imgPath)
        {
                ImageIcon imgIcon = new ImageIcon(imgPath); //Creates a new ImageIcon with the file path
                Image img = imgIcon.getImage().getScaledInstance(1000, 1000, Image.SCALE_AREA_AVERAGING); //Scales the image
                ImagePanel imgPanel = new ImagePanel();     //Instantiates a new image panel
                imgPanel.setBackground(img);                //Sets the background of that image panel to the image that was passed in
                imgPanel.setBackground(Color.black);        //Sets the background to black
                
                return imgPanel; //Returns the image panel background to be added to a component
        }
        
        //More efficient way of setting GridBagConstraints
        public static GridBagConstraints setConstraints(int x, int y, int wi, int h, int we, int f, int a, int i)
        {
                //Sets various constraints
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = x;
                c.gridy = y;
                c.gridwidth = wi;
                c.gridheight = h;
                c.weightx = we;
                c.weighty = we;
                c.fill = f;
                c.anchor = a;
                c.insets = new Insets(i, i, i, i);
                
                return c; //Returns GridBagConstraints
        }
        
        //Custom font (taken from StackOverflow) (https://docs.oracle.com/javase/tutorial/2d/text/fonts.html)
        public static Font createFont(int s)
        {
                Font font = new Font("Arial", Font.PLAIN, 90);
                
                try
                {
                        Font myFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/HVD_Comic_Serif_Pro.ttf"));
                        font = myFont.deriveFont(Float.parseFloat(s + "f"));
                }
                catch (IOException | FontFormatException e)
                {
                        System.out.println("There was an error getting the font");
                }
                
                return font;
        }
}
