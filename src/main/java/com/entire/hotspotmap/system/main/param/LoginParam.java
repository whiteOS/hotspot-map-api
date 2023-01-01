package com.entire.hotspotmap.system.main.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginParam implements Serializable {
    private String username;

    private String password;
}
