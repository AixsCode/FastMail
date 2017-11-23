package com.wxs.fastmail.Events;

/**
 * Created by wxs on 2017/8/1.
 */

public class VoiceEvent {

    private String voiceflag;
    private String voiceresult;

    public VoiceEvent(String voiceflag, String voiceresult)
    {
        this.voiceflag = voiceflag;
        this.voiceresult = voiceresult;
    }

    public VoiceEvent() {
    }

    public void setVoiceflag(String voiceflag) {
        this.voiceflag = voiceflag;
    }

    public void setVoiceresult(String voiceresult) {
        this.voiceresult = voiceresult;
    }

    public String getVoiceflag() {
        return voiceflag;
    }

    public String getVoiceresult() {
        return voiceresult;
    }
}
