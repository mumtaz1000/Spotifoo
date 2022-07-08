package novarepotential.src;

import java.util.ArrayList;
import java.util.Scanner;

public class Menus {
    public void mainMenuDisplayOption(){
        System.out.println("Main menu options:");
        System.out.println("[1] Songs");
        System.out.println("[2] Artists");
        System.out.println("[3] Albums");
        System.out.println("[4] Genres");
        System.out.println("[5] Search");
        System.out.println("Choose an option and press enter: ");
    }
    public String songsMenuDisplayOption(){
        Scanner menuOption = new Scanner(System.in);
        System.out.println("Songs display menu");
        ArrayList<String> songs = new ArrayList<>();
        String choosenSong = new String();
        int num = 0;
        FileSystem fileObj = new FileSystem();
        songs = fileObj.readFile("C:\\Users\\mumta\\IdeaProjects\\java-8\\src\\novarepotential\\assets\\assets\\data.txt");
        for(String singleSong: songs){
            num +=1;
            System.out.println("["+num+"] "+singleSong);
        }
        System.out.println("Enter the song number which you want to play.");
        //Taking input from user
        try{
            int opt = menuOption.nextInt();
            opt -=1;
            choosenSong = songs.get(opt);
            System.out.println("You choose song "+choosenSong);

        } catch (Exception e){
            System.out.println("Please enter the valid input");
        }
        return choosenSong.toLowerCase().replace(' ','-');
    }



    public void mainMenu(int input){
        System.out.println("Running menu "+input);
        switch(input){
            case 1:
                String songName = songsMenuDisplayOption();
                Main mainObj = new Main();
                String filename = "C:\\Users\\mumta\\IdeaProjects\\java-8\\src\\novarepotential\\assets\\assets\\songs\\";
                System.out.println("Song to play from menu "+filename.concat(songName).concat(".mp3"));
                mainObj.playMusic(filename.concat(songName).concat(".mp3"));

                break;
            case 2:
                System.out.println("Artists");
                break;
            case 3:
                System.out.println("Albums");
                break;
            case 4:
                System.out.println("Genres");
                break;
            case 5:
                System.out.println("Search");
                break;
            default:
                System.out.println("Please enter the number between 0 to 5");


        }
    }
    public void songMenu(){

    }
}
