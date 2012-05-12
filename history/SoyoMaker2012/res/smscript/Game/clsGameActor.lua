clsGameActor = {}
setmetatable(clsGameActor,clsGameBattler)
clsGameActor.__index = clsGameActor
function clsGameActor:new(actorIndex)
	local self = {}
	self = clsGameBattler:new()
	self.isSkillCanUseSuper = self.isSkillCanUse
	setmetatable(self,clsGameActor)
	self:setup(actorIndex)
	self.lastSkillIndex = 0
	return self
end
-- 建立角色
-- actorIndex : 角色索引
function clsGameActor:setup(actorIndex)
    self.reader.actorIndex = actorIndex
    self.reader.name = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,actorIndex):name()
    self.reader.intro = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,actorIndex):intro()
    self.reader.headImg = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,actorIndex):headImg()
    self.reader.charImg = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,actorIndex):charImg()
    self.reader.battleImg = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,actorIndex):battleImg()
    self.reader.vocationIndex = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,actorIndex):vocationIndex()
    self.reader.helmIndex = -1
    self.reader.jewelryIndex = -1
    self.reader.weaponIndex = -1
    self.reader.shieldIndex = -1
    self.reader.armourIndex = -1
    self.reader.bootsIndex = -1
    self.reader.lev = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,actorIndex):starLev()
    self.reader.exp = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,actorIndex):exp(self.reader.lev)
    self.reader.skills = {}
   	for i = 1,self:vocation():skillsLenth() do
   		local skillIndex = self:vocation():skill(i-1)
    	if globalDataManager:getModel(const.DATA_MANAGER_SKILL,skillIndex):lev() <= self.reader.lev then
        	self:learnSkill(skillIndex) 
        end
    end
    self:clearExtraValues()
    self:recoverAll()
end
-- 获取名字
function clsGameActor:name()
	return self.reader.name
end
-- 获取等级
function clsGameActor:level()
	return self.reader.lev
end
-- 是否角色
function clsGameActor:isActor()
	return true
end
-- 获取索引
function clsGameActor:index()
	return self.reader.actorIndex
end
-- 获取队伍位置
function clsGameActor:position()
	return table.index(globalGameParty:members(),self)
end
-- 获取角色
function clsGameActor:actor()
	return globalGameActors:get(self.reader.actorIndex)
end
-- 获取职业
function clsGameActor:vocation()
	return globalDataManager:getModel(const.DATA_MANAGER_VOCATION,self.reader.vocationIndex)
end
-- 获取头像
function clsGameActor:headImg()
	return self.reader.headImg
end
-- 获取技能数组
function clsGameActor:skills()
	local result = {}
	for _,i in pairs(self.reader.skills) do
		table.insert(result,globalDataManager:getModel(const.DATA_MANAGER_SKILL,i))
	end
	return result
end
-- 获取武器哈希表
function clsGameActor:weapons()
	local result = {}
	if self.reader.weaponIndex >= 0 then
    	result[const.EQUIP_WEAPON] = globalDataManager:getModel(const.DATA_MANAGER_EQUIP,self.reader.weaponIndex)
    end
    if self:twoSwordsStyle() and self.reader.shieldIndex >= 0 then
    	result[const.EQUIP_SHIELD] = globalDataManager:getModel(const.DATA_MANAGER_EQUIP,self.reader.shieldIndex)
    end
   
    return result
end
-- 获取防具哈希表
function clsGameActor:armors()
	local result = {}
	if self:twoSwordsStyle() and self.reader.shieldIndex >= 0 then
    	result[const.EQUIP_SHIELD] = globalDataManager:getModel(const.DATA_MANAGER_EQUIP,self.reader.shieldIndex)
    end
    if self.reader.helmIndex >= 0 then
    	result[const.EQUIP_HELM] = globalDataManager:getModel(const.DATA_MANAGER_EQUIP,self.reader.helmIndex)
    end
    if self.reader.jewelryIndex >= 0 then
    	result[const.EQUIP_JEWELRY] = globalDataManager:getModel(const.DATA_MANAGER_EQUIP,self.reader.jewelryIndex)
    end
    if self.reader.armourIndex then
    	result[const.EQUIP_ARMOUR] = globalDataManager:getModel(const.DATA_MANAGER_EQUIP,self.reader.armourIndex)
    end
    if self.reader.bootsIndex then
    	result[const.EQUIP_BOOTS] = globalDataManager:getModel(const.DATA_MANAGER_EQUIP,self.reader.bootsIndex)
    end
    return result
