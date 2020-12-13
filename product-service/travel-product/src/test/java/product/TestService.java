package product;

import com.yc.pics.domain.PicsDomain;
import com.yc.pics.service.PicsService;
import com.yc.product.domain.ProductDomain;
import com.yc.product.service.ProductService;
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
    private ProductService productService;




    @Test
    public void testList() {
        logger.info("调用Service.list");
        List<ProductDomain> list = productService.list();
        System.out.println(list);
    }


    @Test
    public void testSave() {
        Random r = new Random();
        ProductDomain productDomain = new ProductDomain(16, "", 3,77,"",77,
                "","");
        productService.save(productDomain);
        logger.info("新增的产品编号:" + productDomain.getPid());
        //断言.
        Assert.notNull(productDomain.getPid(), "not insert");
    }

    @Test
    public void testDelete() {
        productService.delete(16);
    }



}
