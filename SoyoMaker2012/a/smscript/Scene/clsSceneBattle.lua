clsSceneBattle = {}
setmetatable(clsSceneBattle,clsScene)
clsSceneBattle.__index = clsSceneBattle
function clsSceneBattle:new()
	local self = {}
	self = clsScene:new()
	setmetatable(self,clsSceneBattle)
	return self
end
-- 开始
function clsSceneBattle:onStart()
	globalGameTemp.inBattle = true
	self.waitCount = 18
	globalGameTroop:setup(0)
	self.spriteSet = clsSpriteSetBattle:new()
	self.spriteSet:setTouchListener()
	self:createWindows()
	self:processBattleStart()
	for _,actor in pairs(globalGameParty:members()) do
		print(actor:superGuard())
	end
	self.main = true
end
-- 建立各个窗体
function clsSceneBattle:createWindows()
	self.viewportinfo = clsViewport:new(0,0,globalGameEngine:getWidth(),globalGameEngine:getHeight())
	self.viewportinfo:setZ(1000)
    self.commands = {[0] = "攻击",[1] = "防御",[2] = "技能",[3] = "物品",[4] = "逃跑"}
	self.commandWindow = clsWindowCommand:new(self.viewportinfo,96,self.commands,1,0,32,WLH)
	self.commandWindow:setup()
	self.commandWindow:setY(304)
	self.commandWindow:setX(220)
	self.commandWindow:setTouchListener()
	self.statusWindow = clsWindowBattleStatus:new(self.viewportinfo,316,304,264,152)
	self.statusWindow:setTouchListener()
	self.itemWindow = clsWindowItem:new(self.viewportinfo,0,0,globalGameEngine:getWidth(),globalGameEngine:getHeight()-24,32,WLH)
	self.itemWindow:setTouchListener()
	self.skillWindow = clsWindowSkill:new(self.viewportinfo,0,0,globalGameEngine:getWidth(),globalGameEngine:getHeight()-24,32,WLH)
	self.skillWindow:setTouchListener()
	self.actorWindow = clsWindowActorCommand:new(self.viewportinfo,272,2,32,104)
	self.actorWindow:setup(1)
	self.actorWindow:setX((globalGameEngine:getWidth() - self.actorWindow:width())/2)
	self.actorWindow:setY((globalGameEngine:getHeight() - (self.actorWindow:height() + self.actorWindow.commandWindow:height()))/2)
	self.actorWindow:setTouchListener()
	self.messageWindow = clsWindowBattleMessage:new(self.viewportinfo,220,304)
	self.messageWindow:setZ(50)
	--self.messageWindow:setVisible(true)
end
-- 处理触屏
function clsSceneBattle:onTouch(x,y,type)
end
-- 更新
function clsSceneBattle:update()
	if self:judgeWinOrLoss() then
		return
	end
	self:updateBasic(self.main)
	if self.waitCount > 0 then
		self.waitCount = self.waitCount - 1
		return
	end
	if self.commandWindow.active then
		self:updateActorsCommandSeletion()
	elseif self.spriteSet:enemyActive() then
		self:updateTargetEnemySelect()
	elseif self.itemWindow:visible() and self.itemWindow.active then
		self:updateItemSelect()
	elseif self.skillWindow:visible() and self.skillWindow.active then
		self:updateSkillSelect()
	elseif self.actorWindow:visible() and self.actorWindow.active then
		self:updateTargetActorSelect()
	else
		self:processAction()
	end
end
-- 退出
function clsSceneBattle:onStop()
end
-- 基本更新处理
function clsSceneBattle:updateBasic(main)
	if main == nil then
		main = false
	end
	globalGameTroop:update()
	self.spriteSet:update()
end
-- 等待
function clsSceneBattle:wait(duration,noFast)
	if noFast == nil then
		noFast = false
	end
	for i = 0,duration do
    	self:updateBasic(self.main)
    	if not noFast and i >= math.floor(duration / 2) and self:isShowFast() then
     		break 
    	end
    end