end
-- 获取装备哈希表
function clsGameActor:equips()
	local result = {}
	for k,v in pairs(self:weapons()) do
		result[k] = v
	end
	
	for k,v in pairs(self:armors()) do
		result[k] = v
	end
	return result
end
-- 获取属性修正值ֵ
function clsGameActor:attributeRate(attributeIndex,attributeValue)
	local tmp = self:attributes()
	local result = tmp[attributeIndex]
	result = result * attributeValue / 100
	for _,armor in pairs(self:armors()) do
		if armor.attributes[attributeIndex] ~= nil then
			result = result * armor.attributes[attributeIndex] / 100
		end
	end
	for _,buff in pairs(self:buffs()) do
		if buff.attributes[attributeIndex] ~= nil then
			result = result * buff.attributes[attributeIndex] / 100
		end
	end
	return result
end
-- 获取附加状态几率
function clsGameActor:buffProbability(buffIndex,buffValue)
	if globalDataBuffs[buffIndex].nonresistance then
    	return 100
    else
    	local rank = self:vocation().buffs[buffIndex]
    	if smRandom:nextInt(100) < buffValue then
      		return rank
      	else
      		return 0
    	end
    end
end
-- 状态是否被防御̬
function clsGameActor:isBuffResist(buffIndex,buffValue)
	if smRandom:nextInt(100) < buffValue then -- ͨ��״̬�����ж�
		for _,armor in pairs(table.compact(self:armors())) do
			if armor.buffs[buffIndex] ~= nil then
				if smRandom:nextInt(100) < armor.buffs[buffIndex] then -- ���ߵֿ�
      				return true 
      			end
      		end
    	end
    	return false
    else
    	return true
    end
end
-- 获取普通攻击属性列表
function clsGameActor:attributes()
	local result = {}
    if #table.compact(self:weapons()) == 0 then
    	result[const.UNARMED_COMBAT] = 100
    	return result                  -- ���֣���Ϊ������
    end
    for _,weapon in pairs(table.compact(self:weapons())) do
    	for i,v in pairs(weapon.attributes) do
    		if result[i] == nil or result[i] < v then
    			result[i] = v
    		end
    	end
    end
    return result
end
-- 获取最大体力界限
function clsGameActor:maxHpLimit()
	return 9999
end
-- 获取最大基本体力ֵ
function clsGameActor:baseMaxHp()
	return globalDataManager:getModel(const.DATA_MANAGER_ACTOR,self.reader.actorIndex):maxHp(self.reader.lev) + (self:vita()/7)^2+self:vita()
end
-- 获取最大基本魔力
function clsGameActor:baseMaxSp()
	return globalDataManager:getModel(const.DATA_MANAGER_ACTOR,self.reader.actorIndex):maxSp(self.reader.lev)
end
-- 获取基本攻击力
function clsGameActor:baseAtk()
	local n = 0
	n = (self:stre()/10 )^2 + self:stre()
	return n
end
-- 获取武器攻击力
function clsGameActor:equipAtk()
	local n = 0
	for _,equip in pairs(table.compact(self:equips())) do
		n = n + equip.atk
	end
	return n
end
-- 获取基本防御力
function clsGameActor:baseDef()
	local n = 0
	n = (self:vita()*0.8) + self:vita()
	return n
end
-- 获取装备防御力
function clsGameActor:equipDef()
	local n = 0
	for _,equip in pairs(table.compact(self:equips())) do
		n = n + equip:effect(1)
	end
	return n
end
-- 获取装备魔攻
function clsGameActor:equipMatk()
	local n = 0
	for _,equip in pairs(table.compact(self:equips())) do
		n = n + equip.matk
	end
	return n
end
-- 获取装备魔防
function clsGameActor:equipMdef()
	local n = 0
	for _,equip in pairs(table.compact(self:equips())) do
		n = n + equip.mdef
	end
	return n
end
-- 获取基本力量
function clsGameActor:baseStr()
	local n = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,self.reader.actorIndex):stre(self.reader.lev)
	for _,equip in pairs(table.compact(self:equips())) do
		n = n + equip.stre
	end
	return n
end
-- 获取基本智力
function clsGameActor:baseInt()
	local n = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,self.reader.actorIndex):inte(self.reader.lev)
	for _,equip in pairs(table.compact(self:equips())) do
		n = n + equip.inte
	end
	return n
end
-- 获取基本敏捷
function clsGameActor:baseAgi()
	local n = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,self.reader.actorIndex):agil(self.reader.lev)
	for _,equip in pairs(table.compact(self:equips())) do
		n = n + equip.agil
	end
	return n
