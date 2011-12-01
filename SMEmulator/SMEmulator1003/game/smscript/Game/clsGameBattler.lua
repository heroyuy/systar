-- 处理战斗者的类,这个类作为  clsGameActor和clsGameEnemy的超级类来使用
clsGameBattler = {}
clsGameBattler.__index = clsGameBattler
function clsGameBattler:new()
	local self = {}
	setmetatable(self,clsGameBattler)
	self.reader = {
	              battleImg = "",                          -- ս战斗图
	              battleTone = 0,                          -- 战斗图色调
	              hp = 0,                                   -- 体力
	              sp = 0,                                   -- 魔力
	              action = clsGameBattleAction:new(self),   -- ս动作
	              skipped = false,                          -- 行动结果：跳过标志
				  missed = false,                           -- 行动结果：落空标志
				  evaded = false,                           -- 行动结果：闪躲标志
                  critical = false,                         -- 行动结果：会心一击标志
                  absorbed = false,                         -- 行动结果：吸收标志
                  hpDamage = 0,                             -- 行动结果：体力伤害标志
                  spDamage = 0,                             -- 行动结果：魔力伤害标志
				  buffs = {},                               -- 状态（index数组）
				  }
	self.hidden = false       -- 隐藏标志
	self.immortal = false     -- 不死身标志
	self.aniIndex = -1        -- 动画Inedex
	self.aniMirror = false    -- 动画纵向翻转标志
	self.whiteFlash = false   -- 白色屏幕闪烁标志
	self.blink = false        -- 闪烁标志
	self.collapse = false     -- 倒下标志
	self.buffLast = {}        -- 状态剩馀回合（哈希表）
	self:clearExtraValues()
    self:clearSpriteEffects()
    self:clearActionResults()
	return self
end
-- 获取战斗图
function clsGameBattler:battleImg()
	return ".\\game\\image\\battler\\"..self.reader.battleImg
end
-- 获取战斗图色调
function clsGameBattler:battleTone()
	return self.reader.battleTone
end
-- 获取体力
function clsGameBattler:hp()
	return self.reader.hp
end
-- 获取魔力
function clsGameBattler:sp()
	return self.reader.sp
end
-- 获取动作
function clsGameBattler:action()
	return self.reader.action
end
-- 跳过标志
function clsGameBattler:skipped()
	return self.reader.skipped
end
-- 落空标志
function clsGameBattler:missed()
	return self.reader.missed
end
-- 闪避标志
function clsGameBattler:evaded()
	return self.reader.evaded
end
-- 会心一击标志
function clsGameBattler:critical()
	return self.reader.critical
end
-- 吸收标志
function clsGameBattler:absorbed()
	return self.reader.absorbed
end
-- 获取体力伤害
function clsGameBattler:hpDamage()
	return self.reader.hpDamage
end
-- 获取魔力伤害
function clsGameBattler:spDamage()
	return self.reader.spDamage
end
-- 清除特别数值
function clsGameBattler:clearExtraValues()
	self.maxHpPlus = 0
    self.maxSpPlus = 0
    self.atkPlus = 0
    self.defPlus = 0
    self.strPlus = 0
    self.intPlus = 0
    self.agiPlus = 0
    self.vitPlus = 0
    self.dexPlus = 0
    self.lucPlus = 0
    self.matkPlus = 0
    self.mdefPlus = 0
end
-- 清除精灵效果
function clsGameBattler:clearSpriteEffects()
	self.aniIndex = -1        
	self.aniMirror = false    
	self.whiteFlash = false   
	self.blink = false        
	self.collapse = false     
end
-- 清除行动结果
function clsGameBattler:clearActionResults()
	self.reader.skipped = false                        
	self.reader.missed = false                          
	self.reader.evaded = false                           
    self.reader.critical = false                        
    self.reader.absorbed = false                         
    self.reader.hpDamage = 0                            
    self.reader.spDamage = 0
    self.reader.addedBuffs = {}              -- 附加状态数组
    self.reader.removedBuffs = {}            -- 移除状态数组
    self.reader.remainedBuffs = {}           -- 剩余状态数组                           
end
-- 状态
function clsGameBattler:buffs()
	local result = {}
    for k,v in pairs(self.reader.buffs) do
    	result[k] = globalDataManager:getModel(const.DATA_MANAGER_BUFF,v)
    end
    return result
end
-- 附加状态
function clsGameBattler:addedBuffs()
	local result = {}
    for k,v in pairs(self.reader.addedBuffs) do
    	result[k] = globalDataManager:getModel(const.DATA_MANAGER_BUFF,v)
    end
    return result
end
-- 去除状态
function clsGameBattler:removedBuffs()
	print("获取移除状态开始")
	local result = {}
    for k,v in pairs(self.reader.removedBuffs) do
    	print(k,v,"xx")
    	result[k] = globalDataManager:getModel(const.DATA_MANAGER_BUFF,v)
    end
    print("获取移除状态结束")
    return result
end
-- 剩余状态
function clsGameBattler:remainedBuffs()
	local result = {}
    for k,v in pairs(self.reader.remainedBuffs) do
    	result[k] = globalDataManager:getModel(const.DATA_MANAGER_BUFF,v)
    end
    return result
end
-- 状态是否可用
function clsGameBattler:isBuffsActive()
	if #self.reader.addedBuffs == 0 then
		return true
	end
	if #self.reader.removedBuffs == 0 then 
		return true
	end
	if #self.reader.remainedBuffs == 0 then
		return true 
	end
	return false
end
-- 体力最大值
function clsGameBattler:maxHpLimit()
	return 999999
end
-- 最大体力 
function clsGameBattler:maxHp()
	return math.min(math.max(math.floor(self:baseMaxHp() + self.maxHpPlus,1)),self:maxHpLimit())
end
-- 最大魔力
function clsGameBattler:maxSp()
	return math.min(math.max(math.floor(self:baseMaxSp() + self.maxSpPlus,1)),9999)
end
-- 攻击力
function clsGameBattler:atk()
	local n = math.min(math.max(self:baseAtk()+self.atkPlus,1),999)
	local base = math.min(math.max(self:baseAtk()+self.atkPlus,1),999)
    for _,buff in pairs(self:buffs()) do 
    	if buff:atk() ~= nil and buff:type() == const.BUFF_TYPE_LAST then -- 状态为持续
    		if buff:lastType() == const.BUFF_LASTTYPE_SINGLE then -- 单次持续
    			if buff:atk():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:atk():value()
    			end
    			if buff:atk():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:atk():value() / 100
    			end
    			if buff:atk():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:atk():value() / 100
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TIME then  -- 时间持续
    			if not self:removeBuffsAutoTime(buff) then
    				if buff:atk():rule() == const.BUFF_RULETYPE_VALUE then
    					n = n + buff:atk():value() * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:atk():rule() == const.BUFF_RULETYPE_TPERCENT then
    					n = n + base * buff:atk():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:atk():rule() == const.BUFF_RULETYPE_CPERCENT then
    					n = n + n * buff:atk():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    			end
    		end
    		if buff:lastType() == 1 then  -- 回合持续
    			if buff:atk():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:atk():value() * self.buffLast[buff.index]
    			end
    			if buff:atk():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:atk():value() / 100 * self.buffLast[buff.index]
    			end
    			if buff:atk():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:atk():value() / 100 * self.buffLast[buff.index]
    			end
    		end
    	end
    end
    if self:isActor() then
    	n = n + self:equipAtk()
    end
    n = math.min(math.max(math.floor(n),1),999)
    return n
