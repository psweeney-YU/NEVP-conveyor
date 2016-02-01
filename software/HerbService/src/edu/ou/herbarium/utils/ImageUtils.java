package edu.ou.herbarium.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;

public class ImageUtils {

	private int width;
	private int height;
	private int scaleWidth;
	double support = (double) 3.0;
	double PI = (double) 3.14159265358978;
	double[] contrib;
	double[] normContrib;
	double[] tmpContrib;
	int startContrib, stopContrib;
	int nDots;
	int nHalfDots;

	public ArrayList<File> zoomInImage(DataHandler imageHandler, File parentDir)
			throws IOException {
		ArrayList<File> files = new ArrayList<File>();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		imageHandler.writeTo(output);
		InputStream in = new ByteArrayInputStream(output.toByteArray());
		BufferedImage bImageFromConvert = ImageIO.read(in);
		int originalHeight = bImageFromConvert.getHeight();
		int originalWidth = bImageFromConvert.getWidth();
		double nameIndex = 100;
		double ratioIndex = 1;
		for (int index = Math.max(originalHeight,
				originalWidth); index > 500; index /= 2) {
			if(ratioIndex==1){
				OutputStream os = new FileOutputStream(
						new File(parentDir.getAbsolutePath()+"/"+(nameIndex/ratioIndex)+".jpg"));
				imageHandler.writeTo(os);
				ratioIndex *=2;
			}
			else{
				files.add(saveImageAsJpeg(bImageFromConvert,
						(int)(originalWidth/ratioIndex),
						(int)(originalHeight/ratioIndex),
						parentDir.getAbsolutePath()+"/"+(nameIndex/ratioIndex)+".jpg"));
				ratioIndex *=2;
			}
		}
		return files;
	}

	public File saveImageAsJpeg(BufferedImage bImageFromConvert,
			int formatWidth, int formatHeight, String fileName) throws IOException {
		int imageWidth = bImageFromConvert.getWidth();
		int imageHeight = bImageFromConvert.getHeight();
		int changeToWideth = 0;
		int changeToHeight = 0;
		File returnFile = new File(fileName);
		if (imageWidth > 0 && imageHeight > 0) {
			if (imageWidth / imageHeight >= formatWidth / formatHeight) {
				if (imageWidth > formatWidth) {
					changeToWideth = formatWidth;
					changeToHeight = (imageHeight * formatWidth) / imageWidth;
				} else {
					changeToWideth = imageWidth;
					changeToHeight = imageHeight;
				}
			} else {
				if (imageHeight > formatHeight) {
					changeToHeight = formatHeight;
					changeToWideth = (imageWidth * formatHeight) / imageHeight;
				} else {
					changeToWideth = imageWidth;
					changeToHeight = imageHeight;
				}
			}
		}
		bImageFromConvert = imageZoomOut(bImageFromConvert, changeToWideth,
				changeToHeight);
		ImageIO.write(bImageFromConvert, "JPEG", returnFile);
		return returnFile;
	}

	public BufferedImage imageZoomOut(BufferedImage srcBufferImage, int w, int h) {
		width = srcBufferImage.getWidth();
		height = srcBufferImage.getHeight();
		scaleWidth = w;
		if (DetermineResultSize(w, h) == 1) {
			return srcBufferImage;
		}
		CalContrib();
		if(w>h){
			BufferedImage pbOut = HorizontalFiltering(srcBufferImage, w);
			BufferedImage pbFinalOut = VerticalFiltering(pbOut, h);
			return pbFinalOut;
		}
		else{
			BufferedImage pbOut = VerticalFiltering(srcBufferImage, h);
			BufferedImage pbFinalOut = HorizontalFiltering(pbOut,w);
			return pbFinalOut;
		}
		
	}

	private int DetermineResultSize(int w, int h) {
		double scaleH, scaleV;
		scaleH = (double) w / (double) width;
		scaleV = (double) h / (double) height;
		// no zoomout on original image
		if (scaleH >= 1.0 && scaleV >= 1.0) {
			return 1;
		}
		return 0;
	} // end of DetermineResultSize()

	private void CalContrib() {
		nHalfDots = (int) ((double) width * support / (double) scaleWidth);
		nDots = nHalfDots * 2 + 1;
		try {
			contrib = new double[nDots];
			normContrib = new double[nDots];
			tmpContrib = new double[nDots];
		} catch (Exception e) {
			System.out.println("init   contrib,normContrib,tmpContrib" + e);
		}
		int center = nHalfDots;
		contrib[center] = 1.0;
		double weight = 0.0;
		int i = 0;
		for (i = 1; i <= center; i++) {
			contrib[center + i] = Lanczos(i, width, scaleWidth, support);
			weight += contrib[center + i];
		}
		for (i = center - 1; i >= 0; i--) {
			contrib[i] = contrib[center * 2 - i];
		}
		weight = weight * 2 + 1.0;
		for (i = 0; i <= center; i++) {
			normContrib[i] = contrib[i] / weight;
		}
		for (i = center + 1; i < nDots; i++) {
			normContrib[i] = normContrib[center * 2 - i];
		}
	} // end of CalContrib()
	
	private double Lanczos(int i, int inWidth, int outWidth, double Support) {
        double x;
        x = (double) i * (double) outWidth / (double) inWidth;
        return Math.sin(x * PI) / (x * PI) * Math.sin(x * PI / Support)
                / (x * PI / Support);
    }

