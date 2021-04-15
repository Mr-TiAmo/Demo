//package com.li.node;
//
//import org.springframework.data.neo4j.annotation.Query;
//import org.springframework.data.neo4j.repository.Neo4jRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
///**
// * @program: Demo
// * @description:
// * @author: 栗翱
// * @create: 2020-07-02 17:01
// **/
//@Repository
//public interface IUserInfoRepository extends Neo4jRepository<UserInfo,Long> {
//
//
//
//
//    /**
//     * 查询某个节点的所有子节点分页
//     * @param userId
//     * @return
//     */
//    @Query("Match (p:UserInfo{userId:{userId}})-[*]->(s:UserInfo) return s")
//    List<UserInfo> findChildList(@Param("userId") Long userId);
//
//    /**
//     * 查询某个节点的直属父节点
//     * @param userId
//     * @return
//     */
//    @Query("Match (p:UserInfo)-[*]->(s:UserInfo{userId:{userId}}) return p limit 1")
//    UserInfo findParent(@Param("userId") Long userId);
//
//    /**
//     * 查询某个节点的所有父节点
//     * @param userId
//     * @return
//     */
//    @Query("Match (p:UserInfo)-[*]->(s:UserInfo{userId:{userId}}) return p")
//    List<UserInfo> findParentList(@Param("userId") Long userId);
//
//
//    /**
//     * 查询某个节点的 指定等级的 最近的父节点
//     * @param userId
//     * @param userLevel
//     * @return
//     */
//    @Query("Match (p:UserInfo{userLevel:{userLevel}})-[*]->(s:UserInfo{userId:{userId}}) return p limit 1")
//    UserInfo findParentInfoByLevel(@Param("userId") Long userId, @Param("userLevel") Integer userLevel);
//
//    /**
//     * 查询某个节点的 指定等级的 指定层级 最近的父节点
//     * @param userId
//     * @param userLevel 等级
//     * @param hierarchy 层级
//     * @return
//     */
//    @Query("Match (p:UserInfo{userLevel:{userLevel}})-[*]->(s:UserInfo{userId:{userId}}) return p limit {hierarchy}")
//    UserInfo findParentInfoByLevelAndHierarchy(@Param("userId") Long userId, @Param("userLevel") Integer userLevel,@Param("hierarchy") Integer hierarchy);
//
//    /**
//     * 查询某个节点的 指定等级的所有父节点
//     * @param userId
//     * @param userLevel
//     * @return
//     */
//    @Query("Match (p:UserInfo{userLevel:{userLevel}})-[*]->(s:UserInfo{userId:{userId}}) return p")
//    List<UserInfo> findParentByLevel(@Param("userId") Long userId, @Param("userLevel") Integer userLevel);
//
//    /**
//     * 获取指定用户的节点信息
//     * @param userId
//     * @return
//     */
//    @Query("MATCH (ee:UserInfo) WHERE ee.userId = {userId} RETURN ee")
//    UserInfo getUserInfoById(@Param("userId") Long userId);
//
//    /**
//     * 修改指定用户的粉丝数量
//     * @param userId
//     * @param followNum
//     * @return
//     */
//    @Query("MATCH (ee:UserInfo) WHERE ee.userId = {userId} SET ee.followNum = {followNum} RETURN ee")
//    UserInfo updateFollowNumByUserId(@Param("userId")Long userId,@Param("followNum")Integer followNum);
//
//    /**
//     * 修改指定用户的用户等级
//     * @param userId
//     * @param userLevel
//     * @return
//     */
//    @Query("MATCH (ee:UserInfo) WHERE ee.userId = {userId} SET ee.userLevel = {userLevel} RETURN ee")
//    UserInfo updateUserLevelByUserId(@Param("userId")Long userId,@Param("userLevel")Integer userLevel);
//
//    /**
//     * 修改指定用户的成长值
//     * @param userId
//     * @param growNum
//     * @return
//     */
//    @Query("MATCH (ee:UserInfo) WHERE ee.userId = {userId} SET ee.growNum = {growNum} RETURN ee")
//    UserInfo updateGrowNumByUserId(@Param("userId")Long userId,@Param("growNum")Integer growNum);
//
//    /**
//     * 添加两个用户的上下级关系
//     * @param userId
//     * @param sonUserId
//     */
//    @Query("MATCH (u:UserInfo{userId:{userId}}) MATCH (p:UserInfo{userId:{sonUserId}})  CREATE (u) - [s:superior] -> (p) ")
//    void addSuperior(@Param("userId")Long userId,@Param("sonUserId")Long sonUserId);
//
//    /**
//     * 删除图数据库中的所有数据
//     */
//    @Query("MATCH (n:UserInfo) DETACH DELETE n")
//    void deleteAllData();
//
//
//}