end
-- 防御力
function clsGameBattler:def()
	local n = math.min(math.max(self:baseDef()+self.defPlus,1),999)
	local base = math.min(math.max(self:baseDef()+self.defPlus,1),999)
    for _,buff in pairs(self:buffs()) do 
    	if buff:def() ~= nil and buff:type() == const.BUFF_TYPE_LAST then -- 持续类型
    		if buff:lastType() == const.BUFF_LASTTYPE_SINGLE then -- 单次持续
    			if buff:def():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:def():value()
    			end
    			if buff:def():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:def():value() / 100
    			end
    			if buff:def():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:def():value() / 100
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TIME then  -- 时间持续
    			if not self:removeBuffsAutoTime(buff) then
    				if buff:def():rule() == const.BUFF_RULETYPE_VALUE then
    					n = n + buff:def():value() * (os.time - self.buffLast[buff.index])/const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:def():rule() == const.BUFF_RULETYPE_TPERCENT then
    					n = n + base * buff:def():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:def():rule() == const.BUFF_RULETYPE_CPERCENT then
    					n = n + n * buff:def():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TURN then  -- 回合持续
    			if buff:def():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:def():value() * self.buffLast[buff.index]
    			end
    			if buff:def():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:def():value() / 100 * self.buffLast[buff.index]
    			end
    			if buff:def():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:def():value() / 100 * self.buffLast[buff.index]
    			end
    		end
    	end
    end
    if self:isActor() then
    	n = n + self:equipDef()
    end
    n = math.min(math.max(math.floor(n),1),999)
    return n
end
-- 基本魔攻
function clsGameBattler:baseMatk()
	local n = 0
	n = (self:inte()/10)^2+sele:inte()
	return n
end
-- 获取魔攻
function clsGameBattler:matk()
	local n = math.min(math.max(self:baseMatk()+self.matkPlus,1),999)
	local base = math.min(math.max(self:baseMatk()+self.matkPlus,1),999)
    for _,buff in pairs(self:buffs()) do 
    	if buff:matk() ~= nil and buff:type() == const.BUFF_TYPE_LAST then -- 持续类型
    		if buff:lastType() == const.BUFF_LASTTYPE_SINGLE then -- 单次持续
    			if buff:matk():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:matk():value()
    			end
    			if buff:matk():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:matk():value() / 100
    			end
    			if buff:matk():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:matk():value() / 100
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TIME then  -- 时间持续
    			if not self:removeBuffsAutoTime(buff) then
    				if buff:matk():rule() == const.BUFF_RULETYPE_VALUE then
    					n = n + buff:matk():value() * (os.time - self.buffLast[buff.index])/const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:matk():rule() == const.BUFF_RULETYPE_TPERCENT then
    					n = n + base * buff:matk():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:matk():rule() == const.BUFF_RULETYPE_CPERCENT then
    					n = n + n * buff:matk():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TURN then  -- 回合持续
    			if buff:matk():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:matk():value() * self.buffLast[buff.index]
    			end
    			if buff:matk():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:matk():value() / 100 * self.buffLast[buff.index]
    			end
    			if buff:matk():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:matk():value() / 100 * self.buffLast[buff.index]
    			end
    		end
    	end
    end
    if self:isActor() then
    	n = n + self:euqipMatk()
    end
    n = math.min(math.max(math.floor(n),1),999)
    return n
end
-- 基本魔防
function clsGameBattler:baseMdef()
	local n = 0
	n = self:inte() * 0.8
	return n
end
-- 获取魔防
function clsGameBattler:matk()
	local n = math.min(math.max(self:baseMdef()+self.mdefPlus,1),999)
	local base = math.min(math.max(self:baseMdef()+self.mdefPlus,1),999)
    for _,buff in pairs(self:buffs()) do 
    	if buff:mdef() ~= nil and buff:type() == const.BUFF_TYPE_LAST then -- 持续类型
    		if buff:lastType() == const.BUFF_LASTTYPE_SINGLE then -- 单次持续
    			if buff:mdef():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:matk():value()
    			end
    			if buff:mdef():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:matk():value() / 100
    			end
    			if buff:mdef():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:matk():value() / 100
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TIME then  -- 时间持续
    			if not self:removeBuffsAutoTime(buff) then
    				if buff:mdef():rule() == const.BUFF_RULETYPE_VALUE then
    					n = n + buff:matk():value() * (os.time - self.buffLast[buff.index])/const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:mdef():rule() == const.BUFF_RULETYPE_TPERCENT then
    					n = n + base * buff:matk():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:mdef():rule() == const.BUFF_RULETYPE_CPERCENT then
    					n = n + n * buff:matk():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TURN then  -- 回合持续
    			if buff:mdef():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:matk():value() * self.buffLast[buff.index]
    			end
    			if buff:mdef():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:matk():value() / 100 * self.buffLast[buff.index]
    			end
    			if buff:mdef():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:matk():value() / 100 * self.buffLast[buff.index]
    			end
    		end
    	end
    end
    if self:isActor() then
    	n = n + self:euqipMdef()
    end
    n = math.min(math.max(math.floor(n),1),999)
    return n
end
-- 获取力量
function clsGameBattler:stre()
	local n = math.min(math.max(self:baseStr()+self.strPlus,1),999)
	local base = math.min(math.max(self:baseStr()+self.strPlus,1),999)
    for _,buff in pairs(self:buffs()) do 
    	if buff:stre() ~= nil and buff:type() == const.BUFF_TYPE_LAST then -- ״̬Ϊ������
    		if buff:lastType() == const.BUFF_LASTTYPE_SINGLE then -- ��������Ϊ����
    			if buff:stre():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:stre():value()
    			end
    			if buff:stre():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:stre():value() / 100
    			end
    			if buff:stre():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:stre():value() / 100
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TIME then  --��������Ϊʱ�����
    			if not self:removeBuffsAutoTime(buff) then
    				if buff:stre():rule() == const.BUFF_RULETYPE_VALUE then
    					n = n + buff:stre():value() * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:stre():rule() == const.BUFF_RULETYPE_TPERCENT then
    					n = n + base * buff:stre():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:stre():rule() == const.BUFF_RULETYPE_CPERCENT then
    					n = n + n * buff:stre():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL 
    				end
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TURN then  -- ��������Ϊ�غϳ���
    			if buff:stre():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:stre():value() * self.buffLast[buff.index]
    			end
    			if buff:stre():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:stre():value() / 100 * self.buffLast[buff.index]
    			end
    			if buff:stre():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:stre():value() / 100 * self.buffLast[buff.index]
    			end
    		end
    	end
    end
    n = math.min(math.max(math.floor(n),1),999)
    return n
