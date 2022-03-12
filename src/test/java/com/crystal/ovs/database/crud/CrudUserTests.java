package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.User;
import com.crystal.ovs.dao.UserRole;
import org.junit.Test;

public class CrudUserTests {
    @Test
    public void insertUserTest() { // works, after you make the select method you can properly add assertions in this test
        User user = new User(0, "client", "client", UserRole.CLIENT, "client@gmail.com");
        CrudUser.insertUser(user);
    }
}
