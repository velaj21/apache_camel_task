package com.example.demo.services.Task3;

import com.example.demo.services.Task2.UserJSONEntity;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;


@Component
class MessageListenerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("rabbitmq://localhost:5672/TestExchange?username=guest&password=guest&autoDelete=false&queue=TestQueue")
                .log("Received message: ${body}")
                .unmarshal()
                .json(JsonLibrary.Jackson, UserJSONEntity.class)
                .process(exchange -> {
                    UserJSONEntity myMessage = exchange.getIn().getBody(UserJSONEntity.class);
                    System.out.println(myMessage);
                });
    }
}