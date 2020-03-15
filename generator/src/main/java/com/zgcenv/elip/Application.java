package com.zgcenv.elip;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName application
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/6 15:14
 * @Version 1.0
 **/
public class Application {
    public static void main(String[] args) {
        String projectPath = "D:\\ideaWorkspace\\elip\\generator\\";
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true) // 是否支持AR模式
                .setAuthor("Mr.Jangni") // 作者
                .setActiveRecord(false)
                .setOutputDir(projectPath + "src\\main\\java") // 生成路径
                .setFileOverride(true)  // 文件覆盖
                .setIdType(IdType.ASSIGN_ID) // 主键策略
                .setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I
//                .setControllerName("%sController")
                // 默认service接口名IXXXService 自定义指定之后就不会用I开头了
//                .setServiceImplName("%sServiceImpl")
//                .setMapperName("%sMapper")
//                .setXmlName("%sMapper")
                .setBaseResultMap(true)//生成基本的resultMap
                .setBaseColumnList(true)//生成基本的SQL片段
                .setOpen(false);//生成后是否打开文件夹

        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/hzx?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&autoReconnect=true")
                .setUsername("root")
                .setPassword("root");
        //3. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.zgcenv.elip.common")
                .setMapper("dao")//dao
                .setService("service")//servcie
                .setController("controller")//controller
                .setEntity("entity");

        //4. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) //全局大写命名
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(true)    //驼峰转连字符
//                .setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity")
//                .setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController")
                .setTablePrefix(pkConfig.getModuleName() + "_")//是否生成实体时，生成字段注解
                .setTablePrefix("sys_") //表前缀
//                .setExclude("test") // 排除生成的表
                .setInclude("sys_user", "sys_user_role", "sys_role", "sys_menu", "sys_role_menu", "sys_interface", "sys_role_interface", "sys_param");  // 生成的表


        //5. 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "\\src\\main\\resources\\mapper\\" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);

        //6. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig)
                .setCfg(cfg)
                .setTemplate(new TemplateConfig().setXml(null))
                .setTemplateEngine(new FreemarkerTemplateEngine());
        //7. 执行
        ag.execute();
    }
}
