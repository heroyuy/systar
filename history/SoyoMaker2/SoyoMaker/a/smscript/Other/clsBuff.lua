-- ״̬�������
clsBuff = {}
clsBuff.__index = clsBuff
-- ������
function clsBuff:new()
  local self = {}
  setmetatable(self,clsBuff)
  self.index = -1 --״̬����
  self.type = -1 --״̬����
  self.name = "" --״̬��
  self.desc = "" --״̬����
  self.icon = "" --ͼ���ַ
  self.lev = -1 --״̬���ȼ�
  self.aniIndex = -1 --�������
  self.lastType = -1 --��������
  self.lastValue = -1 --����ֵ
  self.params = {}  -- ��ϣ�?��������->{�������ͣ��޸����ͣ��޸�ֵ}
  self.attributes = {} -- ��ϣ�?���Ա�š�>״ֵ̬
  self.buffs = {}  -- ��ϣ�?״̬���->״̬Ч��ֵ
  self.removeConditions = {}  -- 状态移除条件
  self.restriction = 0 -- 状态限制
  return self
end
-- 对于一些默认值的赋值ֵ
function clsBuff:setup()
  self.nonresistance = (self.attributes[const.NONRESISTANCE] ~= nil)
  self.offsetByOpposite = (self.attributes[const.OFFSET_BY_OPPSITE] ~= nil)
  self.slipDamage = (self.attributes[const.SLIP_DAMAGE] ~= nil)
  self.reduceHitRatio = (self.attributes[const.REDUCE_HIT_RATIO] ~= nil)
  self.battleOnly = (self.removeConditions[const.BUFF_CONDITIONTYPE_BATTLE] ~= nil)
  self.releaseByDamage = (self.removeConditions[const.BUFF_CONDITIONTYPE_DAMAGE] ~= nil)
  if self.type == const.BUFF_TYPE_LAST then -- 只有当状态为持续类型是有效
  	self.atk = self.params[const.BUFF_PARAM_ATK]
  	self.def = self.params[const.BUFF_PARAM_DEF]
  	self.stre = self.params[const.BUFF_PARAM_STRE]
  	self.inte = self.params[const.BUFF_PARAM_INTE]
  	self.agil = self.params[const.BUFF_PARAM_AGIL]
  	self.vita = self.params[const.BUFF_PARAM_VITA]
  	self.dext = self.params[const.BUFF_PARAM_DEXT]
  	self.luck = self.params[const.BUFF_PARAM_LUCK]
  	self.matk = self.params[const.BUFF_PARAM_MATK]
  	self.mdef = self.params[const.BUFF_PARAM_MDEF]
  end
  if self.lastType == const.BUFF_LASTTYPE_TIME then 
  	self.lastTime = self.removeConditions[const.BUFF_CONDITIONTYPE_TIME].lastParam
  	self.autoReleaseProb = self.removeConditions[const.BUFF_CONDITIONTYPE_TURN].param
  else
  	self.lastTurn = self.removeConditions[const.BUFF_CONDITIONTYPE_TURN].lastParam
  	self.autoReleaseProb = self.removeConditions[const.BUFF_CONDITIONTYPE_TURN].param
  end
end