package com.ehpadManagement.userManagement.model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ClientConfigResource {
    @Value("${server.port}")
    private String serverPort;
    @Value("${eureka.client.serviceUrl.defaultZone}")
    private String urlEureka;

    @GetMapping("/serveurPort")
    public String getServerPort(){
        return serverPort;
    }

}