end
-- 判断是否瞬间显示
function clsSceneBattle:isShowFast()
	return false
end
-- 判断胜负
function clsSceneBattle:judgeWinOrLoss()
	if globalGameTemp.inBattle then
    	if globalGameParty:isAllDead() then
        	self:processDefeat()
        	return true
      	elseif globalGameTroop:isAllDead() then
        	self:processVictory()
        	return true
      	else
        	return false
      	end
    else
      	return true
    end
end
-- 计算平均逃跑几率
function clsSceneBattle:makeEscapeRatio()
	
end
-- 胜利处理
function clsSceneBattle:processVictory()
	--self.viewportinfo.visible = false
    self.messageWindow:setVisible(true)
    --RPG::BGM.stop
    --$game_system.battle_end_me.play
    --unless $BTEST
      --$game_temp.map_bgm.play
      --$game_temp.map_bgs.play
    --end
    self:displayExpAndGold()
    self:displayDropItems()
    self:displayLevelUp()
    self:battleEnd(0)
end
-- 显示所获得的金钱和经验值
function clsSceneBattle:displayExpAndGold()
end
-- 显示所获得的掉落物品
function clsSceneBattle:displayDropItems()
end
-- 显示升级
function clsSceneBattle:displayLevelUp()
end
-- 失败处理
function clsSceneBattle:processDefeat()
	--@info_viewport.visible = false
    self.messageWindow:setVisible(true)
    local text = string.gsub(vocab.DEFEAT, "*s", globalGameParty:name(), 1)
    table.insert(self.messageWindow.message,text)
    self.messageWindow:refresh()
    self.waitCount = 10
    self:battleEnd(2)
end
-- 结束战斗
-- result : 结果（0：胜利，1：逃跑，2：失败）
function clsSceneBattle:battleEnd(result)
	if result == 2 and not globalGameTroop.canLose then
      self:callGameover()
    else
      globalGameParty:clearActions()
      globalGameParty:removeBuffsBattle()
      globalGameTroop:clear()
      globalGameTemp.inBattle = false
      print("战斗胜利")
      --if $game_temp.battle_proc != nil
        --$game_temp.battle_proc.call(result)
        --$game_temp.battle_proc = nil
      --end
      --unless $BTEST
        --$game_temp.map_bgm.play
        --$game_temp.map_bgs.play
      --end
      curScene = clsSceneMap:new()
      --@message_window.clear
      --Graphics.fadeout(30)
    end
    
end
-- 战斗事件处理
function clsSceneBattle:processBattleEvent()

end
-- 
-- 等待讯息结束
function clsSceneBattle:waitForMessage()

end
-- 开始队伍命令选择
function clsSceneBattle:startActorsCommandSeletion()
	if globalGameTemp.inBattle then
		self.messageWindow:setVisible(false)
    	self.commandWindow.active = true
    	self.commandWindow:setVisible(true)
    	self.statusWindow:setVisible(true)
    end
end
-- 更新角色命令选择
function clsSceneBattle:updateActorsCommandSeletion()
	self.commandWindow:update()
	if self.commandWindow.index ~= -1 then
		if self.commandWindow.index == 0 then
			self.activeBattler:action():setAttack()
			self.commandWindow.index = -1
			self:startTargetEnemySelect()
		elseif self.commandWindow.index == 1 then
			self.activeBattler:action():setGuard()
			self.commandWindow.index = -1
			self:nextActor()
		elseif self.commandWindow.index == 2 then
			self:startSkillSelect()
			self.commandWindow.index = -1
		elseif self.commandWindow.index == 3 then
			self:startItemSelect()
			self.commandWindow.index = -1
		elseif self.commandWindow.index == 4 then
			self.activeBattler:action():setEscape()
			self.commandWindow.index = -1
			self:nextActor()
		end
	end
