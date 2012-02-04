-- 设定路径
package.path = package.path .. ";.\\game\\smscript\\common\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\common\\system\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\common\\ui\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\data\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\scenes\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\scenes\\map\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\scenes\\title\\?.lua"

--common\system
require("System")
require("clsList")
require("clsQueue")
require("clsHashMap")
require("clsStack")
require("clsAStar")

--common\ui
require("smUIConst")
require("smSkin")
require("clsUILayer")
require("clsUIButton")
require("clsUISprite")

--data
require("smDataLoader")
require("clsModel")
require("clsBag")
require("clsCharacter")
require("clsPlayer")
require("globalData")
require("globalData_player")
require("globalData_map")

--scene
require("clsScene")
--scene\map
require("clsTiledMapLayer")
require("clsSceneMap")
--scene\title
require("clsSceneTitle")

