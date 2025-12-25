package project.demo;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.demo.controller.HelloController;

@SpringBootTest
class DemoProjectTest {

    @Autowired
    private HelloController controller;

    @Test
    void contextLoads(){
        assertTrue(controller.hello().contains("Bonjour tout le monde!"));
    }
}
