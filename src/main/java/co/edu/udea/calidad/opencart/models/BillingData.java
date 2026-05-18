package co.edu.udea.calidad.opencart.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillingData {

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String pais;
    private String departamento;

    public static BillingData fromMap(Map<String, String> row) {
        return BillingData.builder()
                .nombre(row.get("nombre"))
                .apellido(row.get("apellido"))
                .email(row.get("email"))
                .telefono(row.get("telefono"))
                .direccion(row.get("direccion"))
                .ciudad(row.get("ciudad"))
                .pais(row.get("pais"))
                .departamento(row.get("departamento"))
                .build();
    }
}
