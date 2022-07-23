//package Spotifoo.src;
import java.nio.file.Paths;

public class Menus {

    public static String filePath (String filename){
        return String.valueOf(Paths.get("assets/", filename));
    }
    public static final String txtFilePath = filePath("data.txt");
    public static final String songFilePath = filePath("songs").concat("\\");
    public static final String songImagePath = filePath("albums").concat("\\");

    public static final String noPicturePath = filePath("no-picture.png");
    public void mainMenu(int input){
        switch(input) {
            case 0:
                break;
            case 1:
                Songs.songMenuOption();
                break;
            case 2:
                Display.displayIndividualArtistOrAlbumOrGenreName(1, "artist");
                break;
            case 3:
                Display.displayIndividualArtistOrAlbumOrGenreName(2, "album");
                break;
            case 4:
                Display.displayIndividualArtistOrAlbumOrGenreName(3, "genre");
                break;
            case 5:
                User.userSearchSongInput();
                break;
            default:
                System.out.println("Please enter the number between 1 to 5");
                break;
        }
    }

}
