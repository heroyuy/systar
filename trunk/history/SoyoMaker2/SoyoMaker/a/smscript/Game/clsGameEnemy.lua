-- 敌人类
clsGameEnemy = {}
setmetatable(clsGameEnemy,clsGameBattler)
clsGameEnemy.__index = clsGameEnemy
function clsGameEnemy:new(position, index)
	local self = {}
	self = clsGameBattler:new()
	setmetatable(self,clsGameEnemy)
	self.reader.position = position              -- ������λ��
	self.reader.index = index                    -- ����index
	self.reader.originalName = self:enemy():name()        -- ԭ���
	self.leter = ""                              -- �������ӵ���
	self.plural = false                          -- ������ֱ�־
	self.reader.battleImg = self:enemy():battleImg()    
	self.reader.battleTone = self:enemy():tone()
	self.reader.hp = self:enemy():maxHp()
	self.reader.sp = self:enemy():maxSp()
	self.reader.actions = {}
    for i = 1,self:enemy():actionsSize() do
    	self.reader.actions[i] = self:enemy():action(i-1)
    end
	return self
end
-- 获取敌人原始名字
function clsGameEnemy:originalName()
	return self.reader.originalName
end
-- ��ɫ�ж�
function clsGameEnemy:isActor()
	return false
end
-- ��ȡ�����ڶ����е�λ��
function clsGameEnemy:position()
	return self.reader.position
end
-- ��ȡ���˶���
function clsGameEnemy:enemy()
	return globalDataManager:getModel(const.DATA_MANAGER_ENEMY,self.reader.index)
end
-- ��ȡ���
function clsGameEnemy:name()
	if self.plural then
		return self.reader.originalName + self:leter()
	else
		return self.reader.originalName
	end
end
-- 获取武器
function clsGameEnemy:weapons()
	local result = {}
	if self:enemy():equipsContains(const.EQUIP_WEAPON) then
		result[const.EQUIP_WEAPON] = self:enemy():equip(const.EQUIP_WEAPON)
	end
	return result
end
function clsGameEnemy:armors()
	local result = {}
	if self:enemy():equipsContains(const.EQUIP_HELM) then
		result[const.EQUIP_HELM] = self:enemy():equip(const.EQUIP_HELM)
	end
	if self:enemy():equipsContains(const.EQUIP_JEWELRY) then
		result[const.EQUIP_JEWELRY] = self:enemy():equip(const.EQUIP_JEWELRY)
	end
	if self:enemy():equipsContains(const.EQUIP_SHIELD) then
		result[const.EQUIP_SHIELD] = self:enemy():equip(const.EQUIP_SHIELD)
	end
	if self:enemy():equipsContains(const.EQUIP_ARMOUR) then
		result[const.EQUIP_ARMOUR] = self:enemy():equip(const.EQUIP_ARMOUR)
	end
	if self:enemy():equipsContains(const.EQUIP_BOOTS) then
		result[const.EQUIP_BOOTS] = self:enemy():equip(const.EQUIP_BOOTS)
	end
	return result
end
function clsGameEnemy:equips()
	local result = self:armors()
	result[const.EQUIP_WEAPON] = self:weapons()[const.EQUIP_WEAPON]
	return result
end
-- ��ȡ���������ֵ
function clsGameEnemy:baseMaxHp()
	return self:enemy():maxHp()
end
-- ��ȡ��ħ�����ֵ
function clsGameEnemy:baseMaxSp()
	return self:enemy():maxSp()
end
-- ��ȡ������
function clsGameEnemy:baseAtk()
	return self:enemy():atk()
end
-- ��ȡ�������
function clsGameEnemy:baseDef()
	return self:enemy():def()
end
-- ��ȡ������
function clsGameEnemy:baseStr()
	return self:enemy():stre()
end
-- ��ȡ������
function clsGameEnemy:baseInt()
	return self:enemy():inte()
end
-- ��ȡ������
function clsGameEnemy:baseAgi()
	return self:enemy():agil()
end
-- ��ȡ������
function clsGameEnemy:baseVit()
	return self:enemy():vita()
end
-- ��ȡ������
function clsGameEnemy:baseDex()
	return self:enemy():dext()
end
-- ��ȡ������
function clsGameEnemy:baseLuc()
	return self:enemy():luck()
end
-- ��ȡ������
function clsGameEnemy:hit()
	local n = 0
	n = (self:weapons()[const.EQUIP_WEAPON] == nil and 95) or self:weapons()[const.EQUIP_WEAPON].hit
	return n
end
-- ��ȡ������
function clsGameEnemy:eva()
	local n = 5
	for _,armor in pairs(table.compact(self:armors())) do
		n = n + equip.eva
	end
	return n
end
-- ��ȡ����һ����
function clsGameEnemy:cri()
	return (self.hasCritical and 10) or 0
end
-- ��ȡ�ܻ���
function clsGameEnemy:odds()
	return 1
end
-- ������������ֵ
function clsGameEnemy:attributeRate(attributeIndex,attributeValue)
	local tmp = self:attributes()
	local result = tmp[attributeIndex]
	result = result * attributeValue / 100
	for _,buff in pairs(self:buffs()) do
		if buff.attributes[attributeIndex] ~= nil then
			result = result * buff.attributes[attributeIndex] / 100
		end
	end
	return result
