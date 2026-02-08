package com.nuolo.api.user;

import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.Record;

import java.time.LocalDateTime;
public class UsersTable extends TableImpl<Record> {

    public static final UsersTable USERS = new UsersTable();

    public final TableField<Record, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER, this);
    public final TableField<Record, String> USER_ID = createField(DSL.name("user_id"), SQLDataType.VARCHAR, this);
    public final TableField<Record, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR, this);
    public final TableField<Record, String> PASSWORD = createField(DSL.name("password"), SQLDataType.VARCHAR, this);
    public final TableField<Record, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR, this);
    public final TableField<Record, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME, this);

    private UsersTable() {
        super(DSL.name("users"));
    }
}
