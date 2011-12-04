package com.soyomaker.data;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.soyomaker.data.model.Cell;
import com.soyomaker.data.model.ImageSet;
import com.soyomaker.data.model.Layer;
import com.soyomaker.data.model.Map;

public class DataLoader {

	public Map loadMap(int id) {
		Map map = new Map();
		try {
			FileInputStream fis = new FileInputStream(new File(
					"game/data/map/map" + id + ".gat"));
			DataInputStream dis = new DataInputStream(fis);
			map.setId(dis.readInt());
			map.setName(dis.readUTF());
			map.setMusicName(dis.readUTF());
			// map.setBattleback(dis.readUTF());
			// map.setBattleMusic(dis.readUTF());
			map.setRowNum(dis.readInt());
			map.setColNum(dis.readInt());
			map.setCellWidth(dis.readInt());
			map.setCellHeight(dis.readInt());
			map.setImageSetNum(dis.readInt());
			for (int i = 0; i < map.getImageSetNum(); i++) {
				ImageSet imageSet = new ImageSet();
				imageSet.setId(dis.readInt());
				imageSet.setPath(dis.readUTF());
				map.putImageSets(imageSet);
			}
			map.setLayerNum(dis.readInt());
			Layer[] layers = new Layer[map.getLayerNum()];
			for (int i = 0; i < map.getLayerNum(); i++) {
				layers[i] = new Layer();
				layers[i].setDeepth(dis.readInt());
				Cell[][] cells = new Cell[map.getColNum()][map.getRowNum()];
				for (int j = 0; j < map.getRowNum(); j++) {
					for (int k = 0; k < map.getColNum(); k++) {
						cells[j][k] = new Cell();
						cells[j][k].setImageSetId(dis.readInt());
						if (cells[j][k].getImageSetId() == -1) {
							cells[j][k].setTiledIndex(-1);
						} else {
							cells[j][k].setTiledIndex(dis.readInt());
						}

					}
				}
				layers[i].setCells(cells);
			}
			map.setLayers(layers);
			int[][] ways = new int[map.getRowNum()][map.getColNum()];
			for (int i = 0; i < map.getRowNum(); i++) {
				for (int j = 0; j < map.getColNum(); j++) {
					ways[i][j] = dis.readInt();
				}
			}
			map.setAreaId(ways);
			int[][] npcIds = new int[map.getRowNum()][map.getColNum()];
			for (int i = 0; i < map.getRowNum(); i++) {
				for (int j = 0; j < map.getColNum(); j++) {
					npcIds[i][j] = dis.readInt();
				}
			}
			map.setNpcIds(npcIds);
			dis.close();
			fis.close();
		} catch (IOException ex) {
			System.out.println("加载Map出错");
			ex.printStackTrace();
		}
		return map;
	}

}