end
-- �������
function clsGameBattler:inte()
	local n = math.min(math.max(self:baseInt()+self.intPlus,1),999)
	local base = math.min(math.max(self:baseInt()+self.intPlus,1),999)
    for _,buff in pairs(self:buffs()) do 
    	if buff:inte() ~= nil and buff:type() == const.BUFF_TYPE_LAST then -- ״̬Ϊ������
    		if buff:lastType() == const.BUFF_LASTTYPE_SINGLE then -- ��������Ϊ����
    			if buff:inte():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:inte():value()
    			end
    			if buff:inte():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:inte():value() / 100
    			end
    			if buff:inte():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:inte():value() / 100
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TIME then  --��������Ϊʱ�����
    			if not self:removeBuffsAutoTime(buff) then
    				if buff:inte():rule() == const.BUFF_RULETYPE_VALUE then
    					n = n + buff:inte():value() * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:inte():rule() == const.BUFF_RULETYPE_TPERCENT then
    					n = n + base * buff:inte():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:inte():rule() == const.BUFF_RULETYPE_CPERCENT then
    					n = n + n * buff:inte():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL 
    				end
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TURN then  -- ��������Ϊ�غϳ���
    			if buff:inte():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:inte():value() * self.buffLast[buff.index]
    			end
    			if buff:inte():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:inte():value() / 100 * self.buffLast[buff.index]
    			end
    			if buff:inte():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:inte():value() / 100 * self.buffLast[buff.index] 
    			end
    		end
    	end
    end
    n = math.min(math.max(math.floor(n),1),999)
    return n
end
-- �������
function clsGameBattler:agil()
	local n = math.min(math.max(self:baseAgi()+self.agiPlus,1),999)
	local base = math.min(math.max(self:baseAgi()+self.agiPlus,1),999)
    for _,buff in pairs(self:buffs()) do 
    	if buff:agil() ~= nil and buff:type() == const.BUFF_TYPE_LAST then -- ״̬Ϊ������
    		if buff:lastType() == const.BUFF_LASTTYPE_SINGLE then -- ��������Ϊ����
    			if buff:agil():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:agil():value()
    			end
    			if buff:agil():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:agil():value() / 100
    			end
    			if buff:agil():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:agil():value() / 100
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TIME then  --��������Ϊʱ�����
    			if not self:removeBuffsAutoTime(buff) then
    				if buff:agil():rule() == const.BUFF_RULETYPE_VALUE then
    					n = n + buff:agil():value() * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:agil():rule() == const.BUFF_RULETYPE_TPERCENT then
    					n = n + base * buff:agil():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:agil():rule() == const.BUFF_RULETYPE_CPERCENT then
    					n = n + n * buff:agil():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL 
    				end
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TURN then  -- ��������Ϊ�غϳ���
    			if buff:agil():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:agil():value() * self.buffLast[buff.index]
    			end
    			if buff:agil():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:agil():value() / 100 * self.buffLast[buff.index]
    			end
    			if buff:agil():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:agil():value() / 100 * self.buffLast[buff.index]
    			end
    		end
    	end
    end
    n = math.min(math.max(math.floor(n),1),999)
    return n
end
-- �������
function clsGameBattler:vita()
	local n = math.min(math.max(self:baseVit()+self.vitPlus,1),999)
	local base = math.min(math.max(self:baseVit()+self.vitPlus,1),999)
    for _,buff in pairs(self:buffs()) do 
    	if buff:vita() ~= nil and buff:type() == const.BUFF_TYPE_LAST then -- ״̬Ϊ������
    		if buff:lastType() == const.BUFF_LASTTYPE_SINGLE then -- ��������Ϊ����
    			if buff:vita():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:vita():value()
    			end
    			if buff:vita():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:vita():value() / 100
    			end
    			if buff:vita():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:vita():value() / 100
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TIME then  --��������Ϊʱ�����
    			if not self:removeBuffsAutoTime(buff) then
    				if buff:vita():rule() == const.BUFF_RULETYPE_VALUE then
    					n = n + buff:vita():value() * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:vita():rule() == const.BUFF_RULETYPE_TPERCENT then
    					n = n + base * buff:vita():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:vita():rule() == const.BUFF_RULETYPE_CPERCENT then
    					n = n + n * buff:vita():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL 
    				end
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TURN then  -- ��������Ϊ�غϳ���
    			if buff:vita():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:vita():value() * self.buffLast[buff.index]
    			end
    			if buff:vita():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:vita():value() / 100 * self.buffLast[buff.index]
    			end
    			if buff:vita():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:vita():value() / 100 * self.buffLast[buff.index]
    			end
    		end
    	end
    end
    n = math.min(math.max(math.floor(n),1),999)
    return n
end
-- �������
function clsGameBattler:dext()
	local n = math.min(math.max(self:baseDex()+self.dexPlus,1),999)
	local base = math.min(math.max(self:baseDex()+self.dexPlus,1),999)
    for _,buff in pairs(self:buffs()) do 
    	if buff:dext() ~= nil and buff:type() == const.BUFF_TYPE_LAST then -- ״̬Ϊ������
    		if buff:lastType() == const.BUFF_LASTTYPE_SINGLE then -- ��������Ϊ����
    			if buff:dext():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:dext():value()
    			end
    			if buff:dext():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:dext():value() / 100
    			end
    			if buff:dext():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:dext():value() / 100
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TIME then  --��������Ϊʱ�����
    			if not self:removeBuffsAutoTime(buff) then
    				if buff:dext():rule() == const.BUFF_RULETYPE_VALUE then
    					n = n + buff:dext():value() * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:dext():rule() == const.BUFF_RULETYPE_TPERCENT then
    					n = n + base * buff:dext():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:dext():rule() == const.BUFF_RULETYPE_CPERCENT then
    					n = n + n * buff:dext():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL 
    				end
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TURN then  -- ��������Ϊ�غϳ���
    			if buff:dext():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:dext():value() * self.buffLast[buff.index]
    			end
    			if buff:dext():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:dext():value() / 100 * self.buffLast[buff.index]
    			end
    			if buff:dext():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:dext():value() / 100 * self.buffLast[buff.index]
    			end
    		end
    	end
    end
    n = math.min(math.max(math.floor(n),1),999)
    return n
end
-- �������
function clsGameBattler:luck()
	local n = math.min(math.max(self:baseLuc()+self.lucPlus,1),999)
	local base = math.min(math.max(self:baseLuc()+self.lucPlus,1),999)
    for _,buff in pairs(self:buffs()) do 
    	if buff:luck() ~= nil and buff:type() == const.BUFF_TYPE_LAST then -- ״̬Ϊ������
    		
    		if buff:lastType() == const.BUFF_LASTTYPE_SINGLE then -- ��������Ϊ����
    			if buff:luck():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:luck():value()
    			end
    			if buff:luck():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:luck():value() / 100
    			end
    			if buff:luck():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:luck():value() / 100
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TIME then  --��������Ϊʱ�����
    			if not self:removeBuffsAutoTime(buff) then
    				if buff:luck():rule() == const.BUFF_RULETYPE_VALUE then
    					n = n + buff:luck():value() * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:luck():rule() == const.BUFF_RULETYPE_TPERCENT then
    					n = n + base * buff:luck():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL
    				end
    				if buff:luck():rule() == const.BUFF_RULETYPE_CPERCENT then
    					n = n + n * buff:luck():value() / 100 * (os.time - self.buffLast[buff.index]) / const.BUFF_TIMELAST_INTERVAL 
    				end
    			end
    		end
    		if buff:lastType() == const.BUFF_LASTTYPE_TURN then  -- ��������Ϊ�غϳ���
    			if buff:luck():rule() == const.BUFF_RULETYPE_VALUE then
    				n = n + buff:luck():value() * self.buffLast[buff.index]
    			end
    			if buff:luck():rule() == const.BUFF_RULETYPE_TPERCENT then
    				n = n + base * buff:luck():value() / 100 * self.buffLast[buff.index]
    			end
    			if buff:luck():rule() == const.BUFF_RULETYPE_CPERCENT then
    				n = n + n * buff:luck():value() / 100 * self.buffLast[buff.index]
    			end
    		end
    	end
    end
    n = math.min(math.max(math.floor(n),1),999)
    return n
