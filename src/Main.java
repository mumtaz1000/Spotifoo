package novarepotential.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Objects created to access different classes and their methods
        FileSystem fileObj = new FileSystem();

        fileObj.readFile("C:\\Users\\mumta\\IdeaProjects\\java-8\\src\\novarepotential\\assets\\assets\\data.txt");

        //String filename = "C:\\Users\\mumta\\IdeaProjects\\java-8\\src\\com\\spotifoo\\app\\assets\\assets\\songs\\welcome-to-new-york";
        //mainMenuSection();

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
