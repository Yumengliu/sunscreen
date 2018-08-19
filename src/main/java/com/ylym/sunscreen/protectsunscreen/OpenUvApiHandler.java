package com.ylym.sunscreen.protectsunscreen;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OpenUvApiHandler {

  private static final String API_KEY = "c015ac9443354da8f82d96188de36122";
  private static final String BASE_URL = "https://api.openuv.io/api/v1/uv?";
  private static final String LAT_KEY = "lat";
  private static final String LON_KEY = "lng";

  private OkHttpClient client;

  public OpenUvApiHandler() {
    client = new OkHttpClient();
  }

  public JSONObject RequestBasedOnLocation(Double lat, Double lon) throws IOException {
    Request request = new Request.Builder()
        .url(buildRequestUrl(lat, lon))
        .get()
        .addHeader("x-access-token", API_KEY)
        .build();
    Response response = client.newCall(request).execute();
    return new JSONObject(response.body().string());
  }

  private String buildRequestUrl(Double lat, Double lon) {
    HttpUrl requestUrl = HttpUrl.parse(BASE_URL).newBuilder()
        .addQueryParameter(LAT_KEY, lat.toString())
        .addQueryParameter(LON_KEY, lon.toString()).build();
    return requestUrl.toString();
  }
}
