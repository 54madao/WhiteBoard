package com.bean;

import com.bean.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-16T19:40:48")
@StaticMetamodel(WhiteBoard.class)
public class WhiteBoard_ { 

    public static volatile SingularAttribute<WhiteBoard, Users> owner;
    public static volatile SetAttribute<WhiteBoard, Users> subscriber;
    public static volatile SingularAttribute<WhiteBoard, String> name;
    public static volatile SingularAttribute<WhiteBoard, String> description;
    public static volatile SingularAttribute<WhiteBoard, Long> id;

}