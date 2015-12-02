#include "lua_myclass_auto.hpp"
#include "sharerec_for_cocos2d.h"
#include "tolua_fix.h"
#include "LuaBasicConversions.h"



int lua_myclass_ShareRec_listLocalVideos(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 2)
    {
        long long** arg0;
        int* arg1;
        #pragma warning NO CONVERSION TO NATIVE FOR long long**
		ok = false;
        #pragma warning NO CONVERSION TO NATIVE FOR int*
		ok = false;
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_listLocalVideos'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::listLocalVideos(arg0, arg1);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:listLocalVideos",argc, 2);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_listLocalVideos'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_clearCache(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_clearCache'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::clearCache();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:clearCache",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_clearCache'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_stopRecorder(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_stopRecorder'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::stopRecorder();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:stopRecorder",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_stopRecorder'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_setDebuggable(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_setDebuggable'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::setDebuggable();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:setDebuggable",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_setDebuggable'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_pauseRecorder(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_pauseRecorder'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::pauseRecorder();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:pauseRecorder",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_pauseRecorder'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_setText(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        const char* arg0;
        std::string arg0_tmp; ok &= luaval_to_std_string(tolua_S, 2, &arg0_tmp, "sharerec.ShareRec:setText"); arg0 = arg0_tmp.c_str();
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_setText'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::setText(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:setText",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_setText'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_startRecorder(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_startRecorder'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::startRecorder();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:startRecorder",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_startRecorder'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_addCustomAttr(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 2)
    {
        const char* arg0;
        const char* arg1;
        std::string arg0_tmp; ok &= luaval_to_std_string(tolua_S, 2, &arg0_tmp, "sharerec.ShareRec:addCustomAttr"); arg0 = arg0_tmp.c_str();
        std::string arg1_tmp; ok &= luaval_to_std_string(tolua_S, 3, &arg1_tmp, "sharerec.ShareRec:addCustomAttr"); arg1 = arg1_tmp.c_str();
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_addCustomAttr'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::addCustomAttr(arg0, arg1);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:addCustomAttr",argc, 2);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_addCustomAttr'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_showShare(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_showShare'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::showShare();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:showShare",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_showShare'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_setMaxFrameSize(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        cn::sharerec::ShareRec::LevelMaxFrameSize arg0;
        ok &= luaval_to_int32(tolua_S, 2,(int *)&arg0, "sharerec.ShareRec:setMaxFrameSize");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_setMaxFrameSize'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::setMaxFrameSize(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:setMaxFrameSize",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_setMaxFrameSize'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_setRecordAudio(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        uint16_t arg0;
        ok &= luaval_to_uint16(tolua_S, 2,&arg0, "sharerec.ShareRec:setRecordAudio");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_setRecordAudio'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::setRecordAudio(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:setRecordAudio",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_setRecordAudio'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_setMinDuration(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        long long arg0;
        ok &= luaval_to_long_long(tolua_S, 2,&arg0, "sharerec.ShareRec:setMinDuration");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_setMinDuration'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::setMinDuration(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:setMinDuration",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_setMinDuration'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_showVideoCenter(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_showVideoCenter'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::showVideoCenter();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:showVideoCenter",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_showVideoCenter'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_showProfile(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_showProfile'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::showProfile();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:showProfile",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_showProfile'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_getLocalVideoPath(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 2)
    {
        long long arg0;
        char** arg1;
        ok &= luaval_to_long_long(tolua_S, 2,&arg0, "sharerec.ShareRec:getLocalVideoPath");
        #pragma warning NO CONVERSION TO NATIVE FOR char**
		ok = false;
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_getLocalVideoPath'", nullptr);
            return 0;
        }
        bool ret = cn::sharerec::ShareRec::getLocalVideoPath(arg0, arg1);
        tolua_pushboolean(tolua_S,(bool)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:getLocalVideoPath",argc, 2);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_getLocalVideoPath'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_setUseES3(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        uint16_t arg0;
        ok &= luaval_to_uint16(tolua_S, 2,&arg0, "sharerec.ShareRec:setUseES3");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_setUseES3'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::setUseES3(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:setUseES3",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_setUseES3'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_resumeRecorder(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_resumeRecorder'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::resumeRecorder();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:resumeRecorder",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_resumeRecorder'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_isAvailable(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_isAvailable'", nullptr);
            return 0;
        }
        bool ret = cn::sharerec::ShareRec::isAvailable();
        tolua_pushboolean(tolua_S,(bool)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:isAvailable",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_isAvailable'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_deleteLocalVideo(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        long long arg0;
        ok &= luaval_to_long_long(tolua_S, 2,&arg0, "sharerec.ShareRec:deleteLocalVideo");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_deleteLocalVideo'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::deleteLocalVideo(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:deleteLocalVideo",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_deleteLocalVideo'.",&tolua_err);
#endif
    return 0;
}
int lua_myclass_ShareRec_setDesignResolution(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"sharerec.ShareRec",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_myclass_ShareRec_setDesignResolution'", nullptr);
            return 0;
        }
        cn::sharerec::ShareRec::setDesignResolution();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "sharerec.ShareRec:setDesignResolution",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_myclass_ShareRec_setDesignResolution'.",&tolua_err);
#endif
    return 0;
}
static int lua_myclass_ShareRec_finalize(lua_State* tolua_S)
{
    printf("luabindings: finalizing LUA object (ShareRec)");
    return 0;
}

int lua_register_myclass_ShareRec(lua_State* tolua_S)
{
    tolua_usertype(tolua_S,"sharerec.ShareRec");
    tolua_cclass(tolua_S,"ShareRec","sharerec.ShareRec","",nullptr);

    tolua_beginmodule(tolua_S,"ShareRec");
        tolua_function(tolua_S,"listLocalVideos", lua_myclass_ShareRec_listLocalVideos);
        tolua_function(tolua_S,"clearCache", lua_myclass_ShareRec_clearCache);
        tolua_function(tolua_S,"stopRecorder", lua_myclass_ShareRec_stopRecorder);
        tolua_function(tolua_S,"setDebuggable", lua_myclass_ShareRec_setDebuggable);
        tolua_function(tolua_S,"pauseRecorder", lua_myclass_ShareRec_pauseRecorder);
        tolua_function(tolua_S,"setText", lua_myclass_ShareRec_setText);
        tolua_function(tolua_S,"startRecorder", lua_myclass_ShareRec_startRecorder);
        tolua_function(tolua_S,"addCustomAttr", lua_myclass_ShareRec_addCustomAttr);
        tolua_function(tolua_S,"showShare", lua_myclass_ShareRec_showShare);
        tolua_function(tolua_S,"setMaxFrameSize", lua_myclass_ShareRec_setMaxFrameSize);
        tolua_function(tolua_S,"setRecordAudio", lua_myclass_ShareRec_setRecordAudio);
        tolua_function(tolua_S,"setMinDuration", lua_myclass_ShareRec_setMinDuration);
        tolua_function(tolua_S,"showVideoCenter", lua_myclass_ShareRec_showVideoCenter);
        tolua_function(tolua_S,"showProfile", lua_myclass_ShareRec_showProfile);
        tolua_function(tolua_S,"getLocalVideoPath", lua_myclass_ShareRec_getLocalVideoPath);
        tolua_function(tolua_S,"setUseES3", lua_myclass_ShareRec_setUseES3);
        tolua_function(tolua_S,"resumeRecorder", lua_myclass_ShareRec_resumeRecorder);
        tolua_function(tolua_S,"isAvailable", lua_myclass_ShareRec_isAvailable);
        tolua_function(tolua_S,"deleteLocalVideo", lua_myclass_ShareRec_deleteLocalVideo);
        tolua_function(tolua_S,"setDesignResolution", lua_myclass_ShareRec_setDesignResolution);
    tolua_endmodule(tolua_S);
    std::string typeName = typeid(cn::sharerec::ShareRec).name();
    g_luaType[typeName] = "sharerec.ShareRec";
    g_typeCast["ShareRec"] = "sharerec.ShareRec";
    return 1;
}
TOLUA_API int register_all_myclass(lua_State* tolua_S)
{
	tolua_open(tolua_S);
	
	tolua_module(tolua_S,"sharerec",0);
	tolua_beginmodule(tolua_S,"sharerec");

	lua_register_myclass_ShareRec(tolua_S);

	tolua_endmodule(tolua_S);
	return 1;
}

