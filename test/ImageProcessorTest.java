import java.io.StringReader;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Represents the TextImageProcessorTest that tests all  the possible
 * cases of the Image Processor methods.
 */
public class ImageProcessorTest {

  // test that checks for IllegalArguments
  @Test(expected = IllegalArgumentException.class)
  public void IllegalTests() {
    IView im0 = new ImageView(null, null);
    IController ic0 = new ImageControl(null, null, null);
    RGB rgb0 = new RGB(-1, -2, 256);
  }


  @Test
  public void testTextImageProcessor() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.ppm image save " +
        "res/Savedimage.ppm image filter " +
        "red image image-red-filter save " +
        "res/image-red-filter.ppm " +
        "image-red-filter filter blue image image-blue-filter " +
        "save res/image-blue-filter.ppm " +
        "image-blue-filter filter green image image-green-filter " +
        "save res/image-green-filter.ppm " +
        "image-green-filter " +
        "filter value image image-value-filter " +
        "save res/image-value-filter.ppm " +
        "image-value-filter filter luma image image-luma-filter " +
        "save res/image-luma-filter " +
        "image-luma-filter filter intensity image image-intensity-filter " +
        "save res/image-intensity-filter.ppm " +
        "image-intensity-filter quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  @Test
  public void testTextImageProcessorHorizontalFlip() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.ppm " +
        "image save res/Savedimage.ppm " +
        "image horizontal-flip image image-HF save " +
        "res/image-HF.ppm image-HF quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  @Test
  public void testTextImageProcessorVerticalFlip() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.ppm " +
        "image save res/Savedimage.ppm " +
        "image vertical-flip image image-VF save " +
        "res/image-VF.ppm image-VF quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  @Test
  public void testTextImageProcessorBrightenImage() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.ppm " +
        "image save res/Savedimage.ppm " +
        "image brighten 50 image image-brighten save " +
        "res/image-brighten.ppm image-brighten quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  @Test
  public void testTextImageProcessorDarkenImage() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.ppm " +
        "image save res/Savedimage.ppm " +
        "Savedimage darken 50 image image-darken save " +
        "res/image-darken.ppm image-darken quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  // tests going from the horizontal image to the vertical image without
  // uploading the original image first just pulling directly from it
  @Test
  public void testTextImageProcessorHFtoVF() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/image-HF.ppm " +
        "imageHF vertical-flip imageHF imageHF-VF save " +
        "res/imageHF-VF.ppm imageHF-VF quit ");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  // testing going from the vertical image to the horizontal image
  @Test
  public void testTextImageProcessorVFtoHF() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/image-VF.ppm " +
        "imageVF horizontal-flip imageVF imageVF-HF save " +
        "res/imageVF-HF.ppm imageVF-HF quit ");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  // testing going from the horizontal image brightened and then darken the brightened image
  @Test
  public void testTextImageProcessorHFtoBrightthenDark() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.ppm " +
        "image save res/Savedimage.ppm " +
        "image horizontal-flip image image-HF save " +
        "res/image-HF.ppm image-HF " +
        "brighten 50 image-HF imageHF-brighten save " +
        "res/imageHF-brighten.ppm imageHF-brighten " +
        "darken 50 imageHF-brighten imageHF-brighten-darken save " +
        "res/imageHF-brighten-darken.ppm imageHF-brighten-darken quit ");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  // tests the vertical image brightened and then darken the brightened image
  @Test
  public void testTextImageProcessorVFtoBrightthenDark() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.ppm " +
        "image save res/Savedimage.ppm " +
        "image vertical-flip image image-VF save " +
        "res/image-VF.ppm image-VF " +
        "brighten 50 image-VF imageVF-brighten save " +
        "res/imageVF-brighten.ppm imageVF-brighten " +
        "darken 50 imageVF-brighten imageVF-brighten-darken save " +
        "res/imageVF-brighten-darken.ppm imageVF-brighten-darken quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  // tests the horizontal image from darken to brigthen
  @Test
  public void testTextImageProcessorHFtoDarktoBright() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.ppm " +
        "image save res/Savedimage.ppm " +
        "image horizontal-flip image image-HF save " +
        "res/image-HF.ppm image-HF " +
        "darken 50 image-HF imageHF-darken save " +
        "res/imageHF-darken.ppm imageHF-darken " +
        "brighten 50 imageHF-darken imageHF-darken-brighten save " +
        "res/imageHF-darken-brighten.ppm imageHF-darken-brighten quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  //tests the vertical image from brighten to darken
  @Test
  public void testTextImageProcessorVFtoDarktoBright() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.ppm " +
        "image save res/Savedimage.ppm " +
        "image vertical-flip image image-VF save " +
        "res/image-VF.ppm image-VF " +
        "darken 50 image-VF imageVF-darken save " +
        "res/imageVF-darken.ppm imageVF-darken " +
        "brighten 50 imageVF-darken imageVF-darken-brighten save " +
        "res/imageVF-darken-brighten.ppm imageVF-darken-brighten quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  @Test
  public void testTextImageProcessorNew() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.png image save " +
        "res/Savedimage.png image filter " +
        "red image image-red-filter save " +
        "res/image-red-filter.png " +
        "image-red-filter filter blue image image-blue-filter " +
        "save res/image-blue-filter.png " +
        "image-blue-filter filter green image image-green-filter " +
        "save res/image-green-filter.png " +
        "image-green-filter " +
        "filter value image image-value-filter " +
        "save res/image-value-filter.png " +
        "image-value-filter " +
        "filter luma image image-luma-filter " +
        "save res/image-luma-filter.png " +
        "image-luma-filter " +
        "filter intensity image image-intensity-filter " +
        "save res/image-intensity-filter.png " +
        "image-intensity-filter " +
        "filter sepia image image-sepia-filter " +
        "save res/image-sepia-filter.png " +
        "image-sepia-filter " +
        "adjust sharpen image image-sharpen-adjust " +
        "save res/image-sharpen-adjust.png " +
        "image-sharpen-adjust " +
        "adjust blur image image-blur-adjust " +
        "save res/image-blur-adjust.png " +
        "image-blur-adjust " +
        "quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  @Test
  public void testBlur() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader(
        "load res/myImage.png image " +
            "adjust blur image image-blur-adjust " +
            "adjust blur image-blur-adjust image-blur-adjust " +
            "adjust blur image-blur-adjust image-blur-adjust " +
            "adjust blur image-blur-adjust image-blur-adjust " +
            "adjust blur image-blur-adjust image-blur-adjust " +
            "adjust blur image-blur-adjust image-blur-adjust " +
            "adjust blur image-blur-adjust image-blur-adjust " +
            "adjust blur image-blur-adjust image-blur-adjust " +
            "adjust blur image-blur-adjust image-blur-adjust " +
            "adjust blur image-blur-adjust image-blur-adjust " +
            "adjust blur image-blur-adjust image-blur-adjust " +
            "save res/image-blur-adjust.png " +
            "image-blur-adjust " +
            "quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }


  @Test
  public void testTextImageProcessorNewPPM() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.ppm image save " +
        "res/Savedimage.ppm image filter " +
        "red image image-red-filter save " +
        "res/image-red-filter.ppm " +
        "image-red-filter filter blue image image-blue-filter " +
        "save res/image-blue-filter.ppm " +
        "image-blue-filter filter green image image-green-filter " +
        "save res/image-green-filter.ppm " +
        "image-green-filter " +
        "filter value image image-value-filter " +
        "save res/image-value-filter.ppm " +
        "image-value-filter " +
        "filter luma image image-luma-filter " +
        "save res/image-luma-filter.ppm " +
        "image-luma-filter " +
        "filter intensity image image-intensity-filter " +
        "save res/image-intensity-filter.ppm " +
        "image-intensity-filter " +
        "filter sepia image image-sepia-filter " +
        "save res/image-sepia-filter.ppm " +
        "image-sepia-filter " +
        "adjust sharpen image image-sharpen-adjust " +
        "save res/image-sharpen-adjust.ppm " +
        "image-sharpen-adjust " +
        "adjust blur image image-blur-adjust " +
        "save res/image-blur-adjust.ppm " +
        "image-blur-adjust " +
        "quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }


  @Test
  public void testTextImageProcessorNewBMP() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.bmp image save " +
        "res/Savedimage.bmp image filter " +
        "red image image-red-filter save " +
        "res/image-red-filter.bmp " +
        "image-red-filter filter blue image image-blue-filter " +
        "save res/image-blue-filter.bmp " +
        "image-blue-filter filter green image image-green-filter " +
        "save res/image-green-filter.bmp " +
        "image-green-filter " +
        "filter value image image-value-filter " +
        "save res/image-value-filter.bmp " +
        "image-value-filter " +
        "filter luma image image-luma-filter " +
        "save res/image-luma-filter.bmp " +
        "image-luma-filter " +
        "filter intensity image image-intensity-filter " +
        "save res/image-intensity-filter.bmp " +
        "image-intensity-filter " +
        "filter sepia image image-sepia-filter " +
        "save res/image-sepia-filter.bmp " +
        "image-sepia-filter " +
        "adjust sharpen image image-sharpen-adjust " +
        "save res/image-sharpen-adjust.bmp " +
        "image-sharpen-adjust " +
        "adjust blur image image-blur-adjust " +
        "save res/image-blur-adjust.bmp " +
        "image-blur-adjust " +
        "quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  @Test
  public void testTextImageProcessorNewJPG() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.jpg image save " +
        "res/Savedimage.jpg image filter " +
        "red image image-red-filter save " +
        "res/image-red-filter.jpg " +
        "image-red-filter filter blue image image-blue-filter " +
        "save res/image-blue-filter.jpg " +
        "image-blue-filter filter green image image-green-filter " +
        "save res/image-green-filter.jpg " +
        "image-green-filter " +
        "filter value image image-value-filter " +
        "save res/image-value-filter.jpg " +
        "image-value-filter " +
        "filter luma image image-luma-filter " +
        "save res/image-luma-filter.jpg " +
        "image-luma-filter " +
        "filter intensity image image-intensity-filter " +
        "save res/image-intensity-filter.jpg " +
        "image-intensity-filter " +
        "filter sepia image image-sepia-filter " +
        "save res/image-sepia-filter.jpg " +
        "image-sepia-filter " +
        "adjust sharpen image image-sharpen-adjust " +
        "save res/image-sharpen-adjust.jpg " +
        "image-sharpen-adjust " +
        "adjust blur image image-blur-adjust " +
        "save res/image-blur-adjust.jpg " +
        "image-blur-adjust " +
        "quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  @Test
  public void testTextImageProcessorNewJPGPPM() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.jpg image " +
        "save res/myImage-jpg-to-jpg.jpg image " +
        "save res/myImage-jpg-to-png.png image " +
        "save res/myImage-jpg-to-ppm.ppm image " +
        "save res/myImage-jpg-to-bmp.bmp image " +
        "quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  @Test
  public void testTextImageProcessorNewPPMBMP() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("load res/myImage.bmp image " +
        "save res/myImage-bmp-to-jpg.ppm image " +
        "save res/myImage-bmp-to-jpg.png image " +
        "save res/myImage-bmp-to-jpg.jpg image " +
        "save res/myImage-bmp-to-jpg.bmp image " +
        "quit");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }

  @Test
  public void testTextImageProcessorFILE() {
    IModel im00 = new ImageModel();
    Readable r00 = new StringReader("file res/script.txt");
    IView iv00 = new ImageView(System.out, im00);
    IController ic00 = new ImageControl(im00, iv00, r00);
    ic00.run();
    assertEquals(System.out, System.out);
  }
}