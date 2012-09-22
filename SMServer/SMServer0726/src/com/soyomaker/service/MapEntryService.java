package com.soyomaker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.MapEntry;

/**
 * @author wp_g4
 * 
 */
@Service("mapEntryService")
@Transactional
public class MapEntryService extends AbstractService<MapEntry> {

	public List<MapEntry> findByMapId(int mapId) {
		return this.find("from MapEntry mapEntry where mapEntry.mapId=?", mapId);
	}

}
