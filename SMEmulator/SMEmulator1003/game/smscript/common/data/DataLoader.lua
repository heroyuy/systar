--[[
  description:数据加载工具
  author:wp_g4
  date:2011/12/8
--]]

smDataLoader={}

--初始化数据字典
globalDictionary={map={},npc={}}

--定义文件路径
smDataLoader.vocationPath=".\\game\\luadata\\vocation.gat";
smDataLoader.playerPath=".\\game\\luadata\\player.gat";
smDataLoader.skillPath=".\\game\\luadata\\skill.gat";
smDataLoader.itemPath=".\\game\\luadata\\item.gat";
smDataLoader.equipPath=".\\game\\luadata\\equip.gat";
smDataLoader.enemyPath=".\\game\\luadata\\enemy.gat";
smDataLoader.enemyTroopPath=".\\game\\luadata\\enemyTroop.gat";
smDataLoader.statusPath=".\\game\\luadata\\status.gat";
smDataLoader.mapPath=".\\game\\luadata\\map\\map{index}.gat";
smDataLoader.npcPath=".\\game\\luadata\\npc{index}.gat";
smDataLoader.animationPath=".\\game\\luadata\\animation.gat";
smDataLoader.systemPath=".\\game\\luadata\\system.gat";

--加载Vocation数据
function smDataLoader:loadVocation()
  dofile(smDataLoader.vocationPath)
end

--加载Player数据
function smDataLoader:loadPlayer()
  dofile(smDataLoader.playerPath)
  smLog:setDebug(true)
  smLog:info(globalDictionary.players[1].name)
  smLog:setDebug(false)
end

--加载Skill数据
function smDataLoader:loadSkill()
  dofile(smDataLoader.skillPath)
end

--加载Item数据
function smDataLoader:loadItem()
  dofile(smDataLoader.itemPath)
end

--加载Equip数据
function smDataLoader:loadEquip()
  dofile(smDataLoader.equipPath)
end

--加载Enemy数据
function smDataLoader:loadEnemy()
  dofile(smDataLoader.enemyPath)
end

--加载EnemyTroop数据
function smDataLoader:loadEnemyTroop()
  dofile(smDataLoader.enemyTroopPath)
end

--加载Status数据
function smDataLoader:loadStatus()
  dofile(smDataLoader.statusPath)
end

--加载Map数据
function smDataLoader:loadMap(id)
  local t=os.time()
  local path=string.gsub(smDataLoader.mapPath,"{index}",id)
  dofile(path)
  t=os.time()-t
  print(t)
end

--加载NPC数据
function smDataLoader:loadNPC(id)
  dofile(".\\game\\luadata\\map\\map0.gat")
end

--加载Animation数据
function smDataLoader:loadAnimation()
  dofile(smDataLoader.animationPath)
end

--加载System数据
function smDataLoader:loadSystem()
  dofile(smDataLoader.systemPath)
end
