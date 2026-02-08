package com.nuolo.api.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import org.immutables.value.Value.Default;

import java.time.LocalDateTime;

@Serial.Structural
@Value.Immutable
@Value.Style(forceJacksonPropertyNames = false)
@JsonSerialize(as = ImmutableUser.class)
@JsonDeserialize(as = ImmutableUser.class)
public abstract class User {

    public static User create(String userId, String email, String name, String password) {
        return ImmutableUser.builder()
                .userId(userId)
                .email(email)
                .name(name)
                .password(password)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public ImmutableUser copy() {
        return ImmutableUser.copyOf(this);
    }

    @Default
    public int getId() { return 0; }

    public abstract String getUserId();

    public abstract String getEmail();

    @JsonIgnore
    public abstract String getPassword();

    public abstract String getName();

    public abstract LocalDateTime getCreatedAt();
}
