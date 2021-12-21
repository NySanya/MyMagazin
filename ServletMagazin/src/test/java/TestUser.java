import User.User;
import org.junit.Assert;
import org.junit.Test;

public class TestUser {
    @Test
    public void testGetUserName(){
        User u = new User("testUserName","testUserPass","M", "18.05.2001","worker");
        Assert.assertEquals("testUserName", u.getNameUser());
    }

    @Test
    public void testGetUserPass(){
        User u = new User("testUserName","testUserPass","M", "18.05.2001","worker");
        Assert.assertEquals("testUserPass", u.getPassUser());
    }

    @Test
    public void testGetUserSex(){
        User u = new User("testUserName","testUserPass","M", "18.05.2001","worker");
        Assert.assertEquals("M", u.getSex());
    }

    @Test
    public void testGetUserBirthday(){
        User u = new User("testUserName","testUserPass","M", "18.05.2001","worker");
        Assert.assertEquals("18.05.2001", u.getBirthday());
    }

    @Test
    public void testGetUserWorker(){
        User u = new User("testUserName","testUserPass","M", "18.05.2001","worker");
        Assert.assertEquals("worker", u.getWorker());
    }


    @Test
    public void testSetUserName(){
        User u = new User("testUserName","testUserPass","M", "18.05.2001","worker");
        u.setNameUser("Ivan");
        Assert.assertEquals("Ivan", u.getNameUser());
    }

    @Test
    public void testSetUserPass(){
        User u = new User("testUserName","testUserPass","M", "18.05.2001","worker");
        u.setPassUser("7777a");
        Assert.assertEquals("7777a", u.getPassUser());
    }

    @Test
    public void testSetUserSex(){
        User u = new User("testUserName","testUserPass","M", "18.05.2001","worker");
        u.setSex("W");
        Assert.assertEquals("W", u.getSex());
    }

    @Test
    public void testSetUserBirthday(){
        User u = new User("testUserName","testUserPass","M", "18.05.2001","worker");
        u.setBirthday("15.05.2003");
        Assert.assertEquals("15.05.2003", u.getBirthday());
    }


}
