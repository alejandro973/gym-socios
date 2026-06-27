package com.gym.socios.service;

import com.gym.socios.client.PlanesClient;
import com.gym.socios.dto.*;
import com.gym.socios.model.Socio;
import com.gym.socios.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional; // se usa este import para poder usar el optional<>

@Service
@Transactional
@RequiredArgsConstructor
public class SocioService {

    
    private final SocioRepository socioRepository;
    private final PlanesClient planesClient;


    //El método mapToDto es el "traductor" o "ensamblador" de tu servicio.
    //  Su función principal es tomar un objeto de la base de datos (la Entidad)
    //  y transformarlo en un objeto que el cliente pueda ver (el DTO).

    private SocioResponseDTO mapToDto(Socio s){
         String nombreP = "Sin Plan";
        String estadoP = "Inactivo";

        try { // PlanDTO es la clase y planExterno el objeto para poder usar sus getters
            PlanDTO planExterno = planesClient.buscarPlanPorRun(s.getRun());
            if (planExterno != null) {
                nombreP = planExterno.getNombrePlan();
                estadoP = planExterno.getEstadoPlan();
            }
            // Exception clase principal de errores y "e" nombre de la variable para guardar la informacion del error
        } catch (Exception e) {
            // Si el microservicio de planes no responde, el socio se muestra sin plan
            System.err.println("Error consultando ms-planes: " + e.getMessage());
        }
        // err. hace mencion a mensajes de error 
        // e.getMessage() es un metodo que vive dentro del objeto para dar informacion del error 

        return new SocioResponseDTO(
            s.getId(),
            s.getRun(),
            s.getNombre(),
            s.getApellido(),
            s.getCorreo(),
            s.getFechaNacimiento(),
            nombreP, // Viene de la llamada Feign
            estadoP  // Viene de la llamada Feign
        );
    }

    public List<SocioResponseDTO> obtenerTodos(){
        return socioRepository.findAll().stream() //.stream se usa filtrar uno a uno los elementos de una lista y asi aplicar el metodo .map
         .map(this::mapToDto).collect(Collectors.toList()); // .map transforma un socio a un SocioResponseDTO
    }   // this(busca en esta clase que estoy parado es decir, socioService) (:: usa el metodo a continuacion)
        
    // el optional devuelve una caja que puede o no tener un objeto
    public Optional<SocioResponseDTO> obtenerPorId(long id){
        return socioRepository.findById(id).map(this::mapToDto);
    }

    public Optional<SocioResponseDTO> buscarPorRun(String run) {
    return socioRepository.findByRun(run)
            .map(this::mapToDto); // Transforma la entidad Socio a SocioResponseDto
}

    public SocioResponseDTO guardarSocio(SocioRequestDTO dto){    // recibe un dto como parametro
        Socio s = new Socio(null,dto.getRun(),dto.getNombre(),dto.getApellido(), // lo convierte en objeto con el Socio s
        dto.getCorreo(),dto.getFechaNacimiento());
        return mapToDto(socioRepository.save(s)); // guarda el objeto actualizado con id generado por la base de datos y lo conviere en DTO 
 
    }

    public Optional<SocioResponseDTO> actualizarSocio(long id,SocioRequestDTO dto){
        // El primer 'return' entrega la "caja" (Optional) al mundo exterior.
       return  socioRepository.findById(id).map(existente ->{
            existente.setRun(dto.getRun());
            existente.setNombre(dto.getNombre());
            existente.setApellido(dto.getApellido());
            existente.setCorreo(dto.getCorreo());
            existente.setFechaNacimiento(dto.getFechaNacimiento());
            // El segundo 'return' guarda los cambios y mete el DTO dentro de la "caja".
            return mapToDto(socioRepository.save(existente));

        });
    }

    public void eliminarSocio(long id){
        socioRepository.deleteById(id);
    }

    public List<SocioResponseDTO> buscarPorCategoria(String categoria){
        return  socioRepository.findAll().stream().map(this::mapToDto) // llamamos y convertimos los socios en dto
        .filter(dto -> dto.getEstadoPlan().equalsIgnoreCase(categoria)) // filtramos (dto es un nombre temporal) egualsIgnoreCase da lo mismo si es minuscula o mayuscula
        .collect(Collectors.toList());

    }



}
