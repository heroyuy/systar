clsGameParty = {}
setmetatable(clsGameParty,clsGameUnit)
clsGameParty.__index = clsGameParty
-- 构造体
function clsGameParty:new()
	local self = {}
	setmetatable(self,clsGameParty)
	self.reader = {
				   gold = 0,                                         -- 金钱
                   steps = 0,                                        -- 步行
                   actors = {},                                      -- 角色列表
                   items = {[0] = 20},                                       -- 物品
                   equips = {},                                      -- 装备
				  }
	self.lastItemIndex = 0                                           -- 最后物品标志
    self.lastActorIndex = 0                                          -- 最后角色标志
    self.lastTargetIndex = 0                                         -- 最后目标标志
	return self
end
-- 获取成员
function clsGameParty:members()
	local result = {}
	for _,i in pairs(self.reader.actors) do
    	table.insert(result,globalGameActors:get(i))
    end
    return result
end
-- 获取物品
function clsGameParty:items()
	local result = {}
	for i,v in pairs(self.reader.items) do
		if v > 0 then
        	table.insert(result,globalDataManager:getModel(const.DATA_MANAGER_ITEM,i)) 
      	end
    end
    for i,v in pairs(self.reader.equips) do
		if v > 0 then
        	table.insert(result,globalDataManager:getModel(const.DATA_MANAGER_EQUIP,i)) 
      	end
    end
    return result
end
-- 建立队伍
function clsGameParty:setupStartingMembers()
	self.reader.actors = {}
    for i = 0,globalDataManager:getModel(const.DATA_MANAGER_CONFIG,0):playersSize()-1 do
    	table.insert(self.reader.actors,globalDataManager:getModel(const.DATA_MANAGER_CONFIG,0):player(i))
    end
end
-- 获取名字
function clsGameParty:name()
	if #self.reader.actors == 0 then
      return ""
    elseif #self.reader.actors == 1 then
      return members[0]:name()
    else
      return string.gsub(vocab.PARTY_NAME,"*s",self:members()[1]:name())
    end
end
-- 获取最大等级
function clsGameParty:maxLevel()
	local level = 0
    for _,i in pairs(self.reader.actors) do
    	local actor = globalGameActors.get(i)
    	if level < actor:level() then
    		level = actor:level()
    	end
    end
    return level
end
-- 加入队员
function clsGameParty:addActor(actorIndex)
	if #self.reader.actors < const.MAX_MEMBERS and not table.include(self.reader.actors,actorIndex) then
      table.insert(self.reader.actors,actorIndex)
      globalGamePlayer:refresh()
    end
end
-- 减去队员
function clsGameParty:removeActor(actorIndex)
   table.delete(self.reader.actors,actorIndex)
   globalGamePlayer:refresh()
end
-- 获取金钱
function clsGameParty:gainGold(n)
    self.reader.gold = math.min(math.max(self.reader.gold + n, 0), 9999999)
end
-- 失去金钱
function clsGameParty:loseGold(n)
    self:gainGold(-n)
end
-- 增加步数
function clsGameParty:increaseSteps()
    self.reader.steps = self.reader.steps + 1
end
-- 获取物品数量
function clsGameParty:itemNumber(item)
	local number = 0
	if item:isA() == "Item" then
		number = self.reader.items[item:getIndex()]
	end
    if item:isA() == "Equip" then
    	number = self.reader.equips[item:getIndex()]
    end
    return (number == nil and 0 ) or number
end
-- 是否有此物品
function clsGameParty:isHasItem(item, includeEquip)
	if self:itemNumber(item) > 0 then
		return true
	end
	if includeEquip then
		for _,actor in pairs(self:members()) do
			if getmettatable(item) == "clsEquip" then
				if actor:equips()[item.kind] == item then
					return true
				end
			end
		end
	end
	return false
end
-- 损失物品
function clsGameParty:loseItem(item,n,includeEquip)
	if includeEquip == nil then
		includeEquip = false
	end
    self:gainItem(item, -n, includeEquip)
end
-- 获得/损失物品
function clsGameParty:gainItem(item,n,includeEquip)
	if includeEquip == nil then
		includeEquip = false
	end
    local number = self:itemNumber(item)
    if item:isA() == "Item" then
      self.reader.items[item:getIndex()] = math.min(math.max(number + n, 0), 99)
    elseif item:isA() == "Equip" then
      self.reader.equips[item:getIndex()] = math.min(math.max(number + n, 0), 99)
    end
    n = n + number
    if includeEquip and n < 0 then
    	for _,actor in pairs(self:members()) do
        	while n < 0 and table.include(actor:equips(),item) do
          		actor.discardEquip(item)
          		n = n + 1
        	end
      	end
    end
end
-- 消耗物品
function clsGameParty:consumeItem(item)
	if item:isA() == "Item" and item:consumable() then
    	self:loseItem(item,item:cost(0):costValue())
    end
end
-- 可以输入命令的判定
function clsGameParty:isInputable()
	for _,actor in pairs(self:members()) do
		if actor:isInputable() then
      		return true 
      	end
    end
    return false
end
-- 物品是否可使用
function clsGameParty:isItemCanUse(item)
	if not item:isA() == "Item" then
		return false 
	end
	if self:itemNumber(item) == 0 then
    	return false 
    end
    if globalGameTemp.inBattle then
      	return item:isBattleOk()
    else
      	return item:isMenuOk()
    end
end
--
function clsGameParty:doAutoRecovery()
end
-- 判断是否全灭
function clsGameParty:isAllDead()
	if #self.reader.actors == 0 and not globalGameTemp.inBattle then
    	return false 
    end
    return #self:existingMembers() == 0
end
-- 移除战斗状态
function clsGameParty:removeBuffsBattle()
end