end
-- 获取基本体力
function clsGameActor:baseVit()
	local n = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,self.reader.actorIndex):vita(self.reader.lev)
	for _,equip in pairs(table.compact(self:equips())) do
		n = n + equip.vita
	end
	return n
end
-- 获取基本灵巧
function clsGameActor:baseDex()
	local n = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,self.reader.actorIndex):dext(self.reader.lev)
	for _,equip in pairs(table.compact(self:equips())) do
		n = n + equip.dext
	end
	return n
end
-- 获取基本幸运
function clsGameActor:baseLuc()
	local n = globalDataManager:getModel(const.DATA_MANAGER_ACTOR,self.reader.actorIndex):luck(self.reader.lev)
	for _,equip in pairs(table.compact(self:equips())) do
		n = n + equip.luck
	end
	return n
end
-- ��ȡ������
function clsGameActor:hit()
	local n = 0
	if self:twoSwordsStyle() then
		local n1 = (self:weapons()[const.EQUIP_WEAPON] == nil and 95) or self:weapons()[const.EQUIP_WEAPON].hit
		local n2 = (self:weapons()[const.EQUIP_SHIELD] == nil and 95) or self:weapons()[const.EQUIP_SHIELD].hit
		n = math.min(n1,n2)
	else
		n = (self:weapons()[const.EQUIP_WEAPON] == nil and 95) or self:weapons()[const.EQUIP_WEAPON].hit
	end
	return n
end
-- ��ȡ������
function clsGameActor:eva()
	local n = 5
	for _,armor in pairs(table.compact(self:armors())) do
		n = n + equip.eva
	end
	return n
end
-- 暴击率
function clsGameActor:cri()
	local n = 4
	if self:criticalBonus() then
		n = n + 4
	end
	for _,weapon in pairs(table.compact(self:weapons())) do
		if weapon:criticalBonus() then
			n = n + 4
		end
	end
	return n
end
-- 受击率
function clsGameActor:odds()
	return 1
end
-- 双刀流
function clsGameActor:twoSwordsStyle()
	return self:vocation():twoSwordsStyle()
end
-- 装备固定
function clsGameActor:fixEquipment()
	return self:vocation():fixEquipment()
end
-- 自动战斗
function clsGameActor:autoBattle()
	return self:vocation():autoBattle()
end
-- 防御加强
function clsGameActor:superGuard()
	return self:vocation():superGuard()
end
-- 药物知识
function clsGameActor:pharmacology()
	return self:vocation():pharmacology()
end
-- 暴击频发
function clsGameActor:criticalBonus()
	if self:vocation():criticalBonus() then
		return true
	end
	for _,weapon in pairs(table.compact(self:weapons())) do
		if weapon:criticalBonus() then
			return true
		end
	end
	return false
end
-- ��ȡ�������غ������ơ�ѡ��
function clsGameActor:fastAttack()
	for _,weapon in pairs(table.compact(self:weapons())) do
		if weapon:fastAttack() then
			return true
		end
	end
	return false
end
-- ��ȡ�������������ѡ��
function clsGameActor:dualAttack()
	for _,weapon in pairs(table.compact(self:weapons())) do
		if weapon:dualAttack() then
			return true
		end
	end
	return false
end
-- ��ȡ���ߡ���ֹ����һ����ѡ��
function clsGameActor:preventCritical()
	for _,armor in pairs(table.compact(self:armors())) do
		if armor:preventCritical() then
			return true
		end
	end
	return false
end
-- ��ȡ���ߡ�ħ����ļ��롹ѡ��
function clsGameActor:halfSpCost()
	for _,armor in pairs(table.compact(self:armors())) do
		if armor:halfSpCost() then
			return true
		end
	end
	return false
end
-- ��ȡ���ߡ�˫������ֵ��ѡ��
function clsGameActor:doubleExpGain()
	for _,armor in pairs(table.compact(self:armors())) do
		if armor:doubleExpGain() then
			return true
		end
	end
	return false
end
-- ��ȡ���ߡ������Զ��ظ���ѡ��
function clsGameActor:autoHpRecover()
	for _,armor in pairs(table.compact(self:armors())) do
		if armor:autoHpRecover() then
			return true
		end
	end
	return false
end
-- ��ȡ��ͨ�������� ID
function clsGameActor:atkAniIndex()
	if self:twoSwordsStyle() then
		if self:weapon()[const.EQUIP_WEAPON] ~= nil then
			return self:weapon()[const.EQUIP_WEAPON].useAniIndex
		end
		return (self:weapon()[const.EQUIP_SHIELD] == nil and const.ANI_DATA_UNARMED_COMBAT) or const.ANI_DATA_NIL
	else
		return (self:weapon()[const.EQUIP_WEAPON] == nil and const.ANI_DATA_UNARMED_COMBAT) or self:weapon()[const.EQUIP_WEAPON].useAniIndex
	end
