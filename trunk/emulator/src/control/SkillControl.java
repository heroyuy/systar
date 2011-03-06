package control;

import view.SkillView;
import engine.Control;
import engine.GameEngine;
import engine.View;
import engine.script.Event;
import engine.script.ScriptEngine;
import game.RpgGame;
import model.Bag;
import model.Const;
import model.GameData;

/**
 *
 * @author Administrator
 */
public class SkillControl implements Control {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private ScriptEngine se = ScriptEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();

    public void keyPressed(View view, int key) {
        if (view instanceof SkillView) {
            switch (key) {
                case Const.Key.KEY_UP:
                    if (gd.skill_curIndex > 0) {
                        gd.skill_curIndex--;
                        if (gd.skill_curIndex < gd.skill_topIndex) {
                            gd.skill_topIndex--;
                        }
                    }
                    break;
                case Const.Key.KEY_DOWN:
                    if (gd.skill_curIndex < gd.player.bag.getList(Bag.SKILL).length-1) {
                        gd.skill_curIndex++;
                        if (gd.skill_curIndex > gd.skill_topIndex+gd.skill_showNum-1) {
                            gd.skill_topIndex++;
                        }
                    }
                    break;
                case Const.Key.KEY_RS:
                    game.setCurView(Const.ViewId.VIEW_MAP);
                    break;
            }
        }
        ge.clearKey();
    }

    public void dealEvent(View view, Event event) {
    }
}