end
-- 获取超级防御选项
function clsGameBattler:superGuard()
	return false
end
-- 获取快速攻击选项
function clsGameBattler:fastAttack()
	return false
end
-- 获取连续攻击选项
function clsGameBattler:dualAttack()
	return false
end
-- 获取防止会心一击选项
function clsGameBattler:preventCritical()
	return false
end
-- 获取魔力消耗一般选项
function clsGameBattler:halfSpCost()
	return false
end
-- 获取药物知识
function clsGameBattler:pharmacology()
	return false
end
-- 设置 MaxHp
-- newMaxHp : 新的 MaxHP
function clsGameBattler:setMaxHp(newMaxHp)
	self.maxHpPlus = self.maxHpPlus + newMaxHp - self:maxHp()
    self.maxHpPlus = math.min(math.max(self.maxHpPlus, -9999),9999)
    self.reader.hp = math.min(self.reader.hp, self:maxHp())
end
-- 设置 MaxSP
-- newMaxSp : 新的 MaxSP
function clsGameBattler:setMaxSp(newMaxSp)
	self.maxSpPlus = self.maxSpPlus + newMaxSp - self:maxSp()
    self.maxSpPlus = math.min(math.max(self.maxSpPlus, -9999),9999)
    self.reader.sp = math.min(self.reader.sp, self:maxSp())
end
-- 设置攻击力
-- newAtk : 新的攻击力
function clsGameBattler:setAtk(newAtk)
	self.atkPlus = self.atkPlus + newAtk - self:Atk()
    self.atkPlus = math.min(math.max(self.atkPlus, -999),999)
end
-- 设置防御力
-- newDef : 新的防御力
function clsGameBattler:setDef(newAtk)
	self.defPlus = self.defPlus + newDef - self:def()
    self.defPlus = math.min(math.max(self.defPlus, -999),999)
end
-- 设置魔攻
-- newMatk : 新的魔攻
function clsGameBattler:setMatk(newMatk)
	self.matkPlus = self.matkPlus + newMatk - self:matk()
    self.matkPlus = math.min(math.max(self.matkPlus, -999),999)
end
-- 设置魔防
-- newMdef : 新的魔防
function clsGameBattler:setMdef(newAtk)
	self.mdefPlus = self.mdefPlus + newMdef - self:mdef()
    self.mdefPlus = math.min(math.max(self.mdefPlus, -999),999)
end
-- 设置力量
-- newStr : 新的力量
function clsGameBattler:setStr(newStr)
	self.strPlus = self.strPlus + newStr - self:stre()
    self.strPlus = math.min(math.max(self.strPlus, -999),999)
end
-- 设置智力
-- newInt: 新的智力
function clsGameBattler:setInt(newInt)
	self.intPlus = self.intPlus + newInt - self:inte()
    self.intPlus = math.min(math.max(self.intPlus, -999),999)
end
-- 设置敏捷
-- newAgi : 新的敏捷
function clsGameBattler:setAgi(newAgi)
	self.agiPlus = self.agiPlus + newAgi - self:agil()
    self.agiPlus = math.min(math.max(self.agiPlus, -999),999)
end
-- 设置体力
-- newVit : 新的体力
function clsGameBattler:setVit(newVit)
	self.vitPlus = self.vitPlus + newVit - self:vita()
    self.vitPlus = math.min(math.max(self.vitPlus, -999),999)
end
-- 设置灵巧
-- newDex : 新的灵巧
function clsGameBattler:setDex(newDex)
	self.dexPlus = self.dexPlus + newDex - self:dext()
    self.dexPlus = math.min(math.max(self.dexPlus, -999),999)
end
-- 设置幸运
-- newLuc : 新的幸运
function clsGameBattler:setLuc(newLuc)
	self.lucPlus = self.lucPlus + newLuc - self:luck()
    self.lucPlus = math.min(math.max(self.lucPlus, -999),999)
end
-- 设置 HP
-- hp : 新的 HP
function clsGameBattler:setHp(hp)
	self.reader.hp = math.max(math.min(hp, self:maxHp()),0)
    if self.reader.hp == 0 and not self:isBuff(0) and not self.immortal then
    	self:addBuff(0)       -- 附加死亡状态̬
    	table.insert(self.reader.addedBuffs,0)
    elseif self.reader.hp > 0 and self:isBuff(0) then
    	self:removeBuff(0)    -- 移除死亡状态̬
    	table.insert(self.reader.removedBuffs,0)
    end
end
-- 设置 SP
-- sp : 新的 SP
function clsGameBattler:setSp(sp)
	self.reader.sp = math.max(math.min(sp, self:maxSp()),0)
end
 -- 全恢复
 function clsGameBattler:recoverAll()
    self.reader.hp = self:maxHp()
	self.reader.sp = self:maxSp()
	for _,i in pairs(table.clone(self.reader.buffs)) do self:removeBuff(i) end
end
-- 是否死亡
function clsGameBattler:isDead()
	return (not self.hidden and self.reader.hp == 0 and not self.immortal)
end
-- 是否存在
function clsGameBattler:isExist()
	return (not self.hidden and not self:isDead())
end
-- 是否可以输入
function clsGameBattler:isInputable()
	return (not self.hidden and self:restriction() <= 1)
end
-- 是否可以行动
function clsGameBattler:isMovable()
	return (not self.hidden and self:restriction() < 4)
end
-- 是否可以回避
function clsGameBattler:isParriable()
	return (not self.hidden and self:restriction() < 5)
end
-- 是否沉默
function clsGameBattler:isSilent()
	return (not self.hidden and self:restriction() == 1)
end
-- 是否暴走
function clsGameBattler:isBerserker()
	return (not self.hidden and self:restriction() == 2)
end
-- 是否狂乱
function clsGameBattler:isConfusion()
	return (not self.hidden and self:restriction() == 3)
end
-- 是否防御中
function clsGameBattler:isGuarding()
	return self.reader.action:isGuard()
end
-- 获取状态修正值ֵ
function clsGameBattler:attributeRate(attributeIndex,attributeValue)
	return 100
end
-- 获取状态成功率
function clsGameBattler:buffProbability(buffIndex,buffValue)
	return 0
end
-- 状态是否被防御
function clsGameBattler:isBuffResist(buffIndex,buffValue)
	return false
end
-- 获取普通攻击状态列表
function clsGameBattler:attributes()
	return {}
end
-- 获取普通攻击状态变化(+)
function clsGameBattler:plusBuffSet()
	return {}
end
-- 获取普通攻击状态变化(-)
function clsGameBattler:minusBuffSet()
	return {}
end
-- 是否在状态中̬
function clsGameBattler:isBuff(buffIndex)
	return table.include(self.reader.buffs,buffIndex)
