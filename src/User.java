package Spotifoo.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Spotifoo.src.Display.displayWarningMsg;
import static Spotifoo.src.Main.mainMenuSection;
import static Spotifoo.src.Songs.*;

public class User {
    protected static int validateUserInput(int minUserInput,int maxUserInput){
        Scanner menuOption = new Scanner(System.in);
        boolean correctInput = false;
        int input = 0;
        while (!correctInput){
            input = menuOption.nextInt();
            if(input == 0){
                mainMenuSection();
            } else {
                if (input > minUserInput && input <= maxUserInput) {
                    System.out.println(input);
                    correctInput = true;
                } else {
                    System.out.println(displayWarningMsg("Please enter the number between " + minUserInput + " to " + maxUserInput));
                }
            }
        }
        return input;
    }
    protected static void userSearchSongInput(){
        Songs songObj = new Songs();
        Scanner searchSongInput = new Scanner(System.in);
        System.out.println("Enter the song to search ");
        String userInput = searchSongInput.nextLine();
        List<String> searchedSong = new ArrayList<>();
        List<String> singleSongList = new ArrayList<>();
        if (userInput.length() != 0){
            try{
                if(userInput.equals("0")){
                    mainMenuSection();
                } else {

                    System.out.println("You entered "+trimLowerCaseString(userInput));

                    for (String name: songObj.songsNames) {
                       // System.out.println("Song searching "+name);
                        if(!KMPSearch(trimLowerCaseString(name), trimLowerCaseString(userInput)).isEmpty()){
                            searchedSong.add(name);
                        }
                    }

                    displayArtistsOrAlbumsOrGenre(searchedSong);
                    boolean correct = false;
                    while( !correct ) {
                        try {
                            int opt = validateUserInput(0, searchedSong.size());
                            if (opt == 0) {
                                mainMenuSection();
                            } else {
                                opt -= 1;
                                singleSongList.add(searchedSong.get(opt));
                                System.out.println("You choose " + singleSongList.get(0));
                                System.out.println("List "+singleSongList);
                                /*List<String> songFile = songDetails(singleSongList,4);
                                System.out.println("Song file "+songFile);
                                List<String> songImg = songDetails(singleSongList, 5);
                                System.out.println("Song image "+songImg);
                                playMusic(getSongToPlay(singleSongList,songFile, songImg));*/
                                correct = true;}
                        } catch (Exception e) {
                            System.out.println(displayWarningMsg("Please enter the valid input to play the song "));
                        }
                    }

                }
            }
            catch(Exception e){
                System.out.println(displayWarningMsg("Please enter the valid input"));
            }
        }

    }
    private static String trimLowerCaseString(String inputString){
        return inputString.toLowerCase().replaceAll(" ","");
    }
   private static int[] patternArray(String searchString){
        int patternLength = searchString.length();
        int len = 0;
        int i = 1;
        int[] compiledPatternArray = new int[patternLength];
        compiledPatternArray[0] = 0;

        while(i < searchString.length()){
            if(searchString.charAt(i) == searchString.charAt(len)){
                len++;
                compiledPatternArray[i] = len;
                i++;
            } else {
                if(len != 0){
                    len = compiledPatternArray[len - 1];
                } else {
                    compiledPatternArray[i] = len;
                    i++;
                }
            }
        }
       //System.out.println("Compiled Pattern Array "+ Arrays.toString(compiledPatternArray));
        return compiledPatternArray;
   }
   private static List<Integer> KMPSearch(String song, String searchInput){
        int[] compiledPatternArray = patternArray(searchInput);
       int songIndex = 0;
        int searchInputIndex = 0;

       List<Integer> foundIndexes = new ArrayList<>();
       while(songIndex < song.length()){
           if(searchInput.charAt(searchInputIndex) == song.charAt(songIndex)) {
               searchInputIndex++;
               songIndex++;
           }
           if(searchInputIndex == searchInput.length()){
               foundIndexes.add(songIndex - searchInputIndex);
               searchInputIndex = compiledPatternArray[searchInputIndex - 1];
           }
           else if (songIndex < song.length() && searchInput.charAt(searchInputIndex) != song.charAt(songIndex)){
               if(searchInputIndex != 0){
                   searchInputIndex = compiledPatternArray[searchInputIndex - 1];
               } else {
                   songIndex = songIndex + 1;
               }
           }
       }
        return foundIndexes;
   }
}
