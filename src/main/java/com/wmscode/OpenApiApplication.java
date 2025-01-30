package com.wmscode;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

import jakarta.ws.rs.core.Application;

@OpenAPIDefinition(
    info = @Info(
        title="Pedidos Guitar Shop API",
        version = "V1.0.0",
        contact = @Contact(
                name = "Pedidos Guitar Shop",
                url = "https://wmcode.com/",
                email = "support@wmscode.com"),
        license = @License(
                name = "MIT License",
                url = "https://www.mit.edu/~amini/LICENSE.md")))
public class OpenApiApplication extends Application { }
