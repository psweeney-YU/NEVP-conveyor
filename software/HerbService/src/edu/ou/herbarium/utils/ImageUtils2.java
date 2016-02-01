package edu.ou.herbarium.utils;

import java.io.*;     
import java.awt.*;     
import java.awt.image.*;     
import java.awt.Graphics;     
import java.awt.color.ColorSpace;     

import javax.activation.DataHandler;
import javax.imageio.ImageIO;     
    
public class ImageUtils2     
{     
    public static BufferedImage scale(DataHandler handler, int scale, boolean flag)     
    {     
        try    
        {     
        	ByteArrayOutputStream output = new ByteArrayOutputStream();
        	handler.writeTo(output);
    		InputStream in = new ByteArrayInputStream(output.toByteArray());
    		BufferedImage src = ImageIO.read(in);
            int width = src.getWidth(); 
            int height = src.getHeight(); 
            if (flag)     
            {     
                width = width * scale;     
                height = height * scale;     
            }     
            else    
            {     
                width = width / scale;     
                height = height / scale;     
            }     
            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT); 
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);     
            Graphics g = tag.getGraphics();     
            g.drawImage(image, 0, 0, null); 
            g.dispose();     
            return tag;
        }     
        catch (IOException e)     
        {     
            e.printStackTrace(); 
            return null;
        }     
    }     
    
    
    public void convert(String source, String result)     
    {     
        try    
        {     
            File f = new File(source);     
            f.canRead();     
            f.canWrite();     
            BufferedImage src = ImageIO.read(f);     
            ImageIO.write(src, "JPG", new File(result));     
        }     
        catch (Exception e)     
        {     
            // TODO Auto-generated catch block     
            e.printStackTrace();     
        }     
    }     
    
    public void gray(String source, String result)     
    {     
        try    
        {     
            BufferedImage src = ImageIO.read(new File(source));     
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);     
            ColorConvertOp op = new ColorConvertOp(cs, null);     
            src = op.filter(src, null);     
            ImageIO.write(src, "JPEG", new File(result));     
        }     
        catch (IOException e)     
        {     
            e.printStackTrace();     
        }     
    }
    
//    public static void main(String args[]){
//    	scale("C:/11.jpg","C:/11_50.jpg",2,false);
//    	scale("C:/11.jpg","C:/11_25.jpg",4,false);
//    	scale("C:/11.jpg","C:/11_12.5.jpg",8,false);
//    }
}    
