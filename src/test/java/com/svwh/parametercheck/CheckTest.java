package com.svwh.parametercheck;

import com.svwh.parametercheck.config.TestConfig;
import com.svwh.parametercheck.controller.TestController;

import com.svwh.parametercheck.entity.A;
import com.svwh.parametercheck.entity.B;
import com.svwh.parametercheck.entity.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @Author cxk
 * @Date 2024/5/19 17:13
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class CheckTest {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CheckTest.class);

    @Autowired
    private TestController testController;


    @Test
    public void testNotNull() {
        try {
            A a = new A();
            a.setName("cxk");
            LOGGER.info(testController.testNotNull("parameter",a));
        }catch (Exception e){
            LOGGER.error("error",e);
        }
    }

    @Test
    public void testNotEmpty(){
        try {
            A a = new A();
            a.setB(new B());
            a.setName("");
            List<String> list = new ArrayList<>();
//            list.add("test");
            a.getB().setList(list);
            LOGGER.info(testController.testNotEmpty(a));
        }catch (Exception e){
            LOGGER.error("error",e);
        }
    }


    @Test
    public void testNotBlank(){
        try {
            A a = new A();
            a.setName(" ");
            LOGGER.info(testController.testNotBlank(a));
        }catch (Exception e){
            LOGGER.error("error",e);
        }
    }

    @Test
    public void testMustTrue(){
        try {
            A a = new A();
            a.setB(new B());
            a.getB().setVip(false);
            LOGGER.info(testController.testMustTrue(a));
        }catch (Exception e){
            LOGGER.error("error",e);
        }
    }

    @Test
    public void testMustFalse(){
        try {
            A a = new A();
            a.setB(new B());
            a.getB().setVip(true);
            LOGGER.info(testController.testMustFalse(a));
        }catch (Exception e){
            LOGGER.error("error",e);
        }
    }

    @Test
    public void testMustMax(){
        try {
            A a = new A();
            a.setB(new B());
            a.getB().setAge(-1);
            LOGGER.info(testController.testMustMax(a));
        }catch (Exception e){
            LOGGER.error("error",e);
        }
    }

    @Test
    public void testMustMin(){
        try {
            A a = new A();
            a.setB(new B());
            a.setAge(20);
            a.getB().setAge(30);
            LOGGER.info(testController.testMustMin(a));
        }catch (Exception e){
            LOGGER.error("error",e);
        }
    }

    @Test
    public void testMustFuture(){
        try {
            A a = new A();
            a.setLocalDateTime(LocalDateTime.of(2021,1,1,1,1,1));
            a.setB(new B());
            a.getB().setAge(300);
            LOGGER.info(testController.testMustFuture(a));
        }catch (Exception e){
            LOGGER.error("error",e);
        }
    }

    @Test
    public void testMustPast(){
        try {
            A a = new A();
            a.setB(new B());
            a.setUpdateTime("2025-1-1 10:22:01");
            a.getB().setC(new C());
            a.getB().getC().setDateTime(LocalDateTime.of(2022,1,1,1,1,1));
            a.getB().setAge(300);
            LOGGER.info(testController.testMustPast(a));
        }catch (Exception e){
            LOGGER.error("error",e);
        }
    }

    @Test
    public void testMustEmail(){
        try {
            A a = new A();
            a.setB(new B());
            a.getB().setC(new C());
            a.getB().setEmail("xx@");
            a.getB().getC().setDateTime(LocalDateTime.of(2025,1,1,1,1,1));
            a.getB().setAge(300);
            LOGGER.info(testController.testMustEmail(a));
        }catch (Exception e){
            LOGGER.error("error",e);
        }
    }

    @Test
    public void testMustLength(){
        try {
            A a = new A();
            a.setName("1234567890102");
            a.setB(new B());
            a.getB().setC(new C());
            a.getB().setEmail("xx@");
            a.getB().getC().setDateTime(LocalDateTime.of(2025,1,1,1,1,1));
            a.getB().setAge(300);
            LOGGER.info(testController.testMustLength(a));
        }catch (Exception e){
            LOGGER.error("error",e);
        }
    }

    @Test
    public void testMustPattern(){
        try {
            A a = new A();
            a.setName("1234567890102");
            a.setB(new B());
            a.getB().setC(new C());
            a.getB().setEmail("xx@");
            a.getB().getC().setDateTime(LocalDateTime.of(2025,1,1,1,1,1));
            a.getB().setAge(300);
            LOGGER.info(testController.testMustPatter(a));
        }catch (Exception e){
            LOGGER.error("error",e);
        }
    }
}
