package com.trunko.config;

import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.template.Engine;
import com.trunko.filters.ExceptionLogInfo;
import com.trunko.web.controller.project.ProjectController;
import com.trunko.web.dao.project.ProjectModel;

public  class ProjectConfig extends JFinalConfig {


    @Override
    public void configConstant(Constants constants) {
     //   constants.setDevMode(true);
        constants.setEncoding("utf-8");

    }

    @Override
    public void configRoute(Routes routes) {
       routes.add("/", ProjectController.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins me) {
        loadPropertyFile("db.properties");
        C3p0Plugin cp = new C3p0Plugin(getProperty("url"), getProperty("username"), getProperty("password"));
        me.add(cp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
        me.add(arp);
        arp.setShowSql(true);
        arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
        arp.addMapping("tb_project", ProjectModel.class);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {
        interceptors.add(new ExceptionLogInfo());
    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