end
-- ��ȡ��ͨ�������� ID(˫����������)
function clsGameActor:atkAniIndexOther()
	if self:twoSwordsStyle() then
		if self:weapon()[const.EQUIP_SHIELD] ~= nil then
			return (self:weapon()[const.EQUIP_SHIELD] == nil and const.ANI_DATA_NIL) or self:weapon()[const.EQUIP_SHIELD].useAniIndex
		end
	else
		return const.ANI_DATA_NIL
	end
end
-- ��ȡ����ֵ�ַ�
function clsGameActor:expS()
	return (self.reader.expList[self.reader.lev + 1] > 0 and self.reader.exp) or "----"
end
-- ��ȡ��һ������ֵ�ַ�
function clsGameActor:nextExpS()
	return (self.reader.expList[self.reader.lev + 1] > 0 and self.reader.expList[self.reader.lev + 1]) or "----"
end
-- ��ȡ����һ��ʣ�ž���ֵ�ַ�
function clsGameActor:nextRestExpS()
	return (self.reader.expList[self.reader.lev + 1] > 0 and (self.reader.expList[self.reader.lev + 1] - self.reader.exp)) or "----"
end
-- ���װ����Ŀ��INDEX��
function clsGameActor:changeEquipByIndex(equipType,itemIndex,test)
    self:changeEquip(equipType, globalDataEquips[itemIndex], test)
end
-- ���װ����Ŀ�����
function clsGameActor:changeEquip(equipType,equip,test)
	local lastEquip = self:equips()[equipType]
    if not test then
    	if equip ~= nil then
    		if globalGameParty:itemNumber(equip) == 0 then
      			return  
      		end
      	end
      	globalGameParty:gainEquip(lastEquip, 1)
      	globalGameParty:loseEquip(equip, 1)
    end
    local equipIndex = (equip == nil and const.EQUIP_DATA_NIL ) or equip.index
    if equipType == const.EQUIP_WEAPON then
    	self.reader.weaponIndex = equipIndex
    	if not self:isTwoHandsLegal() then
    		self:changeEquip(const.EQUIP_SHIELD, nil, test)
    	end
    elseif equipType == const.EQUIP_SHIELD then     -- ��˫��װ���ĳ��ϣ��Զ�ж��װ��
    	self.reader.shieldIndex = equipIndex
    	if not self:isTwoHandsLegal() then          -- ��˫��װ���ĳ��ϣ��Զ�ж��װ��
    		self:changeEquip(const.EQUIP_WEAPON, nil, test)
    	end
    elseif equipType == const.EQUIP_HELM then
    	self.reader.helmIndex = equipIndex
    elseif equipType == const.EQUIP_JEWELRY then
    	self.reader.jewelryIndex = equipIndex
    elseif equipType == const.EQUIP_ARMOUR then
    	self.reader.armourIndex = equipIndex
    elseif equipType == const.EQUIP_BOOTS then
    	self.reader.bootsIndex = equipIndex
    end
end
-- ����װ��
function clsGameActor:discardEquip(equip)
	if equip.kind == const.EQUIP_WEAPON then
		if self.reader.weaponIndex == equip.index then
			self.reader.weaponIndex = const.EQUIP_DATA_NIL
		elseif self:isTwoHandsLegal() and self.reader.shieldIndex == equip.index then
			self.reader.shieldIndex = const.EQUIP_DATA_NIL
		end
	elseif equip.kind ~= const.EQUIP_WEAPON then
		if not self:isTwoHandsLegal() and self.reader.shieldIndex == equip.index then
			self.reader.shieldIndex = const.EQUIP_DATA_NIL
		elseif self.reader.helmIndex == equip.index then
			self.reader.helmIndex = const.EQUIP_DATA_NIL
		elseif self.reader.jewelryIndex == equip.index then
			self.reader.jewelryIndex = const.EQUIP_DATA_NIL
		elseif self.reader.armourIndex == equip.index then
			self.reader.armourIndex = const.EQUIP_DATA_NIL
		elseif self.reader.bootsIndex == equip.index then
			self.reader.bootsIndex = const.EQUIP_DATA_NIL
		end
	end
end
-- ˫��װ���ж�
function clsGameActor:isTwoHandsLegal()
	if self:weapons()[const.EQUIP_WEAPON] ~= nil and self:weapons()[const.EQUIP_WEAPON].twoHanded then
		if self.reader.shieldIndex ~= const.EQUIP_DATA_NIL then
      		return false
      	end 
    end
    if self:weapons()[const.EQUIP_SHIELD] ~= nil and self:weapons()[const.EQUIP_SHIELD].twoHanded then
    	if self.reader.weaponIndex ~= const.EQUIP_DATA_NIL then
      		return false
      	end 
    end
    return true
