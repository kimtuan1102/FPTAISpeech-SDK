package ai.fpt.speech;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;

import ai.fpt.speech.callback.RecognizeCallBack;
import ai.fpt.speech.model.RecognizeResponse;
import ai.fpt.speech.service.ServiceImpl;


public class SpeechClient {
    private static MediaRecorder mRecorder;
    private static SpeechClient speechClient = null;
    private static RecognizeCallBack callBack;
    private static SpeechClientSettings _speechClientSettings;
    public static SpeechClient create(RecognizeCallBack recognizeCallBack, SpeechClientSettings speechClientSettings) {
        if (speechClient == null) {
            speechClient = new SpeechClient();
        }
        callBack = recognizeCallBack;
        _speechClientSettings = speechClientSettings;
        return speechClient;
    }
    public void startRecording() {
        String mFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.wav";
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mRecorder.setOutputFile(mFileName);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e("app", "prepare() failed");
        }
        mRecorder.start();
    }
    public File stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.wav");
    }
    public void recognize(File audio) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServiceImpl service = new ServiceImpl();
                    String utterance = service.speechToText(audio, _speechClientSettings);
                    RecognizeResponse recognizeResponse = service.predict(utterance, _speechClientSettings);
                    if (callBack != null) {
                        callBack.onRecognizedSuccess(recognizeResponse);
                    }
                } catch (IOException | JSONException e) {
                    if (callBack != null) {
                        callBack.onRecognizedFailed();
                    }
                    Log.e("app", e.toString());
                }
            }
        });
        thread.start();
    }
}
