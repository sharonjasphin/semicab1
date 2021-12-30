package com.objectrep;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Invoices {
	
	public Invoices(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "loadNum")
	private WebElement InvoiceSearchLoadNum;
	
	@FindBy(xpath = "//span[text()='Search']")
	private WebElement InvoiceSearch;
	
	@FindBy(xpath = "//span[text()='Reset']")
	private WebElement InvoiceReset;
	
	@FindBy(xpath = "//div[text()='Created On']/parent::div/following-sibling::div//*[name()='svg' and @stroke='currentColor']")
	private WebElement invoicedetails;
	
	@FindBy(xpath = "//div[text()='Customer']/following-sibling::div")
	private WebElement InvoiceCustomer;
	
	@FindBy(xpath = "//div[text()='Load#']/following-sibling::div/div")
	private WebElement InvoiceLoadNum;
	
	@FindBy(xpath = "//div[text()='Payment Terms (Days)']/following-sibling::div")
	private WebElement InvoicePaymentTerms;
	
	@FindBy(xpath = "//div[text()='Contract#']/following-sibling::div/div")
	private WebElement InvoiceContractNum;
	
	@FindBy(xpath = "//div[text()='Payment Terms (days)']/parent::div/following-sibling::div/div")
	private WebElement ContractPaymentTerms;
	
	@FindBy(xpath = "//div[text()='Status']/following-sibling::div/span")
	private WebElement InvoiceStatus;
	
	@FindBy(xpath = "//div[text()='Invoice']/following-sibling::div//*[name()='svg']")
	private WebElement InvoiceEllipsis;
	
	@FindBy(xpath = "//span[text()='Cancel']")
	private WebElement CancelInvoice;
	
	@FindBy(xpath = "(//span[contains(text(),'Load')]/parent::div/following-sibling::div/child::span//*[name()='svg'])[3]")
	private WebElement LoadChargeInvoice;
	
	@FindBy(xpath = "//div[text()='Customer Freight Charge']/following-sibling::div//*[name()='path']")
	private WebElement RegenerateInvoice;
	
	@FindBy(xpath = "//div[text()='Carrier Freight Charge']/following-sibling::div//*[name()='path']")
	private WebElement RegenerateCarrierInvoice;
	
	@FindBy(xpath = "(//div[@class='App_detailfieldvalue__u7uLp App_valuespacing__3BH1i jss110'])[4]")
	private WebElement Invoice;
	
	@FindBy(xpath = "//span[text()='Search']")
	private WebElement SearchInvoice;
	
	
	@FindBy(xpath = "//span[text()='Cancelled']")
	private WebElement VerifyCancelledStatus;
	
	@FindBy(xpath = "(//span[contains(text(),'Load')]/parent::div/following-sibling::div/child::span//*[name()='svg'])[3]")
	private WebElement InvoicechargeIcon;
	
	@FindBy(xpath = "//span[text()='Edit Invoice']")
	private WebElement EditInvoice;

	@FindBy(xpath = "//div[text()='Per Shipment']/following-sibling::div//*[name()='svg']")
	private WebElement EditInvoiceIcon;
	
	@FindBy(xpath = "//input[@id='charge']")
	private WebElement InvoiceCharge;
	
	@FindBy(id = "loadNum")
	private WebElement InvoiceLoadSearch;
	
	@FindBy(xpath = "//span[text()='Invoiced']")
	private WebElement StatusInvoiced;
	
	@FindBy(xpath = "//div[text()='Invoiced']")
	private WebElement AccountingInvoice;
	
	@FindBy(xpath = "(//span[text()='Submit'])[2]")
	private WebElement EditLineSubmit;
	
	@FindBy(xpath = "(//span[text()='Submit'])")
	private WebElement EditLineSubmit2;
	
	@FindBy(xpath = "//div[text()='Accessorial Type']/parent::div/following-sibling::div/descendant::div/following::div/following::div/following::div/following::div")
	private WebElement VerifyInvoiveLineCharge;
	
	@FindBy(xpath = "//div[text()='Invoice Amount']/following-sibling::div")
	private WebElement VerifyInvoiceAmount;
	//Getters

	public WebElement getInvoiceSearchLoadNum() {
		return InvoiceSearchLoadNum;
	}

	public WebElement getInvoiceSearch() {
		return InvoiceSearch;
	}

	public WebElement getInvoiceReset() {
		return InvoiceReset;
	}

	public WebElement getInvoicedetails() {
		return invoicedetails;
	}

	public WebElement getInvoiceCustomer() {
		return InvoiceCustomer;
	}

	public WebElement getInvoiceLoadNum() {
		return InvoiceLoadNum;
	}

	public WebElement getInvoiceStatus() {
		return InvoiceStatus;
	}

	public WebElement getInvoicePaymentTerms() {
		return InvoicePaymentTerms;
	}

	public WebElement getInvoiceContractNum() {
		return InvoiceContractNum;
	}

	public WebElement getContractPaymentTerms() {
		return ContractPaymentTerms;
	}

	public WebElement getInvoiceEllipsis() {
		return InvoiceEllipsis;
	}

	public WebElement getCancelInvoice() {
		return CancelInvoice;
	}

	public WebElement getLoadChargeInvoice() {
		return LoadChargeInvoice;
	}

	public WebElement getRegenerateInvoice() {
		return RegenerateInvoice;
	}


	public WebElement getInvoice() {
		return Invoice;
	}

	public WebElement getSearchInvoice() {
		return SearchInvoice;
	}

	public WebElement getVerifyCancelledStatus() {
		return VerifyCancelledStatus;
	}

	public WebElement getInvoicechargeIcon() {
		return InvoicechargeIcon;
	}

	public WebElement getEditInvoice() {
		return EditInvoice;
	}

	public WebElement getEditInvoiceIcon() {
		return EditInvoiceIcon;
	}

	public WebElement getInvoiceCharge() {
		return InvoiceCharge;
	}

	public WebElement getInvoiceLoadSearch() {
		return InvoiceLoadSearch;
	}

	public WebElement getStatusInvoiced() {
		return StatusInvoiced;
	}

	public WebElement getAccountingInvoice() {
		return AccountingInvoice;
	}

	public WebElement getEditLineSubmit() {
		return EditLineSubmit;
	}

	public WebElement getEditLineSubmit2() {
		return EditLineSubmit2;
	}

	public WebElement getVerifyInvoiveLineCharge() {
		return VerifyInvoiveLineCharge;
	}

	public WebElement getVerifyInvoiceAmount() {
		return VerifyInvoiceAmount;
	}

	public WebElement getRegenerateCarrierInvoice() {
		return RegenerateCarrierInvoice;
	}


	
}
