package type;

import com.yc.type.domain.TypeDomain;
import com.yc.type.service.TypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
public class TestService {

    private static final Logger logger = Logger.getLogger(TestService.class.getName());

    @Autowired
    private TypeService typeService;

    @Test
    public void testList() {
        logger.info("调用typeService.list");
        List<TypeDomain> list = typeService.list();
        System.out.println(list);
    }


}
