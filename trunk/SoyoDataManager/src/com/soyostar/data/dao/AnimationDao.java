/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.data.dao;

import com.soyostar.data.DataManager;
import com.soyostar.data.model.animation.Animation;
import com.soyostar.data.model.animation.Module;
import com.soyostar.data.model.animation.Sequence;
import com.soyostar.data.model.animation.Layer;
import com.soyostar.data.model.animation.Tile;
import com.soyostar.data.model.animation.TileSet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author cokey 4.28
 */
public class AnimationDao extends Dao<Animation> {

    @Override
    public void load() {
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
            f = new File(DataManager.getInstance().getPath() + "/data/animation.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            int aniNum = dis.readInt();
            Animation ani = null;
            for (int i = 0; i < aniNum; i++) {
                ani = new Animation();
                ani.setIndex(dis.readInt());
                int tsn = dis.readInt();
                for (int j = 0; j < tsn; j++) {
                    TileSet tileset = new TileSet();
                    tileset.id = dis.readInt();
                    tileset.path = dis.readUTF();
                    int tn = dis.readInt();
                    for (int k = 0; k < tn; k++) {
                        Tile tile = new Tile();
                        tile.tilesetID = tileset.id;
                        tile.x = dis.readInt();
                        tile.y = dis.readInt();
                        tile.width = dis.readInt();
                        tile.height = dis.readInt();
                        tileset.tiles.add(tile);
                    }
                    ani.tileSets.add(tileset);
                }
                int sn = dis.readInt();
                for (int j = 0; j < sn; j++) {
                    Sequence seq = new Sequence();
                    int ln = dis.readInt();
                    for (int k = 0; k < ln; k++) {
                        Layer sl = new Layer();
                        sl.type = dis.readByte();
                        sl.x = dis.readInt();
                        sl.y = dis.readInt();
                        sl.width = dis.readInt();
                        sl.height = dis.readInt();
                        int mn = dis.readInt();
                        for (int l = 0; l < mn; l++) {
                            Module module = new Module();
                            module.tileID = dis.readInt();
                            module.x = dis.readInt();
                            module.y = dis.readInt();
                            sl.modules.add(module);
                        }
                        seq.layers.add(sl);
                    }
                    seq.delay = dis.readInt();
                    ani.sequences.add(seq);
                }
                saveModel(ani);
            }
        } catch (IOException e) {
            System.out.println("没有可加载的Animation");
        }
    }

    @Override
    public void save() {
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(DataManager.getInstance().getPath() + "/data/animation.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);

            dos.writeInt(size());
            Animation[] animations = this.getModels(Animation.class);
            Animation ani = null;
            for (int i = 0; i < size(); i++) {
                ani = animations[i];
                dos.writeInt(ani.getIndex());
                int n = ani.tileSets.size();
                dos.writeInt(n);
                for (int j = 0; j < n; j++) {
                    dos.writeInt(ani.tileSets.get(j).id);
                    dos.writeUTF(ani.tileSets.get(j).path);
                    int m = ani.tileSets.get(j).tiles.size();
                    dos.writeInt(m);
                    for (int k = 0; k < m; k++) {
                        //dos.writeInt(ani.tileSets.get(j).id);
                        dos.writeInt(ani.tileSets.get(j).tiles.get(k).x);
                        dos.writeInt(ani.tileSets.get(j).tiles.get(k).y);
                        dos.writeInt(ani.tileSets.get(j).tiles.get(k).width);
                        dos.writeInt(ani.tileSets.get(j).tiles.get(k).height);
                    }
                }
                n = ani.sequences.size();
                for (int j = 0; j < n; j++) {
                    int m = ani.sequences.get(j).layers.size();
                    dos.writeInt(m);
                    for (int k = 0; k < m; k++) {
                        dos.writeByte(ani.sequences.get(j).layers.get(k).type);
                        dos.writeInt(ani.sequences.get(j).layers.get(k).x);
                        dos.writeInt(ani.sequences.get(j).layers.get(k).y);
                        dos.writeInt(ani.sequences.get(j).layers.get(k).width);
                        dos.writeInt(ani.sequences.get(j).layers.get(k).height);
                        int x = ani.sequences.get(j).layers.get(k).modules.size();
                        dos.writeInt(x);
                        for (int l = 0; l < x; l++) {
                            dos.writeInt(ani.sequences.get(j).layers.get(k).modules.get(l).tileID);
                            dos.writeInt(ani.sequences.get(j).layers.get(k).modules.get(l).x);
                            dos.writeInt(ani.sequences.get(j).layers.get(k).modules.get(l).y);
                        }
                    }
                    dos.writeInt(ani.sequences.get(j).delay);
                }

            }
        } catch (IOException e) {
        }
    }
}
