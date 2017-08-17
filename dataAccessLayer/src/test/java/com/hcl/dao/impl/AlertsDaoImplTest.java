/*package com.hcl.dao.impl;


import static org.mockito.Mockito.*; 

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;

import com.hcl.dao.AlertsDao;

//Load Spring contexte
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AlertsDaoImplTest {
   
  // Create Mock
  @Mock
  private AlertsDao alertsDaoMock;
   
  @InjectMocks
  @Autowired
  private IInvoiceService invoiceService;
   
   
  @Before
  public void setUp() {
      MockitoAnnotations.initMocks(this);
  }

   
  @Test
  public void testProcessInvoice() throws SQLException {
       
      // specify mock behave when method called
      when(invoiceDaoMock.save(any(Invoice.class))).thenReturn(Long.valueOf(1));
       
      Assert.assertNotNull(invoiceService);
      Map<Product, Integer> products = new HashMap<Product, Integer>();
      products.put(new Product("labtop", BigDecimal.valueOf(1255.50)), 2);
      Invoice invoice = invoiceService.processInvoice(products);
       
      Assert.assertEquals(1255.50 * 2, invoice.getTotal().doubleValue(), 0);
       
  }
}*/