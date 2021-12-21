package User;

public class User {
    private String nameUser ;
    private String passUser ;
    private String sex ;
    private String birthday;
    private String worker ;



    public User(String name, String pass, String sex, String birthday, String worker){
        this.nameUser=name;
        this.passUser=pass;
        this.sex = sex;
        this.birthday = birthday;
        this.worker = worker;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getPassUser() {
        return passUser;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getWorker() {
        return worker;
    }

}
