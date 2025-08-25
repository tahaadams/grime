
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the controller class which operates on both the IView and IModel
 * classes and runs their methods.
 */
public class ImageControl implements IController {
  private Scanner in;
  private final IView view;
  private final IModel model;

  /**
   * Represents the constructor which takes in the mode, view, and in.
   *
   * @param model IModel
   * @param view  IView
   * @param in    Readable
   * @throws IllegalArgumentException if any of the fields are null.
   */
  public ImageControl(IModel model, IView view, Readable in) throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("The model nor view nor Scanner can be null");
    }
    this.model = model;
    this.view = view;
    this.in = new Scanner(in);
  }


  /**
   * Represents the run method which essentially says "go"
   * to the controller and operates on all
   * methods in the model and view classes.
   */
  public void run() {
    String curDataName;
    String newDataName;
    String filePathing;
    ImageData curData;
    String component;
    String shift;
    String maskComponent;
    String maskDataName;
    ImageData maskData;
    ImageData heldData;

    boolean quit = false;

    view.showWelcome();

    while (!quit) {

      view.showMenu();

      String option = in.next();

      switch (option) {
        case "file":
          view.showCommand("run");
          filePathing = in.next();
          try {
            this.in = new Scanner(new FileInputStream(filePathing));
          } catch (IOException e) {
            view.showError("script", "File " + filePathing + " Not Found");
          }
          break;
        case "load":
          view.showCommand("load");
          filePathing = in.next();
          view.showNaming();
          newDataName = in.next();
          model.loadImage(filePathing, newDataName);
          break;
        case "filter":
          view.showOptions("filter type", "red, blue, "
              + "green, value, luma, intensity, sepia");
          component = in.next();
          if (!component.equals("red")
              && !component.equals("blue")
              && !component.equals("green")
              && !component.equals("value")
              && !component.equals("luma")
              && !component.equals("intensity")
              && !component.equals("sepia")) {
            view.showError("input", "Input " + component + " Is Not A filter Type");
            break;
          }
          view.showCommand("filter " + component);
          curDataName = in.next();
          view.showNaming();
          newDataName = in.next();
          curData = model.getImage(curDataName);
          if (curData == null) {
            view.showError("selection", "File " + curDataName + " Does Not Exist In Package");
            break;
          }
          view.showOptions("whether or not you want to mask", "yes, no");
          maskComponent = in.next();
          if (maskComponent.equals("yes")) {
            view.showCommand("use for masking");
            maskDataName = in.next();
            maskData = model.getImage(maskDataName);
            if (maskData == null) {
              view.showError("selection",
                  "File " + maskDataName + " Does Not Exist In Package");
              break;
            }
            heldData = model.getImage(curDataName).filter(component);
            model.addImage(newDataName, curData.maskImage(heldData, maskData));
          }
          else if (maskComponent.equals("no")) {
            curData = curData.filter(component);
            model.addImage(newDataName, curData);
          }
          else {
            view.showError("input", "Input " + maskComponent + " Is Not A Type");
          }
          break;
        case "adjust":
          view.showOptions("adjust type", "blur, sharpen");
          component = in.next();
          if (!component.equals("blur")
              && !component.equals("sharpen")) {
            view.showError("input", "Input " + component + " Is Not A adjust Type");
            break;
          }
          view.showCommand("adjust " + component);
          curDataName = in.next();
          view.showNaming();
          newDataName = in.next();
          curData = model.getImage(curDataName);
          if (curData == null) {
            view.showError("selection", "File " + curDataName + " Does Not Exist In Package");
            break;
          }
          view.showOptions("whether or not you want to mask", "yes, no");
          maskComponent = in.next();
          if (maskComponent.equals("yes")) {
            view.showCommand("use for masking");
            maskDataName = in.next();
            maskData = model.getImage(maskDataName);
            if (maskData == null) {
              view.showError("selection",
                  "File " + maskDataName + " Does Not Exist In Package");
              break;
            }
            heldData = model.getImage(curDataName).adjust(component);
            model.addImage(newDataName, curData.maskImage(heldData, maskData));
          }
          else if (maskComponent.equals("no")) {
            curData = curData.adjust(component);
            model.addImage(newDataName, curData);
          }
          else {
            view.showError("input", "Input " + maskComponent + " Is Not A Type");
          }
          break;
        case "flip":
          view.showOptions("flip type", "horizontal, vertical");
          component = in.next();
          if (!component.equals("horizontal")
              && !component.equals("vertical")) {
            view.showError("input", "Input " + component + " Is Not A flip Type");
            break;
          }
          view.showCommand("flip " + component + "ly");
          curDataName = in.next();
          view.showNaming();
          newDataName = in.next();
          curData = model.getImage(curDataName);
          if (curData == null) {
            view.showError("selection", "File " + curDataName + " Does Not Exist In Package");
            break;
          }
          view.showOptions("whether or not you want to mask", "yes, no");
          maskComponent = in.next();
          if (maskComponent.equals("yes")) {
            view.showCommand("use for masking");
            maskDataName = in.next();
            maskData = model.getImage(maskDataName);
            if (maskData == null) {
              view.showError("selection",
                  "File " + maskDataName + " Does Not Exist In Package");
              break;
            }
            heldData = model.getImage(curDataName).flip(component);
            model.addImage(newDataName, curData.maskImage(heldData, maskData));
          }
          else if (maskComponent.equals("no")) {
            curData = curData.flip(component);
            model.addImage(newDataName, curData);
          }
          else {
            view.showError("input", "Input " + maskComponent + " Is Not A Type");
          }
          break;
        case "shift":
          view.showOptions("shift type", "brighten, darken");
          component = in.next();
          if (!component.equals("brighten")
              && !component.equals("darken")) {
            view.showError("input", "Input " + component + " Is Not A shift Type");
            break;
          }
          view.showOptions(component + " amount", "whole number");
          shift = in.next();
          try {
            Integer.parseInt(shift);
          } catch (NumberFormatException n) {
            view.showError("input", "Input " + shift + " Is Not An Integer");
            break;
          }
          view.showCommand(component);
          curDataName = in.next();
          view.showNaming();
          newDataName = in.next();
          curData = model.getImage(curDataName);
          if (curData == null) {
            view.showError("selection", "File " + curDataName + " Does Not Exist In Package");
            break;
          }
          view.showOptions("whether or not you want to mask", "yes, no");
          maskComponent = in.next();
          if (maskComponent.equals("yes")) {
            view.showCommand("use for masking");
            maskDataName = in.next();
            maskData = model.getImage(maskDataName);
            if (maskData == null) {
              view.showError("selection",
                  "File " + maskDataName + " Does Not Exist In Package");
              break;
            }
            heldData = model.getImage(curDataName).shift(component, Integer.parseInt(shift));
            model.addImage(newDataName, curData.maskImage(heldData, maskData));
          }
          else if (maskComponent.equals("no")) {
            curData = curData.shift(component, Integer.parseInt(shift));
            model.addImage(newDataName, curData);
          }
          else {
            view.showError("input", "Input " + maskComponent + " Is Not A Type");
          }
          break;
        case "save":
          view.showNaming();
          filePathing = in.next();
          view.showCommand("save");
          curDataName = in.next();
          curData = model.getImage(curDataName);
          if (curData == null) {
            view.showError("selection", "File " + curDataName + " Does Not Exist In Package");
            break;
          }
          model.saveImage(filePathing, curData);
          // save specified image
          break;
        case "quit":
          quit = true;
          break;
        default:
          view.showError("command", "Command " + option + " Does Not Exist");
      }
    }
    view.showGoodbye();
  }
}



