/**
 * Created by Владимир on 16.09.2016.
 */
public class Game {
    private static Game ourInstance = new Game();

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {
    }
}
