package type;

import com.yc.product.domain.ProductDomain;
import com.yc.type.domain.TypeDomain;
import com.yc.type.service.TypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Random;
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

    @Test
    public void testSave() {
        Random r = new Random();
        TypeDomain typeDomain = new TypeDomain(16, "", "",77);
        typeService.save(typeDomain);
        logger.info("新增的产品编号:" + typeDomain.getTno());
        //断言.
        Assert.notNull(typeDomain.getTno(), "not insert");
    }

    @Test
    public void testDelete() {
        typeService.delete(16);
    }


}
