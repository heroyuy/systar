package com.soyomaker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.Npc;

/**
 * @author wp_g4
 * 
 */
@Service("npcService")
@Transactional
public class NpcService extends AbstractService<Npc> {

	public List<Npc> findByMapId(int mapId) {
		return this.find("from Npc npc where npc.mapId=?", mapId);
	}

}
