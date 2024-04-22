package stream;

import com.kaifei.algo.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StreamLomda {

    private static List<User> list = new ArrayList<>();


    @Before
    public void init(){
        User  james = User.builder().id(23).name("james").age(39).build();
        User  curry = User.builder().id(30).name("curry").age(30).build();
        User  davis = User.builder().id(3).name("davis").age(30).build();

        list.add(james);
        list.add(curry);
        list.add(davis);
    }


    @Test
    public void sort(){
        List<User> collect = list.stream().sorted((a, b) -> {
            return a.getAge() < b.getAge() ? -1 : 1;
        }).collect(Collectors.toList());

        System.out.println(collect);
    }

}
