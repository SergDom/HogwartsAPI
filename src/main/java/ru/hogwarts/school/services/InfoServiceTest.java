package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Service
@Profile("!production")
public class InfoServiceTest implements InfoService{
   @Value("${server.port}")
    private Integer portServer;
    private static final Logger logger = LoggerFactory.getLogger(InfoService.class);
    @Override
    public Integer portNumber() {
        return portServer;
    }

    @Override
    public Long getValue() {
        logger.info("Was invoked method to find sum");
        long start = System.currentTimeMillis();
        long num = 1_000_000;
        long sum = LongStream
                .range(1, num + 1)
                .sum();
        long finish = System.currentTimeMillis();
        long elapsedTime = finish - start;
        logger.info("Time spent, ms: " + elapsedTime);
        return sum;
    }
}
