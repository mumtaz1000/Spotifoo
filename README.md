# Spotifoo

Project folder structure
==============================================================
Top-level directory layout
* .idea                 
* assets                  # Contains data.txt, song and album art folder
    * albums              # Contains all songs png files
    * songs               # Contains all songs mp3 files
    * data.txt            # Txt file contains all songs info
    * no-picture.png      # Png file
* out                    
* src                     # Contains source code
    * META-INF
    * Display
    * FileSystem
    * Main            
    * Menus
    * Songs
    * User
* README.md
* Spotifoo.iml


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
As collections and ArrayLists are used to fetch and store all songs data in the 
form of arraylist to get the required input for example songs name only, to display
songs names to user, to fetch the mp3 file name or required png file name and other 
required functionality, those functionalities reside inside this class.
* User
Contain functions that required user input for example search a song etc.


Help taken from online
----------------------
KMP algorithm is used to search the required song containing the pattern 
entered by user. 

Reference
========
* KMP Algorithm for Pattern Searching
  https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/


