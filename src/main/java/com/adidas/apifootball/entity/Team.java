package com.adidas.apifootball.entity;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
public class Team {

    long id;
    String country;
    long founded;
    boolean national;
    String logo;
    String name;

    public Team(JSONObject jsonTeam) {

        this.id = jsonTeam.getLong("id");
        if (jsonTeam.get("country") != null)
            this.country = jsonTeam.getString("country");
        if (jsonTeam.get("founded") != null)
            this.founded = jsonTeam.getLong("founded");
        if (jsonTeam.get("national") != null)
            this.national = jsonTeam.getBoolean("national");
        if (jsonTeam.get("logo") != null)
            this.logo = jsonTeam.getString("logo");
        if (jsonTeam.get("name") != null)
            this.name = jsonTeam.getString("name");

    }


    public long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public long getFounded() {
        return founded;
    }

    public boolean isNational() {
        return national;
    }

    public String getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }
}
