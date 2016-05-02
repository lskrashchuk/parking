package services;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.lskrashchuk.training.parking.dataaccess.BrandDao;
import by.lskrashchuk.training.parking.datamodel.Brand;
import by.lskrashchuk.training.parking.service.BrandService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-context-test.xml"})
public class BrandServiceTest {
	
	@Inject
	private BrandService brandService;
	
	@Test
	public void testRegistrationBrand(){
		Brand brand = new Brand();
		brand.setName("Peugeot");
        brandService.register(brand);
        

        Brand registredBrand = brandService.getBrand(brand.getId());

        Assert.assertNotNull(registredBrand);

        String updatedBrand = "newBrand";
        brand.setName(updatedBrand);
        brandService.update(brand);

        Assert.assertEquals(updatedBrand, brandService.getBrand(brand.getId()).getName());

        brandService.delete(brand.getId());

        Assert.assertNull(brandService.getBrand(brand.getId()));
  
	}



}
