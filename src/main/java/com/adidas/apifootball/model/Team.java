package com.adidas.apifootball.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
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



}
