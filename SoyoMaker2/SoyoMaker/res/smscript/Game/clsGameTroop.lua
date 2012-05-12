-- 敌人队伍类
clsGameTroop = {}
setmetatable(clsGameTroop,clsGameUnit)
clsGameTroop.__index = clsGameTroop
-- 构造体
function clsGameTroop:new()
	local self = {}
	setmetatable(self,clsGameTroop)
	self.reader = {
				  --screen = clsGameScreen:new(),                   -- ս������״̬
                  --interpreter = clsGameInterpreter:new(),         -- ս���¼�������
                  eventFlags = {},                                -- ս���¼���ִ�б�־
                  turnCount = 0,                                 -- �غϼ���
                  nameCounts = {},                                -- ������Ƽ���
                  enemies = {},                                   -- ���˶����Ա�����˶������飩
				  }
	self:clear()
	return self
end
-- 获取队伍
function clsGameTroop:members()
	return self.reader.enemies
end
-- 清除队伍
function clsGameTroop:clear()
	--self.reader.screen:clear()
    --self.reader.interpreter:clear()
    --table.clear(self.reader.eventFlags)
    self.reader.enemies = {}
    self.reader.turnCount = 0
    self.reader.namesCount = {}
    self.canEscape = false                         -- ���������־
    self.canLose = false                           -- ����ʧ�ܱ�־
    self.preemptive = false                        -- ���ֹ�����־
    self.surprise = false                          -- ͵Ϯ������־
    self.turnEnding = false                        -- �غϽ������־
    self.forcingBattler = nil                      -- ǿ��ս���ж�Ŀ��
end
-- 获取回合数
function clsGameTroop:turnCount()
	return self.reader.turnCount
end
-- 获取队伍
function clsGameTroop:troop()
	return globalDataManager:getModel(const.DATA_MANAGER_ENEMYTROOP,self.reader.troopIndex)
end
-- 设置
function clsGameTroop:setup(troopIndex)
	self:clear()
	self.reader.troopIndex = troopIndex
	self.reader.enemies = {}
	for i = 1,self:troop():enemysSize() do
		self.reader.enemies[i] = clsGameEnemy:new(i,self:troop():enemy(i-1))
	end
end
-- 获取敌人名称列表
function clsGameTroop:enemyNames()
	local names = {}
	for _,enemy in pairs(self:members()) do
		if enemy:isExist() then
			if not table.include(names,enemy:originalName()) then
				table.insert(names,enemy:originalName())
			end
		end
    end
    return names
end
-- 增加回合数
function clsGameTroop:increaseTurn()
	self.reader.turnCount = self.reader.turnCount + 1
end
-- 生成战斗行动
function clsGameTroop:makeActions()
	if self.preemptive then
      self:clearActions()
    else
      for _,enemy in pairs(self:members()) do
        enemy:makeAction()
      end
    end
end 
-- 判断全灭
function clsGameTroop:isAllDead()
	return (#self:existingMembers() == 0)
end
-- 计算经验值总数
function clsGameTroop:expTotal()
	local exp = 0
	for _,enemy in pairs(self:deadMembers()) do 
		if not enemy.hidden then
			exp = exp + enemy:exp()
		end
	end
	return exp
end
-- 计算金钱总数
function clsGameTroop:goldTotal()
	local gold = 0
	for _,enemy in pairs(self:deadMembers()) do
		if not enemy.hidden then
			gold = gold + enemy:gold()
		end
	end
	return gold
end
-- 更新
function clsGameTroop:update()
end
-- 判断是否全灭
function clsGameTroop:isAllDead()
    return #self:existingMembers() == 0
end