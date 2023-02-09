package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.services.InfoService;


@RestController
public class InfoController  {

    private final InfoService infoService;
    private static final Logger logger = LoggerFactory.getLogger(InfoService.class);

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping(path = "/getPort")   // port - 8080
    public ResponseEntity<Integer> getPortInfo() {

        return ResponseEntity.ok(infoService.portNumber());
    }
}
