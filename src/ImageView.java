

import java.io.PrintStream;

/**
 * Represents the view class for the MVC model.
 * Going to hold the methods that contain the view operations.
 * It will then be called in the controller and then printed to the console.
 */
public class ImageView implements IView {
  private PrintStream out;
  private IModel model;


  /**
   * Constructor that takes in a PrintStream to output in the console.
   *
   * @param out   prinstream
   * @param model IModel
   * @throws IllegalArgumentException if any of the fields are null.
   */
  public ImageView(PrintStream out, IModel model) throws IllegalArgumentException {
    if (out == null || model == null) {
      throw new IllegalArgumentException("The Model or the PrintStream cannot be null");
    }
    this.out = out;
    this.model = model;
  }

  /**
   * Represents the menu of the console which the user will be able to see.
   */
  public void showMenu() {
    out.println("\nMENU");
    out.println("---------------------------------------"
        + "------------------------------------------");
    out.println("load: load a file name via the directory");
    out.println("file: upload a text file top run a script");
    out.println("save: save the specified file");
    out.println("---------------------------------------"
        + "------------------------------------------");
    out.println("filter: filter the image");
    out.println("adjust: adjust the image");
    out.println("flip: flip the image");
    out.println("shift: shift the image");
    out.println("-----------------------------------------"
        + "----------------------------------------");
    out.println("quit: quit the program");
    out.println("---------------------------------------"
        + "------------------------------------------");
    out.println("Current Files in Package: " + model.getImagePackage().keySet());
    out.println("");
    out.print("Enter command: ");
    // think this will work
  }

  @Override
  public void showWelcome() {
    out.println("\nWelcome to the Image Processor.");
  }

  @Override
  public void showGoodbye() {
    out.println("\nGoodbye!");
  }

  public void showCommand(String command) {
    out.print("\nSelect file to " + command + ": ");
  }

  public void showNaming() {
    out.print("\nGive name to new file: ");
  }

  public void showOptions(String inputType, String options) {
    out.print("\nInput " + inputType + " (" + options + "): ");
  }

  public void showError(String type, String message) {
    out.print("\nInvalid " + type + ", Please try again. (" + message + ")\n");
  }

}

