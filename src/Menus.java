package novarepotential.src;

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
    public void songsMenuDisplayOption(){
        System.out.println("Songs display menu \n");
        FileSystem fileObj = new FileSystem();
        fileObj.readFile("C:\\Users\\mumta\\IdeaProjects\\java-8\\src\\novarepotential\\assets\\assets\\data.txt");

    }



    public void mainMenu(int input){
        System.out.println("Running menu "+input);
        switch(input){
            case 1:
                System.out.println("Songs");
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
