package com.yapper.controller;

import com.yapper.entity.Server;
import com.yapper.repository.ServerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ServerController {
    private final ServerRepository serverRepository;

    public ServerController(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @GetMapping(value="/servers")
    public ResponseEntity<List<Server>> getServers() {
        return new ResponseEntity<>(serverRepository.findAll(), HttpStatus.OK);
    }
}
