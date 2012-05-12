-- ���?λ���ࡣ�������Ϊ Game_Party ���� Game_Troop ��ĳ�������ʹ�á�
clsGameUnit = {}
clsGameUnit.__index = clsGameUnit
-- ������
function clsGameUnit:new()
	local self = {}
	setmetatable(self,clsGameUnit)
	return self
end
-- ��ȡ��Ա�����������أ�
function clsGameUnit:members()
	return {}
end
-- ��ȡ��ս����Ա
function clsGameUnit:existingMembers()
	local result = {}
	for _,battler in pairs(self:members()) do
		if battler:isExist() then
      		table.insert(result,battler)
      	end
    end
    return result
end
-- ��ȡ�޷�ս����Ա
function clsGameUnit:deadMembers()
	local result = {}
	for _,battler in self:members() do
		if battler.isDead() then
      		table.insert(result,battler)
      	end
    end
    return result
end
-- ������ж�Ա�ж�
function clsGameUnit:clearActions()
	for _,battler in pairs(self:members()) do
		battler:action():clear()
    end
end
-- ���Ŀ��ѡ��
function clsGameUnit:randomTarget()
	local roulette = {}
    for _,member in pairs(self:existingMembers()) do
    	for i = 1,member:odds() do
    		table.insert(roulette,member)
    	end
    end
    return (#roulette > 0 and roulette[smRandom:nextInt(#roulette)+1]) or nil
end
-- ���Ŀ��ѡ���޷�ս���ߣ�
function clsGameUnit:randomDeadTarget()
	local roulette = {}
    for _,member in self:deadMembers() do
    	table.insert(roulette,member)
    end
    return (#roulette > 0 and roulette[smRandom:nextInt(#roulette)]) or nil
end
-- ֱ��Ŀ��ѡ��
function clsGameUnit:smoothTarget(position)
	local member = self:members()[position]
	if member ~= nil and member:isExist() then
    	return member 
    end
    return self:existingMembers()[1]
end
-- ֱ��Ŀ��ѡ���޷�ս���ߣ�
function clsGameUnit:smoothDeadTarget(position)
	local member = self:members()[position]
	if member ~= nil and member:isDead() then
    	return member 
    end
    return self:deadMembers()[1]
end
-- ����ƽ������
function clsGameUnit:averageAgi()
	local result = 0
    local n = 0
    for _,member in pairs(self:members()) do
      result = result + member:agil()
      n = n + 1
    end
    if n > 0 then
    	result = result / n 
    end
    if result == 0 then
    	result = 1 
    end
    return result
end
-- ���������˺�Ч��غϣ�
function clsGameUnit:slipDamageEffectTurn()
	for _,member in pairs(self:members()) do
    	member:slipDamageEffectTurn()
    end
end
-- ���������˺�Ч��ʱ�䣩
function clsGameUnit:slipDamageEffectTime()
	for _,member in self:members() do
    	member:slipDamageEffectTime()
    end
end