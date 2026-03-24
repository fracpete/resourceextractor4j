How to make a release
=====================

* Switch to Java 11

* Run the following command to deploy the artifact:

  ```
  mvn release:clean release:prepare release:perform
  ```

* Push all changes

* Go to Maven Central and publish artifacts

  https://central.sonatype.com/publishing/deployments
