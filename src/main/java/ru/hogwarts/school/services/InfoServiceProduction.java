package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@Profile("production")
public class InfoServiceProduction implements InfoService {
    private static final Logger logger = LoggerFactory.getLogger(InfoService.class);
    @Value("${server.port}")
    private Integer portServer;

    @Override
    public Integer portNumber() {
        return portServer;
    }

    @Override
    public Long getValue() {
        logger.info("Was invoked method to find sum");
        long start = System.currentTimeMillis();
//         int sum = IntStream.iterate(1, a -> a + 1)
//                .limit(1_000_000)
//                .parallel()
//                .reduce(0, Integer::sum);

//        int sum = Stream
//                .iterate(1, a -> a +1)
//                .limit(1_000_000)
//                .mapToInt(Integer::intValue)
//                .sum();

        long num = 1_000_000;
//        int sum = IntStream
//                .range(1, num + 1)
//                .sum();

        long sum = ((1 + num) * num) / 2;
        long finish = System.currentTimeMillis();
        long elapsedTime = finish - start;
        logger.info("Time spent, ms: " + elapsedTime);
        return sum;
    }
}
