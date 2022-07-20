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
        List<String> inputList = new ArrayList<>();
        String song, songFile, songImg;
        List<String> songToPlay = new ArrayList<>();
        boolean correctSong = false;
        int songNum = 0;
        if (userInput.length() != 0){
            try{
                if(userInput.equals("0")){
                    mainMenuSection();
                } else {

                    System.out.println("You entered "+userInput);

                    for (String name: songObj.songsNames) {
                        if(!KMPSearch(name.replaceAll(" ",""), userInput).isEmpty()){
                            searchedSong.add(name);
                        }
                    }
                    for (String songName: searchedSong) {
                        songNum++;
                        System.out.println("["+songNum+"]"+" "+songName);
                        inputList.add(songName);
                    }
                    if(inputList.size() > 1)
                    {
                        System.out.println("Choose the song to play ");

                        //Taking input from user
                       while( !correctSong ) {
                            try {
                                int opt = validateUserInput(0, inputList.size());
                                if (opt == 0) {
                                    mainMenuSection();
                                } else {
                                    opt -= 1;
                                    System.out.println("You choose song " + inputList.get(opt));
                                    for (String songName : songObj.songsAll) {
                                        if(songName.contains(inputList.get(opt))){
                                            song = String.valueOf(songDetails(Collections.singletonList(songName),0));
                                            System.out.println(song);
                                            System.out.println("Song info "+songName);
                                            songToPlay.add(song);
                                            songFile = String.valueOf(songDetails(Collections.singletonList(songName),4));
                                            songToPlay.add(songFile);
                                            songImg = String.valueOf(songDetails(Collections.singletonList(songName), 5));
                                            songToPlay.add(songImg);
                                        }
                                    }
                                    correctSong = true;}
                            } catch (Exception e) {
                                System.out.println(displayWarningMsg("Please enter the valid input to play the song "));
                            }
                        }
                        playMusic(songToPlay);
                    }
                    else {
                        System.out.println("Nothing to choose");
                    }
                }
            }
            catch(Exception e){
                System.out.println(displayWarningMsg("Please enter the valid input"));
            }
        }

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
