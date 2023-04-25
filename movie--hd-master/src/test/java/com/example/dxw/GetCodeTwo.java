package com.example.dxw;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Test;


public class GetCodeTwo {
    @Test
    public void main1() {
        String path=System.getProperty("user.dir") + "\\src\\main\\java";
        String databaseUrl="jdbc:mysql://localhost:3306/film?serverTimezone=GMT%2B8";/**数据库地址*/
        String parent="com.example.dxw.movie";/**生成文件项目名称的文件夹*/
        String include="collect";/**映射数据库的表*/
        String include2="ratings_";/**需要去掉的表前缀*/
        String username="root";/**数据库账号*/
        String password="root";/**数据库密码*/
        String setAuthor="";/**数据库密码*/
        Boolean setSwagger2=true;//开启Swagger2模式

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        gc.setOutputDir(path);//代码生成后的存放路径
        gc.setAuthor(setAuthor);//作者
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
//        gc.setActiveRecord();
        /*
         * mp生成service层代码，默认接口名称第一个字母有 I
         * UcenterService
         * */
//        gc.setServiceName("%sService");    //去掉Service接口的首字母I

        gc.setEntityName("%sDO");
        gc.setIdType(IdType.AUTO); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(setSwagger2);//开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(databaseUrl);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("serviceedu"); //模块名
        pc.setParent(parent);
//        pc.setController("controller");
        pc.setEntity("pojo");
//        pc.setService("service");
//        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(include); //映射的具体的表
        strategy.setEntityLombokModel(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() +include2); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }
    public String se(String str){
        String[] split = str.split("/");
        str="";
        for (int i = 0; i < split.length; i++) {
            if (i<split.length-1){
                str+=split[i]+".";
            }else {
                str+=split[i];
            }
        }
        return str;
    }
}
