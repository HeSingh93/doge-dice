
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
  testImplementation("org.mockito:mockito-core:2.+")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
  implementation("org.slf4j:slf4j-simple:1.7.21")
  implementation("com.google.code.gson:gson:2.8.6")
}

sourceSets {
  main {
    resources {
      srcDirs(listOf("resources/sounds"))
    }
  }
}

configure<JavaPluginConvention> {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

javafx {
  version = "11"
  modules("javafx.controls", "javafx.fxml")
}

tasks.create<Test>("testModel") {
  testLogging.showStandardStreams = true
  include("**/model/*.class")
  useJUnitPlatform()
}

tasks.create<Test>("testControllers") {
  testLogging.showStandardStreams = true
  include("**/controllers/*.class")
  useJUnitPlatform()
}

tasks.test {
  testLogging.showStandardStreams = true
  useJUnitPlatform()
}

tasks.create<Jar>("fatJar") {
  archiveClassifier.set("all")
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE
  manifest {
    attributes["Implementation-Title"] = "a"
    attributes["Implementation-Version"] = "1.0"
    attributes["Main-Class"] = "com.example.dogedice.Launcher"
    attributes["Class-Path"] = "dogedice-1.0-SNAPSHOT-all.jar"
  }
  from(configurations.runtimeClasspath.get()
      .map { if (it.isDirectory) it else zipTree(it) })
  val sourcesMain = sourceSets.main.get()
  from(sourcesMain.output)
}

