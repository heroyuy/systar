-- 设定路径
package.path = package.path .. ";"..smGameEngine:getGamePath().."/smscript/common/?.lua"
package.path = package.path .. ";"..smGameEngine:getGamePath().."/smscript/common/system/?.lua"
package.path = package.path .. ";"..smGameEngine:getGamePath().."/smscript/common/ui/?.lua"
package.path = package.path .. ";"..smGameEngine:getGamePath().."/smscript/data/?.lua"
package.path = package.path .. ";"..smGameEngine:getGamePath().."/smscript/scenes/?.lua"
package.path = package.path .. ";"..smGameEngine:getGamePath().."/smscript/scenes/map/?.lua"
package.path = package.path .. ";"..smGameEngine:getGamePath().."/smscript/scenes/title/?.lua"

--common\system
require("System")
require("clsList")
require("clsQueue")
require("clsHashMap")
require("clsStack")
require("clsAStar")
require("globalNotifier")

--common\ui
require("globalUIConst")
require("globalSkin")
require("clsUILayer")
require("clsUIButton")
require("clsUISprite")

--data
require("globalConst")
require("smDataLoader")
require("clsModel")
require("clsBag")
require("clsCharacter")
require("clsPlayer")
require("clsNPC")
require("globalData")
require("globalData_player")

--scene
require("clsScene")
--scene\map
require("clsTiledMapLayer")
require("clsSceneMap")
--scene\title
require("clsSceneTitle")

