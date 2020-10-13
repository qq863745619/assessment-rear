package com.nju.software.assessment;

import com.nju.software.assessment.bean.Form;
import com.nju.software.assessment.dao.FormDao;
import net.sourceforge.jtds.jdbc.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssessmentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssessmentApplicationTests {



    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private FormDao formDao;

    @Test
    public void testMongodb(){



    }

    @Test
    public void testMongodb2(){
        Form form = formDao.findFormById(39);

        System.out.println(form.toString());
    }


}
