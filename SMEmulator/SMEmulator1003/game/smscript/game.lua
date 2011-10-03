function onStart()
smLog:info("onStart");
end

function onTouch(x,y,type)
smLog:info("onTouch-> x:"..x.." y="..y.." type:"..type);
end

function update()
smLog:info("update");
end

function paint(painter)
smLog:info("paint");
painter:setColor(0xabcdef);
painter:fillRect(0,0,200,100);
painter:setColor(0xfefefe);
painter:setTextSize(25);
painter:drawString("HelloWorld!",11,11,0);
painter:setColor(0xefefef);
painter:drawString("HelloWorld!",10,10,0);
end

function onStop()
smLog:info("onStop");
end
