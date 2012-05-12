clsGameBattleAction = {}
clsGameBattleAction.__index = clsGameBattleAction
function clsGameBattleAction:new(battler)
	local self = {}
	setmetatable(self,clsGameBattleAction)
	self.battler = battler      -- 战斗者
	self:clear()
	return self
end
-- 清除
function clsGameBattleAction:clear()
  self.speed = 0                   -- 速度
  self.type = -1                   -- 类型（0 静止	1 普通攻击	2 防御	3 使用物品	5 使用技能	6 逃跑）
  self.skillIndex = 0              -- 技能 ID
  self.itemIndex  = 0              -- 物品 ID
  self.targetPosition = -1         -- 目标索引
  self.forcing = 0                 -- 强制标志
  self.value = 0                   -- 自动战斗评价值
end
-- 获取队伍同伴
function clsGameBattleAction:friendsUnit()
	if self.battler:isActor() then
		return globalGameParty
	else
		return globalGameTroop
	end
end
-- 获取敌人同伴
function clsGameBattleAction:opponentsUnit()
	if self.battler:isActor() then
		return globalGameTroop
	else
		return globalGameParty
	end
end
-- 设置普通攻击
function clsGameBattleAction:setAttack()
	self.type = const.ACTION_DATA_ATTACK
end
-- 设置防御
function clsGameBattleAction:setGuard()
	self.type = const.ACTION_DATA_GUARD
end
-- 设置技能
function clsGameBattleAction:setSkill(skillIndex)
	self.type = const.ACTION_DATA_SKILL
	self.skillIndex = skillIndex
end
-- 设置物品
function clsGameBattleAction:setItem(itemIndex)
	self.type = const.ACTION_DATA_ITEM
end
-- 设置逃跑
function clsGameBattleAction:setEscape()
	self.type = const.ACTION_DATA_ESCAPE
end
-- 设置等待
function clsGameBattleAction:setWait()
	self.type = const.ACTION_DATA_WAIT
end
--  普通攻击判定
function clsGameBattleAction:isAttack()
	return self.type == const.ACTION_DATA_ATTACK
end
-- 防御判定
function clsGameBattleAction:isGuard()
	return self.type == const.ACTION_DATA_GUARD
end
-- 无动作判定
function clsGameBattleAction:isNothing()
	return self.type < 0
end
-- 技能判定
function clsGameBattleAction:isSkill()
	return self.type == const.ACTION_DATA_SKILL
end
-- 获取技能对象
function clsGameBattleAction:skill()
	return (self:isSkill() and globalDataManager:getModel(const.DATA_MANAGER_SKILL,self.skillIndex)) or nil
end
-- 物品判定
function clsGameBattleAction:isItem()
	return self.type == const.ACTION_DATA_ITEM
end
-- 获取物品对象
function clsGameBattleAction:item()
	return (self:isItem() and globalDataManager:getModel(const.DATA_MANAGER_ITEM,self.itemIndex)) or nil
end
-- 己方使用判定
function clsGameBattleAction:isForFriend()
	if self:isSkill() and self:skill():isForFriend() then
		return true
	end
	if self:isItem() and self:item():isForFriend() then
    	return true
    end
    return false
end
-- 己方用 (无法战斗) 判定
function clsGameBattleAction:isForDeadFriend()
	if self:isSkill() and self:skill():isForDeadFriend() then
		return true
	end
	if self:isItem() and self:item():isForDeadFriend() then
    	return true
    end
    return false
end
-- 随机目标
function clsGameBattleAction:decideRandomTarget()
	local target
	if self:isForFriend() then
    	target = self:friendsUnit():randomTarget()
    elseif self:isForDeadFriend() then
    	target = self:friendsUnit():randomDeadTarget()
    else
    	target = self:opponentsUnit():randomTarget()
    end
    if target == nil then
    	self:clear()
    else
    	self.targetPosition = target:position()
    end
end
-- 最终目标判定
function clsGameBattleAction:decideLastTarget()
	local target
	if self.targetPosition == -1 then
    	target = nil
    elseif self:isForFriend() then
    	target = self:friendsUnit():members()[self.targetPosition]
    else
    	target = self:opponentsUnit():members()[self.targetPosition]
    end
    if target == nil or not target:isExist() then
    	self:clear()
    end
end
-- 准备行动
function clsGameBattleAction:prepare()
	if self.battler:isBerserker() or self.battler:isConfusion() then   -- 暴走或丧乱的场合
    	self:setAttack()                                     -- 改变成普通攻击
    end
end
-- 判断行动有效度
function clsGameBattleAction:isValid()
	if self:isNothing() then                      -- 什麽都不做 
    	return false 
    end
    if self.forcing then                      	  -- 强制行动
   		return true 
    end
    if not self.battler:isMovable() then          -- 无法行动
    	return false  
    end
    if self:isSkill() then                        -- 技能
    	if not self.battler:isSkillCanUse(self:skill()) then
      		return false 
      	end
    elseif self:isItem() then                     -- 物品
    	if not self:friendsUnit():isItemCanUse(self:item()) then
      		return false 
      	end
    end
    return true
  end
-- 设置速度
function clsGameBattleAction:makeSpeed()
	self.speed = self.battler:agil() + smRandom:nextInt(5 + math.floor(self.battler:agil() / 4))
	if self:isSkill() then
    	self.speed = self.speed + self:skill():speed() 
    end
    if self:isItem() then
    	self.speed = self.speed + self:item():speed()
    end
    if self:isGuard() then
    	self.speed = self.speed + 2000  
    end
    if self:isAttack() and self.battler:fastAttack() then
    	self.speed = self.speed + 1000 
    end
end
-- 生成目标数组
function clsGameBattleAction:makeTargets()
	if self:isAttack() then
      return self:makeAttackTargets()
    elseif self:isSkill() then
      return self:makeObjTargets(self:skill())
    elseif self:isItem() then
      return self:makeObjTargets(self:item())
    end
end
-- 生成普通攻击目标
function clsGameBattleAction:makeAttackTargets()
	local targets = {}
    if self.battler:isConfusion() then
    	table.insert(targets,self:friendsUnit():randomTarget())
    elseif self.battler:isBerserker() then
    	table.insert(targets,self:opponentsUnit():randomTarget())
    else
    	table.insert(targets,self:opponentsUnit():smoothTarget(self.targetPosition))
    end
    if self.battler:dualAttack() then      -- 连续攻击
    	targets = table.union(targets,targets)
    end
    return table.compact(targets)
end
-- 生成技能或物品目标
function clsGameBattleAction:makeObjTargets(obj)
	local targets = {}
    if obj:isForOpponent() then
    	if obj:isForOne() then         -- 敌单体
        	table.insert(targets,self:opponentsUnit():smoothTarget(self.targetPosition))
        elseif obj:isForALL() then     -- 敌全体
        	targets = self:opponentsUnit():existingMembers()
        end
    elseif obj:isForUser() then        -- 使用者
    	table.insert(targets,self.battler)
    elseif obj:isForDeadFriend() then
    	if obj:isForOne() then           -- 己方死亡单体
        	table.insert(targets,self:friendsUnit():smoothDeadTarget(self.targetPosition))
        elseif obj:isForALL() then     -- 己方死亡全体
        	targets = self:friendsUnit():deadMembers()
        end
    elseif obj:isForFriend() then
    	if obj:isForOne() then           -- 己单体
        	table.insert(targets,self:friendsUnit():smoothTarget(self.targetPosition))
        elseif obj:isForALL() then     -- 己全体
        	targets = self:friendsUnit():existingMembers()
        end
    end
    return table.compact(targets)
end