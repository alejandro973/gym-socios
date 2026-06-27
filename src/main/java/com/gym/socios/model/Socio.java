package com.gym.socios.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name = "socio")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Socio {

 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @NotBlank(message = "Es necesario un RUN para el registro")
    @Column(unique = true,length = 13,nullable = false)
    private String run;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 12,message = "El maximo son 12 caracteres")
    @Column(length = 12,nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    @Size(max = 12,message = "El maximo son 12 caracteres")
    @Column(length = 12,nullable = false)
    private String apellido;

    @NotBlank(message = "Debe ingresar un correo")
    @Size(max = 30,message = "El maximo son 30 caracteres")
    @Column(length = 30,nullable = false)
    private String correo;

    @NotNull(message = "Ingrese una fecha valida")
    @Past(message = "Ingrese una fecha anterior") // esto es para que el usuario ingrese una fecha anterior a la actual
    @Column(nullable = false)
    private Date fechaNacimiento;
}
