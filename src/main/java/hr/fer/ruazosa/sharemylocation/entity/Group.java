package hr.fer.ruazosa.sharemylocation.entity;

/**
 * Created by Nika on 6/25/2017.
 */

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "grupa")
public class Group {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "grupa_grupaid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "grupaID", unique = true, nullable = false)
    private long id;

    @Column(name = "imeGrupa")
    @Size(min = 3, max = 20)
    private String groupName;

    @Size(min = 3, max = 20)
    @Column(name = "adminGrupa")
    private String groupAdmin;

    @Column(name = "brClanova")
    private int noOfMembers;

    @Column(name = "ikona")
    private String userFirstName;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(String groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public int getNoOfMembers() {
        return noOfMembers;
    }

    public void setNoOfMembers(int noOfMembers) {
        this.noOfMembers = noOfMembers;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }



}
