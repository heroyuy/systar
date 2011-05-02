/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.data.model.animation;

import com.soyostar.data.model.Model;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Sequence extends Model  {

    public ArrayList<Layer> layers = new ArrayList<Layer>();
    public int delay;
}
