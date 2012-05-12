-- 为table加上几个方法。
table.clone = function (t)
				  local tmp = {}
				  for k,v in pairs(t) do
					if type(v) == "table" then
						  tmp[k] = table.clone(v)
					   else
						  tmp[k] = v
					end
				  end
				  setmetatable(tmp,getmetatable(t))
				return tmp;
			  end
table.delete = function (t,e)
				   for i = 1,#t do
				      if t[i] == e then
					     table.remove(t,i)
						 i = i - 1
					  end
				   end
				   return e
               end
table.union = function (a,b)
			  local tmp = {}
			    for k,v in pairs(a) do
				  if type(k) == "number" then
				    tmp[k] = v
				  end
				end
				for k,v in pairs(b) do
				  if type(k) == "number" then
				    table.insert(tmp,b[k])
				  end
				end
			  return tmp
			end
table.intersect = function (a,b)
					   local tmp = {}
					   local i = 1
					   for k,v in pairs(a) do
					     if type(k) == "number" then
						   for u,e in pairs(b) do
							 if type(u) == "number" then
							   if v == e then
							     tmp[i] = v
								 i = i + 1
							   end
							 end
						   end
						 end
					   end
					   return tmp
					 end
table.subtract = function (a,b)
				   local tmp = {}
				   for k,v in pairs(a) do
				     if type(k) == "number" then
				       tmp[k] = v
				     end
				   end
				   for _,v in pairs(table.intersect(a,b)) do
				     table.delete(tmp,v)
				   end
				   return tmp
                 end
table.maxValues = function (t)
				  	local valuesMax = 0
				  	for _,v in pairs(t) do
					  if v > valuesMax then
						  valuesMax = v
					  end
				  	end
				  	return valuesMax
			      end
table.index = function (t,val)
				for k,v in pairs(t) do
					if v == val then
						return k
					end
				end
				return nil
			  end
table.compact = function (t)
					table.delete(t,nil)
					return t
				end
table.include = function (t,e)
				   for _,v in pairs(t) do
					  if v == e then
					     return true
					  end
				   end
				   return false
                end
table.shift = function (t)
			  	 local tmp = t[1]
			  	 table.remove(t,1)
			  	 return tmp
			  end
table.smInsert = function (t,e)
				 	if t[0] == nil then
				 		t[0] = e
				 	else
				 		table.insert(t,e)
				 	end
				 end