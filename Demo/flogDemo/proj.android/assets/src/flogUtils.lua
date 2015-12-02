universal_button_rect = 
{
--{ name = "achievements.png", rect = cc.rect(72, 144, 62, 60) },
{ name = "arrow.png", rect = cc.rect(136, 150, 60, 38) },
--{ name = "clock_btn.png", rect = cc.rect(62, 266, 58, 50) },
--{ name = "heart_button.png", rect = cc.rect(2, 300, 52, 47) },
--{ name = "key1.png", rect = cc.rect(198, 150, 54, 58) },
--{ name = "leaderboard.png", rect = cc.rect(2, 134, 68, 44) },
--{ name = "levels.png", rect = cc.rect(64, 206, 58, 58) },
--{ name = "movie.png", rect = cc.rect(196, 210, 58, 58) },
{ name = "replay.png", rect = cc.rect(124, 250, 58, 56) },
{ name = "restore.png", rect = cc.rect(136, 190, 58, 58) },
--{ name = "settings.png", rect = cc.rect(2, 180, 60, 58) }
}
universal_button_count = table.getn(universal_button_rect)

-- fonts resource
s_markerFeltFontPath   = "fonts/Marker Felt.ttf"

g_startREC = 0

-- cclog
cclog = function(...)
    print(string.format(...))
end

-- for CCLuaEngine traceback
function __G__TRACKBACK__(msg)
    cclog("----------------------------------------")
    cclog("LUA ERROR: " .. tostring(msg) .. "\n")
    cclog(debug.traceback())
    cclog("----------------------------------------")
end

function getRectByArrayInfo( name , info )
	local count = table.getn(info)
	
	local index
	for index = 1,count do
		if name == info[index].name then
			return info[index].rect
		end
	end
	
	return nil
end

function getIndexByArrayInfo( name , info )
	local count = table.getn(info)
	
	local index
	for index = 1,count do
		if name == info[index].name then
			return index
		end
	end
	
	return 0
end

function getInteger( value )
	local retValue = math.ceil (value)
	if retValue ~= value then
	  retValue = retValue - 1
	end
	return retValue
end

