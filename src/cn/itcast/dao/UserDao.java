package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/*
* 操作数据库中的User表的类
* */
public class UserDao {

        //声明JDBCTemplate对象共用
    private JdbcTemplate template =  new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录方法
     * @param loginUser 只有用户名何密码
     * @return 包含用户所有数据
     */
    public User login(User loginUser){
        User user = null;
        try {
            //1.编写sql
            String  sql = "SELECT * FROM user WHERE username = ?  AND password = ?";
            //2.调用query方法
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
            System.out.println(user);
        }catch (Exception e){
            System.err.println("您传入的用户不存在");
        }
        return user;

    }

}
