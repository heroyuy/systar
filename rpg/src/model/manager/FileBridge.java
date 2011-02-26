package model.manager;

import java.io.DataInputStream;
import java.io.InputStream;

/**
 *
 * 文件管理器
 */
public class FileBridge {

    protected static final byte FILE_TYPE_SYSTEM = 0;
    protected static final byte FILE_TYPE_ANIMATION = 1;
    protected static final byte FILE_TYPE_ENEMY = 2;
    protected static final byte FILE_TYPE_ENEMYTROOP = 3;
    protected static final byte FILE_TYPE_ITEM = 4;
    protected static final byte FILE_TYPE_EQUIP = 5;
    protected static final byte FILE_TYPE_SKILL = 6;
    protected static final byte FILE_TYPE_PLAYER = 7;
    protected static final byte FILE_TYPE_MAP = 8;
    private InputStream is = null;
    private DataInputStream dis = null;

    protected DataInputStream getDataInputStream(byte Type, int index) {
        switch (Type) {
            case FILE_TYPE_SYSTEM:
                is = getClass().getResourceAsStream("/data/system.gat");
                break;
            case FILE_TYPE_ANIMATION:
                is = getClass().getResourceAsStream("/data/ani.gat");
                break;
            case FILE_TYPE_ENEMY:
                is = getClass().getResourceAsStream("/data/enemy.gat");
                break;
            case FILE_TYPE_ENEMYTROOP:
                is = getClass().getResourceAsStream("/data/enemytroop.gat");
                break;
            case FILE_TYPE_ITEM:
                is = getClass().getResourceAsStream("/data/item.gat");
                break;
            case FILE_TYPE_EQUIP:
                is = getClass().getResourceAsStream("/data/equip.gat");
                break;
            case FILE_TYPE_SKILL:
                is = getClass().getResourceAsStream("/data/skill.gat");
                break;
            case FILE_TYPE_PLAYER:
                is = getClass().getResourceAsStream("/data/player.gat");
                break;
            case FILE_TYPE_MAP:
                is = getClass().getResourceAsStream("/data/map/map" + index + ".gat");
                break;
            default:
                is = null;
        }
        dis = new DataInputStream(is);
        return dis;
    }
}
