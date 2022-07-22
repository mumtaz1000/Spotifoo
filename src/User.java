package Spotifoo.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static Spotifoo.src.Display.displayWarningMsg;
import static Spotifoo.src.Main.mainMenuSection;
import static Spotifoo.src.Songs.*;

public class User {
    protected static int validateUserInput(int minUserInput,int maxUserInput){
        //This function is used to check that user enter the number
        // between the range for example in the main menu user must enter
        // between 0 to 5 otherwise it will give an error
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

    protected static void takingArtistOrAlbumNameOrGenreAsUserInput(List<String> inputList){
        Songs songObj = new Songs();
        List<String> requiredOutputList = new ArrayList<>();
        boolean correct = false;
        while( !correct ) {
            try {
                int opt = validateUserInput(0, inputList.size());
                if (opt == 0) {
                    mainMenuSection();
                } else {
                    opt -= 1;
                    System.out.println("You choose " + inputList.get(opt));
                    int songNum = 0;
                    for (String songName : songObj.songsAll) {
                        if(songName.contains(inputList.get(opt))){
                            songNum += 1;
                            requiredOutputList.add(songName);
                            System.out.println("["+songNum+"] "+songDetails(Collections.singletonList(songName),0));
                            //System.out.println("Song info "+artistsSongsList.get(songNum-1));
                        }
                    }
                    System.out.println("Choose the song to play ");
                    playMusic(getSongToPlay(requiredOutputList));
                    correct = true;}
            } catch (Exception e) {
                System.out.println(displayWarningMsg("Please enter the valid input to play the song "));
            }
        }
    }

    protected static void userSearchSongInput(){
        //Taking a user input to search the song by applying
        //Knuth Morris Pratt Pattern Search
        Songs songObj = new Songs();
        Scanner searchSongInput = new Scanner(System.in);
        System.out.println("Enter the song to search ");
        String userInput = searchSongInput.nextLine();
        userInput = userInput.toLowerCase();
        userInput = userInput.replaceAll(" ","");
        List<String> searchedSong = new ArrayList<>();
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
                    System.out.println("Choose the song which you want to play.");
                    //System.out.println(searchedSong.size());
                    if(searchedSong.size() != 0){
                        if(searchedSong.size()>1){
                            playMusic(choosingSongFromSearchResul(searchedSong));
                        }
                        else {
                            String song = searchRequiredSongFromAllSongsList(searchedSong, 0);
                            System.out.println("Single song output "+songDetails(song));
                            playMusic(songDetails(song));
                        }
                    }
                    else {
                        System.out.println("Search result not found!!!");


                    }
                }
            }
            catch(Exception e){
                System.out.println(displayWarningMsg("Please enter the valid input"));
            }
        }

    }
    private static List<String> choosingSongFromSearchResul(List<String> searchedSong){
        String songOutputResult = null;
        boolean correct = false;
        while( !correct ) {
            try {
                int opt = validateUserInput(0, searchedSong.size());
                if (opt == 0) {
                    mainMenuSection();
                } else {
                    opt -= 1;
                    System.out.println("You choose " + searchedSong.get(opt));
                    songOutputResult = searchRequiredSongFromAllSongsList(searchedSong,opt);
                    correct = true;}
            } catch (Exception e) {
                System.out.println(displayWarningMsg("Please enter the valid input to play the song "));
            }
        }
        return songDetails(songOutputResult);
    }
    private static String trimLowerCaseString(String inputString){
        return inputString.toLowerCase().replaceAll(" ","");
    }
    private static String searchRequiredSongFromAllSongsList(List<String> searchedSong, int opt){
        Songs songObj = new Songs();
        String songOutputResult = null;
        for(String searchSong : songObj.songsAll){
            if(searchSong.contains((searchedSong.get(opt)))){
                songOutputResult = searchSong;
                System.out.println("Search found!! "+searchSong);
                System.out.println("Checking output "+songDetails(searchSong));
            }
        }
        return songOutputResult;
    }
   private static int[] patternArray(String searchString){
        //Creating a pattern from user search input
        //in order to search the required song
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
        return compiledPatternArray;
   }
   private static List<Integer> KMPSearch(String songInput, String searchInput){
       //Knuth Morris Pratt Pattern Search implementation
        String song = songInput.toLowerCase();
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
