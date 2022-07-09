package spotifoo.src;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
       mainMenuSection();

    }
    public static void mainMenuSection(){
        /*Main menu section starts here*/
        Menus mainMenuObj = new Menus();
        Scanner menuOption = new Scanner(System.in);

        //This function call is made to display main menu to user
        mainMenuObj.mainMenuDisplayOption();

        //Taking input from user
        try{
            int opt = menuOption.nextInt();
            System.out.println(opt);
            mainMenuObj.mainMenu(opt);
        } catch (Exception e){
            System.out.println("Please enter the valid input");
        }
        /*Main menu section ends here*/
    }
    public void playMusic(String filename) throws IOException {
        //MP3Player mp3Player = new MP3Player(filename);
        Desktop d = Desktop.getDesktop();
        d.open(new File(filename));
        /*mp3Player.play();
        Scanner sc = new Scanner(System.in);

        System.out.println("Write stop to stop the music: ");

        if (sc.nextLine().equalsIgnoreCase("stop")) {
            mp3Player.close();
        }*/
}

}
