package com.adidas.apifootball.controller;

import com.adidas.apifootball.exception.DashboardException;
import com.adidas.apifootball.entity.Team;
import com.adidas.apifootball.service.ApiFootballDashboardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/team")
    public String redirectTeamToHome(Model model) {
        return "home";
    }

    @GetMapping("/team/{id}")
    public String getTeamInfo(@PathVariable(name="id", required=true) String id, Model model) {
        String error = null;
        List<Team> teams = new ArrayList<>();
        try {
            teams = dashboardService.getTeamById(id);
        }catch (DashboardException e){
            error = e.getLocalizedMessage();
        }

        model.addAttribute("teams", teams );
        model.addAttribute("error", error);
        return "team";
    }

    @GetMapping("/team/search")
    public String searchTeams(@RequestParam(name="name", required=true) String name, Model model)  {
        String error = null;
        List<Team> teams = new ArrayList<>();
        try {
            teams = dashboardService.searchTeamsByNameOrCity(name);
        }catch (DashboardException e){
            error = e.getLocalizedMessage();
        }

        model.addAttribute("teams", teams );
        model.addAttribute("request", name);
        model.addAttribute("error", error);
        return "team";
    }

}
