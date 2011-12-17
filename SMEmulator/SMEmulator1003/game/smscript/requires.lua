-- 设定路径
package.path = package.path .. ";.\\game\\smscript\\common\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\common\\moudle\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\common\\ui\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\common\\utils\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\data\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\model\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\model\\manager\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\scenes\\?.lua"

--common\moudle
require("clsBag")

--common\ui
require("globalUIConst")
require("clsLayer")

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

-- 场景
require("clsScene")
require("clsSceneMap")
