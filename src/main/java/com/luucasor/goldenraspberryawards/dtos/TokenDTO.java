package com.luucasor.goldenraspberryawards.dtos;

import io.swagger.annotations.ApiModelProperty;

public class TokenDTO {

    @ApiModelProperty(notes = "token", example = "eyJhbGciOiJIUzI1NiI9.eyJpYXQiOjE2NjE3ODU3NTMsImV4cCI6MTY1MTc5Ojk1MywidXNlcklkIjoxLCJlbWFpbCI6Ipo1dWNhc19yQGdtYWlsLmNvbSIsImZpcnN0TmFtZSI6Ikx1Y2FzIiwibGFzRE2hbWUiOiJPcnRpZ2FyUSG9.RY-64cOPyjtrA3_rVz8xSK4epN5YTeZLbcpsTbcMZp0", required = true)
    private String token;

    public TokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
