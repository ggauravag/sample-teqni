package org.pkb.springlogin.dao;
 
import java.util.List;
 
import org.pkb.springlogin.model.UserInfo;
 
public interface UserInfoDAO {
     
    public UserInfo findUserInfo(String userName);
     
    // [USER,AMIN,..]
    public List<String> getUserRoles(String userName);
     
}