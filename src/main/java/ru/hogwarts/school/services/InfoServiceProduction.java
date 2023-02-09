package ru.hogwarts.school.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("production")
public class InfoServiceProduction implements InfoService {

    @Value("${server.port}")
    private Integer portServer;
    @Override
    public Integer portNumber() {
        return portServer;
    }
}
