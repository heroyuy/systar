-- 存储某些常数，使用请用const
clsConst = {
         -- 属性
		 TWO_SWORDS_STYLE = 1,
		 FIX_EQUIPMENT = 2,
		 AUTO_BATTLE = 3,
		 SUPER_GUARD = 4,
		 PHARMACOLOGY = 5,
         CRITICAL_BONUS = 6,
         PHYSICAL_ATTACK = 7,
		 DAMAGE_TO_MP = 8,
		 ABSORB_DAMAGE = 9,
		 IGNORE_DEFENSE = 10,
		 TWO_HANDED = 11,
		 FAST_ATTACK = 12,
		 DUAL_ATTACK = 13,
		 PREVENT_CRITICAL = 14,
		 HALF_MP_COST = 15,
		 DOUBLE_EXP_GAIN = 16,
		 AUTO_HP_RECOVER = 17,
		 LEVITATE = 18,
		 HAS_CRITICAL = 19,
         NONRESISTANCE = 20,
		 OFFSET_BY_OPPSITE = 21,
		 REDUCE_HIT_RATIO = 22,
		 SLIP_DAMAGE = 23,
		 UNARMED_COMBAT = 24,
		 -- 装备类型
		 EQUIP_HELM = 0,
         EQUIP_JEWELRY = 1,
         EQUIP_WEAPON = 2,
         EQUIP_SHIELD = 3,
         EQUIP_ARMOUR = 4,
         EQUIP_BOOTS = 5,
		 -- 装备效果
		 EQUIP_EFFECT_ATK = 1,
		 EQUIP_EFFECT_DEF = 2,
		 EQUIP_EFFECT_HIT = 3,
		 EQUIP_EFFECT_EVA = 4,
		 EQUIP_EFFECT_STRE = 5,
		 EQUIP_EFFECT_INTE = 6,
		 EQUIP_EFFECT_AGIL = 7,
		 EQUIP_EFFECT_VITA = 8,
		 EQUIP_EFFECT_DEXT = 9,
         EQUIP_EFFECT_LUCK = 10,
         EQUIP_EFFECT_MATK = 11,
         EQUIP_EFFECT_MDEF = 12,
		 -- 物品技能效果
		 USABLE_EFFECT_BASE_DAMAGE = 1,
		 -- 物品效果
		 ITEM_EFFECT_HP_RECOVERY_RATE = 2,
         ITEM_EFFECT_HP_RECOVERY = 3,
		 ITEM_EFFECT_SP_RECOVERY_RATE = 4,
		 ITEM_EFFECT_SP_RECOVERY = 5,
		 ITEM_EFFECT_MAXHP = 6,
		 ITEM_EFFECT_MAXSP =7,
		 ITEM_EFFECT_ATK = 8,
		 ITEM_EFFECT_DEF = 9,
		 ITEM_EFFECT_STRE = 10,
		 ITEM_EFFECT_INTE = 11,
		 ITEM_EFFECT_AGIL = 12,
		 ITEM_EFFECT_VITA = 13,
		 ITEM_EFFECT_DEXT = 14,
		 ITEM_EFFECT_LUCK = 15,
		 ITEM_EFFECT_MATK = 16,
		 ITEM_EFFECT_MDEF = 17,
         -- 技能物品因数默认值
		 USABLE_FACTOR_ATKF = 1,
		 USABLE_FACTOR_INTF = 2,
		 USABLE_FACTOR_AGIF = 3,
		 USABLE_FACTOR_SPEED = 4,
		 USABLE_FACTOR_VARIANCE = 5,
		 -- 技能命中率
		 SKILL_FACTOR_HIT = 6,
		 -- 技能消耗
		 SKILL_COST_SP = 1,
		 -- 物品自身消耗
		 ITEM_COST_CONSUMABLE = 1,
		 -- 状态参数类型
		 BUFF_PARAM_ATK = 1,
		 BUFF_PARAM_DEF = 2,
		 BUFF_PARAM_STRE = 3,
		 BUFF_PARAM_INTE = 4,
		 BUFF_PARAM_AGIL = 5,
		 BUFF_PARAM_VITA = 6,
		 BUFF_PARAM_DEXT = 7,
		 BUFF_PARAM_LUCK = 8,
		 BUFF_PARAM_MATK = 9,
		 BUFF_PARAM_MDEF = 10,
		 -- 状态类型
		 BUFF_TYPE_RESTRICTION = 0,
		 BUFF_TYPE_LAST = 1,
		 -- 状态持续类型
		 BUFF_LASTTYPE_SINGLE = 0,
		 BUFF_LASTTYPE_TIME = 1,
		 BUFF_LASTTYPE_TURN = 2,
		 -- 状态解除条件
		 BUFF_CONDITIONTYPE_BATTLE = 0,
		 BUFF_CONDITIONTYPE_DAMAGE = 1,
		 BUFF_CONDITIONTYPE_TURN = 2,
		 BUFF_CONDITIONTYPE_TIME = 3,
		 -- 状态修改规则޸
		 BUFF_RULETYPE_VALUE = 0,
		 BUFF_RULETYPE_TPERCENT = 1,
		 BUFF_RULETYPE_CPERCENT = 2,
		 -- 时间持续的间隔
		 BUFF_TIMELAST_INTERVAL = 10, 
		 -- 死亡状态默认值̬
		 BUFF_DEAD = 1,
		 -- 动画默认值
		 ANI_DATA_NIL = 0,
		 ANI_DATA_UNARMED_COMBAT = 1,
		 -- 装备默认值
		 EQUIP_DATA_NIL = 0,
		 -- 敌人动作默认值
		 ACTION_DATA_WAIT = 0,
		 ACTION_DATA_ATTACK = 1,
		 ACTION_DATA_GUARD = 2,
		 ACTION_DATA_ITEM = 3,
		 ACTION_DATA_SKILL = 4,
		 ACTION_DATA_ESCAPE = 5,
		 -- 敌人动作条件默认值
		 ACTION_DATA_CONDITION_TURN = 0,
		 ACTION_DATA_CONDITION_HP = 1,
		 ACTION_DATA_CONDITION_LEVEL = 2,
		 ACTION_DATA_CONDITION_SWITCH = 3,
		 ACTION_DATA_CONDITION_VARIABLE = 4,
		 -- 最大队伍数量
		 MAX_MENBERS = 4,
		 -- 地图动态原件变换频率
		 MAP_ACTIVE_CHANCE_FRAME = 16, 
		 -- 数据管理者读取
		 DATA_MANAGER_CONFIG = 0,
		 DATA_MANAGER_BUFF = 1,
		 DATA_MANAGER_SKILL = 2,    
		 DATA_MANAGER_ITEM = 3, 
		 DATA_MANAGER_EQUIP = 4,
		 DATA_MANAGER_VOCATION = 5,        
		 DATA_MANAGER_ACTOR = 6, 
		 DATA_MANAGER_ENEMY = 7,
		 DATA_MANAGER_ENEMYTROOP = 8, 
		 DATA_MANAGER_MAP = 9,
		 DATA_MANAGER_NPC = 10,              
        }
clsConst.__index = clsConst
clsConst.__newindex = function (t,k,v) end
-- 构造体
function clsConst:new()
    local self = {}
	setmetatable(self,clsConst)
	return self
end
