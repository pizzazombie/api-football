package com.adidas.apifootball.service;

import com.adidas.apifootball.model.Team;

import java.util.List;

public interface ApiFootballDashboardService {
    public List<Team> getTeamById(String id);

    public List<Team> searchTeamsByNameOrCity(String name);
}
