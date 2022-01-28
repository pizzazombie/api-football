package com.adidas.apifootball.service;

import com.adidas.apifootball.entity.Team;
import java.util.List;

/**
 * Service for converting Response from api-football.com to list of Teams
 */
public interface ApiFootballDashboardService {

    /**
     * Get team info by id
     * @param id - team identifier
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
