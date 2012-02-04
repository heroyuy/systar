--[[
  description:脚本解释器，提供接口供外部调用
  author:wp_g4
  date:2012/02/02
--]]

--初始化
globalInterpreter={}

----------流程控制类----------
--结束游戏
function globalInterpreter:stopGame()
end

--执行公共事件
function globalInterpreter:executeScript(scriptId)
end

--等待
function globalInterpreter:sleep(millisecond)
end

----------游戏表现类----------
--切换地图
function globalInterpreter:changeMap(mapId,row,col,face)
end

--强制移动   e.g  {0,0,1,2,3,3,1}
function globalInterpreter:forceMove(paths)
end

--显示倒计时
function globalInterpreter:showTimer(millisecond)
end

--播放音乐
function globalInterpreter:playerMusic(name,loop)
end

--停止音乐
function globalInterpreter:stopMusic(name)
end

--物品商店 e.g {10,2,5,0}
function globalInterpreter:openItemShop(itemIds)
end

--装备商店 e.g  {10,2,5,0}
function globalInterpreter:openEquipShop(equipIds)
end

--显示对话
function globalInterpreter:showDialog(name,content,position)
end

--显示输入框
function globalInterpreter:showInputDialog(type)
end

--显示选项框 e.g  {"yes","no","cancel"}
function globalInterpreter:showOptionDialog(options)
end

--更改面向
function globalInterpreter:changeFace(face)
end

--开启战斗
function globalInterpreter:startFight(enemyTroopId,type)
end

--结束战斗
function globalInterpreter:exitFight()
end

--显示图片
function globalInterpreter:showImage(name,x,y,millisecond)
end

--移除图片
function globalInterpreter:removeImage(name)
end

--移动图片
function globalInterpreter:moveImage(name,x,y,millisecond)
end

--旋转图片
function globalInterpreter:rotateImage(name,type,degree,millisecond)
end

--更改图片色调
function globalInterpreter:toneImage(name,a,r,g,b,millisecond)
end

--显示动画
function globalInterpreter:playAnimation(animationId,x,y)
end

--显示动画
function globalInterpreter:playAnimationInFight(animationId,targetType,targetId)
end

--更换皮肤
function globalInterpreter:changeSkin(name)
end

--更改画面色调
function globalInterpreter:toneScene(a,r,g,b,millisecond)
end

--画面震动
function globalInterpreter:shakeScene(range,millisecond,times)
end

--打开系统菜单
function globalInterpreter:openSystemMenu()
end

--打开存档菜单
function globalInterpreter:openRecordMenu()
end

--回主菜单
function globalInterpreter:returnToMainMenu()
end

----------数据处理类----------
--开关操作
function globalInterpreter:operateSwitch(switchId,value)
end

--变量操作
function globalInterpreter:operateVariable(variableId,operateType,valueType,value)
end

--玩家属性操作
function globalInterpreter:operatePlayerProperty(playerId,propertyType,operateType,valueType,value)
end

--名称操作
function globalInterpreter:operatePlayerName(playerId,name)
end

--技能操作
function globalInterpreter:operatePlayerSkill(playerId,skillId,value)
end

--装备操作  e.g  {12,1,-1,-1,-1,-1}   TODO 参数意义待定
function globalInterpreter:operatePlayerEquip(playerId,equipIds)
end

--模型操作
function globalInterpreter:operatePlayerPattern(playerId,characterImageName)
end

--金钱操作
function globalInterpreter:operateMoney(operateType,valueType,value)
end

--物品列表操作
function globalInterpreter:operateItem(index,operateType,valueType,value)
end

--装备列表操作
function globalInterpreter:operateEquip(index,operateType,valueType,value)
end

--敌人属性操作
function globalInterpreter:operateEnemyProperty(enemyId,propertyType,operateType,valueType,value)
end

--敌人模型操作
function globalInterpreter:operateEnemyPattern(enemyId,characterImageName)
end
