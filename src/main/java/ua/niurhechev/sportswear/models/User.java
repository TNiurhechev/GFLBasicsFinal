package ua.niurhechev.sportswear.models;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "userId")
    private int userId;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "passwd")
    private String passwd;
    @Column(name = "isAdmin")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isAdmin;
}
