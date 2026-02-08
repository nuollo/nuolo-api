package com.nuolo.api.auth;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nuolo.api.user.UserResponse;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(forceJacksonPropertyNames = false)
@JsonSerialize(as = ImmutableAuthResponse.class)
@JsonDeserialize(as = ImmutableAuthResponse.class)
public abstract class AuthResponse {

    public static AuthResponse create(String token, UserResponse user) {
        return ImmutableAuthResponse.builder()
                .token(token)
                .user(user)
                .build();
    }

    public abstract String getToken();

    public abstract UserResponse getUser();
}
