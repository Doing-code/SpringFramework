package cn.forbearance.springframework.test;

import cn.forbearance.springframework.beans.factory.DisposableBean;
import cn.forbearance.springframework.beans.factory.InitializingBean;

/**
 * @author cristina
 */
public class UserService implements InitializingBean, DisposableBean {

    private String uId;

    private String company;

    private String location;

    private UserDao userDao;

    public UserService() {
    }

    public UserService(String uId) {
        this.uId = uId;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "uId='" + uId + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\''+
                '}';
    }

    public void query() {
        System.out.println(toString());
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
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

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }
}
