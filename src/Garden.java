import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Garden {
    static Scanner keyboard = new Scanner(System.in);
    static int row = 5, column = 5;
    static String plant = "", fileName;

    public static void main(String[] args) {
        String[][] garden = new String[row][column];

        System.out.println("Would you like to create a new garden or continue a garden?");
        System.out.print("Enter 'New' to create a new garden or enter file name to continue garden: ");
        fileName = "" + keyboard.nextLine() + ".txt";
        if(fileName.toLowerCase().equals("new")){
            createGarden(fileName);
        }
        else {
            System.out.println("Using garden file: " + fileName);
        }

        readFromFile(garden, fileName);
        plantInGarden(garden);
        addToFile(garden, fileName);

        for (String[] strings : garden) {
            System.out.println(Arrays.toString(strings));
        }
    }

    //creates garden file
    public static boolean createGarden(String fileName) {
        try {
            File gardenFile = new File(fileName);
            if (gardenFile.createNewFile()) {
                System.out.println("Created " + fileName);
            } else {
                System.out.println("Garden already exists");
            }
        } catch (IOException e) {
            System.out.println("Error occurred");
        }

        return true;
    }

    //reads from selected file and adds to garden array
    public static void readFromFile(String[][] garden, String fileName){
        //todo: read from file to array
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
            while(sc.hasNextLine()){
                for(int i = 0; i < garden.length; i++){
                    String[] line = sc.nextLine().trim().split(",");
                    for(int j = 0; j < line.length; j++){
                        garden[i][j] = line[j];
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //adds plant to garden array
    public static String[][] plantInGarden(String[][] garden) {
        plantList();

        System.out.println("Where would you like to plant the flower?");
        int x = keyboard.nextInt();
        int y = keyboard.nextInt();

        garden[x][y] = "[" + plant + "]";

        return garden;
    }

    //adds garden array to file
    public static void addToFile(String[][] garden, String fileName){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

            for (String[] strings : garden) {
                for (int j = 0; j < strings.length; j++) {
                    bw.write(strings[j] + ((j == strings.length - 1) ? "" : ","));
                }
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
        }
    }

    public static String plantList() {
        System.out.println("Plant List");
        System.out.println("African Lily: A");
        System.out.println("Azalea: B");
        System.out.println("Bluebell: C");
        System.out.println("Chrysanthemum: D");
        System.out.println("Daffodil: E");
        System.out.println("Hyacinths: F");
        System.out.println("Tulip: G");
        System.out.println("Hydrangea: H");
        System.out.println("Orchids: I");
        System.out.println("Lavender: J");
        System.out.print("Select Plant: ");
        String input = keyboard.nextLine();

        switch (input.toLowerCase()) {
            case "a" -> plant = "A";
            case "b" -> plant = "B";
            case "c" -> plant = "C";
        }

        return plant;
    }
}
