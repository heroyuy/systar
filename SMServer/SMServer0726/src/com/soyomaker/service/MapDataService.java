package com.soyomaker.service;

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

}
