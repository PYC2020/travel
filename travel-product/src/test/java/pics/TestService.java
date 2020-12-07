package pics;

import com.yc.pics.domain.PicsDomain;
import com.yc.pics.service.PicsService;
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
    private PicsService picService;

    @Test
    public void testList() {
        logger.info("调用adminService.list");
        List<PicsDomain> list = picService.list();
        System.out.println(list);
    }


}
