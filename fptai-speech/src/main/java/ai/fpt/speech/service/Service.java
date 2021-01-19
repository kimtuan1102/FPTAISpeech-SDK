package ai.fpt.speech.service;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;

import ai.fpt.speech.SpeechClientSettings;
import ai.fpt.speech.model.RecognizeResponse;

public interface Service {
    String speechToText(File audio, SpeechClientSettings speechClientSettings) throws IOException, JSONException;
    RecognizeResponse predict(String content, SpeechClientSettings speechClientSettings) throws IOException, JSONException;
}
