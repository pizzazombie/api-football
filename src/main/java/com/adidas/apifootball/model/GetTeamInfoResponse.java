package com.adidas.apifootball.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetTeamInfoResponse {
    public List<ResponseArray> response = new ArrayList<>();

    public List<ResponseArray> getResponse() {
        return response;
    }
}
