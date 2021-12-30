package Scripts;

import java.util.Random;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.genericlib.Base;
import com.genericlib.FileLib;
import com.objectrep.LoadManagement;
import com.relevantcodes.extentreports.LogStatus;

public class ValidateCustomerFeightCharge extends Base {

	@Test(groups = { "regression" }, priority = 1)
	public void validateInvoiceCreation() throws Throwable {
		LoadManagement l = PageFactory.initElements(driver, LoadManagement.class);
		// Invoices i=PageFactory.initElements(driver, Invoices.class);
		test = report.startTest("validateInvoiceCreation");
		test.log(LogStatus.PASS, "Logged into SemiCab as " + fl.getDataFromProperty(prop, "username"));
		l.getLoadManagement().click();
		test.log(LogStatus.PASS, "Navigated to Load Management");
		l.getCreateload().click();
		test.log(LogStatus.PASS, "Landed on Create Load Page");
		// Pickup Location
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
		l.getSearchLoad().sendKeys(LoadNum, Keys.ENTER);
		for (int j = 0; j <= 1; j++) {
			if (l.getNoRecordsFound().isDisplayed()) {
				driver.navigate().refresh();
				l.getSearchLoad().sendKeys(LoadNum, Keys.ENTER);
			} else
				break;
		}
		cu.waitTillElementToBeVisible(driver, l.getGetLoadNumOfFirstSearchResult());
		String[] Load = l.getGetLoadNumOfFirstSearchResult().getText().split(" ");
		String LoadNum1 = Load[1].toString();
		Reporter.log(LoadNum1, true);
		AssertJUnit.assertEquals(LoadNum1.equals(LoadNum), true);
		test.log(LogStatus.PASS, "Load Creation and Load status has been verified");
		Reporter.log("Load Created Successfully", true);
		l.getLoadEllispsis().click();
		l.getAcceptLoad().click();
		cu.waitTillElementToBeVisible(driver, l.getSuccessMessageTile());
		test.log(LogStatus.PASS, "Load has been Accepted");
		l.getAccepted().click();
		l.getSearchLoad().sendKeys(LoadNum, Keys.ENTER);
		for (int j = 0; j <= 1; j++) {
			if (l.getNoRecordsFound().isDisplayed()) {
				driver.navigate().refresh();
				l.getSearchLoad().sendKeys(LoadNum, Keys.ENTER);
			} else
				break;
		}
		l.getExpandLoadDetails().click();
		try {
			l.getLoadCharge().click();
		} catch (StaleElementReferenceException e) {
			l.getLoadCharge().click();
		}
		Thread.sleep(2000);
		cu.waitTillElementToBeClickable(driver, l.getCustomerFrieghtCharges2());
		l.getCustomerFrieghtCharges2().click();
		String Freight = FileLib.readstringfromexcel("LoadData", 14, 2);
		load.getCustomerFreightChargetextbox().sendKeys(Freight);
		cu.waitTillElementToBeClickable(driver, l.getSaveCustomerFreightCharge());
		l.getSaveCustomerFreightCharge().click();
		try {
			Thread.sleep(2000);
			l.getEditCustomerFreightCharge().click();
		} catch (StaleElementReferenceException e) {
			l.getEditCustomerFreightCharge().click();
		}
		cu.waitTillElementToBeClickable(driver, l.getAddCustomerFreightCharge());
		l.getAddCustomerFreightCharge().click();
		cu.waitTillElementToBeClickable(driver, l.getAddAccessorialCharge());
		l.getAddAccessorialCharge().click();
		Thread.sleep(3000);
		cu.waitTillElementToBeClickable(driver, l.getAddLayover());
		Thread.sleep(2000);
		l.getAddLayover().click();
		l.getSubmit().click();
		Thread.sleep(3000);
		JavascriptExecutor javascriptexecutor=(JavascriptExecutor)driver;
		javascriptexecutor.executeScript("window.scrollBy(0,500);");
		Thread.sleep(3000);
		l.getDeleteFreightCharges().click();
		javascriptexecutor.executeScript("window.scrollBy(0,-500);");
		l.getSaveCustomerFreightCharge().click();
		Thread.sleep(2000);
		String charge =l.getVerifyCustomerFreightCharge().getText().replace("$", "");
		String total=l.getVerifyTotalCustomerFreightcharge().getText().replace("$", "");
		Random r= new Random();
		int ordernumber=r.nextInt(9999);
		System.out.println("Customer Freight Charge==>"+charge);
		System.out.println("Total Customer Freight Charge==>"+total);
		test.log(LogStatus.PASS, " Customer Freight Charge==>"+charge);
		test.log(LogStatus.PASS, " Total Customer Freight Charge==>"+total);
		AssertJUnit.assertEquals(charge.equals(total), true);




	}
}
