package com.adidas.apifootball.service;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * This client make requests to api-football.com
 */
@Service
public class DashboardClient {

    private static final Logger log = Logger.getLogger(DashboardClient.class.getName());

    @Value("${api-host}")
    String BASE_URL;

    @Value("${api-key}")
    String API_KEY;

    public Response getTeamById(String id){

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().build();
        HttpUrl.Builder queryUrlBuilder = HttpUrl.get(BASE_URL + "/teams").newBuilder();
        queryUrlBuilder.addQueryParameter("id", id);
        Request request = new Request.Builder()
                .url(queryUrlBuilder.build())
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", BASE_URL)
                .post(requestBody)
                .build();

        Call call =  client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            log.info(String.format("Request to %s/teams with parameter id=%s", BASE_URL, id));
            e.printStackTrace();
        }
        return response;
    }

    public Response searchTeamsByNameOrCity(String name){

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().build();
        HttpUrl.Builder queryUrlBuilder = HttpUrl.get(BASE_URL + "/teams").newBuilder();
        queryUrlBuilder.addQueryParameter("search", name);
        Request request = new Request.Builder()
                .url(queryUrlBuilder.build())
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", BASE_URL)
                .post(requestBody)
                .build();

        Call call =  client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            log.severe(String.format("Unable to make a request to %s", BASE_URL));
            e.printStackTrace();
        }
        log.info(String.format("Request to %s/teams with parameter search=%s", BASE_URL, name));
        return  response;
    }

    public String getStatus() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(BASE_URL + "/status")
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", BASE_URL)
                .build();

        Call call =  client.newCall(request);
        Response response = call.execute();

        return Objects.requireNonNull(response.body()).string();
    }
}
