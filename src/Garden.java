import java.io.File;
import java.io.IOException;

public class Garden {
    static int length = 15, width = 15;
    static String[][] garden = new String[length][width];

    public static void main(String[] args){
        try {
            File gardenFile = new File("gardenFile.txt");
            if(gardenFile.createNewFile()){
                System.out.println("Created new file");
            }
            else {
                System.out.println("File already exists");
            }
            }
        catch(IOException e){
            System.out.println("Error occurred");
        }
    }

    public static boolean createGarden(int length, int width) {
        return true;
    }

    public static boolean plantInGarden(String x) {
        return true;
    }
}
