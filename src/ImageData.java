/**
 * Represents the class that contains different methods that
 * operate on the image data.
 * Holds the name of the string and the RGB values of the pixels.
 * Holding the RGB files.
 * Going to hold all files:
 * horizontal, vertical, brighten, save & red
 */
public class ImageData {
  private int width;
  private int height;
  private int maxValue;
  private int imageType;
  private RGB[][] colorData;


  /**
   * Represents the only constructor in the ImageData class.
   *
   * @param w  width
   * @param h  height
   * @param mv maximum value
   * @param cd color data
   * @throws IllegalArgumentException when the RGB is null
   *                                  or the width or height
   *                                  are less than 1 and the
   *                                  max value is less than 0 or
   *                                  more than 255.
   */
  public ImageData(int w, int h, int mv, int it, RGB[][] cd) throws IllegalArgumentException {
    if (cd == null) {
      throw new IllegalArgumentException("RGB cannot be null ");
    }
    if (w < 1 || h < 1) {
      throw new IllegalArgumentException("The width and height cannot be"
          + "less than 1");
    }
    if (mv < 0 || mv > 255) {
      throw new IllegalArgumentException("The maximum value cannot be less than zero "
          + "or greater than 255.");
    }
    this.width = w;
    this.height = h;
    this.maxValue = mv;
    this.imageType = it;
    this.colorData = cd;
  }

  protected void updatemaxValue() {
    int newMaxValue = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newMaxValue = Math.max(newMaxValue, colorData[i][j].maxRGB());
      }
    }
    this.maxValue = newMaxValue;
  }

  protected int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  protected int getMaxValue() {
    return this.maxValue;
  }

  protected int getImageType() {
    return this.imageType;
  }

  protected RGB[][] getColorData() {
    RGB[][] copy = new RGB[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        RGB refPixel = this.colorData[i][j];
        copy[i][j] = new RGB(refPixel.red, refPixel.green, refPixel.blue);
      }
    }
    return copy;
  }


  // filter method that uses all the methods that uses the six RGB methods
  // the different methods that make up one filter
  protected ImageData filter(String component) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // using a switch statement to minimize code duplication
        switch (component) {
          case "red":
            this.colorData[i][j] = colorData[i][j].redRGB();
            break;
          case "blue":
            this.colorData[i][j] = colorData[i][j].blueRGB();
            break;
          case "green":
            this.colorData[i][j] = colorData[i][j].greenRGB();
            break;
          case "value":
            this.colorData[i][j] = colorData[i][j].valueRGB();
            break;
          case "luma":
            this.colorData[i][j] = colorData[i][j].lumaRGB();
            break;
          case "intensity":
            this.colorData[i][j] = colorData[i][j].intensityRBG();
            break;
          case "sepia":
            this.colorData[i][j] = colorData[i][j].sepiaRGB();
            break;
          default:
            this.colorData[i][j] = colorData[i][j];
        }
      }
    }
    return this;
  }

  // blurs the image, use the getKernelData method
  protected ImageData adjust(String component) {
    // uses a kernel
    RGB newRGB;
    RGB[][] currentCD = this.getColorData();
    int ratioRadius;
    double[][] blur = {
        {1 / 16d, 1 / 8d, 1 / 16d},
        {1 / 8d, 1 / 4d, 1 / 8d},
        {1 / 16d, 1 / 8d, 1 / 16d}
    };
    double[][] sharpen = {
        {-1 / 8d, -1 / 8d, -1 / 8d, -1 / 8d, -1 / 8d},
        {-1 / 8d, 1 / 4d, 1 / 4d, 1 / 4d, -1 / 8d},
        {-1 / 8d, 1 / 4d, 1, 1 / 4d, -1 / 8d},
        {-1 / 8d, 1 / 4d, 1 / 4d, 1 / 4d, -1 / 8d},
        {-1 / 8d, -1 / 8d, -1 / 8d, -1 / 8d, -1 / 8d}};
    // at a specific pixel, want to multiply
    // it by the given matrix using a 3x3 box for every
    // pixel value other than if it is on the edge
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        switch (component) {
          case "blur":
            ratioRadius = (blur.length - 1) / 2;
            newRGB = new RGB(0, 0, 0);
            newRGB = getRgb(newRGB, currentCD, ratioRadius, blur, i, j);
            newRGB = newRGB.fixRGB();
            colorData[i][j] = newRGB;
            break;
          case "sharpen":
            ratioRadius = (sharpen.length - 1) / 2;
            newRGB = new RGB(0, 0, 0);
            newRGB = getRgb(newRGB, currentCD, ratioRadius, sharpen, i, j);
            newRGB = newRGB.fixRGB();
            colorData[i][j] = newRGB;
            break;
          default:
            this.colorData[i][j] = colorData[i][j];

        }

      }
    }
    return this;
  }

  private RGB getRgb(RGB newRGB, RGB[][] currentCD, int ratioRadius, double[][] blur, int i,
      int j) {
    for (int k = 0; k < blur.length; k++) {
      for (int l = 0; l < blur.length; l++) {
        if (!((i + (k - ratioRadius) <= -1)
            || (i + (k - ratioRadius) >= height)
            || (j + (l - ratioRadius) <= -1)
            || (j + (l - ratioRadius) >= width))) {
          newRGB = newRGB.addRGB(currentCD[i][j].dRGB(blur[k][l]));
        }
      }
    }
    return newRGB;
  }

  protected ImageData flip(String component) {
    switch (component) {
      case "horizontal":
        return this.flipHorizontally();
      case "vertical":
        return this.flipVertically();
      default:
        return this;
    }
  }

  // flips the image over the horizontal axis
  protected ImageData flipHorizontally() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width / 2; j++) {
        RGB temporaryDataOne = colorData[i][j];
        RGB temporaryDataTwo = colorData[i][width - 1 - j];
        colorData[i][j] = temporaryDataTwo;
        colorData[i][width - 1 - j] = temporaryDataOne;
      }
    }
    return this;
  }

  // flips the image over the vertical axis
  protected ImageData flipVertically() {
    for (int i = 0; i < height / 2; i++) {
      RGB[] temporaryDataOne = colorData[i];
      RGB[] temporaryDataTwo = colorData[height - 1 - i];
      colorData[i] = temporaryDataTwo;
      colorData[height - 1 - i] = temporaryDataOne;
    }
    return this;
  }

  protected ImageData shift(String component, int shift) {
    switch (component) {
      case "brighten":
        return this.shiftPixel(shift);
      case "darken":
        return this.shiftPixel(shift * -1);
      default:
        return this;
    }
  }

  // brightens the pixels based on the given value
  protected ImageData shiftPixel(int shift) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        colorData[i][j] = colorData[i][j].shiftRGB(shift);
      }
    }
    return this;
  }

  // downscales the image
  protected ImageData maskImage(ImageData updatedID, ImageData maskID) {
    boolean maskIsGood = true;
    boolean dimensionIsGood = updatedID.width == maskID.width && updatedID.height == maskID.height;

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        if (!(maskID.colorData[i][j].isBlack() || maskID.colorData[i][j].isWhite())) {
          maskIsGood = false;
        }
      }
    }

    if (maskIsGood && dimensionIsGood) {
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          if (maskID.colorData[i][j].isBlack()) {
            this.colorData[i][j] = updatedID.colorData[i][j];
          }
        }
      }
      return this;
    }

    return this;
  }

}
