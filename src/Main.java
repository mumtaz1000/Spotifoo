package Spotifoo.src;

import java.awt.*;
import java.io.File;
import java.util.Scanner;

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
    public void playMusic(String filename){
        try{
            Desktop d = Desktop.getDesktop();
            d.open(new File(filename));
        }
        catch(Exception e){
            System.out.println("Cannot play mp3 file.");
        }


}

}
