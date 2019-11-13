plugins {
  java
  // Gradle plugin for handling jfx.
  id("application")
  id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "com.example.dogedice.dogedice"
version = "1.0-SNAPSHOT"

application {
  applicationName = "Doge Dice"
  mainClassName = "com.example.dogedice.Launcher"
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
  testCompile("org.testfx:testfx-junit5:4.0.16-alpha")
  compile("org.slf4j:slf4j-simple:1.7.21")
}

configure<JavaPluginConvention> {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

javafx {
  version = "11"
  modules("javafx.controls", "javafx.fxml", "javafx.media")
}

tasks.create<Test>("testDie") {
  include("**/*DieTest.class")
  useJUnitPlatform()
}

tasks.create<Test>("testPlayer") {
  include("**/*PlayerTest.class")
  useJUnitPlatform()
}

tasks.create<Test>("testMainWindow") {
  include("**/*MainWindowTest.class")
  useJUnitPlatform()
}

tasks.test {
  testLogging.showStandardStreams = true
  useJUnitPlatform()
}
