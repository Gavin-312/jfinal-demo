package com.gantao.jfinal.generator;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 */
public class _JFinalDemoGenerator {

	/**
	 * 先加载开发环境配置，然后尝试加载生产环境配置，生产环境配置不存在时不会抛异常
	 * 在生产环境部署时后动创建config.txt，添加的配置项可以覆盖掉
	 * config.txt 中的配置项
	 */
	static Prop p;

	static void loadConfig() {
		if (p == null) {
			p = PropKit.use("config.txt").appendIfExists("config.txt");
		}
	}

	public static DruidPlugin createDruidPlugin() {
		loadConfig();
		return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
	}

	public static DataSource getDataSource() {
		DruidPlugin druidPlugin = createDruidPlugin();
		druidPlugin.start();
		return druidPlugin.getDataSource();
	}

	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPackageName = "com.gantao.jfinal.model.base";
		// base model 文件保存路径
		String baseModelOutputDir = PathKit.getWebRootPath() + "/src/main/java/com/gantao/jfinal/model/base";

		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "com.gantao.jfinal.model";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = baseModelOutputDir + "/..";

		// 创建生成器
		Generator generator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		// 设置数据库方言
		generator.setDialect(new MysqlDialect());
		// 设置是否生成链式 setter 方法
		generator.setGenerateChainSetter(false);
		// 添加不需要生成的表名
//		generator.addExcludedTable("adv");
		// 设置是否在 Model 中生成 dao 对象
		generator.setGenerateDaoInModel(false);
		// 设置是否生成字典文件
		generator.setGenerateDataDictionary(false);
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
		// generator.setRemovedTableNamePrefixes("t_");
		// 生成
		generator.generate();
	}
}




