package com.haijun.utils;

import java.util.ArrayList;
import java.util.List;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * <p>
 * 测试生成代码
 * </p>
 *
 * @author K神
 * @date 2017/12/18
 */
public class GeneratorServiceEntity {

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("funton");
		gc.setOpen(true);
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(true);
		gc.setActiveRecord(true);
//		gc.setDateType(DateType.ONLY_DATE);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://localhost:3306/funton?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8");
		// dsc.setSchemaName("public");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("root");
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(null);
		pc.setParent("com.haijun");
		pc.setEntity("model");
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return projectPath + "/src/main/resources/mapper/"
						+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		mpg.setTemplate(new TemplateConfig().setXml(null));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityBuilderModel(true);
//		strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
		strategy.setRestControllerStyle(true);
//		strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
		//strategy.setInclude("facebook_record");
//		strategy.setSuperEntityColumns("id");
		strategy.setControllerMappingHyphenStyle(false);
		strategy.setTablePrefix("t");
		strategy.entityTableFieldAnnotationEnable(true);
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}
}