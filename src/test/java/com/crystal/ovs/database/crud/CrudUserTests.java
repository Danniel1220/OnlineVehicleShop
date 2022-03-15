package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.User;
import com.crystal.ovs.dao.UserRole;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class CrudUserTests {

    @Test
    public void getNumberOfRowsTest() {
        int numberOfRows = CrudUser.getNumberOfRows();
        Assert.assertEquals(6, numberOfRows);
    }

    @Test
    public void selectAllFromUsersTest(){
        List<User> userList;
        userList = CrudUser.selectAllUsers();
        int nrOfRows = CrudUser.getNumberOfRows();
        Assert.assertEquals(nrOfRows, userList.size());
    }

    @Test
    public void insertUserTest() { // works, after you make the select method you can properly add assertions in this test
        User user = new User(0, "testClient", "client", UserRole.CLIENT, "testClient@gmail.com");
        Assert.assertEquals(1, CrudUser.insertUser(user));
    }

    @Test
    public void updateUserTest() {
        User user = new User(2, "client21", "client", UserRole.CLIENT, "altEmail@gmail.com");
        Assert.assertEquals(1, CrudUser.updateUser(user));
    }

    @Test
    public void deleteUserTest() {
        Assert.assertEquals(1, CrudUser.deleteUser(8));
    }

    @Test
    public void selectUserByCredentialsTest() {
        User user;
        try {
            user = CrudUser.selectUserByCredentials("admin@gmail.com", "admin");
            Assert.assertNotNull(user);
            Assert.assertEquals("admin", user.getName());
            Assert.assertEquals("admin@gmail.com", user.getEmail());
            Assert.assertEquals("admin", user.getPassword());
            Assert.assertEquals(UserRole.ADMIN, user.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectAllAdminUsersTest() {
        List<User> userList;
        userList = CrudUser.selectAllAdminUsers();
        for (User user : userList) {
            Assert.assertEquals(UserRole.ADMIN,user.getRole());
        }
    }

    @Test
    public void selectAllClientUsers() {
        List<User> userList;
        userList = CrudUser.selectAllClientUsers();
        for (User user : userList) {
            Assert.assertEquals(UserRole.CLIENT,user.getRole());
        }
    }

    @Test
    public void selectUserByIdTest() {
        User user = CrudUser.selectUserById(2);
        Assert.assertNotNull(user);
    }

    @Test
    public void selectMissingAdminUserTest() {
        User user;
        try {
            user = CrudUser.selectUserByCredentials("adminMissing@gmail.com", "admin");
            Assert.assertNull(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
