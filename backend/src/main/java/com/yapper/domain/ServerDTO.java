package com.yapper.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServerDTO {
    @JsonProperty("action")
    private String action;

    @JsonProperty("name")
    private String name;

    private LocalDateTime createdTime;

    private boolean active;
}
