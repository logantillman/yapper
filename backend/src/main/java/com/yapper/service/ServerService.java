package com.yapper.service;

import com.yapper.domain.ServerDTO;
import com.yapper.entity.Server;
import com.yapper.repository.ServerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@AllArgsConstructor
@Service
public class ServerService {

    private final ServerRepository serverRepository;

    public void createServer(String serverName) {
        var server = Server.builder()
                .name(serverName)
                .createdTime(LocalDateTime.now(ZoneOffset.UTC))
                .active(true)
                .build();
        serverRepository.save(server);
        log.info("Server \"{}\" created with id: {}", server.getName(), server.getId());
    }

    public boolean updateServer(String serverId, ServerDTO serverDTO) {
        var server = serverRepository.findById(Integer.valueOf(serverId));

        if (server.isEmpty() || serverDTO.getAction().isEmpty()) {
            log.info("Failed to update server {}", serverId);
            return false;
        }

        var foundServer = server.get();

        switch (serverDTO.getAction()) {
            case "updateName" -> {
                if (serverDTO.getName() != null && !serverDTO.getName().isEmpty()) {
                    foundServer.setName(serverDTO.getName());
                    log.info("Attempting to update server name for {}", serverId);
                }
            }
            case "deleteServer" -> {
                foundServer.setActive(false);
                log.info("Attempting to delete server: {}", serverId);
            }
            default -> {
                return false;
            }
        }

        serverRepository.save(foundServer);
        log.info("Successfully performed {} on server {}", serverDTO.getAction(), serverId);
        return true;
    }

    public boolean deleteServer(String serverId) {
        var server = serverRepository.findById(Integer.valueOf(serverId));

        if (server.isEmpty()) {
            log.info("No server with id {} found", serverId);
            return false;
        }

        var foundServer = server.get();
        foundServer.setActive(false);
        serverRepository.save(foundServer);
        log.info("Deleted server: {}", serverId);
        return true;
    }
}