end
-- 下一个角色
function clsSceneBattle:nextActor()
	while true do
    	if self.actorIndex == #globalGameParty:members() then
        	self:mainStar()
            return
   		end
        self.actorIndex = self.actorIndex + 1
        self.statusWindow:setIndex(self.actorIndex)
        self.activeBattler = globalGameParty:members()[self.actorIndex]
        if self.activeBattler:isInputable() then
            break
        end
    end
    self:startActorsCommandSeletion()
end
-- 战斗开始处理
function clsSceneBattle:processBattleStart()
	self.messageWindow:setVisible(true)
	local text
	for _,name in pairs(globalGameTroop:enemyNames()) do
		text = string.gsub(vocab.EMERGE, "*s",name, 1)
		table.insert(self.messageWindow.message,text)
    end
    if globalGameTroop.preemptive then
    	text = string.gsub(vocab.PREEMPTIVE, "*s",globalGameParty:name(), 1)
		table.insert(self.messageWindow.message,text)
    elseif globalGameTroop.surprise then
    	text = string.gsub(vocab.SURPRISE, "*s",globalGameParty:name(), 1)
		table.insert(self.messageWindow.message,text)
    end
    self.messageWindow:refresh()
    self.waitCount = 8
    self:makeEscapeRatio()
    self:processBattleEvent()
	if globalGameTroop.surprise or not globalGameParty:isInputable() then
    	self:mainStar()
    else
    	self.actorIndex = 0
       	self.statusWindow:setIndex(self.actorIndex)
        self:nextActor()
    end
end
-- 开始选择敌人
function clsSceneBattle:startTargetEnemySelect()
	self.commandWindow.active = false
	self.spriteSet:setEnemyActive(true)
end
-- 终止选择敌人
function clsSceneBattle:endTargetEnemySelect()
	self.commandWindow.active = true
	self.spriteSet:setEnemyTouchIndex(-1)
	self.spriteSet:setEnemyActive(false)
	self.waitCount = 8
	self:nextActor()
end
-- 更新选择敌人
function clsSceneBattle:updateTargetEnemySelect()
	if self.spriteSet:getEnemyTouchIndex() ~= -1 then
		self.activeBattler:action().targetPosition = self.spriteSet:getEnemyTouchIndex()
		self:endTargetEnemySelect()
	end
end
-- 开始选择物品
function clsSceneBattle:startItemSelect()
	self.commandWindow.active = false
	--self.commandWindow:setVisible(false)
	self.itemWindow:setActive(true)
	self.itemWindow:setVisible(true)
end
-- 结束选择物品
function clsSceneBattle:endItemSelect()
	self.itemWindow.commandWindow.index = -1
	self.commandWindow.active = true
	self.itemWindow:setActive(false)
	self.itemWindow:setVisible(false)
end
-- 决定选择物品
function clsSceneBattle:determineItem()
	self.activeBattler:action():setItem(self.item:getIndex())
	self.itemWindow:setActive(false)
	self.itemWindow:setVisible(false)
	if self.item:isNeedSelection() then
    	if self.item:isForOpponent() then
    		self.itemWindow.commandWindow.index = -1
        	self:startTargetEnemySelect()
      	else
      		self.itemWindow.commandWindow.index = -1
      		self.commandIndex = 3
        	self:startTargetActorSelect()
     	end
    else
      self:endItemSelect()
      self:nextActor()
    end
end
-- 更新选择物品
function clsSceneBattle:updateItemSelect()
	self.itemWindow:update()
	if self.itemWindow.commandWindow.index ~= -1 then
		if self.itemWindow.commandWindow.index == 0 then
			self.item = self.itemWindow:item()
			if self.item ~= nil then
				globalGameParty.lastItemIndex = self.item:getIndex()
			end
			if globalGameParty:isItemCanUse(self.item) then
				self:determineItem()
			else
				self.itemWindow.commandWindow.index = -1
			end
		elseif self.itemWindow.commandWindow.index == 1 then
			self:endItemSelect()
			self.itemWindow.commandWindow.index = -1
		end
	end
