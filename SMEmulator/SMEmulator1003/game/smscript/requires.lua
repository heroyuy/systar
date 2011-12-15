-- 设定路径
package.path = package.path .. ";.\\game\\smscript\\scenes\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\common\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\common\\utils\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\data\\?.lua"

-- 场景
require("clsScene")
require("clsSceneMap")
require("DataLoader")
require("System")
