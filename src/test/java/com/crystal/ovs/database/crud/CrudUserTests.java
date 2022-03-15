package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.User;
import com.crystal.ovs.dao.UserRole;
import com.crystal.ovs.exceptions.WrongEmailOrPasswordException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CrudUserTests {

    @Test
    public void getNumberOfRowsTest() {
        int numberOfRows = CrudUser.getNumberOfRows();
        Assert.assertEquals(5, numberOfRows);
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
        User user = new User(0, "client", "client", UserRole.CLIENT, "client@gmail.com");
        Assert.assertEquals(1, CrudUser.insertUser(user));
    }

    @Test
    public void updateUserTest() {
        User user = new User(0, "client21", "client", UserRole.CLIENT, "client@gmail.com");
        Assert.assertEquals(1, CrudUser.updateUser(user));
    }

    @Test
    public void deleteUserTest() {
        Assert.assertEquals(1, CrudUser.deleteUser(1));
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
        int nrOfRows = CrudUser.getNumberOfRows();
        Assert.assertEquals(nrOfRows, userList.size());
        for (User user : userList) {
            Assert.assertEquals(UserRole.ADMIN,user.getRole());
        }
    }

    @Test
    public void selectAllClientUsers() {
        List<User> userList;
        userList = CrudUser.selectAllClientUsers();
        int nrOfRows = CrudUser.getNumberOfRows();
        Assert.assertEquals(nrOfRows, userList.size());
        for (User user : userList) {
            Assert.assertEquals(UserRole.CLIENT,user.getRole());
        }
    }

    @Test
    public void selectUserByIdTest() {
        User user = CrudUser.selectUserById(1);
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