	private void CalTempContrib(int start, int stop) {
		double weight = 0;
		int i = 0;
		for (i = start; i <= stop; i++) {
			weight += contrib[i];
		}
		for (i = start; i <= stop; i++) {
			tmpContrib[i] = contrib[i] / weight;
		}
	} // end of CalTempContrib()

	private int GetRedValue(int rgbValue) {
		int temp = rgbValue & 0x00ff0000;
		return temp >> 16;
	}

	private int GetGreenValue(int rgbValue) {
		int temp = rgbValue & 0x0000ff00;
		return temp >> 8;
	}

	private int GetBlueValue(int rgbValue) {
		return rgbValue & 0x000000ff;
	}

	private int ComRGB(int redValue, int greenValue, int blueValue) {
		return (redValue << 16) + (greenValue << 8) + blueValue;
	}

	private int HorizontalFilter(BufferedImage bufImg, int startX, int stopX,
			int start, int stop, int y, double[] pContrib) {
		double valueRed = 0.0;
		double valueGreen = 0.0;
		double valueBlue = 0.0;
		int valueRGB = 0;
		int i, j;
		for (i = startX, j = start; i <= stopX; i++, j++) {
			valueRGB = bufImg.getRGB(i, y);
			valueRed += GetRedValue(valueRGB) * pContrib[j];
			valueGreen += GetGreenValue(valueRGB) * pContrib[j];
			valueBlue += GetBlueValue(valueRGB) * pContrib[j];
		}
		valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen),
				Clip((int) valueBlue));
		return valueRGB;
	} // end of HorizontalFilter()

	private BufferedImage HorizontalFiltering(BufferedImage bufImage, int iOutW) {
		int dwInW = bufImage.getWidth();
		int dwInH = bufImage.getHeight();
		int value = 0;
		BufferedImage pbOut = new BufferedImage(iOutW, dwInH,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < iOutW; x++) {
			int startX;
			int start;
			int X = (int) (((double) x) * ((double) dwInW) / ((double) iOutW) + 0.5);
			int y = 0;
			startX = X - nHalfDots;
			if (startX < 0) {
				startX = 0;
				start = nHalfDots - X;
			} else {
				start = 0;
			}
			int stop;
			int stopX = X + nHalfDots;
			if (stopX > (dwInW - 1)) {
				stopX = dwInW - 1;
				stop = nHalfDots + (dwInW - 1 - X);
			} else {
				stop = nHalfDots * 2;
			}
			if (start > 0 || stop < nDots - 1) {
				CalTempContrib(start, stop);
				for (y = 0; y < dwInH; y++) {
					value = HorizontalFilter(bufImage, startX, stopX, start,
							stop, y, tmpContrib);
					pbOut.setRGB(x, y, value);
				}
			} else {
				for (y = 0; y < dwInH; y++) {
					value = HorizontalFilter(bufImage, startX, stopX, start,
							stop, y, normContrib);
					pbOut.setRGB(x, y, value);
				}
			}
		}
		return pbOut;
	} // end of HorizontalFiltering()

	private int VerticalFilter(BufferedImage pbInImage, int startY, int stopY,
			int start, int stop, int x, double[] pContrib) {
		double valueRed = 0.0;
		double valueGreen = 0.0;
		double valueBlue = 0.0;
		int valueRGB = 0;
		int i, j;
		for (i = startY, j = start; i <= stopY; i++, j++) {
			valueRGB = pbInImage.getRGB(x, i);
			valueRed += GetRedValue(valueRGB) * pContrib[j];
			valueGreen += GetGreenValue(valueRGB) * pContrib[j];
			valueBlue += GetBlueValue(valueRGB) * pContrib[j];
		}
		valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen),
				Clip((int) valueBlue));
		// System.out.println(valueRGB);
		return valueRGB;
	} // end of VerticalFilter()

	private BufferedImage VerticalFiltering(BufferedImage pbImage, int iOutH) {
		int iW = pbImage.getWidth();
		int iH = pbImage.getHeight();
		int value = 0;
//		System.out.println("width = "++" height = ");
		BufferedImage pbOut = new BufferedImage(iW, iOutH,
				BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < iOutH; y++) {
			int startY;
			int start;
			int Y = (int) (((double) y) * ((double) iH) / ((double) iOutH) + 0.5);
			startY = Y - nHalfDots;
			if (startY < 0) {
				startY = 0;
				start = nHalfDots - Y;
			} else {
				start = 0;
			}
			int stop;
			int stopY = Y + nHalfDots;
			if (stopY > (int) (iH - 1)) {
				stopY = iH - 1;
				stop = nHalfDots + (iH - 1 - Y);
			} else {
				stop = nHalfDots * 2;
			}
			if (start > 0 || stop < nDots - 1) {
				CalTempContrib(start, stop);
				for (int x = 0; x < iW; x++) {
					value = VerticalFilter(pbImage, startY, stopY, start, stop,
							x, tmpContrib);
					pbOut.setRGB(x, y, value);
				}
			} else {
				for (int x = 0; x < iW; x++) {
					value = VerticalFilter(pbImage, startY, stopY, start, stop,
							x, normContrib);
					pbOut.setRGB(x, y, value);
				}
			}
		}
		return pbOut;
	} // end of VerticalFiltering()

	int Clip(int x) {
		if (x < 0)
			return 0;
		if (x > 255)
			return 255;
		return x;
	}
}
