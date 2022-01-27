package com.adidas.apifootball.service;

import com.adidas.apifootball.model.Team;

import java.util.List;

/**
 * Service for working with api-football.com API and receiving data about teams
 */
public interface ApiFootballDashboardService {

    /**
     * Get team info
     * @param id - team identification
     * @return - List which contain 1 or 0 teams (if doesn't exist)
     */
    List<Team> getTeamById(String id);

    /**
     * Requesting to api-football.com for searching teams, where team name or city contains query
     * @param name - query for searching
     * @return - List of found teams, size can be 0)
     */
    List<Team> searchTeamsByNameOrCity(String name);
}
