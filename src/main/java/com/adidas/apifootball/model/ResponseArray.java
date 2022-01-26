package com.adidas.apifootball.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseArray {
    Team team;
    Object venue;

    public Team getTeam() {
        return team;
    }
}
