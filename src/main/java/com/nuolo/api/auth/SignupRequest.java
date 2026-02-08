package com.nuolo.api.auth;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(forceJacksonPropertyNames = false)
@JsonSerialize(as = ImmutableSignupRequest.class)
@JsonDeserialize(as = ImmutableSignupRequest.class)
public abstract class SignupRequest {

    @NotBlank(message = "Name is required")
    public abstract String getName();

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    public abstract String getEmail();

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    public abstract String getPassword();
}
