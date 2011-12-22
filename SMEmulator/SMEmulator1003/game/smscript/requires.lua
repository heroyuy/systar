-- 设定路径
package.path = package.path .. ";.\\game\\smscript\\common\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\common\\moudle\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\common\\ui\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\common\\utils\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\data\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\model\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\model\\manager\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\scenes\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\scenes\\map\\?.lua"

--common\moudle
require("clsBag")

--common\ui
require("globalUIConst")
require("globalSkin")
require("clsLayer")
require("clsButton")
require("clsSprite")

--common\utils
require("System")
require("clsList")
require("clsQueue")

--data
require("globalDataLoader")

--model
require("clsModel")
require("clsCharacter")
require("clsPlayer")

--model\manager
require("globalGameData")
require("globalGameData_playerTroop")
require("globalGameData_map")
require("globalGameData_publicMethod")

--scene
require("clsScene")
require("clsSceneTitle")
--scene\map
require("clsMapLayer")
require("clsSceneMap")

