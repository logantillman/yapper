package com.yapper.controller;

import com.yapper.domain.ServerDTO;
import com.yapper.entity.Server;
import com.yapper.repository.ServerRepository;
import com.yapper.service.ServerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class ServerController {
    private final ServerRepository serverRepository;
    private final ServerService serverService;

    @GetMapping("/servers")
    public ResponseEntity<List<Server>> getServers() {
        return new ResponseEntity<>(serverRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/servers")
    public ResponseEntity<Server> createServer(@RequestBody ServerDTO server) {
        serverService.createServer(server.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/servers/{id}")
    public ResponseEntity<Server> updateServer(@PathVariable String id, @RequestBody ServerDTO server) {
        return serverService.updateServer(id, server)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
