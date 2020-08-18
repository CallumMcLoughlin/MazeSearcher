package MazeSearcher.gui;

import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test file for WindowHandler.java
 */

public class WindowHandlerTest {
    WindowHandler handler;

    @Before
    public void Init() {
        WindowHandler.ResetInstance();
        handler = new WindowHandler(null);
    }

    /**
     * Test singleton aspect of WindowHandler
     */
    @Test
    public void TestSingleton() {
        assertEquals(WindowHandler.GetInstance(), handler);
    }

    /**
     * Test trying to instantiate multiple singletons and
     * ensure the original is not overwritten.
     */
    @Test
    public void TestMultipleSingleton() {
        WindowHandler handlerTwo = new WindowHandler(null);
        WindowHandler handlerThree = new WindowHandler(null);

        assertEquals(WindowHandler.GetInstance(), handler);
    }

    /**
     * Test window dictionary addition
     */
    @Test
    public void TestHashMapAddition() {
        Pane dummyPane = new Pane();
        WindowHandler.GetInstance().AddWindow("Test", dummyPane);
        WindowHandler.GetInstance().AddWindow("Test2", dummyPane);

        assertEquals(2, WindowHandler.GetInstance().GetWindowDictionary().keySet().size());
    }

    /**
     * Test window dictionary removal
     */
    @Test
    public void TestHashMapRemoval() {
        Pane dummyPane = new Pane();
        WindowHandler.GetInstance().AddWindow("Test", dummyPane);
        WindowHandler.GetInstance().AddWindow("Test2", dummyPane);
        WindowHandler.GetInstance().AddWindow("Test3", dummyPane);

        WindowHandler.GetInstance().RemoveWindow("Test2");
        WindowHandler.GetInstance().RemoveWindow("Something Random");
        WindowHandler.GetInstance().RemoveWindow("Test");

        assertEquals(1, WindowHandler.GetInstance().GetWindowDictionary().keySet().size());
    }
}
