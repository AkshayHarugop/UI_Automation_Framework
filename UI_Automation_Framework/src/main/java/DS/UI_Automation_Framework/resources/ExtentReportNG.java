package DS.UI_Automation_Framework.resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	private static String getCurrentDate() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("MM_dd_YYYY");
		Date now = new Date();
		return sdfDate.format(now);
	}

	private static String getCurrentTime() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("HH_mm_ss");
		Date now = new Date();
		return sdfDate.format(now);
	}
	
	protected static String TimeStamp = getCurrentDate()+"_"+getCurrentTime();
	
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "\\reports\\"+TimeStamp+"\\" + getCurrentDate() + "_" + getCurrentTime() + "_"
				+ ".html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("DS Automation Reports");
		reporter.config().setDocumentTitle("DS Extent Report");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Akshay H");
		return extent;
	}
}
