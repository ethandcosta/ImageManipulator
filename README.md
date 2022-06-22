# Assignment6-OOD

## _The image sunset.ppm and dragon.jpg and all its derivatives used in this assignment are artistic properties under David Zhang._

## _David Zhang gives access for these images to be used in this assignment and future iterations of this assignment._

# The full program is complete and every feature should be able to work as intended including all operations from previous assignments.

## Changes made from Assignment4 to Assignment5:

- Added a new interface for filterable operations which extends Operations for future-proofing later iterations of possbly additional filterable operations and conditions
- Added a new abstract class for implementations of this filterable operations interface
  due to the fact that all the filterable operations were similar
- Added new lambda functions in our command design Operations map which can now be sensitive to reading the new commands
- Added access modifiers to the controller's fields
- Added support for applying sepia, blur, sharpen and greyscale filters/operations
- Removed most of the save function logic from the model to the ImageUtils class to remove doing I/O operations directly in the model
- Refactored some of the tests to reflect this change save implementation
- Updated CLI scripts for the sunset image to save as new image formats
- Added a new dragon image for testing
- Added new methods in the ImageUtils class for parsing a non-PPM image into a Pixel 2D array and turning a Pixel 2D array back into the specified image format
- Added tests for all new implementations

### Provide the following below as arguments in the program arguments in IntelliJ (CLI):

**Note: IT'S VERY IMPORTANT THAT THE QUOTATION MARKS REMAIN WHEN PASSING IN THE ARGUMENTS!**
**Note: IT'S ALSO VERY IMPORTANT THAT WHEN RUNNING THE PROGRAM WITHOUT CLI ARGUMENTS AND TYPING IN THE CONSOLE, THAT THE SAME SYNTAX FOR THE COMMANDS BELOW ARE FOLLOWED _EXCEPT_ THAT THE QUOTES ARE REMOVED**
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
"load res/sunset.ppm sunset"
"save res/sunset.bmp sunset"
"save res/sunset.jpg sunset"
"save res/sunset.png sunset"
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

### New support for text file parsing in Assignment5 (pass this line below as a command line argument in IntelliJ)

#### Note: This syntax cannot be passed for no-args command-lines (default system console/terminal) so do not pass it as a argument when running the JAR executable.

**The same syntax rules follow from the CLI arguments above and make sure script.txt stays in res/ directory**
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
"-file res/script.txt"
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

## Contents of the Text File res/script.txt

**Note: IT'S VERY IMPORTANT THAT THERE ARE _NO_ QUOTATION MARKS FOR EACH OF THE LINES OF COMMANDS IF PASTING TO A _TEXT FILE_!**
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
load res/dragon.jpg dragon
save res/dragon-immeadiate.ppm dragon
sepia dragon dragon-sepia
blur dragon dragon-blur
blur dragon-blur dragon-blur-twice
horizontal-flip dragon dragon-horizontal
vertical-flip dragon dragon-vertical
luma-component dragon dragon-luma-filter
red-component dragon dragon-red-filter
green-component dragon dragon-green-filter
blue-component dragon dragon-blue-filter
value-component dragon dragon-value-comp
brighten dragon dragon-brighten 15
brighten dragon dragon-darken -15
sharpen dragon dragon-sharpen
save res/dragon.ppm dragon
save res/dragon-copy.jpg dragon
save res/dragon-sharpen.jpg dragon-sharpen
save res/dragon-blur-twice.jpg dragon-blur-twice
save res/dragon-blur.jpg dragon-blur
save res/dragon-sepia.png dragon-sepia
save res/dragon-horizontal.jpg dragon-horizontal
save res/dragon-brighten.png dragon-brighten
save res/dragon-darken.png dragon-darken
save res/dragon-vertical.png dragon-vertical
save res/dragon-green.bmp dragon-green-filter
save res/dragon-red.bmp dragon-red-filter
save res/dragon-blue.bmp dragon-blue-filter
save res/dragon-luma.bmp dragon-luma-filter
save res/dragon-value.bmp dragon-value-comp
save res/dragon-dummy.png dragon
sepia dragon dragon-combo
horizontal-flip dragon-combo dragon-combo
save res/dragon-dummy.png dragon-combo
save res/dragon.jpg dragon
save res/dragon.png dragon
save res/dragon.bmp dragon
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

- interface FilterableOperations:
  Represents an operations function object which can be described as applying some kind of matrix onto the image and doing some math to find the new mapped values of r, g, b for the pixels. Left blank for this current iteration but anticipated for converting or adding new
  filterable operations which may have some additional feature vs regular Operations

- class AbstractOperations:
  Represents an abstraction of all the Operations function objects that we have so far in Assignment5

- class AbstractFilterableOperations
  Represents an abstraction of all the FilterableOperations function objects that we have so far in Assignment5

- class Brighten:
  Represents a function object that applies the function brighten on the model's image

- class VerticalFlip:
  Represents a function object that applies the function vertical-flip on the model's image

- class ValueComponent:
  Represents a function object that applies the function value-component on the model's image

- class Save:
  Represents a function object that applies the function save on the model's image (this is done in the function object itself)

- class Load:
  Represents a function object that applies the function load on the model's image

- class HorizontalFlip:
  Represents a function object that applies the function horizontal-flip on the model's image

- class Blur:
  Represents a function object that applies the function blur on the model's image

- class Sharpen:
  Represents a function object that applies the function sharpen on the model's image

- class Sepia:
  Represents a function object that applies the function sepia on the model's image

- class Greyscale:
  Represents a function object that applies the function greyscale on the model's image

- class util:
  Represents a general utility class that checks for illogical states and arguments in our code and throws exceptions

- class ImageUtils:
  Represents a utility class that focuses on loading in a PPM to a 2D array of Pixels which is how this program perceives images as well as loading a BufferedImage

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

- class BlurTest:
  Tests the methods of the Blur class

- class SharpenTest:
  Tests the methods of the Sharpen class

- class SepiaTest:
  Tests the methods of the Sepia class

- class GreyscaleTest:
  Tests the methods of the Greyscale class, tests are the same as luma-component

- class UtilsTest:
  Tests the methods of the util class

- class ImageUtilsTest:
  Tests the methods of the ImageUtils class

- class ImageProcessorTextViewTest:
  Tests the methods of the ImageProcessorTextView class
