package cn.forbearance.springframework.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cristina
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    public void initDataMethod(){
        System.out.println("执行：init-method");
        hashMap.put("10001", "刘亦菲");
        hashMap.put("10002", "盖尔加朵");
        hashMap.put("10003", "刘德华");
    }

    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
