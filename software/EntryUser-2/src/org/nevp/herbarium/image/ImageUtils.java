package org.nevp.herbarium.image;

import java.io.*;     
import java.awt.*;     
import java.awt.image.*;     
import java.awt.Graphics;     
import java.awt.color.ColorSpace;     
import javax.imageio.ImageIO;     
  
    
public class ImageUtils     
{     
	
	public void rotate(String srcImageFile){
		BufferedImage oldImage;
		try {
			oldImage = ImageIO.read(new FileInputStream(srcImageFile));
			BufferedImage newImage = new BufferedImage(oldImage.getHeight(), oldImage.getWidth(), oldImage.getType());
			Graphics2D graphics = (Graphics2D) newImage.getGraphics();
			graphics.rotate(Math.toRadians(90), newImage.getWidth() / 2, newImage.getHeight() / 2);
			graphics.translate((newImage.getWidth() - oldImage.getWidth()) / 2, (newImage.getHeight() - oldImage.getHeight()) / 2);
			graphics.drawImage(oldImage, 0, 0, oldImage.getWidth(), oldImage.getHeight(), null);
			ImageIO.write(newImage, "JPG", new FileOutputStream(srcImageFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public void scale(String srcImageFile, String result, int scale, boolean flag)     
    {     
        try    
        {     
            BufferedImage src = ImageIO.read(new File(srcImageFile)); 
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
            ImageIO.write(tag, "JPEG", new File(result));
        }     
        catch (IOException e)     
        {     
            e.printStackTrace();     
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
    
}    