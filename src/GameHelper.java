import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class GameHelper {
    private static final String alphabet = "abcdefg";
    private final int gridLenght = 7;
    private final int gridSize = 49;
    private final int[] grid = new int[gridSize];
    private int comCount = 0;

    public String getUserInput(String promt) {
        String inputLine = null;
        System.out.println(promt + " ");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.isEmpty()) {
                return null;
            }
        } catch (IOException e) {
            System.out.println("IOExpection: " + e);
        }
        return Objects.requireNonNull(inputLine).toLowerCase();
    }

    public ArrayList<String> plaseDotCom(int comSize) {
        ArrayList<String> alphaCells = new ArrayList<>();
        String[] alphacoords = new String[comSize];
        String temp = null;
        int[] coords = new int[comSize];
        int attempts = 0;
        boolean success = false;
        int location;

        comCount++;
        int incr = 1;
        if ((comCount % 2) == 1) {
            incr = gridLenght;
        }

        while (!success & attempts++ < 200) {
            location = (int) (Math.random() * gridSize);
            //System.out.println("try "+ location);
            int x = 0;
            success = true;
            while (success && x < comSize) {
                if (grid[location] == 0) {
                    coords[x++] = location;
                    location += incr;
                    if (location >= gridSize) {
                        success = false;
                    }
                    if (x > 0 && (location % gridLenght == 0)) {
                        success = false;
                    }
                } else {
                    System.out.println("used " + location);
                    success = false;
                }
            }
        }
        int x = 0;
        int row = 0;
        int column = 0;
        //System.out.println("\n");
        while (x < comSize) {
            grid[coords[x]] = 1;
            row = (int) (coords[x] / gridLenght);
            column = coords[x] % gridLenght;
            temp = String.valueOf(alphabet.charAt(column));
            alphaCells.add(temp.concat((Integer.toString(row))));
            x++;
            System.out.println(" coord " + x + " = " + alphaCells.get(x - 1));
        }
        System.out.println("\n");
        return alphaCells;
    }


}
