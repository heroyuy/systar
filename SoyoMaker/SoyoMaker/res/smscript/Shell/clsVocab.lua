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
           }
clsVocab.__index = clsVocab
clsVocab.__newindex = function (t,k,v) end
-- ������
function clsVocab:new()
    local self = {}
	setmetatable(self,clsVocab)
	return self
end
-- �ȼ�
function clsVocab:level()
	return "�ȼ�"
end
-- ����
function clsVocab:hp()
	return globalDataSystem.hp
end
-- ħ��
function clsVocab:sp()
	return globalDataSystem.sp
end
-- ������
function clsVocab:atk()
	return globalDataSystem.atk
end
-- ������
function clsVocab:def()
	return globalDataSystem.def
end
-- ħ��
function clsVocab:matk()
	return globalDataSystem.matk
end
-- ħ��
function clsVocab:mdef()
	return globalDataSystem.mdef
end
-- ����
function clsVocab:stre()
	return globalDataSystem.stre
end
-- ����
function clsVocab:inte()
	return globalDataSystem.inte
end
-- ����
function clsVocab:agil()
	return globalDataSystem.agil
end
-- ����
function clsVocab:vita()
	return globalDataSystem.vita
end
-- ����
function clsVocab:dext()
	return globalDataSystem.dext
end
-- ����
function clsVocab:luck()
	return globalDataSystem.luck
end
-- ����
function clsVocab:weapon()
	return globalDataSystem.weapon
end
-- ͷ��
function clsVocab:helm()
	return globalDataSystem.helm
end
-- ����
function clsVocab:shield()
	return globalDataSystem.shield
end
-- ���
function clsVocab:armour()
	return globalDataSystem.armour
end
-- սѥ
function clsVocab:boots()
	return globalDataSystem.boots
end
-- ��Ʒ
function clsVocab:jewelry()
	return globalDataSystem.jewelry
end
-- ��Ʒ
function clsVocab:item()
	return globalDataSystem.item
end
-- ����
function clsVocab:skill()
	return globalDataSystem.skill
end
-- װ��
function clsVocab:equip()
	return globalDataSystem.equip
end
-- ����
function clsVocab:attack()
	return "����"
end
-- ����
function clsVocab:defense()
	return "����"
end
-- ����
function clsVocab:escape()
	return "����"
end
-- ״̬
function clsVocab:buff()
	return "״̬"
end
-- �浵
function clsVocab:save()
	return "�浵"
end
-- ����
function clsVocab:load()
	return "����"
end
-- ��Ϸ����
function clsVocab:gameEnd()
	return "��Ϸ����"
end
-- G��ͨ����λ��
function clsVocab:gold()
	return globalDataSystem.gold
end
-- ȡ��
function clsVocab:cancle()
	return "ȡ��"
end
-- ȷ��
function clsVocab:decide()
	return "ȷ��"
end
-- �µ���Ϸ
function clsVocab:newGame()
	return "�µ���Ϸ"
end
-- ������Ϸ
function clsVocab:continue()
	return "������Ϸ"
end
-- �뿪��Ϸ
function clsVocab:shutDown()
	return "�뿪��Ϸ"
end
-- �ص����⻭��
function clsVocab:toTitle()
	return "�ص����⻭��"
end