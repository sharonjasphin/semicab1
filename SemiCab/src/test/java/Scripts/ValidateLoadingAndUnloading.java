package Scripts;

import java.text.SimpleDateFormat;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.genericlib.Base;
import com.genericlib.FileLib;
import com.objectrep.LoadManagement;
import com.relevantcodes.extentreports.LogStatus;

public class ValidateLoadingAndUnloading extends Base{
	@Test(groups = {"regression"} , priority = 1)
	public void ValidateLoadingAndUnloadingEvent() throws Throwable {

		LoadManagement l =PageFactory.initElements(driver, LoadManagement.class);
		test=report.startTest("ValidateAppointment");
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
		//Loading Status
		l.getExpandLoadDetails().click();
		cu.waitTillElementToBeClickable(driver, l.getLoadStops());
		l.getLoadStops().click();
		cu.waitTillElementToBeClickable(driver, l.getEventStatusIcon());
		l.getEventStatusIcon().click();
		cu.waitTillElementToBeClickable(driver, l.getSelectStatusEvent());
		l.getSelectStatusEvent().click();
		JavascriptExecutor javascriptexecutor=(JavascriptExecutor)driver;
		javascriptexecutor.executeScript("window.scrollBy(0,500);");
		cu.waitTillElementToBeClickable(driver, l.getSelectLoaded());
		l.getSelectLoaded().click();
		cu.waitTillElementToBeClickable(driver, l.getSelectCalender());
		l.getSelectCalender().click();
		l.getSelectArrivedCalenderOk().click();
		test.log(LogStatus.PASS, " loading event was submitted successfully ");
		String[] lt =l.getSelectLoadedTime().getAttribute("value").split(" ");
		l.getSelectArrivedSubmit().click();
		String[] vt=l.getVerifyloadedTime().getText().split(",");
		SimpleDateFormat format1=new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date datelt = format1.parse(lt[0]);
		System.out.println(datelt);
		vt[0]=vt[0].replaceAll("th", "").replaceAll("2nd", "02").replaceAll("1st", "01").replaceAll("3rd", "03").replaceAll(" ", "-").replaceAll("Loaded", "");
		vt[0]=vt[0].replace("\n", "");
		System.out.println(vt[0]);
		SimpleDateFormat format2 = new SimpleDateFormat("MMM-dd-yyyy");
		java.util.Date datevt = format2.parse(vt[0]);
		System.out.println(datevt);
		AssertJUnit.assertEquals(datevt.hashCode()==datelt.hashCode(), true);
		String timelt = lt[1]+" "+lt[2];
		String timevt=vt[1].replaceFirst(" ", "0");
		System.out.println("Loading actual start date and time==>"+datelt+" "+timelt);
		System.out.println("Loading expected start date and time==>"+datevt+" "+timevt);
		test.log(LogStatus.PASS, "Loading actual start date and time==>"+datelt+" "+timelt);
		test.log(LogStatus.PASS, "Loading expected start date and time==>"+datevt+" "+timevt);
		AssertJUnit.assertEquals(timelt.equals(timevt), true);
		test.log(LogStatus.PASS, "Loading date and time has been verified");
		//Unloading Status
		l.getDropOffStatusIcon().click();
		cu.waitTillElementToBeClickable(driver, l.getDropOffStatusIcon());
		l.getSelectStatusEvent().click();
		cu.waitTillElementToBeClickable(driver, l.getSelectStatusEvent());
		javascriptexecutor.executeScript("window.scrollBy(0,800);");
		l.getSelectUnloadedStatus().click();
		cu.waitTillElementToBeClickable(driver, l.getSelectUnloadedStatus());
		l.getSelectCalender().click();
		l.getSelectArrivedCalenderOk().click();
		test.log(LogStatus.PASS, " Unloading event was submitted successfully ");
		String[] ut =l.getSelectLoadedTime().getAttribute("value").split(" ");
		l.getSelectArrivedSubmit().click();
		String[] v1t=l.getVerifyUnloadedTime().getText().split(",");
		SimpleDateFormat format3=new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date dateut = format3.parse(ut[0]);
		System.out.println(dateut);
		v1t[0]=v1t[0].replaceAll("th", "").replaceAll("2nd", "02").replaceAll("1st", "01").replaceAll("3rd", "03").replaceAll(" ", "-").replaceAll("Unloaded", "");
		v1t[0]=v1t[0].replace("\n", "");
		System.out.println(vt[0]);
		SimpleDateFormat format4 = new SimpleDateFormat("MMM-dd-yyyy");
		java.util.Date datev1t = format4.parse(vt[0]);
		System.out.println(datev1t);
		AssertJUnit.assertEquals(datevt.hashCode()==dateut.hashCode(), true);
		String timeut = ut[1]+" "+ut[2];
		String timev1t=vt[1].replaceFirst(" ", "0");
		System.out.println("Unloading actual start date and time==>"+dateut+" "+timeut);
		System.out.println("Unloading expected start date and time==>"+datevt+" "+timevt);
		test.log(LogStatus.PASS, "Unloading actual start date and time==>"+dateut+" "+timeut);
		test.log(LogStatus.PASS, "Unoading expected start date and time==>"+datev1t+" "+timev1t);
		AssertJUnit.assertEquals(timeut.equals(timevt), true);
		test.log(LogStatus.PASS, "Unloading date and time has been verified");
}
}
