package com.example.javatest;

import com.example.javatest.temp.PrivilegeType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/18 16:52
 */
@SpringBootTest(classes = TempTest.class)
public class TempTest {

    @Test
    void test() {
        PrivilegeType privilegeType = PrivilegeType.INSERT;
        Collection<PrivilegeType> c = Collections.singleton(privilegeType);

        for (PrivilegeType privilegeType1 : c) {
            System.out.println(privilegeType1.name());
        }




    }

    @Test
    void test2(){
        Collection<String> c = new HashSet<>();
        String s = "1,2,3,4,5";
        Arrays.asList(s.split(",")).forEach(c::add);
        System.out.println(c);
    }
}
