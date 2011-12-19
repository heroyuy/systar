package.path = package.path .. ";.\\game\\smscript\\?.lua"
require("requires")
smLog:info("读取脚本")
globalDataSystem = {}
globalDataSystem.skin = ".\\game\\image\\skin\\Window1.png\\"
globalDataSystem.title = ".\\game\\image\\skin\\Window.png\\"
globalGameEngine = smGameEngine:getInstance()
globalAStar = smAStar
globalGameMap = clsGameMap:new()
globalGamePlayer = clsGamePlayer:new()
globalDataLoader = smDataLoader
globalDataLoader:setup()
globalDataManager = smDataManager:getInstance()
globalGameActors = clsGameActors:new()
globalGameParty = clsGameParty:new()
globalGameParty:setupStartingMembers()
globalGameTemp = clsGameTemp:new()
globalGameTroop = clsGameTroop:new()
--smLog:info("建立图片")

function onStart()
--curScene = clsSceneMap:new()
curScene = clsSceneBattle:new()
smLog:info("onStart");
--skin = smVXSkin:createSkin(1024,128,globalDataSystem.skin)
img=smImageFactory:createImage("game/image/tileset/001-Grassland01.png");
end

function onTouch(x,y,type)
if curScene ~= nil and type ~= nil and x ~= nil and y ~= nil then
	--smLog:info("onTouch-> x:"..x.." y="..y.." type:"..type);
	table.sort(curScene.viewports,function (a,b) return a:z() < b:z() end)
	for _,v in pairs(curScene.viewports) do
		if v ~= nil then
			if v:onTouch(x,y,type) then
				return
			end
		end
	end
	curScene:onTouch(x,y,type)
end
end

function update()
if curScene ~= nil and curScene.main == false then
	curScene:onStart()
elseif curScene ~= nil and curScene.main == true then
	curScene:update()
end
--smLog:info("update");
end

function paint(painter)
	if painter ~= nil and curScene ~= nil then
		table.sort(curScene.viewports,function (a,b) return a:z() < b:z() end)
		for _,v in pairs(curScene.viewports) do
			if v ~= nil then
				v:paint(painter)
			end
		end
	end
end
smLog("显示图片")
function onStop()
smLog:info("onStop");
end

