package com.example.demo.services.Task2;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class MarshallFiles extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:/home/user/Desktop/demo/src/main/resources?noop=true&fileName=data.csv")
                .log("Data received!")
                .unmarshal().bindy(BindyType.Csv, UserEntity.class)
                .log("Data unmarshalled")
                .marshal().json(JsonLibrary.Jackson)
                .log("${body}");
    }
}
