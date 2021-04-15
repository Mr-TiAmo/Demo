//package com.li.node;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.neo4j.ogm.annotation.*;
//
//import java.io.Serializable;
//import java.util.Set;
//
///**
// * @program: Demo
// * @description:  ParentUserNode
// * @author: 栗翱
// * @create: 2020-07-02 16:54
// **/
//@NodeEntity(label = "UserInfo")
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class UserInfo implements Serializable {
//
//    /**
//     * 图数据库主键ID
//     */
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    /**
//     * 用户id
//     */
//    @Property(name = "userId")
//    private Long userId;
//
//    /**
//     * 父级用户ID
//     */
//    @Property(name = "parentUserId")
//    private Long parentUserId;
//
//    /**
//     * 用户粉丝数
//     */
//    @Property(name = "followNum")
//    private Integer followNum;
//
//    /**
//     * 关系
//     */
//    @Relationship(type = "superior")
//    private Set<UserInfo> fans;
//}
