package com.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class ApplicationDaoGenerator {

    private static final String USER_ENTITY = "User";

    // Users Table - column names
    private static final String USER_ID = "userId";
    private static final String USER_NAME = "username";
    private static final String USER_AVATAR = "avatar";


    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(1, "dao.greenrobot.dao");
        addUsers(schema);
        new DaoGenerator().generateAll(schema, "app/src-gen");
    }

    private static void addUsers(Schema schema) {
        Entity user = schema.addEntity(USER_ENTITY);
        user.addIdProperty();
        user.addStringProperty(USER_ID).unique().notNull();
        user.addStringProperty(USER_NAME);
        user.addStringProperty(USER_AVATAR);
    }


}
