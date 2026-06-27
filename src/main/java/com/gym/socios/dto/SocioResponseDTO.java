package com.gym.socios.dto;
import lombok.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class SocioResponseDTO {

    private Long id;
    private String run;
    private String nombre;
    private String apellido;
    private String correo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;


    private String nombrePlan;
    private String estadoPlan;
   
}
