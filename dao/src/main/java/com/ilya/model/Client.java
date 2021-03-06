package com.ilya.model;




import com.ilya.model.enums_utils.Role;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by ilya on 18.08.2016.
 */
@NamedQueries({
        @NamedQuery(name = "Client.getAll" , query = "select u from Client u ")

})
@Entity
@Table(name = "users")
@Access( AccessType.FIELD)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="name")
    private String name;
    @Column(name ="surname")
    private String surname;
    @Column(name ="dateOfBirth")
    private Date birth;
    @Column(name ="email",unique = true)
    private String email;
    @Column(name ="password")
    private String password;
    @Column(name = "registered")
    private Date registered;
    @Transient
    private boolean isAdmin;
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Embedded
    private Adress adress;

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Adress getAdress(){return adress;}

    public int getId(){return id;}

    public Set<Role> getRoles() {
        return roles;
    }

    public Date getRegistered() {
        return registered;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirth() {
        return birth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client that = (Client) o;
        if (id == 0 || that.id == 0) {
            return false;
        }
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (id == 0) ? 0 : id;
    }
}
