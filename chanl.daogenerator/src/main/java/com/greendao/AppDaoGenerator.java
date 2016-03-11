package com.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class AppDaoGenerator {

    private static final String USER_ENTITY = "User";
    private static final String REPO_ENTITY = "Repo";

    // Users Table - column names
    private static final String USER_LOGIN = "login";
    private static final String USER_NAME = "name";
    private static final String USER_AVATAR = "avatar_url";

    private static final String REPO_NAME = "name";
    private static final String REPO_DESC = "description";
    private static final String REPO_URL = "html_url";


    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(5, "dao.greenrobot.dao");
        addUsers(schema);
        addRepos(schema);
        new DaoGenerator().generateAll(schema, "app/src-gen");
    }

    private static void addUsers(Schema schema) {
        Entity user = schema.addEntity(USER_ENTITY);
        user.addIdProperty();
        user.addStringProperty(USER_LOGIN).unique().notNull();
        user.addStringProperty(USER_NAME);
        user.addStringProperty(USER_AVATAR);
    }

    private static void addRepos(Schema schema) {
        Entity repo = schema.addEntity(REPO_ENTITY);
        repo.addIdProperty();
        repo.addStringProperty(REPO_NAME);
        repo.addStringProperty(REPO_DESC);
        repo.addStringProperty(REPO_URL);
    }


}
