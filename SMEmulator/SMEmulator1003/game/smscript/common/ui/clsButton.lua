--[[
  description:按钮。
  author:wp_g4
  date:2011/12/18
--]]

--类结构定义
clsButton={}
setmetatable(clsButton,clsLayer)
clsButton.__index=clsButton

--字段
clsButton.STATE_NORMAL=0                       --普通状态
clsButton.STATE_HIGHLIGHT=1                    --高亮状态

clsButton.text=nil                             --按钮文字
clsButton.highlightImage=nil                   --高亮时图片
clsButton.normalImage=nil                      --普通时图片
clsButton.textColor="0xffffffff"               --文字颜色
clsButton.textSize=-1                          --文字大小
clsButton.buttonState=clsButton.STATE_NORMAL   --按钮状态
clsButton.highlightBoundTimes=1.5              --按钮高亮范围倍数

--重定向父类paintLayer(painter)方法
clsButton.paintLayerF=clsButton.paintLayer

--构造器
function clsButton:new(x,y,width,height)
  local self = clsLayer:new(x,y,width,height)
  setmetatable(self,clsButton)
  return self
end

--绘制自身
function clsButton:paintLayer(painter)
  --调用父类的paintLayer方法
  self:paintLayerF(painter)
  --绘制图片
  if self.buttonState==clsButton.STATE_NORMAL then
    if self.normalImage then
      painter:drawImage(self.normalImage,0,0,globalUIConst.anchor.LT)
    end
  elseif self.buttonState==clsButton.STATE_HIGHLIGHT then
    if self.highlightImage then
      painter:drawImage(self.highlightImage,0,0,globalUIConst.anchor.LT)
    end
  end
  --绘制文字
  painter:setColor(self.textColor)
  if self.textSize > 0 then
    painter:setTextSize(self.textSize)
  end
  if self.text then
    painter:drawString(self.text, self.width/2, self.height/2, globalUIConst.anchor.HV)
  end
end

function clsButton:onTouch(x,y,type)
  if type==globalUIConst.touchEventType.DOWN then
    self.buttonState=clsButton.STATE_HIGHLIGHT
  elseif type==globalUIConst.touchEventType.MOVE then
    local offsetWidth=(self.highlightBoundTimes-1)*self.width
    local offsetHeight=(self.highlightBoundTimes-1)*self.height
    if isPointInRect(x,y,-offsetWidth,-offsetHeight,self.width+2*offsetWidth,self.height+2*offsetHeight) then
      self.buttonState=clsButton.STATE_HIGHLIGHT
    else
      self.buttonState=clsButton.STATE_NORMAL
    end
  elseif type==globalUIConst.touchEventType.UP then
    if self.delegate and self.buttonState==clsButton.STATE_HIGHLIGHT then
      self.buttonState=clsButton.STATE_NORMAL
      self.delegate:buttonTapped(self)
    end
  end
end

function clsButton:toString()
  local str="clsButton:["
  str=str.."x="..self.x.." y="..self.y.." w="..self.width.." h="..self.height
  str=str.."]"
  return str
end
