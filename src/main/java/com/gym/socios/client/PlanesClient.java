package com.gym.socios.client;

import com.gym.socios.dto.PlanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El nombre "ms-planes" es el que le pondrás al otro servicio después
@FeignClient(name = "planes", url = "localhost:8081") 
public interface PlanesClient {

    @GetMapping("/api/planes/buscar/{run}")
    PlanDTO buscarPlanPorRun(@PathVariable("run") String run);
}