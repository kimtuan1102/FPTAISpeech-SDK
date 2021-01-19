package ai.fpt.speech.service;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import ai.fpt.speech.SpeechClientSettings;
import ai.fpt.speech.model.RecognizeResponse;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServiceImpl implements Service{
    @Override
    public String speechToText(File audio, SpeechClientSettings speechClientSettings) throws IOException, JSONException {
        String voiceEndPoint = speechClientSettings.getVoiceEndpoint();
        String voiceAPIKey = speechClientSettings.getVoiceAPIKey();
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), audio);
        Request request = new Request.Builder()
                .url(voiceEndPoint)
                .addHeader("api_key", voiceAPIKey)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        Log.d("app", response.toString());
        String json = response.body().string();
        Log.d("app", json);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray hypotheses = jsonObject.getJSONArray("hypotheses");
        String utterance = hypotheses.getJSONObject(0).getString("utterance");
        return utterance;
    }

    @Override
    public RecognizeResponse predict(String content, SpeechClientSettings speechClientSettings) throws IOException, JSONException {
        String nlpToken = speechClientSettings.getNlpToken();
        String nlpEndPoint = speechClientSettings.getNlpEndPoint();
        OkHttpClient client = new OkHttpClient();
        JSONObject bodyObj = new JSONObject();
        bodyObj.put("content", content);
        bodyObj.put("save_history", false);
        String jsonBody = bodyObj.toString();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonBody);
        Request request = new Request.Builder()
                .url(nlpEndPoint)
                .addHeader("Authorization", "Bearer " + nlpToken)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        Log.d("app", response.toString());
        String json = response.body().string();
        JSONObject jsonObject = new JSONObject(json);
        JSONObject data = jsonObject.getJSONObject("data");
        Gson gson = new Gson();
        RecognizeResponse recognizeResponse = gson.fromJson(data.toString(), RecognizeResponse.class);
        return recognizeResponse;
    }
}
