import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Represents the model interface which loads the image and
 * retrieves the image packages.
 */
public interface IModel {

  /**
   * Loads the image of the specific file.
   *
   * @param filename takes in the path of the file
   * @param name     is the name of the file
   */
  void loadImage(String filename, String name);

  /**
   * Represents the method that gets the image name from the map.
   * Pulls the value from the map.
   *
   * @return
   */
  ImageData getImage(String name);

  /**
   * Represents the method that adds the image.
   * It takes in the string name and the imageData.
   */
  void addImage(String name, ImageData data);

  /**
   * Represents the method that saves the edited image,
   * ie; it being brightened, flipped, filterd, or darkened.
   *
   * @param filename is the name of the file.
   * @param data the data type.
   */
  void saveOther(String filename, ImageData data);


  /**
   * Represents the method that saves the edited image,
   * ie; it being brightened, flipped, filterd, or darkened.
   *
   * @param filename is the name of the file.
   * @param data the data type.
   */
  void saveImage(String filename, ImageData data);

  BufferedImage returnBuffer(ImageData data);

  /**
   * Represents the method that gets the package.
   *
   * @return the package.
   */
  public Map<String, ImageData> getImagePackage();
}


