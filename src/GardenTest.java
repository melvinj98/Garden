import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GardenTest {
    @Test
    void createGarden(){
        //TODO set size of garden
        Garden garden = new Garden();
        int row = 3, column = 5;
        boolean gardenMade = Garden.createGarden(row, column);
        Assertions.assertEquals(true, gardenMade);
    }

    /*
    @Test
    void plantInGarden(){
        //TODO select/add a plant and pick where in garden to plant
        Garden garden = new Garden();
        String plant = "x";
        String[][] gardenArray = new String[5][5];
        String[][] planted = Garden.plantInGarden(gardenArray);
        Assertions.assertEquals(true, planted );
    }

     */

    @Test
    void removePlant(){
        //TODO remove plant
    }

    @Test
    void movePlant(){
        //TODO move plant to another location
    }

    @Test
    void listPlants(){
        //TODO print plants and their locations
    }

    @Test
    void saveGarden(){
        //TODO save garden
    }

    @Test
    void loadGarden(){
        //TODO load garden from file
    }
}