end
-- 开始选择技能
function clsSceneBattle:startSkillSelect()
	self.commandWindow.active = false
	--self.commandWindow:setVisible(false)
	self.skillWindow:setSkills(self.activeBattler)
	self.skillWindow:setActive(true)
	self.skillWindow:setVisible(true)
end
-- 结束选择技能
function clsSceneBattle:endSkillSelect()
	self.skillWindow.commandWindow.index = -1
	self.commandWindow.active = true
	--self.commandWindow:setVisible(true)
	self.skillWindow:setActive(false)
	self.skillWindow:setVisible(false)
end
-- 更新选择技能
function clsSceneBattle:updateSkillSelect()
	self.skillWindow:update()
	if self.skillWindow.commandWindow.index ~= -1 then
		if self.skillWindow.commandWindow.index == 0 then
			self.skill = self.skillWindow:skill()
			if self.skill ~= nil then
				globalGameParty.lastSkillIndex = self.skill:getIndex()
			end
			if self.activeBattler:isSkillCanUse(self.skill) then
				self:determineSkill()
			else
				self.skillWindow.commandWindow.index = -1
			end
		elseif self.skillWindow.commandWindow.index == 1 then
			self:endSkillSelect()
			self.skillWindow.commandWindow.index = -1
		end
	end
end
-- 决定选择技能
function clsSceneBattle:determineSkill()
	self.activeBattler:action():setSkill(self.skill:getIndex())
	self.skillWindow:setActive(false)
	self.skillWindow:setVisible(false)
	if self.skill:isNeedSelection() then
      if self.skill:isForOpponent() then
      	self.skillWindow.commandWindow.index = -1
        self:startTargetEnemySelect()
      else
      	self.skillWindow.commandWindow.index = -1
      	self.commandIndex = 2
        self:startTargetActorSelect()
      end
    else
    	self.skillWindow.commandWindow.index = -1
      	self:endSkillSelect()
      	self:nextActor()
    end
end
-- 开始选择角色
function clsSceneBattle:startTargetActorSelect()
	--self.commandWindow.active = false
	self.commandWindow:setVisible(false)
	self.statusWindow:setVisible(false)
	self.actorWindow:setActive(true)
	self.actorWindow:setVisible(true)
end
-- 结束选择角色
function clsSceneBattle:endTargetActorSelect()
	self.actorWindow:setActive(false)
	self.actorWindow:setVisible(false)
	--self.commandWindow.active = true
	self.commandWindow:setVisible(true)
	self.statusWindow:setVisible(true)
end
-- 更新选择角色
function clsSceneBattle:updateTargetActorSelect()
	self.actorWindow:update()
	if self.actorWindow.commandWindow.index == 0 then
		self.actorWindow.commandWindow.index = -1
		self.activeBattler:action().targetPosition = self.actorWindow:item():position()
		if self.commandIndex == 2 then
			self.commandIndex = -1
			self:endSkillSelect()
		elseif self.commandIndex == 3 then
			self.commandIndex = -1
			self:endItemSelect()
		end
		self:nextActor()
		self:endTargetActorSelect()
	elseif self.actorWindow.commandWindow.index == 1 then
		self.actorWindow.commandWindow.index = -1
		if self.commandIndex == 2 then
			self.commandIndex = -1
			self.skillWindow:setActive(true)
			self.skillWindow:setVisible(true)
		end
		if self.commandIndex == 3 then
			self.commandIndex = -1
			self.itemWindow:setActive(true)
			self.itemWindow:setVisible(true)
		end
		self:endTargetActorSelect()
	end
