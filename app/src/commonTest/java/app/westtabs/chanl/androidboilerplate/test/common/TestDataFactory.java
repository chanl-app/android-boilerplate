package app.westtabs.chanl.androidboilerplate.test.common;

import dao.greenrobot.dao.User;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {


    public static User makeProfile(String uniqueSuffix) {
        User user = new User();
//            user.setUsername("");
        return user;
    }

//    public static List<Repo> makeListRepos(int num) {
//        List<Repo> list = new ArrayList<>(num);
//        for (int i = 0; i < num; i++) {
//            Repo r = new Repo();
//            r.setName(df.getName());
//            r.setDescription(df.);
//        }
//        return list;
//    }
}