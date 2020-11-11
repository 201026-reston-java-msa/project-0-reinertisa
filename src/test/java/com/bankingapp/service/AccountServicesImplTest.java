package com.bankingapp.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bankingapp.customexceptions.*;

public class AccountServicesImplTest {
	
	private static final AccountServicesImpl accountServices = new AccountServicesImpl();
	
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	/*******************************************************************
	 * isCreateCheckingAccount
	 ******************************************************************/
	
	@Test
	public void testCreateCheckingAccountNullAmount() {
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("Empty Amount");
		accountServices.isValidAmountToCreateCheckingAccount("");				
	}
	
	@Test
	public void testCreateCheckingAccountInvalidAmount() {
		
		expectedException.expect(NumberFormatException.class);
		expectedException.expectMessage("Invalid Amount");
		accountServices.isValidAmountToCreateCheckingAccount("a100");			
	}
	
	@Test
	public void testCreateCheckingAccountNegativeAmount() {
		expectedException.expect(NegativeAmountException.class);
		expectedException.expectMessage("Negative Amount");
		accountServices.isValidAmountToCreateCheckingAccount("-10");
	}
	
	@Test
	public void testCreateCheckingAccountInsufficientAmount() {
		expectedException.expect(InsufficientAmountException.class);
		expectedException.expectMessage("Insufficient Amount");
		accountServices.isValidAmountToCreateCheckingAccount("10");
	}
	
	@Test
	public void testValidCreateCheckingAccount() {
		assertTrue(accountServices.isCreateCheckingAccount(1, "100"));
	}
	
	@Test
	public void testCreateCheckingAccountWithEmptyAmount() {
		assertFalse(accountServices.isCreateCheckingAccount(1, ""));
	}
	
	@Test
	public void testCreateCheckingAccountWithInvalidAmount() {
		assertFalse(accountServices.isCreateCheckingAccount(1, "aaa100"));
	}
	
	@Test
	public void testCreateCheckingAccountWithNegativeAmount() {
		assertFalse(accountServices.isCreateCheckingAccount(1, "-250"));
	}
	
	@Test
	public void testCreateCheckingAccountWithInsufficientAmount() {
		assertFalse(accountServices.isCreateCheckingAccount(1, "24"));
	}

	/*******************************************************************
	 * isCreateSAvingsAccount
	 ******************************************************************/
	
	@Test
	public void testCreateSavingsAccountNullAmount() {
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("Empty Amount");
		accountServices.isValidAmountToCreateSavingsAccount("");				
	}
	
	@Test
	public void testCreateSavingsAccountInvalidAmount() {
		expectedException.expect(NumberFormatException.class);
		expectedException.expectMessage("Invalid Amount");
		accountServices.isValidAmountToCreateSavingsAccount("a100");				
	}
	
	@Test
	public void testCreateSavingsAccountNegativeAmount() {
		expectedException.expect(NegativeAmountException.class);
		expectedException.expectMessage("Negative Amount");
		accountServices.isValidAmountToCreateSavingsAccount("-10");
	}
	
	@Test
	public void testCreateSavingsAccountInsufficientAmount() {
		expectedException.expect(InsufficientAmountException.class);
		expectedException.expectMessage("Insufficient Amount");
		accountServices.isValidAmountToCreateSavingsAccount("10");
	}
	
	@Test
	public void testValidCreateSavingsAccount() {
		assertTrue(accountServices.isCreateSavingsAccount(1, "100"));
	}
	
	@Test
	public void testCreateSavingsAccountWithEmptyAmount() {
		assertFalse(accountServices.isCreateSavingsAccount(1, ""));
	}
	
	@Test
	public void testCreateSavingsAccountWithInvalidAmount() {
		assertFalse(accountServices.isCreateSavingsAccount(1, "aaa100"));
	}
	
	@Test
	public void testCreateSavingsAccountWithNegativeAmount() {
		assertFalse(accountServices.isCreateSavingsAccount(1, "-250"));
	}
	
	@Test
	public void testCreateSavingsAccountWithInsufficientAmount() {
		assertFalse(accountServices.isCreateCheckingAccount(1, "24"));
	}
	
	/*******************************************************************
	 * isMakeAWithdrawal
	 ******************************************************************/
	
