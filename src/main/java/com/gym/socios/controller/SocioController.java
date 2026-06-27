package com.gym.socios.controller;
import com.gym.socios.dto.SocioRequestDTO;
import com.gym.socios.dto.SocioResponseDTO;
import com.gym.socios.service.SocioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/socios")
@RequiredArgsConstructor
@Tag(name = "Socios", description = "Operaciones relacionadas con Socios")
public class SocioController {

    private final SocioService socioService;

    @GetMapping
    @Operation(summary = "Obtener todos los socios",description = "Obtiene un listado de todos los socios")
    public List<SocioResponseDTO> listarTodos() {
        return socioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener socio por id",description = "Obtiene un socio por su id")
    public ResponseEntity <SocioResponseDTO> buscarPorId(@PathVariable Long id) {
        return socioService.obtenerPorId(id) // 1. Llama al servicio
        .map(ResponseEntity::ok) // 2. Si existe el objeto y da un mensaje http 200 -> 200 OK
        .orElse(ResponseEntity.notFound().build()); // 3. Si no -> 404 Not Found , el build() cierra el sobre vacio y lo envia
    }

@GetMapping("/buscar/{run}")
@Operation(summary = "Obtener socio por rut",description = "Obtiene un socio por su rut")
public ResponseEntity<SocioResponseDTO> obtenerPorRun(@PathVariable String run) {
    return socioService.buscarPorRun(run)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un socio",description = "Eliminar un socio por su id")
    public ResponseEntity<Void> borrarSocio(@PathVariable Long id){
         socioService.eliminarSocio(id); // ACCIÓN 1: El servicio borra en la base de datos.
         return ResponseEntity.noContent().build(); // ACCIÓN 2: El controlador construye y envía la respuesta.
    }

    @PostMapping
    @Operation(summary = "Crear un socio nuevo",description = "Crear un socio nuevo por medio de un dto")
    public ResponseEntity<SocioResponseDTO> crearSocio(@Valid @RequestBody SocioRequestDTO socio) { // Valid para validar el NotNull y NotBlank, RequestBody convertir un JSON en objeto
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(socioService.guardarSocio(socio));
        
        
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un socio",description = "Actualizar socio por medio de id y dto")
    public ResponseEntity<SocioResponseDTO> actualizarSocio(@PathVariable Long id, @Valid @RequestBody SocioRequestDTO socio) {
       return socioService.actualizarSocio(id, socio)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/filtro")
    @Operation(summary = "Buscar socio por estado de plan",description = "Buscar socio por estado activo o inactivo")
    public List<SocioResponseDTO> buscarPorEstado(@RequestParam String estado){
        return socioService.buscarPorCategoria(estado);


    }
    
    // http://localhost:8080/api/socios/filtro?estado=Activo busqueda en postman
    
}
