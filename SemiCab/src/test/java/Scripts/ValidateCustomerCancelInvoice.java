package Scripts;

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

public class ValidateCustomerCancelInvoice extends Base {
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
		String Stopname = FileLib.readstringfromexcel("LoadData", 3, 1);
		load.getCreateStopName().sendKeys(Stopname);
		String StopAdd1 = FileLib.readstringfromexcel("LoadData", 4, 1);
		load.getCreateStopAdd1().sendKeys(StopAdd1);
		String City = FileLib.readstringfromexcel("LoadData", 5, 1);
		load.getCreateStopCity().sendKeys(City);
		cu.keyBoardActionsTab(driver);
		cu.waitTillElementToBeClickable(driver, l.getCreateStopState());
		String State = FileLib.readstringfromexcel("LoadData", 14, 2);
		load.getCreateStopState().sendKeys(State);
		l.getCreateStopStatefirstSuggetion().click();
		String Zip = FileLib.readstringfromexcel("LoadData", 7, 1);
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
		String stop = FileLib.readstringfromexcel("LoadData", 3, 2);
		load.getCreateStopName().sendKeys(stop);
		String StopAdd2 = FileLib.readstringfromexcel("LoadData", 4, 2);
		load.getCreateStopAdd1().sendKeys(StopAdd2);
		String city = FileLib.readstringfromexcel("LoadData", 5, 2);
		load.getCreateStopCity().sendKeys(city);
		cu.keyBoardActionsTab(driver);
		cu.waitTillElementToBeClickable(driver, l.getCreateStopState());
		String state = FileLib.readstringfromexcel("LoadData", 6, 2);
		load.getCreateStopState().sendKeys(state);
		l.getCreateStopStatefirstSuggetion().click();
		String zip = FileLib.readstringfromexcel("LoadData", 7, 2);
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
		String customer = FileLib.readstringfromexcel("LoadData", 2, 2);
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
		l.getLoadEllispsis().click();
		l.getMarkDelivered().click();
		Thread.sleep(3000);
		l.getMarkDeliveredDelivered().click();
		cu.waitTillElementToBeVisible(driver, l.getSuccessMessageTile());
		l.getDelivered().click();
		Thread.sleep(2000);
		l.getSearchLoad().sendKeys(LoadNum,Keys.ENTER);

		AssertJUnit.assertEquals(l.getLoadEllispsis().isDisplayed(), true);
		test.log(LogStatus.PASS, "Load has been Marked Delivered");
		l.getAccounting().click();
		String status="INVOICED";
		String statusInvoice=i.getAccountingInvoice().getText().replace("Invoiced", "INVOICED");
		AssertJUnit.assertEquals(status.equals(statusInvoice), true);
		System.out.println(statusInvoice);
		test.log(LogStatus.PASS, "Navigated to Invoice");
		cu.waitTillElementToBeVisible(driver, i.getInvoiceSearchLoadNum());
		Thread.sleep(2000);
		i.getInvoiceSearchLoadNum().sendKeys(LoadNum);
		i.getInvoiceSearch().click();
		cu.waitTillElementToBeVisible(driver, i.getInvoicedetails());
		i.getInvoicedetails().click();
		i.getInvoiceEllipsis().click();
		Thread.sleep(2000);
		cu.waitTillElementToBeVisible(driver, i.getCancelInvoice());
		String status2="CANCELLED";
		i.getCancelInvoice().click();
		String statuscancelled=i.getVerifyCancelledStatus().getText().replace("Cancelled", "CANCELLED");
		AssertJUnit.assertEquals(status2.equals(statuscancelled), true);
		System.out.println(statuscancelled);
		test.log(LogStatus.PASS, "Ivoice has been cancelled");
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
		i.getRegenerateInvoice().click();
		String statusInvoice2=i.getStatusInvoiced().getText().replace("Invoiced", "INVOICED");
		AssertJUnit.assertEquals(status.equals(statusInvoice2), true);
		System.out.println(statusInvoice2);
		Thread.sleep(2000);
		l.getAccounting().click();
		i.getInvoiceLoadSearch().sendKeys(LoadNum,Keys.ENTER);
		Thread.sleep(2000);
		i.getSearchInvoice().click();
		String statusInvoice3=i.getAccountingInvoice().getText().replace("Invoiced", "INVOICED");
		AssertJUnit.assertEquals(status.equals(statusInvoice3), true);
		System.out.println(statusInvoice3);
		test.log(LogStatus.PASS, "Invoice Created after cancelling an existing invoice");

	}
}

