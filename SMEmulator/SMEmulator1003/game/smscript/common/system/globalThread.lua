--[[
  description:线程
  author:wp_g4
  date:2012/02/22
--]]

--结构定义
globalThread = {}

--标识常量
globalThread.THREAD_MAIN=0
globalThread.THREAD_SCRIPT=1

globalThread.coroutineThread=nil
globalThread.currentThread=globalThread.THREAD_MAIN

--启动脚本线程 [此方法只能由主线程调用]
function globalThread:start(run)
  --检查此方法是否被主线程调用
  if self.currentThread~=self.THREAD_MAIN then
    --TODO 如果不是主线程调用此方法则报错
    print("start出错")
    return
  end
  self.coroutineThread=coroutine.create(run)
  self:resume()
end

--继续脚本线程 [此方法只能由主线程调用]
function globalThread:resume()
  --检查此方法是否被主线程调用
  if self.currentThread~=self.THREAD_MAIN then
    --TODO 如果不是主线程调用此方法则报错
    print("resume出错")
    return
  end
  self.currentThread=self.THREAD_SCRIPT
  coroutine.resume(self.coroutineThread)
end

--暂停脚本线程 [此方法只能由脚本线程调用]
function globalThread:pause()
    --检查此方法是否被脚本线程调用
  if self.currentThread~=self.THREAD_SCRIPT then
    --TODO 如果不是脚本线程调用此方法则报错
    print("pause出错")
    return
  end
  self.currentThread=self.THREAD_MAIN
  coroutine.yield()
end

function run()
  for i=0,100 do
    print("sub:"..i)
    globalThread:pause()
  end
end

function main()
  globalThread:start(run)
  for i=0,100 do
    print("main:"..i)
    globalThread:resume()
  end
end

main()
