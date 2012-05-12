-- 窗体皮肤类按照窗体皮肤还有大小直接生成窗体皮肤图片
clsSkin = {}
clsSkin.__index = clsSkin
-- 构造体
function clsSkin:new(width,height)
	local self = {}
	setmetatable(self,clsSkin)
	if width < 24 then
		width = 24
	end
	if height < 24 then
		height = 24
	end
	self.width = width
	self.height = height
	self:setupSkin()
	return self
end
-- 拼窗体
function clsSkin:setupSkin()
	self.image = smImage:createImage(self.width,self.height)
	self.painter = self.image:getPainter()
	local imaArray = self:getSubImage()
	self:drawBody(self.painter,imaArray)
	self:drawFrame(self.painter,imaArray)
	self.cursor = imaArray[22]
	self.upArrow = imaArray[18]
	self.rightArrow = imaArray[19]
	self.downArrow = imaArray[20]
	self.leftArrow = imaArray[21]
	self.arrow = {}
	self.arrow[0] = imaArray[23]
	self.arrow[1] = imaArray[24]
	self.arrow[2] = imaArray[25]
	self.arrow[3] = imaArray[26]
end
-- 切图
function clsSkin:getSubImage()
	local ima = smImage:createImage(globalDataSystem.skin)
	local tmp = {}
	-- 窗体底纹
	tmp[1] = smImage:copyImage(ima,0,0,8,8)
	tmp[2] = smImage:copyImage(ima,8,0,48,8)
	tmp[3] = smImage:copyImage(ima,56,0,8,8)
	tmp[4] = smImage:copyImage(ima,0,8,8,48)
	tmp[5] = smImage:copyImage(ima,8,8,48,48)
	tmp[6] = smImage:copyImage(ima,56,8,8,48)
	tmp[7] = smImage:copyImage(ima,0,56,8,8)
	tmp[8] = smImage:copyImage(ima,8,56,48,8)
	tmp[9] = smImage:copyImage(ima,56,56,8,8)
	-- 窗体框
	-- 窗体框
	tmp[10] = smImage:copyImage(ima,64,0,16,16)
	tmp[11] = smImage:copyImage(ima,80,0,32,16)
	tmp[12] = smImage:copyImage(ima,112,0,16,16)
	tmp[13] = smImage:copyImage(ima,64,16,16,32)
	tmp[14] = smImage:copyImage(ima,112,16,16,32)
	tmp[15] = smImage:copyImage(ima,64,48,16,16)
	tmp[16] = smImage:copyImage(ima,80,48,32,16)
	tmp[17] = smImage:copyImage(ima,112,48,16,16)
	-- 窗体小光标
	tmp[18] = smImage:copyImage(ima,88,16,16,8)  -- 上
	tmp[19] = smImage:copyImage(ima,80,24,8,16)  -- 左
	tmp[20] = smImage:copyImage(ima,88,40,16,8)  -- 下
	tmp[21] = smImage:copyImage(ima,104,24,8,16)  -- 右
	-- 窗体光标框
	tmp[22] = smImage:copyImage(ima,64,64,32,32)
	-- 选择光标
	tmp[23] = smImage:copyImage(ima,96,64,16,16)  -- 左上角
	tmp[24] = smImage:copyImage(ima,112,64,16,16) -- 右上角
	tmp[25] = smImage:copyImage(ima,96,80,16,16)  -- 左下角
	tmp[26] = smImage:copyImage(ima,112,80,16,16) -- 右下角
	return tmp
end
-- 绘制窗体外框
function clsSkin:drawFrame(painter,imaArray)
	local winMidWidth = self.width - 32
	local winMidHeight = self.height - 32
	local drawNumWidth = math.floor(winMidWidth / 32)
	if winMidWidth % 32 ~= 0 then
		drawNumWidth = drawNumWidth + 1
	end
	local drawNumHeight = math.floor(winMidHeight / 32)
	if winMidHeight % 32 ~= 0 then
		drawNumHeight = drawNumHeight + 1
	end
	-- 绘制上下两边
	for i = 1,drawNumWidth do
		if i ~= drawNumWidth then
			painter:drawImage(imaArray[11],16+(i-1)*32,0,painter.LT)
			painter:drawImage(imaArray[16],16+(i-1)*32,self.height - 16,painter.LT)
		end
		if i == drawNumWidth then
			if winMidWidth % 32 ~= 0 then
				local n = self.width - drawNumWidth * 32
				painter:drawImage(smImage:copyImage(imaArray[11],32-n,0,n,16),16+(i-1)*32,0,painter.LT)
				painter:drawImage(smImage:copyImage(imaArray[16],32-n,0,n,16),16+(i-1)*32,self.height - 16,painter.LT)
			else
				painter:drawImage(imaArray[11],16+(i-1)*32,0,painter.LT)
				painter:drawImage(imaArray[16],16+(i-1)*32,self.height - 16,painter.LT)
			end
		end
	end
	-- 绘制左右两边
	for i = 1,drawNumHeight do
		if i ~= drawNumHeight then
			painter:drawImage(imaArray[13],0,16+(i-1)*32,painter.LT)
			painter:drawImage(imaArray[14],self.width - 16,16+(i-1)*32,painter.LT)
		end
		if i == drawNumHeight then
			if winMidHeight % 32 ~= 0 then
				local n = self.height - drawNumHeight * 32
				painter:drawImage(smImage:copyImage(imaArray[13],0,32-n,16,n),0,16+(i-1)*32,painter.LT)
				painter:drawImage(smImage:copyImage(imaArray[14],0,32-n,16,n),self.width-16,16+(i-1)*32,painter.LT)
			else
				painter:drawImage(imaArray[13],0,16+(i-1)*32,painter.LT)
				painter:drawImage(imaArray[14],self.width - 16,16+(i-1)*32,painter.LT)
			end
		end
	end
	-- 绘制四个角
	painter:drawImage(imaArray[10],0,0,painter.LT)
	painter:drawImage(imaArray[12],self.width - 16,0,painter.LT)
	painter:drawImage(imaArray[15],0,self.height-16,painter.LT)
	painter:drawImage(imaArray[17],self.width - 16,self.height - 16,painter.LT)
end
-- 绘制窗体背景
function clsSkin:drawBody(painter,imaArray)
	if self.width > 28 then
		-- 绘制上下两边
		painter:drawImage(smImage:zoomImage(imaArray[2],self.width - 28,8),14,6,painter.LT)
		painter:drawImage(smImage:zoomImage(imaArray[8],self.width - 28,8),14,self.height - 14,painter.LT)
	end
	if self.height > 28 then
		-- 绘制左右两边
		painter:drawImage(smImage:zoomImage(imaArray[4],8,self.height - 28),6,14,painter.LT)
		painter:drawImage(smImage:zoomImage(imaArray[6],8,self.height - 28),self.width - 14,14,painter.LT)
	end
	if self.width > 28 and self.height > 28 then
		-- 绘制剩余窗体中心
	    painter:drawImage(smImage:zoomImage(imaArray[5],self.width - 28,self.height - 28),14,14,painter.LT)
	end
	-- 绘制四个角
	painter:drawImage(imaArray[1],6,6,painter.LT)
	painter:drawImage(imaArray[3],self.width - 14,6,painter.LT)
	painter:drawImage(imaArray[7],6,self.height-14,painter.LT)
	painter:drawImage(imaArray[9],self.width - 14,self.height - 14,painter.LT)
end