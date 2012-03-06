--[[
  description:游戏常量
  author:wp_g4
  date:2012/02/13
--]]

--初始化
globalConst={}

--============对象类型============
globalConst.ObjectType={}
globalConst.ObjectType.VOCATION=0           --职业
globalConst.ObjectType.PLAYER=1             --玩家
globalConst.ObjectType.SKILL=2              --技能
globalConst.ObjectType.ITEM=3               --物品
globalConst.ObjectType.EQUIP=4              --装备
globalConst.ObjectType.ENEMY=5              --敌人
globalConst.ObjectType.ENEMYTROOP=6         --敌人队伍
globalConst.ObjectType.STATUS=7             --状态
globalConst.ObjectType.MAP=8                --地图
globalConst.ObjectType.NPC=9                --NPC
globalConst.ObjectType.ANIMATION=10         --动画

--============消息命令============
globalConst.NotifyCMD={}
--Character
globalConst.NotifyCMD.Character={}
  --[[
    character移动，此通知的参数为{character=player/npc,rowChanged=true}
            第一个参数是移动的目标 第二个标识移动后character的行号是否变化
  --]]
  globalConst.NotifyCMD.Character.MOVED="characterMoved"    --角色移动

