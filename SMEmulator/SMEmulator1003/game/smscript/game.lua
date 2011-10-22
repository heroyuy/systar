function onStart()
--smLog:info("onStart");
end

function onTouch(x,y,type)
--smLog:info("onTouch-> x:"..x.." y="..y.." type:"..type);
end

function update()
--smLog:info("update");
end

function paint(painter)
--smLog:info("paint");
painter:setColor(0xabcdef);
painter:fillRect(0,0,800,480);
painter:setColor(0xfefefe);
painter:setTextSize(50);
painter:drawString("HelloWorld!",301,201,0);
painter:setColor(0xefefef);
painter:drawString("HelloWorld!",300,200,0);
painter:setColor(0xffffff);
painter:setTextSize(15);
painter:drawString(smGameEngine:getActualFps().."",50,50,0);
end

function onStop()
--smLog:info("onStop");
end
