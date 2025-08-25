import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


/**
 * This class contains utility methods to read a PPM image from file and
 * simply print its contents. Feel free to change this method
 * as required.
 */
public class ImageModel implements IModel {
  protected Map<String, ImageData> imagePackage;

  /**
   * Constructor that initializes an empty 1D array.
   * Can define the array size with the number zero because
   * it is not filled yet.
   */
  public ImageModel() {
    this.imagePackage = new TreeMap<String, ImageData>();
  }

  @Override
  public Map<String, ImageData> getImagePackage() {
    return this.imagePackage;
  }


  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @param name     the name of the file.
   */
  public void loadOther(String filename, String name) {
    BufferedImage bi;
    try {
      bi = ImageIO.read(new FileInputStream(filename));
    } catch (IOException e) {
      System.out.println("\nInvalid filepath, Please try again. ("
          + filename + " not a valid File type)\n");
      return;
    }
    //now set up the scanner to read from the string we just built
    // sc = new Scanner(bi.toString());
    int imageType = bi.getType();

    int width = bi.getWidth();
    System.out.println("\nWidth of image: " + width);
    int height = bi.getHeight();
    System.out.println("Height of image: " + height);
    System.out.println("Image type: " + bi.getType());
    System.out.println("RGB of first pixel: " + bi.getRGB(0, 0));

    RGB[][] newColorData = new RGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        bi.getRGB(j, i);
        int r = new Color(bi.getRGB(j, i)).getRed();
        int g = new Color(bi.getRGB(j, i)).getGreen();
        int b = new Color(bi.getRGB(j, i)).getBlue();
        newColorData[i][j] = new RGB(r, g, b);
      }
    }

    int maxValue = new ImageData(width, height, 0, imageType, newColorData).getMaxValue();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    ImageData newImageData = new ImageData(width, height, maxValue, imageType, newColorData);
    this.addImage(name, newImageData);

  }

  @Override
  public ImageData getImage(String name) {
    ImageData id = this.imagePackage.get(name);
    if (id == null) {
      return null;
    } else {
      return new ImageData(id.getWidth(), id.getHeight(), id.getMaxValue(),
          id.getImageType(), id.getColorData());
    }
  }

  @Override
  public void addImage(String name, ImageData data) {
    if (this.imagePackage.containsKey(name)) {
      this.imagePackage.replace(name, data);
    } else {
      this.imagePackage.put(name, data);
    }
  }

  @Override
  public void saveOther(String filename, ImageData data) {
    // file out = new file and whgat every name is
    data.updatemaxValue();
    File output = new File(filename);
    RGB[][] cD = data.getColorData();
    BufferedImage bi = new BufferedImage(data.getWidth(),
        data.getHeight(), data.getImageType());
    try {
      for (int i = 0; i < data.getHeight(); i++) {
        for (int j = 0; j < data.getWidth(); j++) {
          bi.setRGB(j, i, new Color(cD[i][j].getRed(),
              cD[i][j].getGreen(), cD[i][j].getBlue()).getRGB());
        }
      }
      ImageIO.write(bi, filename.substring(filename.lastIndexOf(".") + 1), output);
    } catch (IOException e) {
      System.out.println("File path " + filename + " not found!");
    }
  }

  @Override
  public BufferedImage returnBuffer(ImageData data) {
    // file out = new file and whgat every name is
    data.updatemaxValue();
    RGB[][] cD = data.getColorData();
    BufferedImage bi = new BufferedImage(data.getWidth(), data.getHeight(), data.getImageType());
    for (int i = 0; i < data.getHeight(); i++) {
      for (int j = 0; j < data.getWidth(); j++) {
        bi.setRGB(j, i, new Color(cD[i][j].getRed(),
            cD[i][j].getGreen(), cD[i][j].getBlue()).getRGB());
      }
    }
    return bi;
  }

  // method that saves everything that is not a JPG,BPM, or a PNG
  // need to write a method that says to do the save image as a jpg, bpm,  or png, and then
  // to say if it ends in load ppm go here if not go there
  protected void savePPM(String filename, ImageData data) {
    // file out = new file and whgat every name is
    data.updatemaxValue();
    RGB[][] cD = data.getColorData();
    File output = new File(filename);
    try {
      FileWriter out = new FileWriter(output);
      out.write("P3\n");
      out.write("# Created by Image Processor version 2.0.0\n");
      out.write(data.getWidth() + " " + data.getHeight() + "\n");
      out.write(data.getMaxValue() + "\n");
      for (int i = 0; i < data.getHeight(); i++) {
        for (int j = 0; j < data.getWidth(); j++) {
          out.write(cD[i][j].getRed() + "\n");
          out.write(cD[i][j].getGreen() + "\n");
          out.write(cD[i][j].getBlue() + "\n");
        }
      }
      out.close();
    } catch (IOException e) {
      System.out.println("File path " + filename + " not found!");
    }
  }

  // method that works as a helper for which class to go to
  // need to make a method that abstracts the method in save image and load image
  /**
   * Represents the method that saves the edited image,
   * ie; it being brightened, flipped, filterd, or darkened.
   *
   * @param filename is the name of the file.
   * @param data the data type.
   */
  public void saveImage(String filename, ImageData data) {
    if (filename.substring(filename.lastIndexOf(".") + 1).equals("ppm")) {
      savePPM(filename, data);
    } else {
      saveOther(filename, data);
    }
  }

  // method that works as a helper for which class to go to
  // need to make a method that abstracts the method in save image and load image
  /**
   * Loads the image of the specific file.
   *
   * @param filename takes in the path of the file
   * @param name     is the name of the file
   */
  public void loadImage(String filename, String name) {
    if (filename.substring(filename.lastIndexOf(".") + 1).equals("ppm")) {
      loadPPM(filename, name);
    } else {
      loadOther(filename, name);
    }
  }

  // loads the ppm
  /**
   * Loads the image of a PPM file.
   *
   * @param filename takes in the path of the file
   * @param name     is the name of the file
   */
  public void loadPPM(String filename, String name) {
    Scanner sc;
    // have it change the values as required
    // file name from the user
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File path " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("\nWidth of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
    RGB[][] newColorData = new RGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        newColorData[i][j] = new RGB(r, g, b);
      }
    }
    ImageData newImageData = new ImageData(width, height, maxValue, 3, newColorData);
    this.addImage(name, newImageData);
  }
}

