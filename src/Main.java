package novarepotential.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menus mainMenu = new Menus();
        Scanner menuOption = new Scanner(System.in);

        //This function call is made to display main menu to user
        mainMenu.mainMenuDisplayOption();

        //Taking input from user
        try{
            int opt = menuOption.nextInt();
            System.out.println(opt);
            mainMenu.mainMenu(opt);
        } catch (Exception e){
            System.out.println("Please enter the valid input");
        }


        String filename = "C:\\Users\\mumta\\IdeaProjects\\java-8\\src\\com\\spotifoo\\app\\assets\\assets\\songs\\welcome-to-new-york";

    }
    private void playMusic(String filename ){
        MP3Player mp3Player = new MP3Player(filename);
        mp3Player.play();
        Scanner sc = new Scanner(System.in);

        System.out.println("Write stop to stop the music: ");

        if (sc.nextLine().equalsIgnoreCase("stop")) {
            mp3Player.close();
        }
    }

}
