-- 在没有存档的情况下，处理临时数据的类。
-- 这个类的实例请参考 globalGameTemp。
clsGameTemp = {}
clsGameTemp.__index = clsGameTemp
-- 构造体
function clsGameTemp:new()
	local self = {}
	setmetatable(self,clsGameTemp)
	self.nextScene = nil               -- 切换待机中的画面 (文字列)
    self.mapBgm = nil                  -- 地图画面 BGM (战斗时候记忆用)
    self.mapBgs = nil                  -- 地图画面 BGS (战斗时候记忆用)
    self.eventIndex = 0          	   -- 公用事件 ID
    self.inBattle = false              -- ս战斗中标记
    self.battleProc = nil              -- ս战斗 返回调用 (Proc)
    self.shopGoods = nil               -- 商店商品列表
    self.shopPurchaseOnly = false      -- 仅从商店买入的标记
    self.nameActorIndex = 0            -- 名称输入 角色 ID
    self.nameMaxChar = 0               -- 名称输入 最大文字数
    self.menuBeep = false              -- 菜单 SE 演奏标记
    self.lastFileIndex = 0             -- 最后保存的文件编号
    self.debugTopRow = 0               -- Debug画面 状态保存用
    self.debugIndex = 0                -- Debug画面 状态保存用
    self.backgroundImage = smImage:createImage(".\\game\\image\\picture\\蜀.jpg")        -- 背景位图
	return self
end