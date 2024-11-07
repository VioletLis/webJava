package ru.lisenkova;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.cglib.proxy.Enhancer;
//import org.springframework.cglib.proxy.InvocationHandler;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.lang.reflect.Method;
@Component
public class ExceptionHandlerBeanPostProcesser implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(!bean.getClass().isAnnotationPresent(RestController.class))
            return bean;
        return Enhancer.create(bean.getClass(),bean.getClass().getInterfaces(), new ExceptionsHandler(bean));
    }
}
class ExceptionsHandler implements InvocationHandler {
    Object obj;

    public ExceptionsHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try{
            method.invoke(obj,args);
        }catch (Exception ex)
        {
            System.out.println(ex.getClass());
        }
        return ResponseEntity.badRequest().build();
    }
}
