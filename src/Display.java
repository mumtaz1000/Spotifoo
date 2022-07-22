package Spotifoo.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Spotifoo.src.Songs.*;
import static Spotifoo.src.User.takingArtistOrAlbumNameOrGenreAsUserInput;

public class Display {
    protected static void mainMenuDisplayOption(){
        //This function displays main menu to user to choose an option
        System.out.println("Main menu options:");
        System.out.println("[1] Songs");
        System.out.println("[2] Artists");
        System.out.println("[3] Albums");
        System.out.println("[4] Genres");
        System.out.println("[5] Search");
        //System.out.println("Press [0] to exit");
        System.out.println("Choose an option and press enter: ");
    }
    protected static List<String> songsMenuDisplayOption(){
        //This function displays a list containing only song names to user
        //that are stored in a list and then get required song mp3 file
        //and png file to play mp3 file and show png file
        System.out.println("Songs display menu");
        Songs songObj = new Songs();
        int num = 0;
        for(String singleSong: songObj.songsNames){
            num +=1;
            System.out.println("["+num+"] "+singleSong);
        }
        System.out.println("Enter the song number which you want to play.");
        System.out.println("Press [0] to exit");
        return getSongToPlay(songObj.songsNames);
    }
    protected static List<String> displayArtistsOrAlbumsOrGenre(List<String> displayName){
        //This function is used to create a list of either all the artists name
        // or albums name or genres and sorting it in ascending alphabetical order
        //without repeating any name
        Collections.sort(displayName);
        List<String> requiredList = new ArrayList<>();
        int num = 0;
        for (String name: displayName) {
            if(!requiredList.contains(name)){
                requiredList.add(name);
            }
        }
        for (String name: requiredList) {
            num +=1;
            System.out.println("["+num+"]"+" "+name);
        }
        System.out.println("Press 0 to go back to main menu.");
        return requiredList;
    }
    protected static void displayIndividualArtistOrAlbumOrGenreName(int option, String displayOption){
        //This function displays all the artists name or albums names or genre
        Songs songObj = new Songs();
        List<String> requiredList;
        requiredList = songDetails(songObj.songsAll,option);
        Collections.sort(requiredList);
        List<String> genreList;
        genreList = displayArtistsOrAlbumsOrGenre(requiredList);
        System.out.println("Choose the "+displayOption+" ");
        //Taking input from user
        takingArtistOrAlbumNameOrGenreAsUserInput(genreList);
    }
    protected static String displayWarningMsg(String inputMsg){
        //This function is used to display warning message in case
        // of incorrect input
        String warningTextColor = "\u001B[41m";
        String ANSI_RESET = "\u001B[0m";
        return (warningTextColor+inputMsg+ANSI_RESET) ;
    }
}
