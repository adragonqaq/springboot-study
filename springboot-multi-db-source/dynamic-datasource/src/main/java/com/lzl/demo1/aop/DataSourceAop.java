package com.lzl.demo1.aop;


import com.lzl.demo1.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 *
 * 默认情况下，所有的查询都走从库，插入/修改/删除走主库。我们通过方法名来区分操作类型（CRUD）
 */
@Aspect
@Component
public class DataSourceAop {


    
        @Pointcut("!@annotation(  com.lzl.demo1.annotation.Master)  "  +
                                    "&&  (execution(*    com.lzl.demo1.service..*.select*(..))  "  +
                                    "||  execution(*    com.lzl.demo1.service..*.get*(..)))")
        public  void  readPointcut()  {

        }

        @Pointcut("@annotation(  com.lzl.demo1.annotation.Master)  "  +
                                    "||  execution(*    com.lzl.demo1.service..*.insert*(..))  "  +
                                    "||  execution(*    com.lzl.demo1.service..*.add*(..))  "  +
                                    "||  execution(*    com.lzl.demo1.service..*.update*(..))  "  +
                                    "||  execution(*    com.lzl.demo1.service..*.edit*(..))  "  +
                                    "||  execution(*    com.lzl.demo1.service..*.delete*(..))  "  +
                                    "||  execution(*    com.lzl.demo1.service..*.remove*(..))")
        public  void  writePointcut()  {

        }

        @Before("readPointcut()")
        public  void  read()  {
                DBContextHolder.slave();
        }

        @Before("writePointcut()")
        public  void  write()  {
                DBContextHolder.master();
        }


        /**
               *  另一种写法：if...else...   判断哪些需要读从数据库，其余的走主数据库
               */
//        @Before("execution(*    com.lzl.demo1.service.impl.*.*(..))")
//        public  void  before(JoinPoint  jp)  {
//                String  methodName  =  jp.getSignature().getName();
//
//                if  (StringUtils.startsWithAny(methodName,  "get",  "select",  "find"))  {
//                        DBContextHolder.slave();
//                }else  {
//                        DBContextHolder.master();
//                }
//        }


}
