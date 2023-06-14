package com.example.demo.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.psa.app.model.Box;
import com.psa.app.service.MenaceMachineService;
import com.psa.app.utils.FileUtils;
import com.psa.app.utils.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
public class UtilsTest {


//  @Mock
//  private ToDoRepository toDoRepository;
//
  @InjectMocks
  private FileUtils fileUtil;
  
  @InjectMocks
  private Utils utils;


  @Before
  public void setup() {
      MockitoAnnotations.initMocks(this);
  }

  @Test
  public void loadPropertiesTest() {
  	
      Properties prop = new Properties();
	try {
		prop = fileUtil.loadPropertiesFile("training.properties");
	} catch (Exception e) {
		e.printStackTrace();
	}
	assertNotNull(prop);
      
  }
  
  @Test
  public void readTrainingData() {
	  Map<String, Box> hashmap = new HashMap<>();
	  try {
		  hashmap = fileUtil.readTrainingData();
	} catch (Exception e) {
		e.printStackTrace();
	} 
		assertNotNull(hashmap);
  }
  
  @Test
  public void getRandomNumberTest() {
	int number= utils.getRandomNumber();
	assertNotEquals(number, 0);
  }
  
  @Test
  public void getRandomArrLenTest() {
	int number= utils.getRandomArrLen();
	assertNotEquals(number, 0);
  }

}
