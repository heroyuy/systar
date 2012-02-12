--[[
  description:游戏数据管理器 外部代理
  author:wp_g4
  date:2012/02/04
--]]

----------流程控制类----------
--结束游戏
function globalData.proxy:stopGame()
end

--执行公共事件
function globalData.proxy:executeScript(scriptId)
end

--等待
function globalData.proxy:sleep(millisecond)
end

----------游戏表现类----------
--切换地图
function globalData.proxy:changeMap(mapId,row,col,face)
end

--强制移动   e.g  {0,0,1,2,3,3,1}
function globalData.proxy:forceMove(paths)
end

--显示倒计时
function globalData.proxy:showTimer(millisecond)
end

--播放音乐
function globalData.proxy:playerMusic(name,loop)
end

--停止音乐
function globalData.proxy:stopMusic(name)
end

--开启商店 e.g {10,2,5,0}
function globalData.proxy:openItemShop(itemIds)
end

--显示对话
function globalData.proxy:showDialog(name,content,position)
end

--显示输入框
function globalData.proxy:showInputDialog(type)
end

--显示选项框 e.g  {"yes","no","cancel"}
function globalData.proxy:showOptionDialog(options)
end

--更改面向
function globalData.proxy:changeFace(face)
end

--开启战斗
function globalData.proxy:startFight(enemyTroopId,type)
end

--结束战斗
function globalData.proxy:exitFight()
end

--显示图片
function globalData.proxy:showImage(name,x,y,millisecond)
end

--移除图片
function globalData.proxy:removeImage(name)
end

--移动图片
function globalData.proxy:moveImage(name,x,y,millisecond)
end

--旋转图片
function globalData.proxy:rotateImage(name,type,degree,millisecond)
end

--更改图片色调
function globalData.proxy:toneImage(name,a,r,g,b,millisecond)
end

--显示动画
function globalData.proxy:playAnimation(animationId,x,y)
end

--显示动画
function globalData.proxy:playAnimationInFight(animationId,targetType,targetId)
end

--更换皮肤
function globalData.proxy:changeSkin(name)
end

--更改画面色调
function globalData.proxy:toneScene(a,r,g,b,millisecond)
end

--画面震动
function globalData.proxy:shakeScene(range,millisecond,times)
end

--打开系统菜单
function globalData.proxy:openSystemMenu()
end

--打开存档菜单
function globalData.proxy:openRecordMenu()
end

--回主菜单
function globalData.proxy:returnToMainMenu()
end

----------数据处理类----------
--开关操作
function globalData.proxy:operateSwitch(switchId,value)
end

--获取开关值
function globalData.proxy:getSwitch(switchId)
end

--变量操作
function globalData.proxy:operateVariable(variableId,operateType,valueType,value)
end

--获取变量值
function globalData.proxy:getVariable(variableId)
end

--玩家属性操作
function globalData.proxy:operatePlayerProperty(playerId,propertyType,operateType,valueType,value)
end

--获取玩家属性
function globalData.proxy:getPlayerProperty(playerId,propertyType)
end

--玩家名称操作
function globalData.proxy:operatePlayerName(playerId,name)
end

--获取玩家名称
function globalData.proxy:getPlayerName(playerId)
end

--技能操作
function globalData.proxy:operatePlayerSkill(playerId,skillId,value)
end

--获取技能状态
function globalData.proxy:getPlayerSkillStatus(playerId,skillId)
end

--装备操作  e.g  {12,1,-1,-1,-1,-1}   TODO 参数意义待定
function globalData.proxy:operatePlayerEquip(playerId,equipIds)
end

--获取装备状态 
function globalData.proxy:getPlayerEquipStatus(playerId,equipId)
end

--模型操作
function globalData.proxy:operatePlayerPattern(playerId,characterImageName)
end

--金钱操作
function globalData.proxy:operateMoney(operateType,valueType,value)
end

--获取金钱数量
function globalData.proxy:getMoneyAmount()
end

--物品列表操作
function globalData.proxy:operateItem(itemId,operateType,valueType,value)
end

--获取物品数量
function globalData.proxy:getItemNum(itemId)
end

--装备列表操作
function globalData.proxy:operateEquip(equipId,operateType,valueType,value)
end

--获取装备数量
function globalData.proxy:getEquipNum(equipId)
end

--敌人属性操作
function globalData.proxy:operateEnemyProperty(enemyId,propertyType,operateType,valueType,value)
end

--获取敌人属性
function globalData.proxy:getEnemyProperty(enemyId,propertyType)
end

--敌人模型操作
function globalData.proxy:operateEnemyPattern(enemyId,characterImageName)
end