end
-- 主过程开始
function clsSceneBattle:mainStar()
	self.messageWindow:setVisible(true)
    self.commandWindow:setVisible(false)
    self.statusWindow:setVisible(false)
	globalGameTroop:increaseTurn()
	self.spriteSet:setEnemyActive(false)
	self.spriteSet:setEnemyTouchIndex(-1)
	self.commandWindow.active = false
	self.actorIndex = -1
	self.statusWindow.index = self.actorIndex
	self.activeBattler = nil
	globalGameTroop:makeActions()
	self:makeActionsOrders()
	self.waitCount = 8
	collectgarbage("collect")
end
-- 生成行动顺序
function clsSceneBattle:makeActionsOrders()
	self.actionBattlers = {}
	if not globalGameTroop.surprise then
		self.actionBattlers = table.union(self.actionBattlers,globalGameParty:members())
	end
	if not globalGameTroop.preemptive then
		self.actionBattlers = table.union(self.actionBattlers,globalGameTroop:members())
	end
	for _,battler in pairs(self.actionBattlers) do
    	battler:action():makeSpeed()
    end
    table.sort(self.actionBattlers,function (a,b) return a:action().speed > b:action().speed end)
end
-- 战斗行动处理
function clsSceneBattle:processAction()
	if self:judgeWinOrLoss() then
		return
	end
	if globalGameTemp.nextScene ~= nil then
		return
	end
	self:setNextActiveBattler()
	if self.activeBattler == nil then
    	self:turnEnd()
    	return
    end
    self.waitCount = 3
    self.activeBattler.whiteFlash = true
    if not self.activeBattler:action().forcing then
    	self.activeBattler:action():prepare()
    end
    if self.activeBattler:action():isValid() then
    	self:executeAction()
    end
    if not self.activeBattler:action().forcing then
      --@message_window.clear
      self:removeStatesAuto()
      self:displayCurrentState()
    end
    --self.activeBattler.whiteFlash = false
    --@message_window.clear
end
-- 设置下一战斗者行动
function clsSceneBattle:setNextActiveBattler()
	while true do
		if globalGameTroop.forcingBattler ~= nil then
			self.activeBattler = globalGameTroop.forcingBattler
        	table.delete(self.actionBattlers,self.activeBattler)
        	globalGameTroop.forcingBattler = nil
      	else
        	self.activeBattler = table.shift(self.actionBattlers)
		end
		if self.activeBattler == nil then
			return 
		end
		if self.activeBattler:position() ~= nil then
      		return 
      	end
	end
end
-- 回合结束
function clsSceneBattle:turnEnd()
	globalGameTroop.turnEnding = true
	globalGameTroop:slipDamageEffectTurn()
    globalGameParty:slipDamageEffectTurn()
    globalGameParty:doAutoRecovery()
    globalGameTroop.preemptive =false
    globalGameTroop.surprise = false
    self:processBattleEvent()
    globalGameTroop.turnEnding = false
    self:startTurn()
end
-- 回合开始
function clsSceneBattle:startTurn()
   self.actorIndex = 0
   self.statusWindow:setIndex(self.actorIndex)
   self:nextActor()
end
-- 执行战斗行动
function clsSceneBattle:executeAction()
	if self.activeBattler:action().type == 0 then
		self:executeActionWait()   -- 等待
	elseif self.activeBattler:action().type == 1 then
		self:executeActionAttack() -- 攻击
	elseif self.activeBattler:action().type == 2 then
		self:executeActionGuard()  -- 防御
	elseif self.activeBattler:action().type == 3 then
		self:executeActionItem()   -- 物品
	elseif self.activeBattler:action().type == 4 then
		self:executeActionSkill()  -- 技能
	elseif self.activeBattler:action().type == 5 then
		self:executeActionEscape() -- 逃跑
	end
