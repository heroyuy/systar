package com.soyomaker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.LevelExp;

@Service("levelExpService")
@Transactional
public class LevelExpService extends AbstractService<LevelExp> {

	public Map<Integer, Integer> getLevelExpMap() {
		Map<Integer, Integer> levelExpMap = new HashMap<Integer, Integer>();
		List<LevelExp> levelExpList = this.findAll();
		for (LevelExp levelExp : levelExpList) {
			levelExpMap.put(levelExp.getLevel(), levelExp.getExp());
		}
		return levelExpMap;
	}

}
