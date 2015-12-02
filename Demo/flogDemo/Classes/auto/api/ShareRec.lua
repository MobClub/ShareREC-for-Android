
--------------------------------
-- @module ShareRec
-- @parent_module sharerec

--------------------------------
-- ÁÐ³ö±¾µØÒÑ¾­»º´æµÄÊÓÆµ(Lists the local videos.)
-- @function [parent=#ShareRec] listLocalVideos 
-- @param self
-- @param #long long list
-- @param #int len
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- Çå³ýShareRecµÄ»º´æÄ¿Â¼(Clears the cache folder of ShareRec.)
-- @function [parent=#ShareRec] clearCache 
-- @param self
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- Í£Ö¹Â¼ÖÆÄ£¿é (Stop the recorder module)
-- @function [parent=#ShareRec] stopRecorder 
-- @param self
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- ÏÔÊ¾Òì³£ÈÕÖ¾(Show exception loggs)
-- @function [parent=#ShareRec] setDebuggable 
-- @param self
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- ÔÝÍ£Â¼ÖÆÄ£¿é (Pause the recorder module)
-- @function [parent=#ShareRec] pauseRecorder 
-- @param self
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- ÉèÖÃÊÓÆµÃèÊö(Sets the description of the video.)
-- @function [parent=#ShareRec] setText 
-- @param self
-- @param #char text
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- Æô¶¯Â¼ÖÆÄ£¿é (Start the recorder module)
-- @function [parent=#ShareRec] startRecorder 
-- @param self
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- Ìí¼ÓÊÓÆµµÄ×Ô¶¨ÒåÊôÐÔ(Adds the custom attributes of the video.)
-- @function [parent=#ShareRec] addCustomAttr 
-- @param self
-- @param #char key
-- @param #char value
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- ÏÔÊ¾·ÖÏíÒ³Ãæ(Shows the share page.)
-- @function [parent=#ShareRec] showShare 
-- @param self
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- ÉèÖÃ×î´óÂ¼ÆÁ·Ö±æÂÊ£¨0--£¨480*360£©1--£¨1280*720£©2--£¨1920*1080£©
-- @function [parent=#ShareRec] setMaxFrameSize 
-- @param self
-- @param #int level
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- ÉèÖÃÊÇ·ñÐèÒªÂ¼ÖÆÉùÒô
-- @function [parent=#ShareRec] setRecordAudio 
-- @param self
-- @param #unsigned char isRecordAudio
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- ÉèÖÃÐèÒªÂ¼ÖÆµÄ×î¶ÌÊ±¼ä
-- @function [parent=#ShareRec] setMinDuration 
-- @param self
-- @param #long long duration
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- ´ò¿ªÊÓÆµÖÐÐÄ(Shows the video center.)
-- @function [parent=#ShareRec] showVideoCenter 
-- @param self
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- ÏÔÊ¾ÓÃ»§×ÊÁÏ(Shows the user profile.)
-- @function [parent=#ShareRec] showProfile 
-- @param self
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- Í¨¹ý»º´æµÄÊÓÆµID»ñÈ¡±¾µØÂ·¾¶(Gets the local video path by its ID.)
-- @function [parent=#ShareRec] getLocalVideoPath 
-- @param self
-- @param #long long videoId
-- @param #char path
-- @return bool#bool ret (return value: bool)
        
--------------------------------
-- ÉèÖÃÊÇ·ñÊ¹ÓÃopengl3.0 µÄ·½Ê½Â¼ÆÁ.
-- @function [parent=#ShareRec] setUseES3 
-- @param self
-- @param #unsigned char bES3
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- »Ö¸´Â¼ÖÆÄ£¿é (Resume the recorder module)
-- @function [parent=#ShareRec] resumeRecorder 
-- @param self
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- ÅÐ¶ÏShareRecÊÇ·ñÖ§³Öµ±Ç°µÄÉè±¸(Determines whether ShareRec is available for the current device.)
-- @function [parent=#ShareRec] isAvailable 
-- @param self
-- @return bool#bool ret (return value: bool)
        
--------------------------------
-- É¾³ý»º´æÊÓÆµ(Deletes the local video by its ID.)
-- @function [parent=#ShareRec] deleteLocalVideo 
-- @param self
-- @param #long long videoId
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
--------------------------------
-- ÉèÖÃÉè¼Æ·Ö±æÂÊ (Set design resolution)
-- @function [parent=#ShareRec] setDesignResolution 
-- @param self
-- @return ShareRec#ShareRec self (return value: sharerec.ShareRec)
        
return nil
