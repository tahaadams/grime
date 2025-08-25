#Directions
*****
**To Run The Application In TextImageProcessor**
1. Go to [TextImageProcessor][TextImageProcessor]
2. Run `public static void main(String []args)`

**To Run The Application In GUIImageProcessor**
1. Go to [GUIImageProcessor][GUIImageProcessor]
2. Run `public static void main(String []args)`

OR

1. Open the res file.
2. Open Assignment06.jar

**STRUCTURE FOR INPUTS IN THE TextImageProcessor**
- load **FilePath** ***NameOfNewDataImage***
- file **FilePath**
- save **FilePath** *NameOfDataImageFromPackage*
- quit

**For the following four commands, you will be prompted with whether or not you would like to use a DataImage as a Mask**
- filter **Component** *NameOfDataImageFromPackage* ***NameOfNewDataImage***
- adjust **Component** *NameOfDataImageFromPackage* ***NameOfNewDataImage***
- flip **Component** *NameOfDataImageFromPackage* ***NameOfNewDataImage***
- shift **Component** *NameOfDataImageFromPackage* ***NameOfNewDataImage***

Following this input:
- **MaskComponent** *NameOfDataImageFromPackage*

[TextImageProcessorTest]: test/TextImageProcessorTest.java
[TextImageProcessor]: src/TextImageProcessor.java
[GUIImageProcessor]: src/GUIImageProcessor.java