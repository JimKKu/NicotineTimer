package com.jim.itimerserver.mapper;


import com.jim.itimerserver.entity.user.UserEntity;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface UserMapper {
    // 插入用户
    @Insert("INSERT INTO TBL_USER (USERNAME, PASSWORD, REMARK) " +
            "VALUES (#{username}, #{password}, #{remark})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserEntity user);

    // 根据ID更新用户
    @Update("UPDATE TBL_USER SET " +
            "USERNAME = #{username}, " +
            "PASSWORD = #{password}, " +
            "REMARK = #{remark} " +
            "WHERE ID = #{id}")
    int update(UserEntity user);

    // 根据ID删除用户
    @Delete("DELETE FROM TBL_USER WHERE ID = #{id}")
    int deleteById(Integer id);

    // 根据ID查询用户
    @Select("SELECT * FROM TBL_USER WHERE ID = #{id}")
    UserEntity selectById(Integer id);

    // 根据用户名查询用户
    @Select("SELECT * FROM TBL_USER WHERE USERNAME = #{username}")
    UserEntity selectByUsername(String username);

    // 查询所有用户
    @Select("SELECT * FROM TBL_USER")
    List<UserEntity> selectAll();

    // 检查用户名是否存在
    @Select("SELECT COUNT(*) FROM TBL_USER WHERE USERNAME = #{username}")
    int countByUsername(String username);

    // 统计用户数
    @Select("SELECT COUNT(1) FROM TBL_USER")
    int countAll();

}
