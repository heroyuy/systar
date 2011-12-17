--[[
  description:数据加载工具
  author:wp_g4
  date:2011/12/8
--]]

globalDataLoader={}

--初始化数据字典
globalDictionary={map={},npc={}}

--定义文件路径
globalDataLoader.vocationPath=".\\game\\data\\vocation.gat";
globalDataLoader.playerPath=".\\game\\data\\player.gat";
globalDataLoader.skillPath=".\\game\\data\\skill.gat";
globalDataLoader.itemPath=".\\game\\data\\item.gat";
globalDataLoader.equipPath=".\\game\\data\\equip.gat";
globalDataLoader.enemyPath=".\\game\\data\\enemy.gat";
globalDataLoader.enemyTroopPath=".\\game\\data\\enemyTroop.gat";
globalDataLoader.statusPath=".\\game\\data\\status.gat";
globalDataLoader.mapPath=".\\game\\data\\map\\map{index}.gat";
globalDataLoader.npcPath=".\\game\\data\\npc{index}.gat";
globalDataLoader.animationPath=".\\game\\data\\animation.gat";
globalDataLoader.configPath=".\\game\\data\\config.gat";

--初始化
function globalDataLoader:init()
  --(1)加载Config信息
  self:loadConfig()
  --(2)加载Vocation信息
  self:loadVocation()
  --(3)加载Player信息
  self:loadPlayer()
  --(4)加载Skill信息
  self:loadSkill()
  --(5)加载Item信息
  self:loadItem()
  --(6)加载Equip信息
  self:loadEquip()
  --(7)加载Enemy信息
  self:loadEnemy()
  --(8)加载EnemyTroop信息
  self:loadEnemyTroop()
  --(9)加载Status信息
  self:loadStatus()
  --(10)加载Animation信息
  self:loadAnimation()
end

--加载Config数据
function globalDataLoader:loadConfig()
  dofile(self.configPath)
end

--加载Vocation数据
function globalDataLoader:loadVocation()
  dofile(self.vocationPath)
end

--加载Player数据
function globalDataLoader:loadPlayer()
  dofile(self.playerPath)
end

--加载Skill数据
function globalDataLoader:loadSkill()
  dofile(self.skillPath)
end

--加载Item数据
function globalDataLoader:loadItem()
  dofile(self.itemPath)
end

--加载Equip数据
function globalDataLoader:loadEquip()
  dofile(self.equipPath)
end

--加载Enemy数据
function globalDataLoader:loadEnemy()
  dofile(self.enemyPath)
end

--加载EnemyTroop数据
function globalDataLoader:loadEnemyTroop()
  dofile(self.enemyTroopPath)
end

--加载Status数据
function globalDataLoader:loadStatus()
  dofile(self.statusPath)
end

--加载Animation数据
function globalDataLoader:loadAnimation()
  dofile(self.animationPath)
end

--加载Map数据
function globalDataLoader:loadMap(id)
  local t=os.time()
  local path=string.gsub(self.mapPath,"{index}",id)
  dofile(path)
  t=os.time()-t
  print(t)
end

--加载NPC数据
function globalDataLoader:loadNPC(id)
  local t=os.time()
  local path=string.gsub(self.npcPath,"{index}",id)
  dofile(path)
  t=os.time()-t
  print(t)
end




