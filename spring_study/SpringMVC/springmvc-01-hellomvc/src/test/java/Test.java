import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dahuang
 * @date 2021/10/1 21:55
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springmvc-servlet.xml");
        String[] names = context.getBeanDefinitionNames();
        String[] aliases = context.getAliases("/hello");
        for (String alias : aliases) {
            System.out.println(alias);
        }
        for (String name : names) {
            System.out.println(name);
        }
    }
}
