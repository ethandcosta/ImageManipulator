# Supported Operations:

- blue-component
- blur
- brighten
- green-component
- greyscale
- horizontal-flip
- intensity-component
- load
- luma-component
- red-component
- save
- sepia
- sharpen
- value-component
- vertical-flip

# Program Arguments in IntelliJ (denoted as CLI) vs Console/Terminal vs Text File Scripts

## Passing these commands in the console/terminal manually require no quotation marks. (occurs when no arguments are passed in IntelliJ or if JAR file is executed from terminal)

## Writing commands in a text file manually should have no quotation marks.

## The program **DOES NOT** support passing in a script file from the console and must be made a CLI argument in IntelliJ. Therefore the argument takes the form "-file _path-of-file_".

## For program arguments in IntelliJ, passing in commands require quotation marks around each operation, not just the operation-name.

# Syntax and Examples

## For all operations _except_ brighten, the syntax of passing these commands is identical to those laid out in the assignments on Canvas. They are of the form: operation-name source-name destination-name.

## For **brighten** command, the syntax is of the form: brighten source-name destination-name brighten-value.

### Note: source-name must be a previously entered destination-name or else an error will be thrown.

#### Each example will have its console and CLI argument equivalent. If loading to a text file is desired, copy the console version on with each command on new lines.

CLI: "vertical-flip dog dog-flip" // Console: vertical-flip dog dog-flip
CLI: "value-component dog dog-value" // Console: value-component dog dog-value
CLI: "sharpen dog dog-sharpen" // Console: sharpen dog dog-sharpen
CLI: "sepia dog dog-sepia" // Console: sepia dog dog-sepia
CLI: "save res/dog.ppm dog" // Console: save res/dog.ppm dog
CLI: "brighten dog dog-brighten 10" // Console: brighten dog dog-brighten 10
CLI: "load res/dog.png dog" // Console: load res/dog.png dog
CLI: "-file res/dog.txt" // Console: not supported, must be passed in as a IntelliJ CLI arguments

# Conditions

Since a source name must be present before any operations can be done, the first command must be a load. Note that for load, save, and other potential I/O operations, the source-name syntax is actually the path and the destination-name is the name of the picture version.
