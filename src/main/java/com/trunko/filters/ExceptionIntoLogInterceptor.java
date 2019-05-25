package com.trunko.filters;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.JFinal;
import org.apache.log4j.Logger;

/**
 * 异常处理日志 全局拦截器
**/
public abstract class ExceptionIntoLogInterceptor implements Interceptor {
    private static final  Logger log = Logger.getLogger(ExceptionIntoLogInterceptor.class);

    @Override
    public void intercept(Invocation invocation) {
            //Controller controller=invocation.getController();
            //HttpServletRequest request=controller.getRequest();
            try{
                invocation.invoke(); //一定要注意，把处理放在invoke之后，因为放在之前的话，是会空指针
            }catch (Exception e){
                //log 处理
                logWrite(invocation, e);
            }finally {
                //记录日志到数据库，暂未实现
                try{

                }catch (Exception ee){

                }
            }



    }

    private void logWrite(Invocation inv, Exception e){
        //开发模式 下直接抛出异常到控制台不记录到日志文件中
        if (JFinal.me().getConstants().getDevMode()){
            e.printStackTrace();
        }
        StringBuilder sb =new StringBuilder("\n---Exception Log Begin---\n");
        sb.append("Controller:").append(inv.getController().getClass().getName()).append("\n");
        sb.append("Method:").append(inv.getMethodName()).append("\n");
        sb.append("Exception Type:").append(e.getClass().getName()).append("\n");
        sb.append("Exception Details:");
        log.error(sb.toString(),e);

    }


}
