

import com.test.eventtracker.EventTrackerApplication;
import com.test.eventtracker.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {EventTrackerApplication.class})

@TestExecutionListeners(listeners = {ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
//        WithSecurityContextTestExecutionListener.class})
//@WithMockUser(username = "root", roles = {"USER", "ADMIN"})
public class MessageEntityServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(MessageEntityServiceTest.class);

    @Autowired
    EventService eventService;

//    OrderDTO order;
//
//    @Before
//    public void setUp() {
//
//        order = new OrderDTO();
//        List<ProductDTO> productDTOList = new ArrayList<>();
//
//        ProductDTO p1 = new ProductDTO();
//        p1.setTitle("banana");
//        p1.setPrice(new BigDecimal(1.11));
//        productDTOList.add(p1);
//        ProductDTO p2 = new ProductDTO();
//        p2.setTitle("apple");
//        p2.setPrice(new BigDecimal(2.12));
//        productDTOList.add(p2);
//
//        order.setProducts(productDTOList);
//
//
//    }


//    @Test
////    @Transactional
////   // @Rollback(true)
//    public void testSaveOrder() {
//
//        String orderId = eventService.createOrder(order);
//
//        assertNotNull(orderId);
//    }


    @Test
    @Transactional
    @Rollback(true)
    public void testSaveProduct() {


    }

}
