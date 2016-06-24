package by.lskrashchuk.training.parking.service;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.lskrashchuk.training.parking.datamodel.Color;
import by.lskrashchuk.training.parking.service.ColorService;

	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(locations = {"classpath:service-context-test.xml"})
	public class ColorServiceTest {
		
		@Inject
		private ColorService colorService;

		
		@Test
		public void testRegistrationColor(){
			Color color = new Color();
			color.setName("red");
	        colorService.register(color);
	        
	        Long colorId = color.getId();

	        Color registredColor = colorService.getColor(colorId);

	        Assert.assertNotNull(registredColor);

	        String updatedFName = "новыйЦвет";
	        color.setName(updatedFName);
	        colorService.update(color);

	        Assert.assertEquals(updatedFName, colorService.getColor(color.getId()).getName());

	        colorService.delete(color.getId());

	        Assert.assertNull(colorService.getColor(color.getId()));
	  
		}


}