end
-- 状态已满
function clsGameBattler:isBuffFull(buffIndex)
	if not self:isBuff(buffIndex) then
		return false
	end
	-- 当状态持续回合等于状态最大回合
    if not globalDataManager:getModel(const.DATA_MANAGER_BUFF,buffIndex):lastTurn() ~= -1 then 
    	if self.buffLast[buffIndex] >= globalDataManager:getModel(const.DATA_MANAGER_BUFF,buffIndex):lastTurn() then
			return true
		end
	end
	-- 当状态已持续时间等于状态持续时间
	if not globalDataManager:getModel(const.DATA_MANAGER_BUFF,buffIndex):lastTime() ~= -1 then 
		if os.time() - self.buffLast[buffIndex] >= globalDataManager:getModel(const.DATA_MANAGER_BUFF,buffIndex):lastTime() then
			return true
		end
	end
	return false
end
-- �ж�״̬����
function clsGameBattler:isBuffIgnore(buffIndex)
	for _,buff in pairs(self:buffs()) do
		if buff:buff(buffIndex) ~= nil and not globalDataManager:getModel(const.DATA_MANAGER_BUFF,buffIndex):buff(buff:getIndex()) ~= nil then
			if buff:buff(buffIndex):value() < 0 then 
				return true
			end
		end
	end
	return false
end
-- �ж�״̬����
function clsGameBattler:isBuffOffset(buffIndex)
	if not globalDataManager:getModel(const.DATA_MANAGER_BUFF,buffIndex):offsetByOpposite() then
		return false
	end
	for _,i in pairs(self.reader.buffs) do
		if globalDataManager:getModel(const.DATA_MANAGER_BUFF,buffIndex):buff(i) ~= nil and globalDataManager:getModel(const.DATA_MANAGER_BUFF,buffIndex):buff(i):value() < 0 then
			return true
		end
	end
	return false
end
-- ״̬����
function clsGameBattler:sortBuffs()
	table.sort(self.reader.buffs, function (a,b)
    	local buffA = globalDataManager:getModel(const.DATA_MANAGER_BUFF,a)
    	local buffB = globalDataManager:getModel(const.DATA_MANAGER_BUFF,b)
    	if buffA:lev() ~= buffB:lev() then
        	return buffA:lev() > buffB:lev()
    	else
        	return a < b
      end
    end)
end
-- ����״̬
function clsGameBattler:addBuff(buffIndex)
	print("附加状态开始")
	local buff = globalDataManager:getModel(const.DATA_MANAGER_BUFF,buffIndex)              -- ��ȡ״̬����
	if buff == nil then                                                                     -- �����Ƿ�Ϊ�գ�
		return
	end
	if self:isBuffIgnore(buffIndex) then                                                    -- ״̬�Ƿ����ӣ�
		return
	end
	if not self:isBuff(buffIndex) then                                                      -- ״̬�Ƿ����
		if not self:isBuffOffset(buffIndex) then                                            -- ״̬�Ƿ����
			table.insert(self.reader.buffs,buffIndex)
		end
		if buffIndex == 0 then                                                -- ��״̬�ǡ��޷�ս����
			self.reader.hp = 0                                                              -- ������Ϊ0
		end
		if not self:isInputable() then                                                      -- ��ǽ�ɫ�޷���������
			self.reader.action:clear()                                                      -- ��������ж�
		end     
		print("状态附加通过")   
		for i=0,buff:plusSize() - 1 do                                             -- ��ȡ״̬�����״̬�б�
			if smRandom:nextInt(100) < math.abs(buff:plusBuff(i):value()) then                           -- Ϊ����״̬
				if not self:isBuffResist(buff:plusBuff(i):getIndex(),buff:plusBuff(i):value()) then                                            -- �ж�״̬�Ƿ���Ч
					if not self:isDead() then                                               -- �ж��Ƿ��޷�ս��
						if not (buff:plusBuff(i):getIndex() == 0 and self.immortal) then                -- �ж��Ƿ�Ϊ������
							if self:isBuff(buff:plusBuff(i):getIndex()) then
								table.insert(self.reader.remainedBuffs,buff:plusBuff(i):getIndex())
							else
								if smRandom:nextInt(100) < self:buffProbability(buff:plusBuff(i):getIndex(),buff:plusBuff(i):value()) then   -- ����״̬����
									self:addBuff(buff:plusBuff(i):getIndex())                                         -- ����״̬
									table.insert(self.reader.addedBuffs,buff:plusBuff(i):getIndex())                  -- ��¼�Ѹ���״̬
								end
							end
						end
					end
				end
			end
			for _,i in pairs(table.intersect(self.reader.addedBuffs,self.reader.removedBuffs)) do      -- ���Ӻ��Ƴ�״̬
      			table.delete(self.reader.addedBuffs,buff:plusBuff(i):getIndex())
        		table.delete(self.reader.removedBuffs,buff:plusBuff(i):getIndex())
        	end
	   end
	   for i = 0,buff:minusSize() - 1 do                                                                -- Ϊ���״̬
			if smRandom:nextInt(100) < math.abs(buff:minusBuff(i):getIndex()) then
				self:removeBuff(buff:minusBuff(i):getIndex())
				table.delete(self.reader.removedBuffs,buff:minusBuff(i):getIndex())
			end
			for _,i in pairs(table.intersect(self.reader.addedBuffs,self.reader.removedBuffs)) do      -- ���Ӻ��Ƴ�״̬
      			table.delete(self.reader.addedBuffs,buff:minusBuff(i):getIndex())
        		table.delete(self.reader.removedBuffs,buff:minusBuff(i):getIndex())
        	end
		end
		self:sortBuffs()
	end
	if buff:type() == const.BUFF_TYPE_LAST and buff:lastType() == const.BUFF_LASTTYPE_TIME then -- ��״̬Ϊʱ������ʱ��
		self.buffLast[buff.index] = os.time()
	else
		self.buffLast[buff.index] = 0
	end
	print("附加状态结束")
end
-- ���״̬
function clsGameBattler:removeBuff(buffIndex)
	print("移除状态开始")
	if not self:isBuff(buffIndex) then 
		print("移除状态结束")
		return 
	end
	if buffIndex == 0 and self.reader.hp == 0 then
		self.reader.hp = 1
	end
	table.delete(self.reader.buff,buffIndex)
	self.buffLast[buffIndex] = nil
	print("移除状态结束")
end
-- ��ȡ����
function clsGameBattler:restriction()
	local restrictionMax = 0
	for _,buff in pairs(self:buffs()) do
		if buff:restriction() >= restrictionMax then
			restrictionMax = buff:restriction()
		end
	end
	return restrictionMax
end
--  �ж�״̬ [�����˺�](�غϳ���)
function clsGameBattler:isSlipDamageTurn()
	for _,buff in pairs(self:buffs()) do
		if buff:slipDamage() and buff:type() == const.BUFF_TYPE_LAST and buff:lastType() == const.BUFF_LASTTYPE_TURN then
			return true
		end
	end
	return false
end
-- �ж�״̬[�����˺�](ʱ�����)
function clsGameBattler:isSlipDamageTime()
	for _,buff in pairs(self:buffs()) do
		if buff:slipDamage() and buff:type() == const.BUFF_TYPE_LAST and buff:lastType() == const.BUFF_LASTTYPE_TIME then
			return true
		end
	end
	return false
end
-- �ж�״̬ [����������]
function clsGameBattler:isReduceHitRatio()
	for _,buff in pairs(self:buffs()) do
		if buff:reduceHitRatio() then
			return true
		end
	end
	return false