end
-- ����װ���ж�
function clsGameActor:isEquippable(equip)
    if self:twoSwordsStyle() and equip.kind == const.EQUIP_SHIELD then
      	return false 
    end
	return table.include(self:vocation().equipList,equip.index)
end
-- �����
function clsGameActor:changeExp(exp, show)
    local lastLevel = self.reader.lev
    local lastSkills = self:skills()
    self.reader.exp = math.max(math.min(self.reader.exp, 9999999), 0)
    while self.reader.exp >= self:actor().expList[self.reader.lev + 1] and self:actor().expList[self.reader.lev + 1 ] > 0 do
    	self:levelUp()
    end
    while self.reader.exp < self:actor().expList[self.reader.lev] do
    	self:levelDown()
    end
    self.reader.hp = math.min(self.reader.hp, self:maxHp())
    self.reader.sp = math.min(self.reader.sp, self:maxSp())
    if show and self.reader.lev > lastLevel then
      self:displayLevelUp(table.subtract(self:skills(),lastSkills))
    end
end
-- ��
function clsGameActor:levelUp()
	self.reader.lev = self.reader.lev + 1
	for _,i in pairs(self:vocation().skillList) do
    	if globalDataSkill[i].lev <= self.reader.lev then
        	self:learnSkill(i) 
        end
    end 
end
-- ����
function clsGameActor:levelDown()
	self.reader.lev = self.reader.lev - 1
end
-- ��ʾ��ѶϢ
function clsGameActor:displayLevelUp(newSkills)
	
end
-- ��ȡ���飨��ȡ˫������ֵ�ã�
function clsGameActor:gainExp(exp, show)
	if self:enddoubleExpGain() then
		self:changeExp(self.reader.exp + exp * 2, show)
	else
		self:changeExp(self.reader.exp + exp * 2, show)
	end
end
-- ���ȼ�
function clsGameActor:changeLevel(level, show)
	level = math.max(math.min(level,99),1)
    self:changeExp(self.reader.expList[level], show)
end
-- ѧ�Ἴ��
function clsGameActor:learnSkill(skillIndex)
	if not self:isSkillLearn(globalDataManager:getModel(const.DATA_MANAGER_SKILL,skillIndex)) then
		table.insert(self.reader.skills,skillIndex)
	end
	table.sort(self.reader.skills)
end
-- ������
function clsGameActor:forgetSkill(skillIndex)
	table.delete(self.reader.skills,skillIndex)
end
-- 是否已学习技能
function clsGameActor:isSkillLearn(skill)
	return table.include(self.reader.skills,skill:getIndex())
end
-- ���ü����ж�
function clsGameActor:isSkillCanUse(skill)
	if not self:isSkillLearn(skill) then
		return false
	end
	return self:isSkillCanUseSuper(skill)
end
-- ������
function clsGameActor:setName(name)
	self.reader.name = name
end
-- ���ְҵ ID
function clsGameActor:setVocationIndex(vocationIndex)
	self.reader.vocationIndex = vocationIndex
end
-- ���ͼ��
function clsGameActor:setGraphic(charImg,headImg)
	self.reader.charImg = charImg
	self.reader.headImg = headImg
end
-- ʹ�û���ж�
function clsGameActor:isUseSprite()
	return false
end
-- ִ�е���
function clsGameActor:performCollapse()
	if globalGameTemp.inBattle and self:isDead() then
		self.collapse = true
	end
end
-- ִ���Զ��ظ����غ�����ã�
function clsGameActor:doAutoRecovery()
	if self:autoHpRecover() and not isDead() then
      self.reader.hp = self.reader.hp + self:maxHp() / 20
    end
end
-- ���ս���ж����Զ�ս���ã�
function clsGameActor:makeAction()
	self.reader.action:clear()
	if not isMovable() then
    	return 
    end
    local actionList = {}
    local action = clsGameBattleAction:new(self)
    action:setAttack()
    action:evaluate()
    table.insert(actionList,action)
    for _,skill in pairs(self:skills()) do
    	action = clsGameBattleAction:new(self)
    	action.setSkill(skill.index)
    	action:evaluate()
    	table.insert(actionList,action)
    end
    local maxValue = 0
    for _,action in pairs(actionList) do
    	if action.value > maxValue then
        	self.reader.action = action
        	maxValue = action.value
    	end
    end
end
