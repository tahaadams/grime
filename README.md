For best results, access via Intellij IDEA.

# NEW
****
**Added Masking Feature into TextImageProcessor**
- Added method `ImageData maskImage(ImageData updatedID, ImageData maskID)` in the [ImageData][ImageData] class
- Added method `boolean isWhite()` in the [RGB][RGB] class
- Added method `boolean isBlack()` in the [RGB][RGB] class
- Updated method `void run()` in the [ImageControl][ImageControl] class

**Updated Format of TextImage Processor Commands**
- Added method `RGB getRgb(RGB newRGB, RGB[][] currentCD, int ratioRadius, double[][] blur, int i,
  int j)` in the [ImageData][ImageData] class
- Added method `ImageData flip(String component)` in the [ImageData][ImageData] class
- Added method `ImageData shift(String component, int shift)` in the [ImageData][ImageData] class


# Table of Contents
****
- [**Directions**](#directions)
- [**ImageProcessorTest**](#imageprocessortestimageprocessortest)
- [**TextImageProcessor**](#textimageprocessortextimageprocessor-class--application)
- [**GUIImageProcessor**](#guiimageprocessorguiimageprocessor-class--application)
- [**GUIImageFeatures**](#guiimagefeaturesguiimagefeatures-class)

- [**IModel**](#imodelimodel-interface)

  - [ImageModel](#imagemodelimagemodel-class)

  - [ImageData](#imagedataimagedata-class)
  - [ImageHistogram](#imagehistogramimagehistogram-class)

  - [RGB](#rgbrgb-class)

- [**IView**](#iviewiview-interface)
  
  - [ImageView](#imageviewimageview-class)

- [**IController**](#icontrollericontroller-interface)
  
  - [ImageControl](#imagecontrolimagecontrol-class)
- [**Citations**](#citations)

## Directions
*****

**To Run The Application In TextImageProcessor** 
1. Go to [TextImageProcessor][TextImageProcessor]
2. Run `public static void main(String []args)`

**To Run The Application In GUIImageProcessor**
1. Go to [GUIImageProcessor][GUIImageProcessor]
2. Run `public static void main(String []args)`

OR

1. Open the res file.
2. Open grime.jar

[**Back to Table Of Contents**](#table-of-contents)

## [ImageProcessorTest][ImageProcessorTest]
*****
**Contains the general tests for the image processor***

*At its current state, the tests do not actually confirm the inputs work, due to technical issues. However, if you were to follows the given directions above, running the tests should create all the designated images without fail.

[**Back to Table Of Contents**](#table-of-contents)

## [TextImageProcessor][TextImageProcessor] Class / Application
****

**Contains the application that runs the image processor in text form**
 - `public static void main(String []args)` Runs the entire image processor through commands

[**Back to Table Of Contents**](#table-of-contents)

## [GUIImageProcessor][GUIImageProcessor] Class / Application
****

**Contains the application that runs the image processor in GUI form**
- `public static void main(String []args)` Runs the entire image processor through GUI

[**Back to Table Of Contents**](#table-of-contents)

## [GUIImageFeatures][GUIImageFeatures] Class
****

**Contains the application that runs the image processor in GUI form**
- `public static void main(String []args)` Runs the entire image processor through GUI

[**Back to Table Of Contents**](#table-of-contents)

## [IModel][IModel] Interface
****

**Contains the methods that alter the data.**
(*are represented in the class* ***ImageModel***)

[**Back to Table Of Contents**](#table-of-contents)

### [ImageModel][ImageModel] Class
****

`protected Map<String, ImageData> imagePackage` Holds a package of images in terms of String and ImageData

`ImageModel()` Creates a new ImageModel with an empty package of images

 - `public Map<String, ImageData> getImagePackage()` Pulls the package of images
 - `void loadImage(String filename, String name)` Directs how the image will load according to its image type
 - `void loadPPM(String filename, String name)` Loads a PPM image with given directory and assigns it a specific name
 - `void loadOther(String filename, String name)` Loads an image with given directory and assigns it a specific name
 - `ImageData getImage(String name)` Gets an image from the package of images based on the specific name
 - `void addImage(String name, ImageData data)` Adds an image to the package based on the specific name and image data
 - `void saveImage(String name, ImageData data)` Directs how the image will save according to its image type
 - `void savePPM(String name, ImageData data)` Saves a PPM image with given directory and uses an image data from the image package
 - `void saveOther(String name, ImageData data)` Saves an image with given directory and uses an image data from the image package
 - `BufferedImage returnBuffer(ImageData data)` Returns a BufferedImage of the given ImageData

[**Back to Table Of Contents**](#table-of-contents)

### [ImageData][ImageData] Class
****

`private int width` Holds the width of the image

`private int height` Holds the height of the image

`private RGB[][] colorData` Holds the color data of the multiple RGB values of the image

`private int maxValue` Holds the maximum RGB value of the image

`public ImageData(int w, int h, int mv, RGB[][] cd)` Creates a new ImageData according to the given width, height, color data, and maximum RGB value

- `protected void updatemaxValue()` Updates the maximum RGB value of the ImageData
- `protected int getWidth()` Pulls the width and returns it
- `protected int getHeight()` Pulls the height and returns it
- `protected RGB[][] getColorData()` Pulls the color data and returns it
- `protected int getMaxValue()` Pulls the maximum RGB value and returns it
- `protected ImageData shift(String component, int shift)` Shifts the RGB values based on the given component
- `protected ImageData shiftPixel(int shift)` Shifts the RGB values of the color data by the given amount and returns it
- `protected ImageData filter(String component)` Filters the RGB values of the color data by the given component and returns it
- `protected ImageData flip(String component)` Flips the RGB values by the given component and returns it
- `protected ImageData flipHorizontally()` Flips the RGB values of the color data horizontally and returns it
- `protected ImageData flipVertically()` Flips the RGB values of the color data vertically and returns it
- `protected ImageData adjust(String component)` Adjusts the RGB values of the color data by the given component and returns it
- `RGB getRgb(RGB newRGB, RGB[][] currentCD, int ratioRadius, double[][] blur, int i,
  int j)` Helper for adjusting the RGB values
- `ImageData maskImage(ImageData updatedID, ImageData maskID)` Masks the RGB values with the given reference Mask and updated RGB values

[**Back to Table Of Contents**](#table-of-contents)

### [ImageHistogram][ImageHistogram] Class
****
`int maxValue` Holds the maximum value in any section of the histogram

`int[] histogramR` Holds the histogram of the red component

`int[] histogramG` Holds the histogram of the green component

`int[] histogramB` Holds the histogram of the blue component

`int[] histogram` Holds the histogram of the intensity component

`public RGB(ImageData data)` Creates a new histogram based on the given data

- `public void paint(Graphics g)` Paints the histogram itself

[**Back to Table Of Contents**](#table-of-contents)

### [RGB][RGB] Class
****

`int red` Holds the red of the RGB

`int green` Holds the green of the RGB

`int blue` Holds the blue of the RGB

`public RGB(int r, int g, int b)` Creates a new RGB according to the given red, green, and blue

- `public int maxRGB()` Pulls the maximum RGB value of the RGB and returns it
- `public int getRed()` Pulls the red and returns it
- `public int getGreen()` Pulls the green and returns it
- `public int getBlue()` Pulls the blue and returns it
- `public RGB shiftRGB(int shift)` Shifts the RGB values of the RGB by the given amount and returns it
- `public RGB redRGB()` filters the RGB values of the RGB by the red component and returns it
- `public RGB greenRGB()` filters the RGB values of the RGB by the green component and returns it
- `public RGB blueRGB()` filters the RGB values of the RGB by the blue component and returns it
- `public RGB maxRGB()` filters the RGB values of the RGB by the max component and returns it
- `public RGB lumaRGB()` filters the RGB values of the RGB by the luma component and returns it
- `public RGB intensityRGB()` filters the RGB values of the RGB by the intensity component and returns it
- `public RGB sepiaRGB()` filters the RGB values of the RGB by a sepia filter and returns it
- `public RGB dRGB(double d)` adjusts the values of the RGB by the given double and returns it
- `public RGB fixRGB()` adjusts the values of the RGB and returns it
- `public RGB addRGB(RGB that)` adds that RGB to the RGB and returns it
- `protected boolean isWhite()` determines whether the RGB value is white
- `protected boolean isBlack()` determines whether the RGB value is black

[**Back to Table Of Contents**](#table-of-contents)

## [IView][IView] Interface
****

**Contains the methods that display the data.**
(*are represented in the class* ***ImageView***)

[**Back to Table Of Contents**](#table-of-contents)

### [ImageView][ImageView] Class
****

`private PrintStream out` Holds the output of the view 

`private IModel model` Holds the model of the view

`public ImageView(PrintStream out, IModel model)` Creates a new ImageView based on the given output and model

 - `public void showMenu()` Displays the menu message of the view
 - `public void showWelcome()` Displays the welcome message of the view
 - `public void showGoodbye()` Displays the goodbye message of the view
 - `public void showCommand(String command)` Displays the command message of the view with the specific command
 - `public void showNaming()` Displays the name message of the view
 - `public void showOptions(String inputType, String options)` Displays the option message of the view with the specific input type and options
 - `public void showError(String type, String message)` Displays the error message of the view with the specfic type and message

[**Back to Table Of Contents**](#table-of-contents)

## [IController][IController] Interface
****

**Contains the methods that transfer the data.**
(*are represented in the class* ***ImageControl***)

[**Back to Table Of Contents**](#table-of-contents)

### [ImageControl][ImageControl] Class
****

`private Scanner in` Holds the input of the controller

`private IView view` Holds the view of the controller

`private IModel model` Holds the model of the controller

`public ImageControl(IModel model, IView view, Readable in)` Creates a new ImageControl based on the given model, view, and input

- `public void run()` Runs the controller

[**Back to Table Of Contents**](#table-of-contents)

# Citations
****
 - **myImage.jpg** From Tyler Knohl

[**Back to Table Of Contents**](#table-of-contents)

[ImageHistogram]: src/ImageHistogram.java
[GUIImageFeatures]: src/GUIImageFeatures.java
[GUIImageProcessor]: src/GUIImageProcessor.java
[ImageProcessorTest]: test/ImageProcessorTest.java
[TextImageProcessor]: src/TextImageProcessor.java
[IModel]: src/IModel.java
[IView]: src/IView.java
[IController]: src/IController.java
[ImageModel]: src/ImageModel.java
[ImageData]: src/ImageData.java
[RGB]: src/RGB.java
[ImageView]: src/ImageView.java
[ImageControl]: src/ImageControl.java
