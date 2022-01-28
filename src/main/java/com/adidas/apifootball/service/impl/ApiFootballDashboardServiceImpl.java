package com.adidas.apifootball.service.impl;

import com.adidas.apifootball.exception.DashboardException;
import com.adidas.apifootball.model.Team;
import com.adidas.apifootball.service.ApiFootballDashboardService;
import com.adidas.apifootball.service.DashboardClient;
import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@Service
public class ApiFootballDashboardServiceImpl implements ApiFootballDashboardService {

    private static final Logger log = Logger.getLogger(ApiFootballDashboardServiceImpl.class.getName());

    @Value("${api-host}")
    String BASE_URL;

    @Value("${api-key}")
    String API_KEY;

    @Autowired DashboardClient dashboardClient;

    public List<Team> getTeamById(String id) {

        Response response = dashboardClient.getTeamById(id);
        List<Team> teams = convertHttpResponseToTeamsList(response);
        return teams;
    }


    public List<Team> searchTeamsByNameOrCity(String name)  {

        Response response = dashboardClient.searchTeamsByNameOrCity(name);
        return convertHttpResponseToTeamsList(response);
    }

    //  Object "errors" can be either json array or json object,
    //  this method checks it and throws exception with error if exist
    private void checkResponseForErrors(JSONObject obj){
        Object errors = obj.get("errors");
        if (errors instanceof JSONArray ){
            JSONArray array = (JSONArray) errors;
            if (!array.isEmpty()) {
                throw new DashboardException(array.getString(0));
            }
        }else {
            JSONObject jsonObject = (JSONObject) errors;
            throw new DashboardException(jsonObject.toString());
        }
    }

    private List<Team> convertHttpResponseToTeamsList(Response response) {
        Gson gson = new Gson();
        List<Team> teams = new ArrayList<>();
        String responseString = null;
        try {
            responseString = Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            log.info("Error while converting json response");
            e.printStackTrace();
        }
        JSONObject obj = new JSONObject(responseString);
        JSONArray array = obj.getJSONArray("response");
        if (array.isEmpty())
            checkResponseForErrors(obj);
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonTeam = array.getJSONObject(i).getJSONObject("team");
            Team team = gson.fromJson(jsonTeam.toString(), Team.class);
            teams.add(team);
        }
        return teams;
    }

}
