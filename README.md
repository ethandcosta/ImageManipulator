# Assignment4-OOD

## _The image sunset.ppm and all its derivatives used in this assignment are artistic properties under David Zhang._

## _David Zhang gives access for these images to be used in this assignment and future iterations of this assignment._

### Provide the following below as arguments in the command line

**Note: IT'S VERY IMPORTANT THAT THE QUOTATION MARKS REMAIN WHEN PASSING IN THE ARGUMENTS!**
**Note: IT'S ALSO VERY IMPORTANT THAT WHEN RUNNING THE PROGRAM WITHOUT CLI ARGUMENTS AND TYPING IN THE CONSOLE, THAT THE SAME SYNTAX FOR THE COMMANDS BELOW ARE FOLLOWED _EXCEPT_ THAT THE QUOTES ARE REMOVED**
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
"load res/sunset.ppm sunset"
"horizontal-flip sunset sunset-horizontal"
"brighten sunset sunset-brighten-10 10"
"brighten sunset sunset-brighten-25 25"
"brighten sunset sunset-darken-10 -10"
"brighten sunset sunset-darken-25 -25"
"vertical-flip sunset sunset-vertical"
"red-component sunset sunset-red-component"
"green-component sunset sunset-green-component"
"blue-component sunset sunset-blue-component"
"value-component sunset sunset-value-component"
"luma-component sunset sunset-luma-component"
"intensity-component sunset sunset-intensity-component"
"horizontal-flip sunset-vertical sunset-vertical-horizontal"
"vertical-flip sunset-horizontal sunset-horizontal-vertical"
"brighten sunset sunset-combo 13"
"horizontal-flip sunset-combo sunset-combo"
"vertical-flip sunset-combo sunset-combo"
"luma-component sunset-combo sunset-combo"
"save res/sunset-combo.ppm sunset-combo"
"save res/sunset-vertical.ppm sunset-vertical"
"save res/sunset-horizontal.ppm sunset-horizontal"
"save res/sunset-green.ppm sunset-green-component"
"save res/sunset-brighten-10.ppm sunset-brighten-10"
"save res/sunset-darken-10.ppm sunset-darken-10"
"save res/sunset-darken-25.ppm sunset-darken-25"
"save res/sunset-brighten-25.ppm sunset-brighten-25"
"save res/sunset-red.ppm sunset-red-component"
"save res/sunset-blue.ppm sunset-blue-component"
"save res/sunset-luma.ppm sunset-luma-component"
"save res/sunset-value.ppm sunset-value-component"
"brighten sunset sunset-dummy -45"
"save res/sunset-dummy.ppm sunset-dummy"
"save res/sunset-dummy.ppm sunset-combo"
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

### All commands should be inputted in the same format as per the assignment **except** the brighten command where the increment value _must_ come after the source name and then the destination name.

##### PPM files in the res/ folder with the word "reference" in its name refers to a GIMP edited PPM file for comparison purposes.

- interface ImageProcessorController:
  This interface contains the listen method which acts as the listener for the controller of this program. It is designed for futureproofing for later iterations of controllers.

- class ImageProcessorControllerImpl:
  Represents the implementation of the ImageProcessorController for Assignment 4 with a hashmap to map string inputs to operations with the futureproofing idea that we can later map keystrokes and button presses to the existing map of strings. Also a version "library" of image name strings is mapped with a corresponding model to recall existing images that have been loaded to the program. Either the existing model stays as the template or "focus model", or it is changed for one in the "library" of models, or it is overridden.

- class AbstractImageProcessorModel:
  Represents an abstraction of the ImageProcessorModel which will probably all require the methods to add an operation, remove an operation, and to view the model's image which is created as a copy of model's image

- interface ImageProcessor:
  Represents an Image Processor which is the core model of this program which handles the logic of changing image states as well as image attributes (eg. name, height, width). The operations methods are added to this interface as a design choice that assumes that all image operations in Assignment 4 are basic enough and therefore need to be supported for all image processor model implementaions. As a slight safeguard to this assumption, methods to add or remove supported operations for a model is introduced but not implemented fully.

- class ImageProcessorModel:
  Represents one implementation of ImageProcessor for Assignment 4 with the ability to override images which is represented as a 2D Pixel array and modifies a new 2D array, but does not necessarily override the new array into this model. One could more aptly describe this model as a "focus model" which only stores one 2D array of Pixels as the reference or template image which only changes when an override occurs. The temporary images created by the operation methods (e.g. brighten) are returned back to the controller to be stored in its library of images.

- class Pixel:
  Represents an abstraction of a pixel that can be either black/white or RGB which
  both have locations in the image. Error free since Pixels aren't created as a standalone and are only done so as part of the model's image 2D array.

- class RGBPixel:
  Represents a RGB pixel that has a red, green, and blue value. There are getters and setters in this method to obtain each individual value and luma, intensity and brightness. We allowed mutation because we saw it as a viable design choice due to how decoupled each part of our program is.

- class BWPixel:
  Represents a black/white pixel with limited method options. Some methods that
  are inherited throw exceptions due to lack of support but we went with the design choice of inherting these methods in case of future need of using the black/white pixel.

- interface Operations:
  Represents an operations function object which is mapped upon by String inputs to indicate commmands in this program. These operations follow the command design and are put in a hashmap and have fields designed to take the parameters needed to carry out transformations in the model.

- class AbstractOperations:
  Represents an abstraction of all the Operations function objects that we have so far in Assignment4

- class Brighten:
  Represents a function object that applies the function brighten on the model's image

- class VerticalFlip:
  Represents a function object that applies the function vertical-flip on the model's image

- class ValueComponent:
  Represents a function object that applies the function value-component on the model's image

- class Save:
  Represents a function object that applies the function save on the model's image

- class Load:
  Represents a function object that applies the function load on the model's image

- class HorizontalFlip:
  Represents a function object that applies the function horizontal-flip on the model's image

- class util:
  Represents a general utility class that checks for illogical states and arguments in our code and throws exceptions

- class ImageUtils:
  Represents a utility class that focuses on loading in a PPM to a 2D array of Pixels which is how this program perceives images

- class ImageProcessorControllerImplTest:
  Tests the methods of the ImageProcessorControllerImpl class

- class BWPixelTest:
  Tests the methods of the BWPixel class

- class RGBPixelTest:
  Tests the methods of the RGBPixel class

- class ImageProcessorModelTest:
  Tests the methods of the ImageProcessorModel class

- class LoadTest:
  Tests the methods of the Load class

- class ValueComponentTest:
  Tests the methods of the ValueComponent class

- class SaveTest:
  Tests the methods of the Save class

- class BrightenTest:
  Tests the methods of the Brighten class

- class HorizontalFlipTest:
  Tests the methods of the HorizontalFlip class

- class VerticalFlipTest:
  Tests the methods of the VerticalFlip class

- class UtilsTest:
  Tests the methods of the util class

- class ImageUtilsTest:
  Tests the methods of the ImageUtils class

- class ImageProcessorTextViewTest:
  Tests the methods of the ImageProcessorTextView class
