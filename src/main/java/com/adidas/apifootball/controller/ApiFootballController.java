package com.adidas.apifootball.controller;

import com.adidas.apifootball.model.GetTeamInfoResponse;
import com.adidas.apifootball.model.Team;
import com.adidas.apifootball.service.ApiFootballDashboardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ApiFootballController {

    @Autowired
    ApiFootballDashboardService dashboardService;

    @GetMapping("/")
    public String getStatus( Model model) {

        return "home";
    }

    @GetMapping("/team/{id}")
    public String getTeamInfo(@PathVariable(name="id", required=true) String id, Model model) {
        List<Team> teams = new ArrayList<>();
        try {
            teams = dashboardService.getTeam(id);
        } catch (IOException e) {
            e.printStackTrace();
        }



        model.addAttribute("teams", teams );
        return "team";
    }

    @GetMapping("/team/search")
    public String searchTeams(@RequestParam(name="name", required=true) String name, Model model)  {
        List<Team> teams = new ArrayList<>();
        try {
            teams = dashboardService.searchByName(name);
        } catch (IOException e) {
            e.printStackTrace();
        }


        model.addAttribute("teams", teams );
        return "team";
    }
}
