package co.com.ceiba.data;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document
public class VehiculoData {

    @Id
    private  String placa;

    private  String tipo;

    private  LocalDateTime horaIngreso;

    private  Integer cilindraje;
}
