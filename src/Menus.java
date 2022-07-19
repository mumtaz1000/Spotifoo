package Spotifoo.src;

import java.nio.file.Paths;

import static Spotifoo.src.Display.displayIndividualArtistOrAlbumOrGenreName;
import static Spotifoo.src.Songs.songMenuOption;
import static Spotifoo.src.User.userSearchSongInput;

public class Menus {

    public static String filePath (String filename){
        return String.valueOf(Paths.get("src/Spotifoo/assets/assets/", filename));
    }
    public static final String txtFilePath = filePath("data.txt");
    public static final String songFilePath = filePath("songs").concat("\\");
    public static final String songImagePath = filePath("albums").concat("\\");


    public void mainMenu(int input){
        switch(input) {
            case 0:
                break;
            case 1:
                songMenuOption();
                break;
            case 2:
                displayIndividualArtistOrAlbumOrGenreName(1, "artist");
                break;
            case 3:
                displayIndividualArtistOrAlbumOrGenreName(2, "album");
                break;
            case 4:
                displayIndividualArtistOrAlbumOrGenreName(3, "genre");
                break;
            case 5:
                userSearchSongInput();
                break;
            default:
                System.out.println("Please enter the number between 1 to 5");
                break;
        }
    }

}