end
-- 执行战斗行动：攻击
function clsSceneBattle:executeActionAttack()
	local text = string.gsub(vocab.DO_ATTACK, "*s", self.activeBattler:name(), 1)
	table.insert(self.messageWindow.message,text)
	self.messageWindow:refresh()
    local targets = self.activeBattler:action():makeTargets()
    self:displayAttackAnimation(targets) 
    self.waitCount = 8
    for _,target in pairs(targets) do
    	target:attackEffect(self.activeBattler) 
    	self:displayActionEffects(target)
    end
end
-- 显示动画
function clsSceneBattle:displayAnimation(targets,aniIndex)
	if aniIndex < 0 then
    	self:displayAttackAnimation(targets)
    else
    	self:displayNormalAnimation(targets,aniIndex)
    end
    self.waitCount = 8
    ----self:waitForAnimation()
end
-- 显示攻击动画
function clsSceneBattle:displayAttackAnimation(targets)
	--if self.activeBattler:isA() == "Enemy" then
     -- --self:wait(15,true)
    --else
    -- aid1 = self.activeBattler.atk_animation_id
     --aid2 = self.activeBattler.atk_animation_id2
     --self:displayNormalAnimation(targets,aid1,false)
      --self:displayNormalAnimation(targets,aid2,true)
    --end
    --self:waitForAnimation()
end
-- 显示普通动画
function clsSceneBattle:displayNormalAnimation(targets,aniIndex,mirror)
end
-- 显示行动结果
function clsSceneBattle:displayActionEffects(target,obj)
	if not target:skipped() then
      	--local lineNumber = @message_window.line_number
      	--self:wait(5)
      	self.waitCount = 3
      	self:displayCritical(target,obj)
      	self:displayDamage(target,obj)
      	self:displayBuffChanges(target,obj)
      	--if line_number == @message_window.line_number
      		--if not target:isStatesActive() then
        		--self:display_failure(target, obj) 
        	--end
     	 --end
      	--if line_number != @message_window.line_number
       		--self:wait(30)
      	--end
      	--@message_window.back_to(line_number)
    end
end
-- 显示会心一击
function clsSceneBattle:displayCritical(target,obj)
	local text
	if target:critical() then
    	if target:isActor() then
    		text = vocab.CRITICAL_TO_ACTOR
      	else
        	text = vocab.CRITICAL_TO_ENEMY
      	end
		table.insert(self.messageWindow.message,text)
		self.messageWindow:refresh()
    	self.waitCount = 8
    end
end
-- 显示伤害
function clsSceneBattle:displayDamage(target,obj)
	if target:missed() then
    	self:displayMiss(target, obj)
    elseif target:evaded() then
    	self:displayEvasion(target, obj)
    else
    	self:displayHpDamage(target, obj)
    	self:displaySpDamage(target, obj)
    end
    self.messageWindow:refresh()
end
-- 显示落空
function clsSceneBattle:displayMiss(target, obj)
	local text
	if obj == nil or obj:physicalAttack() then 
    	if target:isActor() then
        	text = string.gsub(vocab.ACTOR_NO_DAMEGE, "*s", self.activeBattler:name(), 1)
      	else
        	text = string.gsub(vocab.ENEMY_NO_DAMAGE, "*s", self.activeBattler:name(), 1)
      	end
      	--Sound.play_miss
    else
      	local text = string.gsub(vocab.ACTION_FAILURE, "*s", self.activeBattler:name(), 1)
    end
    table.insert(self.messageWindow.message,text)
    self.waitCount = 11
end
-- 显示回避
function clsSceneBattle:displayEvasion(target, obj)
	if target:isActor() then
      local text = string.gsub(vocab.ACTOR_EVASION, "*s", self.activeBattler:name(), 1)
    else
      local text = string.gsub(vocab.ENEMY_EVASION, "*s", self.activeBattler:name(), 1)
    end
    --Sound.play_evasion
    table.insert(self.messageWindow.message,text)
    self.waitCount = 11
