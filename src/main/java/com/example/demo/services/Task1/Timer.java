package com.example.demo.services.Task1;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.function.Predicate;

// Using a bean configuration
@Component
class Timer1 extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:counter?period=1000").
                bean("getMessageBean").
                to("log:counter");
    }
}

@Component
class GetMessageBean {
    @Bean
    public String getTime() {
        return "Hello World";
    }
}

@Component
public class Timer extends RouteBuilder {
    private Integer RandomNumber;
    private boolean isFirstTime = true;
    private Long firstTime;

    private final Predicate<Integer> isEqualPredicate = value -> value.equals(RandomNumber);

    public void makeFirstTimeConf() {
        firstTime = GetCurrentTime.getTime();  // Save the first time
        isFirstTime = false;  // Set the flag to false
        RandomNumber = new Random().nextInt(6);
    }

    public void changeNumber() {
        firstTime = GetCurrentTime.getTime();
        RandomNumber = new Random().nextInt(6);
    }

    @Override
    public void configure() {
        from("stream://in?promptMessage=Enter your guess:")
                .process(exchange -> {
                    if (isFirstTime) {
                        makeFirstTimeConf();
                    }
                    Integer guess = exchange.getIn().getBody(Integer.class); // Get the user input as integer
                    if (isEqualPredicate.test(guess)) {
                        exchange.getIn().setBody("You found it! Total time is: " + ((GetCurrentTime.getTime() - firstTime) / 1000.0));
                        changeNumber();
                    }
                })
                .to("stream://out");
    }
}

class GetCurrentTime {
    public static Long getTime() {
        return System.currentTimeMillis();
    }
}
