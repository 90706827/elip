package com.zgcenv.plus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.mysql.cj.jdbc.Driver;

import java.util.*;

/**
 * @ClassName application
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/9 23:45
 * @Version 1.0
 **/
public class Application {
    public static void main(String[] args) {
        int result = 1;
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));
        String projectPath = "D:\\ideaWorkspace\\elip\\generator\\";
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                        .setOutputDir(projectPath + "src\\main\\java") // 生成路径
                        .setAuthor("Mr.Jangni") //作者
                        .setFileOverride(true)// 是否覆盖文件
                        .setIdType(IdType.ASSIGN_ID) // 主键策略
                        .setActiveRecord(true)// 开启 activeRecord 模式
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(true)// XML 生成基本的resultMap
                        .setBaseColumnList(true)// XML 生成基本的SQL片段
                        .setOpen(false)//生成后是否打开文件夹
                        //.setKotlin(true) 是否生成 kotlin 代码
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
//                        .setEntityName("%sEntity")
                        .setMapperName("%sDao")
                        .setXmlName("%sDao")
                        .setServiceName("%sService")
                        .setServiceImplName("%sServiceImpl")
                        .setControllerName("%sController")
        ).setDataSource(
                // 数据源配置
                new DataSourceConfig()
                        .setDbType(DbType.MYSQL) // 数据库类型
                        .setTypeConvert(new MySqlTypeConvert() {
                            // 自定义数据库表字段类型转换【可选】
                            @Override
                            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                                System.out.println("转换类型：" + fieldType);
                                // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                                //    return DbColumnType.BOOLEAN;
                                // }
                                return super.processTypeConvert(globalConfig, fieldType);
                            }
                        })
                        .setDbQuery(new MySqlQuery() {

                            /**
                             * 重写父类预留查询自定义字段<br>
                             * 这里查询的 SQL 对应父类 tableFieldsSql 的查询字段，默认不能满足你的需求请重写它<br>
                             * 模板中调用：  table.fields 获取所有字段信息，
                             * 然后循环字段获取 field.customMap 从 MAP 中获取注入字段如下  NULL 或者 PRIVILEGES
                             */
                            @Override
                            public String[] fieldCustom() {
                                return new String[]{"NULL", "PRIVILEGES"};
                            }
                        })
                        .setDriverName(Driver.class.getName())
                        .setUsername("root")
                        .setPassword("root")
                        .setUrl("jdbc:mysql://localhost:3306/zgcenv?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&autoReconnect=true")
        ).setStrategy(
                // 策略配置
                new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名
                        // .setDbColumnUnderline(true)//全局下划线命名
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        .setColumnNaming(NamingStrategy.underline_to_camel)
                        .setTablePrefix(new String[]{"sys_", "mp_", "tb_", "tu_"})// 此处可以修改为您的表前缀
                        // .setInclude(new String[] { "user" }) // 需要生成的表
                        // .setExclude(new String[]{"test"}) // 排除生成的表
                        .setSuperEntityColumns(new String[]{"test_id"}) // 自定义实体，公共字段
                        .setTableFillList(tableFillList)
                        .setEntityBooleanColumnRemoveIsPrefix(true)
                        // .setSuperEntityClass("com.baomidou.demo.TestEntity")// 自定义实体父类
//                        .setSuperMapperClass("com.baomidou.demo.TestMapper") // 自定义 mapper 父类
//                        .setSuperServiceClass("com.baomidou.demo.TestService") // 自定义 service 父类
//                        .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl") // 自定义 service 实现类父类
//                        .setSuperControllerClass("com.baomidou.demo.TestController") // 自定义 controller 父类
                        // 【实体】是否生成字段常量（默认 false）
                        // public static final String ID = "test_id";
                        .setEntityColumnConstant(true)
                        // 【实体】是否为构建者模型（默认 false）
                        // public User setName(String name) {this.name = name; return this;}
                        .setEntityBuilderModel(true)
                        // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                        .setEntityLombokModel(true)
                        // Boolean类型字段是否移除is前缀处理
                        .setEntityBooleanColumnRemoveIsPrefix(true)
                        .setRestControllerStyle(true)
                        .setControllerMappingHyphenStyle(true)
        ).setPackageInfo(
                // 包配置
                new PackageConfig()
                        .setParent("com.zgcenv.elip")// 自定义包路径
                        .setModuleName("common")
                        .setController("controller")// 这里是控制器包名，默认 web
                        .setMapper("dao")//dao
                        .setService("service")//servcie
                        .setEntity("entity")
                        .setXml("dao.xml")
        ).setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }.setFileOutConfigList(Collections.singletonList(new FileOutConfig(
                        "/templates/mapper.xml" + ((1 == result) ? ".ftl" : ".vm")) {
                    // 自定义输出文件目录
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return "../com/xml/" + tableInfo.getEntityName() + ".xml";
                    }
                }))
        ).setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig()
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
//             .setController("...")
//             .setEntity("...")
//             .setMapper("...")
//             .setXml("...")
//             .setService("...")
//             .setServiceImpl("...")
        );
        // 执行生成
        if (1 == result) {
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        }
        mpg.execute();

        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }
}
