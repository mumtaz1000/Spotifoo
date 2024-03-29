//package Spotifoo.src;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    protected static int validateUserInput(int minUserInput,int maxUserInput){
        Scanner menuOption = new Scanner(System.in);
        boolean correctInput = false;
        int input = 0;
        while (!correctInput){
            input = menuOption.nextInt();
            if(input == 0){
                Main.mainMenuSection();
            } else {
                if (input > minUserInput && input <= maxUserInput) {
                    System.out.println(input);
                    correctInput = true;
                } else {
                    System.out.println(Display.displayWarningMsg("Please enter the number between " + minUserInput + " to " + maxUserInput));
                }
            }
        }
        return input;
    }
    protected static void userSearchSongInput() {
        Songs songObj = new Songs();
        Scanner searchSongInput = new Scanner(System.in);
        System.out.println("Enter the song to search or 0 to go back to main menu");
        String userInput = searchSongInput.nextLine();
        List<String> searchedSong = new ArrayList<>();
        boolean correctInput = false;
        while (correctInput != true) {
        if (userInput.length() != 0) {
            try {
                if (userInput.equals("0")) {
                    Main.mainMenuSection();
                } else {
                    System.out.println("You entered " + trimLowerCaseString(userInput));
                    for (String name : songObj.songsNames) {
                        // System.out.println("Song searching "+name);
                        if (!KMPSearch(trimLowerCaseString(name), trimLowerCaseString(userInput)).isEmpty()) {
                            searchedSong.add(name);
                        }
                    }
                    if (searchedSong.size() != 0) {
                        Songs.displayArtistsOrAlbumsOrGenre(searchedSong);
                        System.out.println("Choose the song which you want to play.");
                        //System.out.println(searchedSong.size());
                        if (searchedSong.size() > 1) {
                            Songs.playMusic(choosingSongFromSearchResult(searchedSong));
                        } else {
                            String song = searchRequiredSongFromAllSongsList(searchedSong, 0);
                            System.out.println("Single song output " + Songs.songDetails(song));
                            Songs.playMusic(Songs.songDetails(song));
                        }
                    } else {
                        System.out.println("Search result not found!!!");
                    }
                    correctInput = true;
                }
            } catch (Exception e) {
                System.out.println(Display.displayWarningMsg("Please enter the valid input while searching a song"));
                userSearchSongInput();
            }
        }
    }
    }
    private static List<String> choosingSongFromSearchResult(List<String> searchedSong){
        String songOutputResult = null;
        boolean correct = false;
        while( !correct ) {
            try {
                int opt = validateUserInput(0, searchedSong.size());
                if (opt == 0) {
                    Main.mainMenuSection();
                } else {
                    opt -= 1;
                    System.out.println("You choose " + searchedSong.get(opt));
                    songOutputResult = searchRequiredSongFromAllSongsList(searchedSong,opt);
                    correct = true;}
            } catch (Exception e) {
                System.out.println(Display.displayWarningMsg("Please enter the valid input to play the song "));
            }
        }
        return Songs.songDetails(songOutputResult);
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
                System.out.println("Checking output "+ Songs.songDetails(searchSong));
            }
        }
        return songOutputResult;
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
