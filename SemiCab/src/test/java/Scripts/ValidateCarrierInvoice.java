package Scripts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.genericlib.Base;
import com.genericlib.FileLib;
import com.objectrep.Invoices;
import com.objectrep.LoadManagement;
import com.relevantcodes.extentreports.LogStatus;

public class ValidateCarrierInvoice extends Base{
	@Test(groups = {"regression"} , priority = 1)
	public void validateInvoiceCreation() throws Throwable {
		LoadManagement l =PageFactory.initElements(driver, LoadManagement.class);
		Invoices i=PageFactory.initElements(driver, Invoices.class);
		test=report.startTest("validateInvoiceCreation");
		test.log(LogStatus.PASS,"Logged into SemiCab as "+fl.getDataFromProperty(prop, "username"));
		l.getLoadManagement().click();
		test.log(LogStatus.PASS, "Navigated to Load Management");
		l.getCreateload().click();
		test.log(LogStatus.PASS, "Landed on Create Load Page");
		//Pickup Location
		l.getCreateStop().click();
		l.getActivity().click();
		l.getCreateStopPickup().click();
		LoadManagement load = new LoadManagement(driver);
		String Stopname = FileLib.readstringfromexcel("LoadData", 24, 1);
		load.getCreateStopName().sendKeys(Stopname);
		String StopAdd1 = FileLib.readstringfromexcel("LoadData", 25, 1);
		load.getCreateStopAdd1().sendKeys(StopAdd1);
		String City = FileLib.readstringfromexcel("LoadData", 26, 1);
		load.getCreateStopCity().sendKeys(City);
		cu.keyBoardActionsTab(driver);
		cu.waitTillElementToBeClickable(driver, l.getCreateStopState());
		String State = FileLib.readstringfromexcel("LoadData", 27, 1);
		load.getCreateStopState().sendKeys(State);
		l.getCreateStopStatefirstSuggetion().click();
		String Zip = FileLib.readstringfromexcel("LoadData", 28, 1);
		load.getCreateStopZip().sendKeys(Zip);
		cu.keyBoardActionsBackspace(driver);
		cu.keyBoardActionsBackspace(driver);
		l.getCreateStopArraiveTime().click();
		l.getCreateStopEArraivalCalendar().click();
		l.getCreateStopCalendarOk().click();
		l.getCreateStopLArraivalCalendar().click();
		l.getCreateStopCalendarOk().click();
		l.getCreateStopSubmit().click();
		test.log(LogStatus.PASS, "Added Pickup Location");
		Reporter.log("Added Pickup location", true);
		// Drop Off Location
		l.getCreateStop().click();
		l.getActivity().click();
		l.getCreateStopDrop().click();
		String stop = FileLib.readstringfromexcel("LoadData", 24, 2);
		load.getCreateStopName().sendKeys(stop);
		String StopAdd2 = FileLib.readstringfromexcel("LoadData", 25, 2);
		load.getCreateStopAdd1().sendKeys(StopAdd2);
		String city = FileLib.readstringfromexcel("LoadData", 26, 2);
		load.getCreateStopCity().sendKeys(city);
		cu.keyBoardActionsTab(driver);
		cu.waitTillElementToBeClickable(driver, l.getCreateStopState());
		String state = FileLib.readstringfromexcel("LoadData", 27, 2);
		load.getCreateStopState().sendKeys(state);
		l.getCreateStopStatefirstSuggetion().click();
		String zip = FileLib.readstringfromexcel("LoadData", 28, 2);
		load.getCreateStopZip().sendKeys(zip);
		cu.keyBoardActionsBackspace(driver);
		cu.keyBoardActionsBackspace(driver);
		l.getCreateStopArraiveTime().click();
		l.getCreateStopEArraivalCalendar().click();
		l.getCreateStopCalendarOk().click();
		l.getCreateStopLArraivalCalendar().click();
		l.getCreateStopCalendarOk().click();
		l.getCreateStopSubmit().click();
		test.log(LogStatus.PASS, "Added Drop Off Location");
		Reporter.log("Added Drop Off location", true);
		// Customer
		Thread.sleep(2000);
		cu.waitTillElementToBeClickable(driver, l.getCreateLoadCustomer());
		String customer = FileLib.readstringfromexcel("LoadData", 23, 1);
		load.getCreateLoadCustomer().sendKeys(customer);
		cu.waitTillElementToBeVisible(driver, l.getCreateLoadFirstCustomer());
		l.getCreateLoadFirstCustomer().click();
		test.log(LogStatus.PASS, "Customer has been added");
		l.getCreateLoadNum().clear();
		String LoadNum = cu.getCurrentDateTime();
		l.getCreateLoadNum().sendKeys(LoadNum);
		l.getCreateLoadNext().click();
		test.log(LogStatus.PASS, "Custom Load Number Entered==>" + LoadNum);
		// Shipment and Equipment
		l.getCreateLoadCreateShipment().click();
		String commodity = FileLib.readstringfromexcel("LoadData", 11, 1);
		load.getCreateShipmentCommodity().sendKeys(commodity);
		String weight = FileLib.readstringfromexcel("LoadData", 12, 1);
		load.getCreateShipmentWeight().sendKeys(weight);
		l.getCreateShipmentPickup().click();
		cu.waitTillElementToBeClickable(driver, l.getCreateShipmentSelectPickStop());
		Thread.sleep(2000);
		l.getCreateShipmentSelectPickStop().click();
		l.getCreateShipmentDrop().click();
		cu.waitTillElementToBeClickable(driver, l.getCreateShipmentSelectDropStop());
		Thread.sleep(2000);
		l.getCreateShipmentSelectDropStop().click();
		l.getCreateShipmentSubmit().click();
		test.log(LogStatus.PASS, "Added Shipment Details");
		l.getCreateLoadEquipment().click();
		l.getCreateLoadEquipmentFirstSuggetion().click();
		test.log(LogStatus.PASS, "Equipment type has been selected");
		l.getCreateLoadSubmit().click();
		cu.waitTillElementToBeVisible(driver, l.getCreateLoadOK());
		l.getCreateLoadOK().click();
		test.log(LogStatus.PASS, "Load Created Successfully");
		l.getSearchLoad().sendKeys(LoadNum,Keys.ENTER);
		for(int j=0;j<=1;j++) {
			if(l.getNoRecordsFound().isDisplayed()) {
				driver.navigate().refresh();
				l.getSearchLoad().sendKeys(LoadNum,Keys.ENTER);
			}
			else
				break;
		}
		cu.waitTillElementToBeVisible(driver, l.getGetLoadNumOfFirstSearchResult());
		String[] Load = l.getGetLoadNumOfFirstSearchResult().getText().split(" ");
		String LoadNum1=Load[1].toString();
		Reporter.log(LoadNum1, true);
		AssertJUnit.assertEquals(LoadNum1.equals(LoadNum), true);
		test.log(LogStatus.PASS, "Load Creation and Load status has been verified");
		Reporter.log("Load Created Successfully", true);
		l.getLoadEllispsis().click();
		l.getAcceptLoad().click();
		cu.waitTillElementToBeVisible(driver, l.getSuccessMessageTile());
		test.log(LogStatus.PASS, "Load has been Accepted");
		l.getAccepted().click();
		l.getSearchLoad().sendKeys(LoadNum,Keys.ENTER);
		for(int j=0;j<=1;j++) {
			if(l.getNoRecordsFound().isDisplayed()) {
				driver.navigate().refresh();
				l.getSearchLoad().sendKeys(LoadNum,Keys.ENTER);
			}
			else
				break;
		}
		l.getExpandLoadDetails().click();
		cu.waitTillElementToBeClickable(driver, l.getContractLoadStop());
		l.getContractLoadStop().click();
		Thread.sleep(2000);
		cu.waitTillElementToBeClickable(driver, l.getEventStatusIcon());
		l.getEventStatusIcon().click();
		cu.waitTillElementToBeClickable(driver, l.getSelectStatusEvent());
		l.getSelectStatusEvent().click();
		cu.waitTillElementToBeClickable(driver, l.getSelectArrivedStatus());
		l.getSelectArrivedStatus().click();
		cu.waitTillElementToBeClickable(driver, l.getSelectCalender());
		l.getSelectCalender().click();
		l.getSelectArrivedCalenderOk().click();
		l.getSelectArrivedSubmit().click();
		

		cu.waitTillElementToBeClickable(driver, l.getDropOffStatusIcon());
		Thread.sleep(3000);
		l.getDropOffStatusIcon().click();
		cu.waitTillElementToBeClickable(driver, l.getSelectStatusEvent());
		l.getSelectStatusEvent().click();
		
		JavascriptExecutor javascriptexecutor=(JavascriptExecutor)driver;
		javascriptexecutor.executeScript("window.scrollBy(0,800);");
		cu.waitTillElementToBeClickable(driver, l.getSelectUnloadedStatus());
		l.getSelectUnloadedStatus().click();	
		l.getSelectCalender().click();
		l.getSelectArrivedCalenderOk().click();
		l.getSelectArrivedSubmit().click();
		Thread.sleep(2000);
		l.getCollapse().click();
		l.getLoadEllispsis().click();
		l.getMarkInTransit().click();
		l.getMarkInTransitSubmit().click();
		cu.waitTillElementToBeVisible(driver, l.getSuccessMessageTile());
		test.log(LogStatus.PASS, "Load haas been marked as In-Transit");
		l.getInTransit().click();
		
		l.getSearchLoad().sendKeys(LoadNum,Keys.ENTER);
		for(int j=0;j<=1;j++) {
			if(l.getNoRecordsFound().isDisplayed()) {
				driver.navigate().refresh();
				l.getSearchLoad().sendKeys(LoadNum,Keys.ENTER);
			}
			else
				break;
		}
		l.getExpandLoadDetails().click();
		cu.waitTillElementToBeClickable(driver, l.getContractLoadStop());
		l.getContractLoadStop().click();
		l.getSubmitpod().click();
		l.getUploadPod().click();
		l.getChooseFile().sendKeys("C:\\Users\\Sharon Jasphin\\Downloads\\POD-1638793609874.pdf");
		l.getConfirmDelivery().click();
		Thread.sleep(2000);
		cu.waitTillElementToBeVisible(driver, l.getAccounting());
		Thread.sleep(2000);
		l.getAccounting().click();
		test.log(LogStatus.PASS, "Navigated to Invoice");
		l.getAccountPayable().click();
		

		String status="INVOICED";
		String statusInvoice=i.getAccountingInvoice().getText().replace("Invoiced", "INVOICED");
		AssertJUnit.assertEquals(status.equals(statusInvoice), true);
		System.out.println("Carrier Invoice status after Invoice generation===>" +statusInvoice);
		
		
		

		
		cu.waitTillElementToBeVisible(driver, i.getInvoiceSearchLoadNum());
		i.getInvoiceSearchLoadNum().sendKeys(LoadNum);
		i.getInvoiceSearch().click();
		cu.waitTillElementToBeVisible(driver, i.getInvoicedetails());
		i.getInvoicedetails().click();
		i.getInvoiceEllipsis().click();
		i.getEditInvoice().click();
		Thread.sleep(5000);
		cu.waitTillElementToBeVisible(driver, i.getEditInvoiceIcon());
		i.getEditInvoiceIcon().click();
		Invoices invoice = new Invoices(driver); 
		Thread.sleep(3000);
		String EditInvoice = FileLib.readstringfromexcel("LoadData", 15, 2);
		invoice.getInvoiceCharge().sendKeys(EditInvoice);
		i.getEditLineSubmit().click();
		i.getEditLineSubmit2().click();
		Alert alert=driver.switchTo().alert();
		alert.accept();
		String charge =i.getVerifyInvoiveLineCharge().getText().replace("$", "");
		String InvoiceAmount=i.getVerifyInvoiceAmount().getText().replace("$", "");
		AssertJUnit.assertEquals(charge.equals(InvoiceAmount), true);
		System.out.println("Carrier Frieght InvoiceLine Charge==>"+charge);
		System.out.println("Carrier Frieght Invoice Amount Charge==>"+InvoiceAmount);
		test.log(LogStatus.PASS, "Carrier Frieght InvoiceLine Editted");
		
		i.getInvoiceEllipsis().click();
		Thread.sleep(2000);
		cu.waitTillElementToBeVisible(driver, i.getCancelInvoice());
		String status2="CANCELLED";
		i.getCancelInvoice().click();
		String statuscancelled=i.getVerifyCancelledStatus().getText().replace("Cancelled", "CANCELLED");
		AssertJUnit.assertEquals(status2.equals(statuscancelled), true);
		System.out.println("Carrier Freight invoice after cancellation==>"+statuscancelled);
		test.log(LogStatus.PASS, "Ivoice has been cancelled");
		Thread.sleep(2000);
		l.getLoadManagement().click();
		l.getDelivered().click();
		Thread.sleep(2000);
		l.getSearchLoad().sendKeys(LoadNum,Keys.ENTER);
		l.getExpandLoadDetails().click();
		try {
			i.getLoadChargeInvoice().click();
		} catch (StaleElementReferenceException e) {
			i.getLoadChargeInvoice().click();
		}
		Thread.sleep(2000);	
		i.getRegenerateCarrierInvoice().click();
		String statusInvoice2=i.getStatusInvoiced().getText().replace("Invoiced", "INVOICED");
		AssertJUnit.assertEquals(status.equals(statusInvoice2), true);
		System.out.println(statusInvoice2);
		Thread.sleep(2000);
		l.getAccounting().click();
		l.getAccountPayable().click();
		i.getInvoiceLoadSearch().sendKeys(LoadNum,Keys.ENTER);
		Thread.sleep(2000);
		i.getSearchInvoice().click();
		String statusInvoice3=i.getAccountingInvoice().getText().replace("Invoiced", "INVOICED");
		AssertJUnit.assertEquals(status.equals(statusInvoice3), true);
		System.out.println("Invoice Created after cancelling an exsisting carrier invoice==> "+statusInvoice3);
		test.log(LogStatus.PASS, "Invoice Created after cancelling an existing carrier invoice");

		 
		  
		 
		
	
		
}
}
