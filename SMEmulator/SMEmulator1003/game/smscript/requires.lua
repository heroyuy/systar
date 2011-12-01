-- 设定路径
package.path = package.path .. ";.\\game\\smscript\\Scene\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\Game\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\Sprite\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\Window\\?.lua"
package.path = package.path .. ";.\\game\\smscript\\Shell\\?.lua"
-- 底层和壳
require("clsTable")
require("clsViewport")
require("clsTouchEvent")
require("clsTouchListener")
require("clsConst")
const = clsConst:new()
require("clsSkin")

require("clsRect")
require("clsTileMap")
require("clsVocab")
vocab = clsVocab:new()
-- 游戏层
require("clsGameTemp")
require("clsGameMap")
require("clsGameCharacter")
require("clsGamePlayer")
require("clsGameBattler")
require("clsGameActor")
require("clsGameEnemy")
require("clsGameActors")
require("clsGameBattleActon")
require("clsGameUnit")
require("clsGameTroop")
require("clsGameParty")
require("clsGameSwitches")
require("clsGameVariables")
-- 精灵
require("clsSpriteBase")
require("clsSpriteMap")
require("clsSpriteSetMap")
require("clsSpriteCharacter")
require("clsSpriteBattler")
require("clsSpriteSetBattle")
-- 窗体
require("clsWindowBase")
require("clsWindowSelect")
require("clsWindowCommand")
require("clsWindowBattleStatus")
require("clsWindowUsable")
require("clsWindowItem")
require("clsWindowSkill")
require("clsWindowActorCommand")
require("clsWindowBattleMessage")
-- 场景
require("clsScene")
require("clsSceneMap")
require("clsSceneBattle")
require("clsSceneTitle")

