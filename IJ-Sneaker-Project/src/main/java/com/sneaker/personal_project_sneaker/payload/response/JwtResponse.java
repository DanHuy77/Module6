package com.sneaker.personal_project_sneaker.payload.response;

import com.sneaker.personal_project_sneaker.entity.Customer;

import java.util.List;

public class JwtResponse {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
