package com.sofka.sofkaClient.shared.config.security.auth.jwk;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JwkController {
    private final JWKSet jwkSet;

    public JwkController(JWKSet jwkSet) { this.jwkSet = jwkSet; }

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> keys() {
        return jwkSet.toJSONObject();
    }
}