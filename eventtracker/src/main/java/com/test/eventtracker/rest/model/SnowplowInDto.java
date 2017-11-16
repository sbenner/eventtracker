package com.test.eventtracker.rest.model;

import lombok.Data;

import java.util.List;

@Data
public class SnowplowInDto {
    String schema;
    List<SnowplowDto> data;
}