	@Test
	public void testNullAmountForWithdrawal() {
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("Empty Amount");
		accountServices.isValidAmountToMakeATransaction("");				
	}
	
	@Test
	public void testInvalidAmountForWithdrawal() {
		expectedException.expect(NumberFormatException.class);
		expectedException.expectMessage("Invalid Amount");
		accountServices.isValidAmountToMakeATransaction("a100");				
	}
	
	@Test
	public void testNegativeAmountForWithdrawal() {
		expectedException.expect(NegativeAmountException.class);
		expectedException.expectMessage("Negative Amount");
		accountServices.isValidAmountToMakeATransaction("-10");
	}
	
	@Test
	public void testNullAccountIdForWithdrawal() {
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("Empty AccountId");
		accountServices.isValidAccountToMakeATransaction("");				
	}
	
	@Test
	public void testInvalidAccountIdForWithdrawal() {
		expectedException.expect(InvalidAccountException.class);
		expectedException.expectMessage("Invalid AccountId");
		accountServices.isValidAccountToMakeATransaction("a100");	
		
		expectedException.expect(InvalidAccountException.class);
		expectedException.expectMessage("Invalid AccountId");
		accountServices.isValidAccountToMakeATransaction("-100");
	}
	
	@Test
	public void testValidMakeAWithdrawal() {
		assertTrue(accountServices.isMakeAWithdrawal(1, 1.00));
	}
	
	@Test
	public void testMakeAWithdrawalInvalidAmount() {
		assertFalse(accountServices.isMakeAWithdrawal(1, 1000000));
	}
	
	
	/*******************************************************************
	 * isMakeADeposit
	 ******************************************************************/
	
	@Test
	public void testNullAmountForDeposit() {
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("Empty Amount");
		accountServices.isValidAmountToMakeATransaction("");				
	}
	
	@Test
	public void testInvalidAmountForDeposit() {
		expectedException.expect(NumberFormatException.class);
		expectedException.expectMessage("Invalid Amount");
		accountServices.isValidAmountToMakeATransaction("a100");				
	}
	
	@Test
	public void testNegativeAmountForDeposit() {
		expectedException.expect(NegativeAmountException.class);
		expectedException.expectMessage("Negative Amount");
		accountServices.isValidAmountToMakeATransaction("-10");
	}
	
	@Test
	public void testNullAccountIdForDeposit() {
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("Empty AccountId");
		accountServices.isValidAccountToMakeATransaction("");				
	}
	
	@Test
	public void testInvalidAccountIdForDeposit() {
		expectedException.expect(InvalidAccountException.class);
		expectedException.expectMessage("Invalid AccountId");
		accountServices.isValidAccountToMakeATransaction("a100");	
		
		expectedException.expect(InvalidAccountException.class);
		expectedException.expectMessage("Invalid AccountId");
		accountServices.isValidAccountToMakeATransaction("-100");
	}
	
	@Test
	public void testValidMakeADeposit() {
		assertTrue(accountServices.isMakeADeposit(1, 100.00));
	}
	
	/*******************************************************************
	 * isMakeATransferFunds
	 ******************************************************************/
	
	@Test
	public void testNullAmountForTransfer() {
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("Empty Amount");
		accountServices.isValidAmountToMakeATransaction("");				
	}
	
	@Test
	public void testInvalidAmountForTransfer() {
		expectedException.expect(NumberFormatException.class);
		expectedException.expectMessage("Invalid Amount");
		accountServices.isValidAmountToMakeATransaction("a100");				
	}
	
	@Test
	public void testNegativeAmountForTransfer() {
		expectedException.expect(NegativeAmountException.class);
		expectedException.expectMessage("Negative Amount");
		accountServices.isValidAmountToMakeATransaction("-10");
	}
	
	@Test
	public void testNullAccountIdForTransfer() {
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("Empty AccountId");
		accountServices.isValidAccountToMakeATransaction("");				
	}
	
	@Test
	public void testInvalidAccountIdForTransfer() {
		expectedException.expect(InvalidAccountException.class);
		expectedException.expectMessage("Invalid AccountId");
		accountServices.isValidAccountToMakeATransaction("a100");	
		
		expectedException.expect(InvalidAccountException.class);
		expectedException.expectMessage("Invalid AccountId");
		accountServices.isValidAccountToMakeATransaction("-100");
	}
	
	
}