end
-- 显示体力伤害
function clsSceneBattle:displayHpDamage(target, obj)
	local text
	if target:hpDamage() == 0 then               -- 无伤害
		if obj ~= nil and obj:damageToSp() then
      		return 
      	end
      	if obj ~= nil and obj:baseDamage() == 0 then
      		return 
      	end
      	text = (target:isActor() and vocab.ACTOR_NO_DAMEGE) or vocab.ENEMY_NO_DAMAGE
      	text = string.gsub(text, "*s", target:name(), 1)
    elseif target:absorbed() then                   -- 吸收
    	text = (target:isActor() and vocab.ACTOR_DRAIN) or vocab.ENEMY_DRAIN
    	text = string.gsub(text, "*1$s", target:name(), 1)
    	text = string.gsub(text, "*2$s", globalDataManager:getModel(const.DATA_MANAGER_CONFIG,0):hp(), 1)
    	text = string.gsub(text, "*3$s", -target:hpDamage(), 1)
    elseif target:hpDamage() > 0 then             -- 伤害
    	if target:isActor() then
      		text = vocab.ACTOR_DAMAGE
      		text = string.gsub(text, "*1$s", target:name(), 1)
      		text = string.gsub(text, "*2$s", target:hpDamage(), 1)
        	--Sound.play_actor_damage
        	--$game_troop.screen.start_shake(5, 5, 10)
      	else
        	text = vocab.ENEMY_DAMAGE
      		text = string.gsub(text, "*1$s", target:name(), 1)
      		text = string.gsub(text, "*2$s", target:hpDamage(), 1)
        	--Sound.play_enemy_damage
        	target.blink = true
      	end
    else                                    -- 回复
      	text = (target:isActor() and vocab.ACTOR_RECOVERY) or vocab.ENEMY_RECOVERY
      	text = string.gsub(text, "*1$s", target:name(), 1)
    	text = string.gsub(text, "*2$s", globalDataManager:getModel(const.DATA_MANAGER_CONFIG,0):hp(), 1)
    	text = string.gsub(text, "*3$s", -target:hpDamage(), 1)
      	--Sound.play_recovery
    end
    table.insert(self.messageWindow.message,text)
    self.waitCount = 11
end
function clsSceneBattle:displaySpDamage(target, obj)
	local text
	if target:isDead() then
		return 
	end
	if target:spDamage() == 0 then
    	return 
    end
    if target:absorbed() then                      -- 吸收
    	text = (target:isActor() and vocab.ACTOR_DRAIN) or vocab.ENEMY_DRAIN
    	text = string.gsub(text, "*1$s", target:name(), 1)
    	text = string.gsub(text, "*2$s", globalDataManager:getModel(const.DATA_MANAGER_CONFIG,0):sp(), 1)
    	text = string.gsub(text, "*3$s", -target:spDamage(), 1)
    elseif target:spDamage() > 0 then              -- 伤害
    	text = (target:isActor() and vocab.ACTOR_LOSS) or vocab.ENEMY_LOSS
    	text = string.gsub(text, "*1$s", target:name(), 1)
    	text = string.gsub(text, "*2$s", globalDataManager:getModel(const.DATA_MANAGER_CONFIG,0):sp(), 1)
    	text = string.gsub(text, "*3$s", target:spDamage(), 1)
    else                                           -- 回复
    	text = (target:isActor() and vocab.ACTOR_RECOVERY) or vocab.ENEMY_RECOVERY
    	text = string.gsub(text, "*1$s", target:name(), 1)
    	text = string.gsub(text, "*2$s", globalDataManager:getModel(const.DATA_MANAGER_CONFIG,0):sp(), 1)
    	text = string.gsub(text, "*3$s", -target:spDamage(), 1)
      	--Sound.play_recovery
    end
    table.insert(self.messageWindow.message,text)
    self.waitCount = 11