end
-- ���ս����״̬ (ս������ʱ����)
function clsGameBattler:removeBuffsBattle()
	
	for _,buff in pairs(self:buffs()) do
		if buff:battleOnly() then
			self:removeBuff(buff.index)
		end
	end
end
-- ״̬�غ���Ȼ��� (�غϸı�ʱ����)
function clsGameBattler:removeBuffsAutoTurn()
	self:clearActionResults()
	for k in pairs(table.clone(self.buffLast)) do
    	if globalDataManager:getModel(const.DATA_MANAGER_BUFF,k):lastTurn() ~= -1 then
      		if self.buffLast[k] < globalDataManager:getModel(const.DATA_MANAGER_BUFF,k):lastTurn() then
      			self.buffLast[k] = self.buffLast[k] + 1
      		else
      			if smRandom:nextInt(100) < globalDataManager:getModel(const.DATA_MANAGER_BUFF,k):autoReleaseProb() then
      				self:removeBuff(k)
      				table.insert(self.reader.removedBuffs,k)
      			else
      				self.buffLast[k] = self.buffLast[k] + 1
      			end
      		end 
    	end
    end
end
-- ״̬ʱ����Ȼ��� (���Ըı�ʱ����)
function clsGameBattler:removeBuffsAutoTime(buff)
	if buff:lastTime() ~= nil then
    	if os.time() - self.buffLast[buff.index] > buff:lastTime() then
    		if smRandom:nextInt(100) < buff:autoReleaseProb() then
    			self:removeBuff(buff:getIndex())
      			table.insert(self.reader.removedBuffs,buff:getIndex())
      			return true
    		end
    	end
    end
    return false
end
-- ״̬������� (�ܵ������˺�ʱ����)
function clsGameBattler:removeBuffsShock()
	print("移除攻击状态开始")
	for _,buff in pairs(self:buffs()) do
		if buff:releaseByDamage() then
			self:removeBuff(buff:getIndex())
      		table.insert(self.reader.removedBuffs,buff:getIndex())
		end
	end
	print("移除攻击状态结束")
end
-- ���㼼�����ħ��
function clsGameBattler:calcSpCost(skill)
	if self:halfSpCost() then
		return skill:spCost() / 2
	else
		return skill:spCost()
	end
end
-- 判断技能是否可以使用
function clsGameBattler:isSkillCanUse(skill)
	if skill:isA() ~= "Skill" then
		return false
	end
	if not self:isMovable() then
		return false
	end
	if self:isSilent() and skill.intF > 0 then
		return false
	end
	if self:calcSpCost(skill) > self.reader.sp then
		return false
	end
	if globalGameTemp.inBattle then
      	return skill:isBattleOk()
    else
      	return skill:isMenuOk()
    end
end
-- ��������������
function clsGameBattler:calcHit(user, obj)
	local hit = 0
	local physical = false
	if obj == nil then                          -- ��ͨ����
    	hit = user:hit()                        -- ��ȡ������
    	physical = true
    elseif obj:isA() == "Skill" then   -- ���ܹ���
    	hit = obj:hit()                           -- ��ȡ������
    	physical = obj:physicalAttack()
    else                                        -- ��Ʒ����
    	hit = 100                               -- ��������Ϊ100��
    	physical = obj:physicalAttack()
    end
    if physical then                            -- ���?��
    	if user:isReduceHitRatio() then         -- ���˰���״̬ʱ�����ʽ���Ϊ25��
    		hit = hit / 4 
    	end
    end
    return hit
end
-- ��������������
function clsGameBattler:calcEva(user, obj)
	local eva = self:eva()
    if not obj == nil then                      -- ��objΪ��Ʒ����
    	if not obj.physicalAttack then          -- �����?��ʱ������ 0%
        	eva = 0 
        end
    end
    if not self:isParriable() then              -- �޷�����ĳ���
      eva = 0                                   -- ������Ϊ 0%
    end
    return eva
end
-- ������ͨ�����˺�
function clsGameBattler:makeAttackDamageValue(attacker)
	local atk = math.max(attacker:atk() - self:def() / 2, 1)
	local damage = atk * (20 + attacker:stre()) / 20                          -- �����
	if damage < 0 then                                                        -- �踺���˺�Ϊ 0
    	damage = 0 
    end
    damage = damage * self:attributesMaxRate(attacker)              -- ����У��
    damage = damage / 100
    if damage == 0 then                                                       -- ���˺�Ϊ 0
    	damage = smRandom:nextInt(2)                                          -- һ������˺�Ϊ1
    elseif damage > 0 then                                                    -- ���˺�Ϊ����
    	self.reader.critical = (smRandom:nextInt(100) < attacker:cri())       -- ����һ���ж�
    	if self:preventCritical() then                                        -- ��ֹ����һ���ж�
    		self.reader.critical = false 
    	end
    	if self.reader.critical then                                          -- ����һ��У��
    		damage = damage * 3 
    	end
    end
    damage = self:applyVariance(damage, 20)                                   -- ��ɢ��
    damage = self:applyGuard(damage)                                          -- ����У��
    self.reader.hpDamage = math.floor(damage)                                             -- �����˺�
end
-- ���㼼��/��Ʒ�˺�
function clsGameBattler:makeObjDamageValue(user, obj)
    local damage = obj:baseDamage()                                             -- �����
    if damage > 0 then                                                        -- ���˺�Ϊ��
    	damage = damage + user:atk() * 4 * obj:atkF() / 100                     -- ʹ���߹�������ϵ��
    	damage = damage + user:inte() * 2 * obj:intF() / 100                    -- ʹ���߾�������ϵ��
    	if not obj.ignoreDefense then                                         -- ������ӷ�����
    		damage = damage - self:def() * 2 * obj:atkF() / 100                 -- Ŀ�깥������ϵ��
        	damage = damage - self:inte() * 1 * obj:intF() / 100                -- Ŀ�꾫������ϵ��
    	end
    	if damage < 0 then                                                    -- �踺���˺�Ϊ0
    		damage = 0 
    	end
    elseif damage < 0 then                                                    -- ���˺�Ϊ��
    	damage = damage - user:atk() * 4 * obj:atkF() / 100                     -- ʹ���߹�������ϵ��
    	damage = damage - user:inte() * 2 * obj:intF() / 100                    -- ʹ���߾�������ϵ��
    end
    damage = damage * self:attributesMaxRate(obj)                       -- ����У��
    damage = damage / 100
    damage = self:applyVariance(damage,obj:variance())                              -- ��ɢ��
    damage = self:applyGuard(damage)                                               -- ����У��
    if obj:damageToSp() then 
    	self.reader.spDamage = math.floor(damage)                                         -- �˺�ħ��
    else
    	self.reader.hpDamage = math.floor(damage)                                         -- �˺�����
    end
end 
-- ��������Ч��
function clsGameBattler:makeObjAbsorbEffect(user, obj)
	if obj:absorbDamage() then                                                     -- ���������˺���
    	self.reader.hpDamage = math.min(self.reader.hp, self.reader.hpDamage)    -- �����˺���ΧУ��
    	self.reader.spDamage = math.min(self.reader.sp, self.reader.spDamage)    -- ħ���˺���ΧУ��
    	if self.reader.hpDamage > 0 or self.reader.spDamage > 0 then             -- �˺�Ϊ����
    		self.reader.absorbed = true                                                 -- �����ձ�־
    	end
    end
