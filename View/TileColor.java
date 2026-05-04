package View;

import java.awt.*;

public enum TileColor {
    EMPTY(0,Color.DARK_GRAY),
    RED(1, Color.RED),
    GREEN(2,Color.green),
    BLUE(3,Color.blue);

    private final int id;
    private final Color color;

    TileColor(int id, Color color){
        this.id = id;
        this.color = color;
    }

    public static Color IDtoColor(int id) {
        for (TileColor t : values()) {
            if (t.id == id) return t.color;
        }
        return Color.DARK_GRAY;
    }
}
