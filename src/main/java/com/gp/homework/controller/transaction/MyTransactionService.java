package com.gp.homework.controller.transaction;

import cn.hutool.core.util.StrUtil;
import com.gp.homework.domain.mapper.MyTransactionUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;

/**
 * Create by Wayne on 2020/8/4
 */
@Service
public class MyTransactionService {

    @Autowired
    MyTransactionUserMapper udao ;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;


//    @Transactional
    public boolean test1(TransactionUser user) throws Exception {
        TransactionUser tempUser = udao.findById(user.getId()) ;
        System.out.println("查询的数据1:" + tempUser);

        udao.updateNikeName(tempUser.getId(),"wayne") ;
        udao.updateName(tempUser.getId(),"wayne") ;

        return false;
    }


    /**
     * 使用事物 @Transactional 的时候，想自己对异常进行处理的话，那么我们可以进行手动回滚事物。
     * 在catch中加上 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 方法进行手动回滚。
     * 不过需要注意的是发生异常需要第一时间进行手动回滚事物，也就是要在异常抛出之前
     *
     * @param user
     * @return
     */
    public boolean test2(TransactionUser user) {

        try {
            TransactionUser tempUser = udao.findById(user.getId()) ;
            System.out.println("查询的数据1:" + tempUser);

            udao.updateName(tempUser.getId(),"wayne") ;
            udao.updateNikeName(tempUser.getId(),"wayne111111111111111111111") ;


        } catch (Exception e) {
            System.out.println("发生异常,进行手动回滚！");
            // 手动回滚事物
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 如果我们在使用事物 @Transactional 的时候，调用了其他的子方法进行了数据库的操作，
     * 但是我们想使其事物生效的话，我们可以使用rollbackFor注解或者将该子方法的异常抛出由调用的方法进行处理，
     * 不过这里需要注意的是，子方法也必须是公共的方法！
     *
     *
     *
     * @param user
     * @return
     */
    @Transactional
    public boolean test3(TransactionUser user) {

        /*
         * 子方法出现异常进行回滚
         */
        try {
            System.out.println("查询的数据1:" + udao.findById(user.getId()));
            deal1(user);
            deal2(user);
            deal3(user);
        } catch (Exception e) {
            System.out.println("发生异常,进行手动回滚！");
            // 手动回滚事物
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;

    }

    public void deal1(TransactionUser user) throws SQLException {
        udao.insert(user);
        System.out.println("查询的数据2:" + udao.findById(user.getId()));
    }

    public void deal2(TransactionUser user)  throws SQLException{
        if(user.getId()<20){
            //SQL异常
            udao.insert(user);
        }else{
            user.setNick_name("21");
            udao.updateName(user.getId(),"wayneDemo3") ;
            System.out.println("查询的数据3:" + udao.findById(user.getId()));
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    public void deal3(TransactionUser user)  {

            //SQL异常
        user.setNick_name("111111111111111111111111111111");
        udao.insert(user);

    }


    /**
     * 不想使用事物 @Transactional 注解，想自己进行事物控制(编程事物管理)，控制某一段的代码事物生效，但是又不想自己去编写那么多的代码，
     * 那么可以使用springboot中的DataSourceTransactionManager和TransactionDefinition这两个类来结合使用，能够达到手动控制事物的提交回滚。
     * 不过在进行使用的时候，需要注意在回滚的时候，要确保开启了事物但是未提交，如果未开启或已提交的时候进行回滚是会在catch里面发生异常的！
     *
     *
     * @param user
     * @return
     */
    public boolean test4(TransactionUser user) {
        /*
         * 手动进行事物控制
         */
        TransactionStatus transactionStatus=null;
        boolean isCommit = false;
        try {
            transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            System.out.println("查询的数据1:" + udao.findById(user.getId()));
            // 进行新增/修改
            udao.insert(user);
            System.out.println("查询的数据2:" + udao.findById(user.getId()));

            if(user.getId()<15) {
                user.setNick_name(StrUtil.format("wayne{}",user.getId()+2));
                udao.updateNikeName(user.getId(),user.getNick_name());
                System.out.println("查询的数据3:" + udao.findById(user.getId()));
            }else {
                throw new Exception("模拟一个异常!");
            }
            //手动提交
            dataSourceTransactionManager.commit(transactionStatus);
            isCommit= true;
            System.out.println("手动提交事物成功!");
            throw new Exception("模拟第二个异常!");

        } catch (Exception e) {
            //如果未提交就进行回滚
            if(!isCommit){
                System.out.println("发生异常,进行手动回滚！");
                //手动回滚事物
                dataSourceTransactionManager.rollback(transactionStatus);
            }
            e.printStackTrace();
        }
        return false;
    }

    /**
     * spring里面还有一种事物的控制方法，就是设置断点进行回滚。
     * 方法可行，已验证。
     * @param user
     */
    @Transactional
    public void test5(TransactionUser user){

        Object savePoint =null;
        try{
            //设置回滚点
            savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();

            TransactionUser tempUser = udao.findById(user.getId()) ;
            System.out.println("查询的数据1:" + tempUser);

            udao.updateNikeName(tempUser.getId(),"Fruit") ;
            udao.updateName(tempUser.getId(),"wayne") ;


        }catch(Exception e){
            //出现异常回滚到savePoint。
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            e.printStackTrace();
        }

    }



}
