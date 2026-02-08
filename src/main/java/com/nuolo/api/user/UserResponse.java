package com.nuolo.api.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.LocalDateTime;

@Value.Immutable
@Value.Style(forceJacksonPropertyNames = false)
@JsonSerialize(as = ImmutableUserResponse.class)
@JsonDeserialize(as = ImmutableUserResponse.class)
public abstract class UserResponse {

    public static UserResponse create(String userId, String email, String name, LocalDateTime createdAt) {
        return ImmutableUserResponse.builder()
                .userId(userId)
                .email(email)
                .name(name)
                .createdAt(createdAt)
                .build();
    }

    public abstract String getUserId();

    public abstract String getEmail();

    public abstract String getName();

    public abstract LocalDateTime getCreatedAt();
}