end
-- ����ʹ����Ʒ�������ظ���
function clsGameBattler:calcHpRecovery(user, item)
	local result = self:maxHp() * item:hpRecoveryRate() / 100 + item:hpRecovery()
	if user:pharmacology() then    -- ��ҩ��֪ʶ��Ч��ӳ�2��
    	result = result * 2 
    end
    return result
end
-- ����ʹ����Ʒ��ħ���ظ���
function clsGameBattler:calcSpRecovery(user, item)
	local result = self:maxSp() * item:spRecoveryRate() / 100 + item:spRecovery()
	if user:pharmacology() then    -- ��ҩ��֪ʶ��Ч��ӳ�2��
    	result = result * 2 
    end
    return result
end
-- ��ȡ������Ե�����
function clsGameBattler:attributesMaxRate(obj)
	local rateList
	if getmetatable(getmetatable(obj)) == clsGameBattler then
		if #obj:weapons() == 0 then
			return 100
		end
		rateList = {}
		for _,weapon in pairs(self:weapons()) do
			for i = 0,weapon:attributesSize()- 1 do
				if weapon:attribute(i):getIndex() > 23 then
					table.insert(rateList,self:attributeRate(weapon:attribute(i):getIndex(),weapon:attribute(i):value()))
				end
			end
		end
		if #rateList == 0 then
			return 100
		end
	else
		if obj:attributesSize() == 0 then                -- �ж��Ƿ��������б�
			return 100 
		end
    	rateList = {}
    	for i = 0,obj:attributesSize()-1 do
    		if obj:attribute(i):getIndex() > 23 then
    			table.insert(rateList,self:attributeRate(obj:attribute(i):getIndex(),obj:attribute(i):value()))
    		end
    	end
    end
    return table.maxValues(rateList)
end
-- �����ɢ��
function clsGameBattler:applyVariance(damage, variance)
	if damage ~= 0 then                                                           -- ���˺���Ϊ0
		local amp = math.max(math.abs(damage) * variance / 100, 0)                -- ���㼫��
        damage = damage + smRandom:nextInt(amp+1) + smRandom:nextInt(amp+1) - amp -- ִ�з�ɢ��
	end
	damage = math.floor(damage)
	return damage
end
-- �������У��
function clsGameBattler:applyGuard(damage)
	if damage > 0 and self:isGuarding() then                            -- �ж��Ƿ������
        damage = damage / ((self:superGuard() and 4) or 2)              -- �����˺�
    end
    return damage
end
-- �˺�Ч��
function clsGameBattler:executeDamage(user)
	if self.reader.hpDamage > 0 then          -- ���˺�Ϊ����
      self:removeBuffsShock()                -- �����Ƴ�״̬
    end
    self:setHp(self:hp() - self.reader.hpDamage)
    self:setSp(self:sp() - self.reader.spDamage)
    if self:absorbed() then                     -- ������
    	user:setHp(self:hp() + self.reader.hpDamage)
    	user:setSp(self:sp() + self.reader.spDamage)
    end
end
-- ����״̬�仯
function clsGameBattler:applyBuffChanges(obj)
	if getmetatable(getmetatable(obj)) == clsGameBattler then
		if #obj:weapons() == 0 then
			return 
		end
		for _,weapon in pairs(obj:weapons()) do
    		for i = 0,weapon:plusSize()-1 do                                                     -- ״̬�仯(+)
    			if not self:isBuffResist(weapon:plusBuff(i):getIndex(),weapon:plusBuff(i):value()) then                                         -- �ж�״̬�Ƿ���Ч
    				if not self:isDead() then                                              -- �ж��Ƿ��޷�ս��
    					if not (weapon:plusBuff(i):getIndex() == 0 and self.immortal) then               -- �ж��Ƿ�Ϊ������
    						if self:isBuff() then
    							table.insert(self.reader.remainedBuffs,weapon:plusBuff(i):getIndex())                  -- ��¼Ϊ���״̬
    						else
    							if smRandom:nextInt(100) < self:buffProbability(weapon:plusBuff(i):getIndex(),weapon:plusBuff(i):value()) then  -- ����״̬����
        							self:addBuff(weapon:plusBuff(i):getIndex())                                        -- ����״̬
       								table.insert(self.reader.addedBuffs,weapon:plusBuff(i):getIndex())                 -- ��¼�Ѹ���״̬
      							end
    						end
    					end
    				end
    			end
    			for i = 0,weapon:minusSize()-1 do                                                 -- ״̬�仯(-)
    				if self:isBuff(weapon:plusBuff(i):getIndex()) then                                                 -- �ж�״̬�Ƿ��Ѵ���
      					self:removeBuff(weapon:plusBuff(i):getIndex())                                                 -- �Ƴ�״̬
      					table.insert(self.reader.removedBuffs,weapon:plusBuff(i):getIndex())                           -- ��¼���Ƴ�״̬
      				end
    			end
    		end  
    	end              
    else
    	for i = 0,obj:plusSize()-1 do                                                  -- ״̬�仯(+)
    		print(obj:plusBuff(i):getIndex(),obj:plusBuff(i):value(),"附加状态索引及成功率")
    		if not self:isBuffResist(obj:plusBuff(i):getIndex(),obj:plusBuff(i):value()) then                                         -- �ж�״̬�Ƿ���Ч
    			if not self:isDead() then                                              -- �ж��Ƿ��޷�ս��
    				if not (obj:plusBuff(i):getIndex() == 0 and self.immortal) then               -- �ж��Ƿ�Ϊ������
    					if self:isBuff(obj:plusBuff(i):getIndex()) then
    						table.insert(self.reader.remainedBuffs,obj:plusBuff(i):getIndex())                  -- ��¼Ϊ���״̬
    					else
    						if smRandom:nextInt(100) < self:buffProbability(obj:plusBuff(i):getIndex(),obj:plusBuff(i):value()) then  -- ����״̬����
        						self:addBuff(obj:plusBuff(i):getIndex())                                        -- ����״̬
       							table.insert(self.reader.addedBuffs,obj:plusBuff(i):getIndex())                 -- ��¼�Ѹ���״̬
      						end
    					end
    				end
    			end
    		end
    	end 
    	for i = 0,obj:minusSize()-1 do                                                 -- ״̬�仯(-)
    	print(obj:plusBuff(i):getIndex(),obj:plusBuff(i):value(),"移除状态索引及成功率")
    		if self:isBuff(obj:plusBuff(i):getIndex()) then    
      			self:removeBuff(obj:plusBuff(i):getIndex())                                                 -- �Ƴ�״̬
      			table.insert(self.reader.removedBuffs,obj:plusBuff(i):getIndex())                           -- ��¼���Ƴ�״̬
      		end
    	end                                                -- ��ȡ״̬�仯(-)
    end
    for _,i in pairs(table.intersect(self.reader.addedBuffs,self.reader.removedBuffs)) do -- ���Ӻ��Ƴ�״̬
      	table.delete(self.reader.addedBuffs,i)
        table.delete(self.reader.removedBuffs,i)
    end
    print("状态变更结束")
end
-- �ж���ͨ�����Ƿ��Ч
function clsGameBattler:isAttackEffective(attacker)
	if self:isDead() then
		return false
	end
	return true
