
/**
 * Represents an RGB (red green and blue).
 */
public class RGB {
  protected int red;
  protected int green;
  protected int blue;

  /**
   * Represents the constructor for red green and blue. It is the only
   * constructor inthe class and represents the exceptions
   * to be thrown if there are any.
   *
   * @param r red
   * @param g green
   * @param b blue
   * @throws IllegalArgumentException if the red green or blue are more 255 or less than 0
   */
  public RGB(int r, int g, int b) {
    this.red = r;
    this.green = g;
    this.blue = b;
  }

  public int maxRGB() {
    return Math.max(this.red, Math.max(this.blue, this.green));
  }

  public int getRed() {
    return this.red;
  }

  public int getGreen() {
    return this.green;
  }

  public int getBlue() {
    return this.blue;
  }

  /**
   * Represents the method which brightens and darkens the image.
   *
   * @param shift integer
   * @return a RGB
   */
  // create the brightening or darkening of the image
  public RGB shiftRGB(int shift) {
    // if the value is greter than 255 it stays white
    if (shift > 0) {
      return new RGB(Math.min(this.red + shift, 255),
          Math.min(this.green + shift, 255), Math.min(this.blue + shift, 255));
    } else if (shift < 0) {
      return new RGB(Math.max(this.red + shift, 0),
          Math.max(this.green + shift, 0), Math.max(this.blue + shift, 0));
    } else {
      return new RGB(this.red, this.green, this.blue);
    }
  }


  //returns the filter based on red
  public RGB redRGB() {
    return new RGB(this.red, this.red, this.red);
  }

  // returns the filter based on green
  public RGB greenRGB() {
    return new RGB(this.green, this.green, this.green);
  }

  // returns the filter based on blue
  public RGB blueRGB() {
    return new RGB(this.blue, this.blue, this.blue);
  }

  // returns the filter based on value
  public RGB valueRGB() {
    int value = Math.max(this.red, Math.max(this.blue, this.green));
    return new RGB(value, value, value);
  }

  /**
   * Represents the filter of an image based on the luma.
   *
   * @return RGB
   */
  // returns the filter based on luma
  public RGB lumaRGB() {
    int luma = (int) Math.round((0.2126 * this.red)
        + (0.7152 * this.green) + (0.0722 * this.blue));
    return new RGB(luma, luma, luma);
  }

  //returns the filter based on intensity
  public RGB intensityRBG() {
    int intensity = (this.red + this.green + this.blue) / 3;
    return new RGB(intensity, intensity, intensity);
  }

  // returns the sepia filter of an image
  /**
   * Adjusts the RGB values by a sepia filter.
   *
   * @return RGB
   */
  public RGB sepiaRGB() {
    int sepiaR = (int) Math.round((0.393 * this.red)
        + (0.769 * this.green) + (0.189 * this.blue));
    int sepiaG = (int) Math.round((0.349 * this.red)
        + (0.686 * this.green) + (0.168 * this.blue));
    int sepiaB = (int) Math.round((0.272 * this.red)
        + (0.534 * this.green) + (0.131 * this.blue));
    return new RGB(sepiaR, sepiaG, sepiaB).fixRGB();
  }

  //take in a double and reduces the values of RGB by said double , want to fix
  // the doubles to a random amount
  /**
   * Adjusts the RGB values by the given double value.
   *
   * @return RGB
   */
  public RGB dRGB(double d) {
    int dR = (int) Math.round(d * this.red);
    int dG = (int) Math.round(d * this.green);
    int dB = (int) Math.round(d * this.blue);
    return new RGB(dR, dG, dB);
  }

  protected RGB fixRGB() {
    int newR;
    int newG;
    int newB;
    if (this.red > 255) {
      newR = 255;
    } else {
      newR = Math.max(this.red, 0);
    }
    if (this.green > 255) {
      newG = 255;
    } else {
      newG = Math.max(this.green, 0);
    }
    if (this.blue > 255) {
      newB = 255;
    } else {
      newB = Math.max(this.blue, 0);
    }
    return new RGB(newR, newG, newB);
  }

  protected boolean isWhite() {
    return this.red == 255 && this.green == 255 && this.blue == 255;
  }

  protected boolean isBlack() {
    return this.red == 0 && this.green == 0 && this.blue == 0;
  }

  //adds RGB to other RGB

  public RGB addRGB(RGB that) {
    return new RGB(this.red + that.red, this.green + that.green, this.blue + that.blue);
  }
}

