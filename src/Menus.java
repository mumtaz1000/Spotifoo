package Spotifoo.src;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static Spotifoo.src.Songs.*;

public class Menus {

    public static String filePath (String filename){
        return String.valueOf(Paths.get("src/Spotifoo/assets/assets/", filename));
    }
    public static final String txtFilePath = filePath("data.txt");
    public static final String songFilePath = filePath("songs").concat("\\");
    public static final String songImagePath = filePath("albums").concat("\\");
    public void mainMenuDisplayOption(){
        System.out.println("Main menu options:");
        System.out.println("[1] Songs");
        System.out.println("[2] Artists");
        System.out.println("[3] Albums");
        System.out.println("[4] Genres");
        System.out.println("[5] Search");
        System.out.println("Press [0] to exit");
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

    public void artistMenuDisplayOption(){
        System.out.println("This program is for displaying artist names");
        displayArtistName();
    }

    public void mainMenu(int input){
        switch(input) {
            case 0:
                break;
            case 1:
                songMenuOption();
                break;
            case 2:
                artistMenuDisplayOption();
                break;
            case 3:
                displayIndividualAlbumName();
                break;
            case 4:
                displayIndividualGenre();
                break;
            case 5:
                System.out.println("Search");
                break;
            default:
                System.out.println("Please enter the number between 0 to 5");
                break;
        }
    }

}
