package app.westtabs.chanl.androidboilerplate.test.common;

import org.fluttercode.datafactory.impl.DataFactory;

import java.util.ArrayList;
import java.util.List;

import dao.greenrobot.dao.Repo;
import dao.greenrobot.dao.User;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

    public static User generateUser() {
        DataFactory df = new DataFactory();
        User user = new User();
        user.setName(df.getName());
        user.setLogin(df.getFirstName());
        user.setAvatar_url("https://api.adorable.io/avatars/285/" + user.getLogin() + "@adorable.png");
        return user;
    }

   public static List<Repo> makeListRepos(int num) {
        List<Repo> list = new ArrayList<>(num);
        DataFactory df = new DataFactory();
        for (int i = 0; i < num; i++) {
            Repo r = new Repo();
            r.setName(df.getName());
            r.setDescription(df.getRandomText(df.getNumberUpTo(20)));
            r.setHtml_url("https://api.github.com/users/orignmaster");
        }
        return list;
    }
}