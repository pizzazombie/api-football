package com.adidas.apifootball.service;

import com.adidas.apifootball.model.Team;
import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ApiFootballDashboardService {

    @Value("${api-host}")
    String BASE_URL;

    @Value("${api-key}")
    String API_KEY;

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

    public List<Team> getTeam(String id) throws IOException {

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
        Response response = call.execute();

        List<Team> teams = convertHttpResponseToTeamsList(response);
        return teams;
    }


    public List<Team> searchByName(String name) throws IOException {

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
        Response response = call.execute();



        List<Team> teams = convertHttpResponseToTeamsList(response);
        return teams;

    }
    private JSONObject convertHttpResponseToJSONObject(Response response) throws IOException {
        String responseString = Objects.requireNonNull(response.body()).string();
        JSONObject obj = new JSONObject(responseString);
        JSONArray array = obj.getJSONArray("response");
        return array.getJSONObject(0).getJSONObject("team");
    }
    private List<Team> convertHttpResponseToTeamsList(Response response) throws IOException {
        Gson gson = new Gson();
        List<Team> teams = new ArrayList<>();
        String responseString = Objects.requireNonNull(response.body()).string();
        JSONObject obj = new JSONObject(responseString);
        JSONArray array = obj.getJSONArray("response");
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonTeam = array.getJSONObject(i).getJSONObject("team");
            Team team = gson.fromJson(jsonTeam.toString(), Team.class);
            teams.add(team);
        }
        return teams;
    }

}
