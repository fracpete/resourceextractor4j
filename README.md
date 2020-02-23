# resourceextractor4j

Little Java library for making it easy to extract/read resources on the classpath. 


## Methods

### Extracting files

The `com.github.fracpete.resourceextractor4j.Files` class has the following
static methods:

* `copyResourceTo` - copies a single resource to an output directory
* `copyResourcesTo` - copies the resources provided as list to an output directory
  (when resources are in sub-directories, these will get recreated in the output 
  directory)


### Reading content

The `com.github.fracpete.resourceextractor4j.Content` class has the following
static methods:

* `readBytes` - reads the resources as byte array
* `readLines` - interprets the resource as text file and returns the lines  
* `readProperties` - populates a `java.util.Properties` object with the resource
* `readString` - reads the resource as single string (with or without `java.nio.charset.Charset`) 


## Maven

```xml
    <dependency>
      <groupId>com.github.fracpete</groupId>
      <artifactId>resourceextractor4j</artifactId>
      <version>0.0.1</version>
    </dependency>
```
