package ai.fpt.speech;

import android.content.pm.ApplicationInfo;

public class SpeechClientSettings {
    private String voiceAPIKey;
    private String voiceEndpoint;
    private String nlpToken;
    private String nlpEndPoint;

    public String getVoiceAPIKey() {
        return voiceAPIKey;
    }

    public void setVoiceAPIKey(String voiceAPIKey) {
        this.voiceAPIKey = voiceAPIKey;
    }

    public String getVoiceEndpoint() {
        return voiceEndpoint;
    }

    public void setVoiceEndpoint(String voiceEndpoint) {
        this.voiceEndpoint = voiceEndpoint;
    }

    public String getNlpToken() {
        return nlpToken;
    }

    public void setNlpToken(String nlpToken) {
        this.nlpToken = nlpToken;
    }

    public String getNlpEndPoint() {
        return nlpEndPoint;
    }

    public void setNlpEndPoint(String nlpEndPoint) {
        this.nlpEndPoint = nlpEndPoint;
    }
    public SpeechClientSettings buildSettings(ApplicationInfo applicationInfo){
        String _voiceAPIKey = applicationInfo.metaData.getString("ai.fpt.voicebot.API_KEY");
        String _voiceEndPoint = applicationInfo.metaData.getString("ai.fpt.voicebot.ENPOINT");
        String _nlpToken = applicationInfo.metaData.getString("ai.fpt.nlp.TOKEN");
        String _nlpEndPoint = applicationInfo.metaData.getString("ai.fpt.nlp.ENPOINT");
        this.setVoiceAPIKey(_voiceAPIKey);
        this.setVoiceEndpoint(_voiceEndPoint);
        this.setNlpToken(_nlpToken);
        this.setNlpEndPoint(_nlpEndPoint);
        return this;
    }
}
