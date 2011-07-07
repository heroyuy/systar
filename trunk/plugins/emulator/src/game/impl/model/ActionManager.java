/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.model;

import com.soyostar.app.action.Actable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ActionManager {

    private List<Actable> actions = null;

    public ActionManager() {
        actions = new ArrayList<Actable>();
    }

    public void addAction(Actable actable) {
        actions.add(actable);
    }

    public void run() {
        Actable[] actables = actions.toArray(new Actable[0]);
        for (Actable actable : actables) {
            if (!actable.isActive()) {
                actions.remove(actable);
            } else {
                actable.run();
            }
        }
    }

    private void removeAllDeadAction() {
    }
}
