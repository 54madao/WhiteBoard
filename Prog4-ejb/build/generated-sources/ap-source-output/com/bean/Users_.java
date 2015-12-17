package com.bean;

import com.bean.WhiteBoard;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-16T20:10:49")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> password;
    public static volatile SetAttribute<Users, WhiteBoard> own;
    public static volatile SingularAttribute<Users, Long> id;
    public static volatile SingularAttribute<Users, String> userName;

}