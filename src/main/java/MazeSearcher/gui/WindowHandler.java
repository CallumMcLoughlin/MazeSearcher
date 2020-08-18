package MazeSearcher.gui;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import java.util.HashMap;

/**
 * WindowHandler class to handle all windows for the GUI component of the program.
 */
public class WindowHandler
{
    //Static Instance object
    private static WindowHandler Instance;

    /**
     * Get singleton instance
     * @return Current WindowHandler object
     */
    public static WindowHandler GetInstance()
    {
        return Instance;
    }

    /**
     * Reset singleton class
     */
    public static void ResetInstance()
    {
        Instance = null;
    }

    //Parent pane, top holds menu bar, center is content
    private BorderPane parentPane;
    //List to hold all window GUI components
    private HashMap<String, Node> windowsDictionary;

    /**
     * Constructor to ensure only one object,
     * This ensures singleton pattern not violated.
     * Silently fail if multiple WindowHandlers instantiated.
     */
    public WindowHandler(BorderPane parent)
    {
        if (Instance != null)
        {
            return;
        }

        this.parentPane = parent;
        this.windowsDictionary = new HashMap<>();

        Instance = this;
    }

    /**
     * Get active window
     * @return Current window object
     */
    public Node GetActiveWindow()
    {
        return this.parentPane.getCenter();
    }

    /**
     * Set active window
     * @param name Name of window to show
     */
    public void SetActiveWindow(String name)
    {
        this.parentPane.setCenter(this.windowsDictionary.get(name));
    }

    /**
     * Get all windows
     * @return Hashmap of windows
     */
    public HashMap<String, Node> GetWindowDictionary()
    {
        return this.windowsDictionary;
    }

    /**
     * Add window to current window dictionary
     * @param name Window name
     * @param window Window to add
     */
    public void AddWindow(String name, Node window)
    {
        this.windowsDictionary.put(name, window);
    }

    /**
     * Remove window object from list
     * @param name Window to remove
     */
    public void RemoveWindow(String name)
    {
        this.windowsDictionary.remove(name);
    }
}
