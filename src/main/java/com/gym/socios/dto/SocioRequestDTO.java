package com.gym.socios.dto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

// el Dto Request maneja las solicitudes put y post es lo que el cliente editara o actualizara por eso
//  no se incluye el id ya que este lo genera automaticamente la base de datos



@AllArgsConstructor
@NoArgsConstructor
@Data
public class SocioRequestDTO {

    @NotBlank (message = "El run no puede estar vacio")
    private String run;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    private String apellido;

    @NotBlank(message = "El correo no puede estar vacio")
    @Email
    private String correo;

    @NotNull
    private Date fechaNacimiento;


}