end
-- ��ȡ״̬��Ч��
function clsGameEnemy:buffProbability(buffIndex,buffValue)
	if globalDataBuffs[buffIndex].nonresistance then
    	return 100
    else
    	local rank = self:enemy().buffs[buffIndex]
    	if smRandom:nextInt(100) < buffValue then
      		return rank
      	else
      		return 0
    	end
    end
end
-- �жϷ���״̬
function clsGameEnemy:isBuffResist(buffIndex,buffValue)
	if smRandom:nextInt(100) < buffValue then -- ͨ��״̬�����ж�
    	return false
    else
    	return true
    end
end
-- ��ȡ����ֵ
function clsGameEnemy:exp()
	return self:enemy().exp
end
-- ��ȡ��Ǯ
function clsGameEnemy:gold()
	return self:enemy().money
end
-- ��ȡ������Ʒ
function clsGameEnemy:treasures()
	return self:enemy().treasures
end
-- 是否使用精灵
function clsGameEnemy:isUseSprite()
	return true
end
-- ��ȡս������ x ���
function clsGameEnemy:screenX()
	return (self:position()-1)*130 + 210
end
-- ��ȡս������ y ���
function clsGameEnemy:screenY()
	return 200
end
-- ��ȡս������ Z ���
function clsGameEnemy:screenZ()
	return 100
end
-- ִ�е���
function clsGameEnemy:performCollapse()
	if globalGameTemp.inBattle and self:isDead() then
		self.collapse = true
	end
end
-- ����
function clsGameEnemy:escape()
	self.reader.hidden = true
	self.reader.action:clear()
end
-- 变身
function clsGameEnemy:transform(enemyIndex)
	self.reader.index = enemyIndex
	if self:enemy().name ~= self.reader.originalName then
      self.reader.originalName = self:enemy().name
      self.letter = ""
      self.plural = false
    end
    self.reader.battlerImg = self:enemy().battlerImg    
	self.reader.battlerTone = self:enemy().battlerTone
	self:makeAction()
end
-- 看看条件是否满足
function clsGameEnemy:isConditionsMet(action)
	if action:conditionsSize() == 0 then
		return true
	end
	for i = 0,action:conditionsSize()-1 do
		if action:condition(i):conditionType() == const.ACTION_DATA_CONDITION_TURN then  -- 回合条件
			local n = globalGameTroop:turnCount()
    		local a = action:condition(i):param(0)
    		local b = action:condition(i):param(1)
    		if (b ~= -1 and a~= -1 and n < b and n > a) then
    			return true
    		end
    		if (b ~= -1 and a~= -1 and a == b and n == a) then
    			return true
    		end
    		if (b == -1 and a~= -1 and n == a) then
    			return true
    		end
    	end
    	if action:condition(i):conditionType() == const.ACTION_DATA_CONDITION_HP then   -- HP条件
    		local hpRate = self.reader.hp * 100 / self:maxHp()
    		if hpRate > action:condition(i):param(0) and action:condition(i):param(1) then
    			return true
    		end
    		if action:condition(i):param(1) == nil and hpRate > action:condition(i):param(0) then
    			return true
    		end
    	end
    	if action:condition(i):conditionType() == const.ACTION_DATA_CONDITION_LEVEL then -- 等级条件
    		if globalGameParty:maxLevel() > action:condition(i):param(0) then
    			return true
    		end
    	end
    	if action:condition(i):conditionType() == const.ACTION_DATA_CONDITION_SWITCH then -- 开关条件
    		if globalGameSwitches.get(action:condition(i):param(0)) then
    			return true
    		end
    	end
    	if action:condition(i):conditionType()  == const.ACTION_DATA_CONDITION_VARIABLE then -- 变量条件
    		if globalGameSwitches.get(action:condition(i):param(0)) == action:condition(i):param(1) then
    			return true
    		end
    	end
	end
	return false
end
-- 生成行动
function clsGameEnemy:makeAction()
	self.reader.action:clear()
	if not self:isMovable() then
		return
	end
    local availableActions = {}
    local ratingMax = 0
    for _,action in pairs(self.reader.actions) do
    	if self:isConditionsMet(action) then
      		if action:actionType() == const.ACTION_DATA_SKILL then
      			if self:isSkillCanUse(action:param(0)) then  		
      				table.insert(availableActions,action)
      				ratingMax = math.max(ratingMax, action:rate())
      			end
      		else
      			table.insert(availableActions,action)
      			ratingMax = math.max(ratingMax, action:rate())
      		end
    	end
    end
    local ratingsTotal = 0
    local ratingZero = ratingMax - 30
    for _,action in pairs(availableActions) do
    	if not (action:rate() <= ratingZero) then
      		ratingsTotal = ratingsTotal + action:rate() - ratingZero
      	end
    end
    if ratingsTotal == 0 then
    	return 
    end
    local value = smRandom:nextInt(ratingsTotal)
    for _,action in pairs(availableActions) do
      	if not (action:rate() <= ratingZero) then
      		if value < action:rate() - ratingZero then
        		self.reader.action.type = action:actionType()
        		if action:actionType() == const.ACTION_DATA_SKILL then
        			self.reader.action.skillIndex = action:param(0)
        		end
        		if action:actionType() == const.ACTION_DATA_ITEM then
        			self.reader.action.itemIndex = action:param(0)
        		end
        		self.reader.action:decideRandomTarget()
        		return
       		else
        		value = value - action:rate() + ratingZero
      		end
        end
    end
end