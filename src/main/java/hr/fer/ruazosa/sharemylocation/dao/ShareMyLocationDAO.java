package hr.fer.ruazosa.sharemylocation.dao;

import hr.fer.ruazosa.sharemylocation.entity.Group;
import hr.fer.ruazosa.sharemylocation.entity.User;

/**
 * Created on 16/06/17.
 */
public interface ShareMyLocationDAO {

    User registerUser(User user);
    User getUser(User user);
    User loginUser(String userName, String userPassword);
    User updatePassword(String userName, String userPassword);
    Group createGroup(Group group);
    Group getGroup(Group group);
    Group testGroup(String groupName);
}
