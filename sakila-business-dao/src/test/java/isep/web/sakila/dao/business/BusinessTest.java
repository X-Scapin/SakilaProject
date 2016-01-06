package isep.web.sakila.dao.business;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import isep.web.sakila.jpa.config.PersistenceConfig;

@SpringApplicationConfiguration(classes = PersistenceConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BusinessTest
{
	@Autowired
	private IBusiness business;

	@Test
	public void testGetAllActors()
	{
		Assert.assertEquals(200, business.getAllActors().size());
	}

	@Test
	public void testGetByID()
	{
		Assert.assertEquals("GUINESS", business.getByID(1).getLastName());
	}

}