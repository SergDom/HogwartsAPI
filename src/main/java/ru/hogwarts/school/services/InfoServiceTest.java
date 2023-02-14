package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

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
    public Integer getValue() {
        logger.info("Was invoked method to find sum");
        return IntStream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, Integer::sum);
    }
}
