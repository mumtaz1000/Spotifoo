# Spotifoo

Project folder structure
==============================================================
Top-level directory layout
.
├── .idea                 
├── assets                  # Contains data.txt, song and album art folder
├── out                    
├── src                     # Contains source code
├── README.md  
└── Spotifoo.iml

Assets folder layout
.
├── assets                  # Contains data.txt, song and album art folder
│   ├── albums              # Contains all songs png files
│   ├── songs               # Contains all songs mp3 files
│   ├── data.txt            # Txt file contains all songs info
│   └── no-picture.png      # Png file
└── 

Src folder layout
.
├── src                     # Contains source code
│   ├── META-INF
│   ├── Display               
│   ├── FileSystem  
│   ├── Main            
│   ├── Menus  
│   ├── Songs
│   └── User
└──

Classes inside the src folder
==============================================================
* Display 
This class contains all code related to display including display main menu, 
songs menu, album, genre and artist menu to display warning message to warn user 
about invalid input.
* FileSystem
This class contains function related to reading data from txt file.
* Main (Main class to run the program)
This is the main class which is used to run the program.
* Menus
This class contains a function which is using a switch to display the main menu.
* Songs
As collections and ArrayLists are used to fetch and store all songs data 
* User

Browse Online
--------------

The main file is [StorageSample.java](https://github.com/GoogleCloudPlatform/java-docs-samples/blob/main/storage/xml-api/cmdline-sample/src/main/java/StorageSample.java).


Setup
-----

* [Create](https://cloud.google.com/storage/docs/cloud-console#_creatingbuckets) a Google Cloud Storage bucket
* This module uses [Application Default Credentials](https://developers.google.com/accounts/docs/application-default-credentials). If you are running it outside of [Google Compute Engine](https://cloud.google.com/compute/), you'll need to
    * Download the json private key for a [Service Account](https://cloud.google.com/storage/docs/authentication#service_accounts) and have it available.
    * Set an environment variable: `export GOOGLE_APPLICATION_CREDENTIALS=path/to/your-key.json`
* You must also be able to work with [GitHub](https://help.github.com/articles/set-up-git) repositories.
* Clone repository.

        git clone https://github.com/GoogleCloudPlatform/java-docs-samples.git


Command-line Instructions
-------------------------

* **Prerequisites:**
    * Install the latest version of [Java](https://java.com) and [Maven](https://maven.apache.org/download.html).
    * Set the environment variable: `export GOOGLE_APPLICATION_CREDENTIALS=your-key-filename.json`
    * You may need to set your `JAVA_HOME`.

```bash
cd java-docs-samples/storage/xml-api/cmdline-sample
# Compile and run
mvn compile install
mvn -q exec:java -Dexec.args="your-bucket-name"
```

To enable logging of HTTP requests and responses (highly recommended when
developing), please take a look at logging.properties.


Eclipse Instructions
--------------------

* **Prerequisites:**
    * Install [Eclipse](http://www.eclipse.org/downloads/), the [Maven plugin](http://eclipse.org/m2e/), and optionally the [GitHub plugin](http://eclipse.github.com/).

* Set up Eclipse Preferences

    * Window > Preferences... (or on Mac, Eclipse > Preferences...)
    * Select Maven

        * check on "Download Artifact Sources"
        * check on "Download Artifact JavaDoc"

* Create a new project using `storage/xml-api/cmdline-sample`

    * Create a new Java Project.
    * Choose the **Location** of the project to be the location of `cmdline-sample`
    * Select the project and **Convert to Maven Project** to add Maven Dependencies.
    * Click on Run > Run configurations
        * Navigate to your **Java Application**'s configuration section
        * In the **Arguments** tab, add the name of the bucket you created above as a **Program argument**
        * In the **Environment** tab, create a variable `GOOGLE_APPLICATION_CREDENTIALS` and set it to the path to your json private key file.

* Run

    * Right-click on project
    * Run As > Java Application
    * If asked, type "StorageSample" and click OK