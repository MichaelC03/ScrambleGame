/*
 * Description: ImagePanel extends the JPanel class ro override the setBackground
 * and paintComponent methods to be able to set an image as the background
 * Author: Michael C.
 * Date: January 12th, 2018
 */

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import java.awt.*;

public class ImagePanel extends JPanel
{
        private Image image;
        
        public void setBackground(Image image) //Overrides the setBackground method so
                                               //it is possible to pass in an image
        {
                this.image = image;
        }
        
        public void paintComponent(Graphics g) //Overrides paintComponent method (taken from StackOverflow) (https://stackoverflow.com/questions/19125707/simplest-way-to-set-image-as-jpanel-background)
        {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), Color.black, this); //Paints image according to the width
                                                                                                //and height of the panel with no offset
        }
}
