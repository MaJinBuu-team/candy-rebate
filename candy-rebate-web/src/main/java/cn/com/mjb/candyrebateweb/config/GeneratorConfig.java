package cn.com.mjb.candyrebateweb.config;

import com.github.flying.cattle.mdg.entity.BasisInfo;
import com.github.flying.cattle.mdg.util.EntityInfoUtil;
import com.github.flying.cattle.mdg.util.Generator;
import com.github.flying.cattle.mdg.util.MySqlToJavaUtil;

import java.sql.SQLException;
import java.util.Date;

/**
 * 自动生成工具：mybatis-dsc-generator（dao domain xml）
 *
 * @author buu
 */
public class GeneratorConfig {
    // 基础信息：项目名、作者、版本
    public static final String PROJECT = "candy-rebate-web";
    public static final String AUTHOR = "buu";
    public static final String VERSION = "v1.0.0";
    // 数据库连接信息：连接URL、用户名、秘密、数据库名
    public static final String URL = "jdbc:mysql://rm-8vbyrdzy1e74921c0do.mysql.zhangbei.rds.aliyuncs.com:3306/candy_project_dev?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    public static final String NAME = "zhu_rds";
    public static final String PASS = "!QAZxsw2kwz";
    public static final String DATABASE = "candy_project_dev";
    // 类信息：类名、对象名（一般是【类名】的首字母小写）、类说明、时间
    public static final String CLASSNAME = "CollectionRoute";
    public static final String TABLE = "cr_uac_user";
    public static final String CLASSCOMMENT = "用户表";
    public static final String TIME = "2020年04月15日";
    public static final String AGILE = new Date().getTime() + "";
    // 路径信息，分开路径方便聚合工程项目，微服务项目
    public static final String ENTITY_URL = "cn.com.mjb.candyrebateweb.module.domain";
    public static final String DAO_URL = "cn.com.mjb.candyrebateweb.dao";
    public static final String XML_URL = "cn.com.mjb.candyrebateweb.mapper";
    public static final String SERVICE_URL = "cn.com.mjb.candyrebateweb.service";
    public static final String SERVICE_IMPL_URL = "cn.com.mjb.candyrebateweb.service.impl";
    public static final String CONTROLLER_URL = "cn.com.mjb.candyrebateweb.controller";
    //是否是Swagger配置
    public static final String IS_SWAGGER = "true";

    public static void main(String[] args) {
        BasisInfo bi = new BasisInfo(PROJECT, AUTHOR, VERSION, URL, NAME, PASS, DATABASE, TIME, AGILE, ENTITY_URL,
                DAO_URL, XML_URL, SERVICE_URL, SERVICE_IMPL_URL, CONTROLLER_URL, IS_SWAGGER);
        bi.setTable(TABLE);
        bi.setEntityName(MySqlToJavaUtil.getClassName(TABLE));
        bi.setObjectName(MySqlToJavaUtil.changeToJavaFiled(TABLE));
        bi.setEntityComment(CLASSCOMMENT);
        try {
            bi = EntityInfoUtil.getInfo(bi);
            String fileUrl = "C:\\software\\myIdeaProject\\candy-rebate\\candy-rebate-web\\src\\main\\java\\";// 生成文件存放位置
            //开始生成文件
            String aa1 = Generator.createEntity(fileUrl, bi).toString();
            String aa2 = Generator.createDao(fileUrl, bi).toString();
            String aa3 = Generator.createDaoImpl(fileUrl, bi).toString();
//            String aa4 = Generator.createService(fileUrl, bi).toString();
//            String aa5 = Generator.createServiceImpl(fileUrl, bi).toString();
//            String aa6 = Generator.createController(fileUrl, bi).toString();
            // 是否创建swagger配置文件
//            String aa7 = Generator.createSwaggerConfig(fileUrl, bi).toString();

            System.out.println(aa1);
            System.out.println(aa2);
            System.out.println(aa3);
//            System.out.println(aa4);
//            System.out.println(aa5);


            //System.out.println(aa7);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
