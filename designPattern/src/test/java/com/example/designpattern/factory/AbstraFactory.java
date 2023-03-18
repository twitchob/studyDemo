package com.example.designpattern.factory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/14 15:50
 */
@SpringBootTest(classes = AbstraFactory.class)
public class AbstraFactory {
    @Test
    public void testAbstraFactory() {
        //创建工厂
        IDataBaseUtils dataBaseUtils = new MysqlDataBaseUtils();
        //获取连接
        IConnection connection = dataBaseUtils.getConnection();
        connection.connect();
        //获取命令
        ICommand command = dataBaseUtils.getCommand();
        command.command();
    }

}


/**
 * 连接接口
 */
interface IConnection {
    void connect();
}

/**
 * mysql连接
 */
class MysqlConnection implements IConnection {

    @Override
    public void connect() {
        System.out.println("mysql连接");
    }

}


/**
 * 命令接口
 */
interface ICommand {
    void command();
}


/**
 * mysql命令
 */
class MysqlCommand implements ICommand {

    @Override
    public void command() {
        System.out.println("mysql命令");
    }

}

/**
 * IDataBaesUtils接口
 */
interface IDataBaseUtils {
    IConnection getConnection();

    ICommand getCommand();
}

/**
 * mysql dataBaseUtils
 */
class MysqlDataBaseUtils implements IDataBaseUtils {

    @Override
    public IConnection getConnection() {
        return new MysqlConnection();
    }

    @Override
    public ICommand getCommand() {
        return new MysqlCommand();
    }
}