end
-- 执行战斗行动：防御
function clsSceneBattle:executeActionGuard()
	local text = string.gsub(vocab.DO_GUARD, "*s", self.activeBattler:name(), 1)
	table.insert(self.messageWindow.message,text)  
	self.messageWindow:refresh()  
	self.waitCount = 18
end
-- 执行战斗行动：等待
function clsSceneBattle:executeActionWait()
	local text = string.gsub(vocab.DO_WAIT, "*s", self.activeBattler:name(), 1)
	table.insert(self.messageWindow.message,text)  
	self.messageWindow:refresh()  
	self.waitCount = 18
end
-- 执行战斗行动：使用物品
function clsSceneBattle:executeActionItem()
	local item = self.activeBattler:action():item()
	local text = string.gsub(vocab.USE_ITEM, "*1$s", self.activeBattler:name(), 1)
	text = string.gsub(text, "*2$s", item:name(), 1)
    table.insert(self.messageWindow.message,text) 
    local targets = self.activeBattler:action():makeTargets()
    self:displayAnimation(targets,item:userAniIndex())
    globalGameParty:consumeItem(item)
    globalGameTemp.eventIndex = item:eventIndex()
    for _,target in pairs(targets) do
    	target:itemEffect(self.activeBattler, item)
    	self:displayActionEffects(target, item)
    end
end
-- 执行战斗行动：使用技能
function clsSceneBattle:executeActionSkill()
	local skill = self.activeBattler:action():skill()
	local text = string.gsub(vocab.USE_SKILL, "*1$s", self.activeBattler:name(), 1)
	text = string.gsub(text, "*2$s", skill:name(), 1)
    table.insert(self.messageWindow.message,text) 
    local targets = self.activeBattler:action():makeTargets()
    self:displayAnimation(targets,skill:userAniIndex())
    self.activeBattler:setSp(self.activeBattler:sp() - self.activeBattler:calcSpCost(skill))
    globalGameTemp.eventIndex = skill:eventIndex()
    for _,target in pairs(targets) do
    	target:skillEffect(self.activeBattler, skill)
    	self:displayActionEffects(target, skill)
    end
end
--  执行战斗行动：逃跑
function clsSceneBattle:executeActionEscape()
	local text = string.gsub(vocab.DO_ESCAPE, "*s", self.activeBattler:name(), 1)
	table.insert(self.messageWindow.message,text)  
	self.messageWindow:refresh()  
	self.waitCount = 18
end
-- 显示更改状态
function clsSceneBattle:displayBuffChanges(target, obj)
	if target:missed() or target:evaded() then
		return 
	end
	if not target:isBuffsActive() then
    	return 
    end
    --if @message_window.line_number < 4
      --@message_window.add_instant_text("")
    --end
    self:displayAddedBuffs(target, obj)
    self:displayRemovedBuffs(target, obj)
    self:displayRemainedBuffs(target, obj)
    --if @message_window.last_instant_text.empty?
      --@message_window.back_one
    --else
      --wait(10)
    --end
end
-- 显示附加状态
function clsSceneBattle:displayAddedBuffs(target, obj)
	for _,state in pairs(target:addedBuffs()) do
      	if target:isActor() then
        	--next if state.message1.empty?
        	--text = target.name + state.message1
      	--else
        	--next if state.message2.empty?
        	--text = target.name + state.message2
      	end
      	if state:getIndex() == 0 then                     -- 无法战斗
      		target:performCollapse()
      		
      	end
      	--@message_window.replace_instant_text(text)
      	--self.waitCount = 15
    end
end
-- 显示移除状态
function clsSceneBattle:displayRemovedBuffs(target, obj)
	for _,state in pairs(target:removedBuffs()) do
      	--next if state.message4.empty?
      	--text = target.name + state.message4
      	--@message_window.replace_instant_text(text)
      	--wait(20)
    end
end
-- 显示不变状态
function clsSceneBattle:displayRemainedBuffs(target, obj)
end