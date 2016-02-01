package ou.edu.herbarium.image;

import javax.imageio.ImageIO;  
import java.awt.image.BufferedImage;  
import java.io.*;  
import java.awt.*;  
  
public class ImageSplit {  
    public static void main(String[] args) throws IOException {  
  
        File file = new File("C:/11.jpg"); // I have bear.jpg in my working directory  
        FileInputStream fis = new FileInputStream(file);  
        BufferedImage image = ImageIO.read(fis); //reading the image file  
  
        int rows = 2; //You should decide the values for rows and cols variables  
        int cols = 2;  
        int chunks = rows * cols;  
  
        int chunkWidth = image.getWidth() / cols; // determines the chunk width and height  
        int chunkHeight = image.getHeight() / (rows*4);  
        int count = 0;  
        BufferedImage imgs = null; //Image array to hold image chunks  
        for (int x = 0; x < (rows*4); x++) {  
            for (int y = 0; y < cols; y++) {  
                //Initialize the image array with image chunks  
                imgs = new BufferedImage(chunkWidth, chunkHeight, image.getType());  
  
                // draws the image chunk  
                Graphics2D gr = imgs.createGraphics();  
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
                ImageIO.write(imgs, "jpg", new File("C:/subdir/img" + x+"_"+y+ ".jpg"));
            }  
        }  
        System.out.println("Splitting done");  
  
        System.out.println("Mini images created");  
    }  
}  