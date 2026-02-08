package com.nuolo.api.auth;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(forceJacksonPropertyNames = false)
@JsonSerialize(as = ImmutableLoginRequest.class)
@JsonDeserialize(as = ImmutableLoginRequest.class)
public abstract class LoginRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    public abstract String getEmail();

    @NotBlank(message = "Password is required")
    public abstract String getPassword();
}
