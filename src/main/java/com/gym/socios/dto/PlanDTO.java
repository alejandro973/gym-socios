package com.gym.socios.dto;

import lombok.Data;

@Data
public class PlanDTO {
    // Estos nombres deben coincidir con lo que el otro microservicio enviará
    private String nombrePlan;
    private String estadoPlan; 
}