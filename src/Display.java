package Spotifoo.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Spotifoo.src.Songs.*;

public class Display {
    protected static void mainMenuDisplayOption(){
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
        System.out.println("Songs display menu");
        Songs songObj = new Songs();
        displayIndividualSongElement(songObj.songsNames);
        System.out.println("Enter the song number which you want to play.");
        System.out.println("Press [0] to exit");
        List<String> songFile = new ArrayList<>();
        songFile = songDetails(songObj.songsAll,4);
        List<String> songImg = new ArrayList<>();
        songImg = songDetails(songObj.songsAll, 5);
        return getSongToPlay(songObj.songsNames,songFile,songImg);
    }
    protected static void displayIndividualSongElement(List<String> list){
        int num = 0;
        for(String singleSong: list){
            num +=1;
            System.out.println("["+num+"] "+singleSong);
        }
    }
    protected static void displayIndividualArtistOrAlbumOrGenreName(int option, String displayOption){
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
        String warningTextColor = "\u001B[41m";
        String ANSI_RESET = "\u001B[0m";
        return (warningTextColor+inputMsg+ANSI_RESET) ;
    }
}
