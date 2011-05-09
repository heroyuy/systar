/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game.util;

/**
 *
 * @author Administrator
 */
public class TouchDelegate {

    private static TouchDelegate defaultTouchDelegate =new TouchDelegate();

    public static TouchDelegate getDefaultTouchDelegate() {
        return defaultTouchDelegate;
    }

    

}