end
-- ������ͨ����
function clsGameBattler:attackEffect(attacker)
	self:clearActionResults()
    if not self:isAttackEffective(attacker) then
    	self.reader.skipped = true
    	return
    end
    if smRandom:nextInt(100) >= self:calcHit(attacker) then           -- ����������
    	self.reader.missed = true
    	return
    end
    if smRandom:nextInt(100) < self:calcEva(attacker) then            -- ����������
    	self.reader.evaded = true
    	return
    end
    self:makeAttackDamageValue(attacker)                              -- �����˺�
    self:executeDamage(attacker)                                      -- �˺�Ч��
    if self.reader.hpDamage == 0 then                                 -- �ж��Ƿ��������˺�
    	return                                    
    end
    self:applyBuffChanges(attacker)                                   -- ����״̬
end
-- �жϼ��ܹ���
function clsGameBattler:isSkillEffective(user, skill)
	if skill:isForDeadFriend() ~= self:isDead()then
      return false
    end
    if not globalGameTemp.inBattle and skill:isForFriend() then
      return self:skillTest(user, skill)
    end
    return true
end
-- ����ʹ�ò���
function clsGameBattler:skillTest(user, skill)
	local tester = table.clone(self)
	tester:makeObjDamageValue(user, skill)
	tester:applyBuffChanges(skill)
	if tester.reader.hpDamage < 0 then
		if tester.reader.hp < tester:maxHp() then
      		return true 
      	end
    end
    if tester.reader.spDamage < 0 then
      	if tester.reader.sp < tester:maxSp() then
      		return true 
      	end
    end
    if not #tester.reader.addedBuffs == 0 then
    	return true
    end
    if not #tester.reader.removedBuffs == 0 then
    	return true
    end
    return false
end
-- ����Ч��
function clsGameBattler:skillEffect(user, skill)
	self:clearActionResults()
	if not self:isSkillEffective(user, skill) then
    	self.reader.skipped = true
    	return
    end
    if smRandom:nextInt(100) >= self:calcHit(user, skill) then    -- ����������
    	self.reader.missed = true
    	return
    end
    if smRandom:nextInt(100) < self:calcEva(user, skill) then     -- ����������
    	self.reader.evaded = true
    	return
    end
    self:makeObjDamageValue(user, skill)                          -- �����˺�
    self:makeObjAbsorbEffect(user, skill)                         -- ��������Ч��
    self:executeDamage(user)                                      -- �˺�Ч��
    if skill:physicalAttack() and self.reader.hpDamage == 0 then    -- �ж��Ƿ������˺�
      return                                    
    end
    self:applyBuffChanges(skill)                                  -- ����״̬
end
-- �ж���Ʒ�ܷ�ʹ��
function clsGameBattler:isItemEffective(user, item)
	if item:isForDeadFriend() ~= self:isDead() then
    	return false
    end
    if not globalGameTemp.inBattle and item:isForFriend() then
    	return itemTest(user, item)
    end
    return true
end
-- ��Ʒʹ�ò���
function clsGameBattler:itemTest(user, item)
	local tester = table.clone(self)
	tester:makeObjDamageValue(user, item)
	tester:applyBuffChanges(item)
	if tester.reader.hpDamage < 0 or tester:calcHpRecovery(user, item) > 0 then
		if tester.reader.hp < tester:maxHp() then
      		return true 
      	end
    end
    if tester.reader.spDamage < 0 or tester:calcSpRecovery(user, item) > 0 then
      	if tester.reader.sp < tester:maxSp() then
      		return true 
      	end
    end
    if not #tester.reader.addedBuffs == 0 then
    	return true
    end
    if not #tester.reader.removedBuffs == 0 then
    	return true
    end
    if item:parameter() then
    	return true
    end
    return false
end
-- ������ƷЧ��
function clsGameBattler:itemEffect(user, item)
	self:clearActionResults()
	if not self:isItemEffective(user,item) then
    	self.reader.skipped = true
    	return
    end
    if smRandom:nextInt(100) >= self:calcHit(user, item) then    -- ����������
    	self.reader.missed = true
    	return
    end
    if smRandom:nextInt(100) < self:calcEva(user, item) then     -- ����������
    	self.reader.evaded = true
    	return
    end
    local hpRecovery = self:calcHpRecovery(user, item)           -- ���������ظ���
    local spRecovery = self:calcSpRecovery(user, item)           -- ����ħ���ظ���
    self:makeObjDamageValue(user, item)                          -- �����˺�
    self.reader.hpDamage = math.floor(self.reader.hpDamage - hpRecovery)     -- �����˺���ȥ�ظ���
    self.reader.spDamage = math.floor(self.reader.spDamage - spRecovery)     -- ħ���˺���ȥ�ظ���
    self:makeObjAbsorbEffect(user, item)                         -- ��������Ч��
    self:itemGrowthEffect(user, item)                            -- ����ֵ����Ч��
    self:executeDamage(user)                                     -- �˺�Ч��
    if item:physicalAttack() and self.reader.hpDamage == 0 then    -- �ж��Ƿ������˺�
    	return                                    
    end
    self:applyBuffChanges(item)                                  -- ����״̬
end
-- ��������ֵ����Ч��
function clsGameBattler:itemGrowthEffect(user, item)
	if item:parameter() then
		if item.maxHpPlus ~= nil then
			self.maxHpPlus = self.maxHpPlus + item:maxHpPlus()
		end
		if item.maxSpPlus ~= nil then
			self.maxSpPlus = self.maxSpPlus + item:maxSpPlus()
		end
		if item.atkPlus ~= nil then
			self.atkPlus = self.atkPlus + item:atkPlus() 
		end
		if item.defPlus ~= nil then
			self.defPlus = self.defPlus + item:defPlus() 
		end
		if item.strPlus ~= nil then
			self.strPlus = self.strPlus + item:strPlus() 
		end
		if item.intPlus ~= nil then
			self.intPlus = self.intPlus + item:intPlus() 
		end
		if item.agiPlus ~= nil then
			self.agiPlus = self.agiPlus + item:agiPlus() 
		end
		if item.vitPlus ~= nil then
			self.vitPlus = self.vitPlus + item:vitPlus() 
		end
		if item.dexPlus ~= nil then
			self.dexPlus = self.dexPlus + item:dexPlus() 
		end
		if item.lucPlus ~= nil then
			self.lucPlus = self.lucPlus + item:lucPlus()
		end
	end
end
-- ���������˺�Ч��(�غ�)
function clsGameBattler:slipDamageEffectTurn()
	if self:isSlipDamageTurn() and self.reader.hp > 0 then
    	self.reader.hpDamage = self:applyVariance(self:maxHp() / 10, 10)
    	if self.reader.hpDamage >= self.reader.hp then
    		self.reader.hpDamage = self.reader.hp - 1 
    	end
        self:setHp(self:hp() - self.reader.hpDamage)
    end
end
-- ���������˺�Ч��ʱ�䣩
function clsGameBattler:slipDamageEffectTime()
	if self:isSlipDamageTime() and self.reader.hp > 0 then
    	self.reader.hpDamage = self:applyVariance(self:maxHp() / 10, 10)
    	if self.reader.hpDamage >= self.reader.hp then
    		self.reader.hpDamage = self.reader.hp - 1 
    	end
    	self:setHp(self:hp() - self.reader.hpDamage)
    end
end
