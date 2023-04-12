package cn.forbearance.springframework.test;

/**
 * @author cristina
 */
public class UserService2 implements IUserService{

    private String uId;

    private String company;

    private String location;

    private IUserDao userDao;

    public UserService2() {
    }

    public UserService2(String uId) {
        this.uId = uId;
    }

    public String query() {
        return "树枝777";
    }

    @Override
    public String register(String name) {
        return name;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
