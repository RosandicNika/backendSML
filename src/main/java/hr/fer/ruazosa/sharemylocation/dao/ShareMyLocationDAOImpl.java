package hr.fer.ruazosa.sharemylocation.dao;

import hr.fer.ruazosa.sharemylocation.entity.Group;
import hr.fer.ruazosa.sharemylocation.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created on 16/06/17.
 */
@Repository
@Transactional
public class ShareMyLocationDAOImpl implements ShareMyLocationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User registerUser(User user) {
        if (getUser(user) != null) {
            return null;
        }
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(user);
        return getUser(user);
    }

    @Override
    public User getUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("from User where userName = '" + user.getUserName()+ "'");
        List<User> userList = theQuery.getResultList();
        if (userList == null || userList.isEmpty()) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public User loginUser(String userName, String userPassword) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("from User where userName = '" + userName+ "' AND password = '" + userPassword +"'");
        List<User> userList = theQuery.getResultList();
        if (userList == null || userList.isEmpty()) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public User updatePassword(String userName, String userPassword) {
        User user=new User();
        user.setUserName(userName);
        User sUser = this.getUser(user);
        if(sUser==null) {
            return null;
        }else{
            sUser.setPassword(userPassword);
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.saveOrUpdate(sUser);
            return getUser(sUser);

        }
    }

    @Override
    public Group createGroup(Group group) {
        if (getGroup(group) != null) {
            return null;
        }
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(group);
        return getGroup(group);
    }

    @Override
    public Group getGroup(Group group) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("from Group where groupName = '" + group.getGroupName()+ "'");
        List<Group> groupList = theQuery.getResultList();
        if (groupList == null || groupList.isEmpty()) {
            return null;
        }
        return groupList.get(0);
    }

    @Override
    public Group testGroup(String groupName) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("from Group where groupName = '" + groupName + "'");
        List<Group> groupList = theQuery.getResultList();
        if (groupList == null || groupList.isEmpty()) {
            return null;
        }
        return groupList.get(0);
    }
}
