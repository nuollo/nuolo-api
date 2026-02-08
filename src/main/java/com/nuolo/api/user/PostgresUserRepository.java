package com.nuolo.api.user;

import jakarta.inject.Inject;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.nuolo.api.user.UsersTable.USERS;

@Component
public class PostgresUserRepository implements UserRepository {

    @Inject
    DSLContext dslContext;

    @Override
    public User save(User user) {
        dslContext.insertInto(USERS,
                        USERS.USER_ID,
                        USERS.EMAIL,
                        USERS.PASSWORD,
                        USERS.NAME
                )
                .values(
                        user.getUserId(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getName()
                )
                .onConflict(USERS.EMAIL)
                .doUpdate()
                .set(USERS.PASSWORD, user.getPassword())
                .set(USERS.NAME, user.getName())
                .execute();

        return findByEmail(user.getEmail()).orElse(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return dslContext.select(USERS.fields())
                .from(USERS)
                .where(USERS.EMAIL.eq(email))
                .fetch()
                .stream()
                .map(this::convertToDomain)
                .findFirst();
    }

    @Override
    public boolean existsByEmail(String email) {
        Integer count = dslContext.selectCount()
                .from(USERS)
                .where(USERS.EMAIL.eq(email))
                .fetchOne(0, Integer.class);

        return count != null && count > 0;
    }

    private User convertToDomain(Record record) {
        return ImmutableUser.builder()
                .id(record.get(USERS.ID))
                .userId(record.get(USERS.USER_ID))
                .email(record.get(USERS.EMAIL))
                .password(record.get(USERS.PASSWORD))
                .name(record.get(USERS.NAME))
                .createdAt(record.get(USERS.CREATED_AT))
                .build();
    }
}
