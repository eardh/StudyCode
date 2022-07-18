import com.dahuang.pojo.Client;
import com.dahuang.pojo.aop.UserService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

/**
 * @author dahuang
 * @date 2021/10/2 9:46
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springmvc-servlet.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.userInfo();
    }
}
