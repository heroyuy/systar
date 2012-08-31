package com.soyomaker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.MapData;

/**
 * @author chenwentao
 *
 */
@Service("mapDataService")
@Transactional
public class MapDataService extends AbstractService<MapData> {
	
	public List<MapData> getAllMapData() {
		return findAll();
	}
	
}
