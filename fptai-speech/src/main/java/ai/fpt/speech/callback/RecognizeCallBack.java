package ai.fpt.speech.callback;

import ai.fpt.speech.model.RecognizeResponse;

public interface RecognizeCallBack {
    void onRecognizedSuccess(RecognizeResponse recognizeResponse);
    void onRecognizedFailed();
}
