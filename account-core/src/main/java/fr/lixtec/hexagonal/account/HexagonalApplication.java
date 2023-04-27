package fr.lixtec.hexagonal.account;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Quarkus Hexagonal",
                version = "0.0.1")
)
/**
 * Cette classe assure le bootstrap de l'application
 * 
 * @author Ludovic.Terral
 */
public class HexagonalApplication extends jakarta.ws.rs.core.Application 
{
}