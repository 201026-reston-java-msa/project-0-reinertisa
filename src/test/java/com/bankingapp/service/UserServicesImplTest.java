package com.bankingapp.service;

import static org.junit.Assert.assertFalse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserServicesImplTest {
	
	private static final UserServicesImpl userServicesImpl = new UserServicesImpl();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testUsernameAnEmptyString() {
		assertFalse(userServicesImpl.checkUsernameAndPassword("", "123"));
	}
	
	@Test
	public void testPasswordAnEmptyString() {
		assertFalse(userServicesImpl.checkUsernameAndPassword("ben", ""));
	}
	
	

}
