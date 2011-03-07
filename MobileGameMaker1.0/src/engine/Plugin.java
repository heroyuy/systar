package engine;

import engine.MapEditor;
import engine.MapEditor;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public abstract class Plugin {

    private MapEditor mapEditor = null;

    public Plugin(MapEditor mapEditor) {
        this.mapEditor = mapEditor;
    }

    public abstract JButton getButton();

    public MapEditor getMapEditor() {
        return mapEditor;
    }

    public abstract JMenu getMenu();

    public abstract JMenuItem getMenuItem();
    public abstract JMenu getPopMenu();
}
