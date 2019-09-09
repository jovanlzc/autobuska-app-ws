package com.qubikapp.app.ws.shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
//ukljucujem spring context da mogu da koristim beanove iz spring kontejnera
//koriscen JUnit5
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UtilsTest {
	@Autowired
	Utils utils;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testGenerateUserIdNotNull() {
		String userId = utils.generateUserId(30);
		assertNotNull(userId);
	}
	
	@Test
	void testGenerateUserIdUnique() {
		String userId = utils.generateUserId(30);
		String userId2 = utils.generateUserId(30);
		assertTrue(!userId.equalsIgnoreCase(userId2));
	}
	
	void testGenerateUserIdLength() {
		String userId = utils.generateUserId(30);
		assertTrue(userId.length()==30);
	}
	
	
	@Test 
	void testGenerateEmailVerificationTokenNotNull() {
		String userId = utils.generateUserId(30);
		String emailToken = utils.generateEmailVerificationToken(userId);
		assertNotNull(emailToken);
		
	}
	
	//prosledjujem validan token
		@Test
		void testHasTokenNotExpired() {
			String token=utils.generateEmailVerificationToken(utils.generateUserId(30));
			assertNotNull(token);
			boolean hasTokenExpired = utils.hasTokenExpired(token);
			
			assertFalse(hasTokenExpired);
			
		}
		
		//prosledjujem token koji je istekao,provera da li je istekao,potrebno je postaviti expiration time u SecurityConstraintsu na malo vreme , da bi radio
		@Test
		void testHasTokenExpired() {
			//postaviti vrednost expiration time na manje vreme ili postaviti token koji je zaista istekao!!!
			String expiredToken="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb3Zhbi5sYXppYy4xMEBnbWFpbC5jb20iLCJleHAiOjE1Njg5MDU4NzN9.zZF96EvhXdm-L-0mOmTFs7Ur1XaUdR8hOjn9FuV51mMO5cT9iNT338ZgHYZ5qCvv-fzt_UhwlI3NAL2OnhwBAQ";
//			String expiredToken="1";
			boolean hasTokenExpired = utils.hasTokenExpired(expiredToken);
			assertTrue(hasTokenExpired);
		}
	

}
