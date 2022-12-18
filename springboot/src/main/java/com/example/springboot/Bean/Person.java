package com.example.springboot.Bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Person
 * @author: zhangzhongyuan@szanfu.cn
 * @date: 2022/11/2 0002 11:25
 * @Description:
 */
@Data
public class Person {
    private String name;
    private Integer age;
    private boolean boos;
    private Date brith;

    private Map<String, Object> maps;
    private List< Object> lists;
    private Dog dog;


}
