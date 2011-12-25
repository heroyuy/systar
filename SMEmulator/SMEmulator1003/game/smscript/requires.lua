-- 设定路径
package.path = package.path .. ";.\\game\\smscript\\common\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\common\\ui\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\common\\system\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\data\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\data\\manager\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\scenes\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\scenes\\map\\?.lua"

--common\system
require("System")
require("clsList")
require("clsQueue")
require("clsHashMap")

--common\ui
require("smUIConst")
require("smSkin")
require("clsUILayer")
require("clsUIButton")
require("clsUISprite")

--data
require("globalDataLoader")
require("clsModel")
require("clsBag")
require("clsCharacter")
require("clsPlayer")
require("globalGameData")
require("globalGameData_playerTroop")
require("globalGameData_map")
require("globalGameData_publicMethod")

--scene
require("clsScene")
--scene\map
require("clsMapLayer")
require("clsSceneMap")
--scene\title
require("clsSceneTitle")

