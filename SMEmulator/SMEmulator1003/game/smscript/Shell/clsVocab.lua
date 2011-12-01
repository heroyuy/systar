-- �������������Ϣ�����������϶���Ϊ���������ﲿ��������$data_system��
clsVocab = {
           -- �̵껭��
           SHOP_BUY          = "购买",
           SHOP_SELL         = "贩卖",
           SHOP_CANCEL       = "取消",
           POSSESSION        = "拥有数量",
           -- ״̬����
           EXP_TOTAL         = "当前经验",
           EXP_NEXT          = "下一*s",
           -- ��/ȡ������
           SAVE_MESSAGE      = "储存到第几个档位?",
           LOAD_MESSAGE      = "从第几个档位载入?",
           FILE              = "档案",
           -- �����˴����ڶ�����ʱ
           PARTY_NAME        = "*s 的队伍.",
           -- ��ս����Ϣ
           EMERGE            = "*s 出现!",
           PREEMPTIVE        = "*s 先下手为强!", 
           SURPRISE          = "*s 被偷袭!", 
           ESCAPE_START      = "*s 准备逃跑!",
           ESCAPE_FAILURE    = "但是，逃跑失败!",
           -- ս��������Ϣ
           VICTORY           = "*s 胜利",
           DEFEAT            = "*s 被击败了.",
           OBTAIN_EXP        = "获得*s 点经验值",
           OBTAIN_GOLD       = "获得*s*s 的金钱!",
           OBTAIN_ITEM       = "找到*s !",
           LEVEL_UP          = "*s 已经是 *s *s!",
           OBTAIN_SKILL      = "领悟*s !",
           -- ս���ж�
           DO_ATTACK         = "*s 出手攻击!",
           DO_GUARD          = "*s 护住身上弱点.",
           DO_ESCAPE         = "*s 逃跑了.",
           DO_WAIT           = "*s 正在等待时机.",
           USE_ITEM          = "*1$s 使用了 *2$s .",
           USE_SKILL         = "*1$s 发动了 *2$s .",
           -- ����һ��
           CRITICAL_TO_ENEMY = "会心一击!!",
           CRITICAL_TO_ACTOR = "痛恨一击!!",
           -- ���ڽ�ɫ���ж�
           ACTOR_DAMAGE      = "*1$s 受到 *2$s 点伤害!",
           ACTOR_LOSS        = "*1$s 失去了 *3$s 点的 *2$s!",
           ACTOR_DRAIN       = "*1$s 的 *3$s 被吸收了*2$s点!",
           ACTOR_NO_DAMEGE   = "*s 毫发无伤!",
           ACTOR_NO_HIT      = "攻击落空! *s 毫发无伤!",
           ACTOR_EVASION     = "*s 闪开了攻击!",
           ACTOR_RECOVERY    = "*1$s 的 *2$s 回复了*3$s点!",
           -- ���ڵ��˵��ж�
           ENEMY_DAMAGE      = "*1$s 受到 *2$s 点伤害!",
           ENEMY_LOSS        = "*1$s 失去了 *3$s 点的 *2$s!",
           ENEMY_DRAIN       = "*1$s 的 *3$s 被吸收了*2$s点!",
           ENEMY_NO_DAMAGE   = "*s 毫发无伤!",
           ENEMY_NO_HIT      = "攻击落空! *s 毫发无伤!",
           ENEMY_EVASION     = "*s 闪开了攻击!",
           ENEMY_RECOVERY    = "*1$s 的 *2$s 回复了*3$s点!",
           -- �������?�ܺ���Ʒʹ����Чʱ��
           ACTION_FAILURE    = "对*s 无效!",
           -- 持续伤害
           SLIP_DAMAGE_EFFECT_TURN = "*s 被持续伤害.",
           SLIP_DAMAGE_EFFECT_TIME = "*s 受到时间持续伤害",
           }
clsVocab.__index = clsVocab
clsVocab.__newindex = function (t,k,v) end
-- ������
function clsVocab:new()
    local self = {}
	setmetatable(self,clsVocab)
	return self
end
function clsVocab:newGame()
	return "新的游戏"
end
function clsVocab:continue()
	return "读取存档"
end
function clsVocab:shutDown()
	return "退出游戏"
end
-- 状态提示
function clsVocab:buffTerm(index,actor,type)
	if type == 0 then                -- 状态附加
		if index == 0 then               -- 死亡状态
			if actor == true then
				return "*s 倒下了."
			elseif actor == false then
				return "*s 被打倒了."
			end
		end
		if index == 1 then               -- 毒状态
			if actor == true then
				return "*s 中毒了."
			elseif actor == false then
				return "*s 中毒了."
			end
		end
	elseif type == 1 then            -- 状态解除
		if index == 0 then               -- 死亡状态
			return "*s 又站起来了."
		end
		if index == 1 then               -- 毒状态
			if actor == true then
				return "*s 身上的毒被解除."
			end
		end
	end
end