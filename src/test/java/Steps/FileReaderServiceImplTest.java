package Steps;

import beans.VehicleRegDetails;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import services.FileReaderServiceImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class FileReaderServiceImplTest {
    WebDriver driver = null;
    String dirName="";
    String FileType = "";
    FileReaderServiceImpl fileReaderService = new FileReaderServiceImpl();

    /** This Method will pull the Vehicle Reg details from Local XLSX OR CSV and then Inject into DVLA
     * Website and then validate the Vehicle Details in the DVLA Website....
     */

    @Then("^Launch DLVA application  And Fill VRegDetails Then Validate$")
    public void launchDLVAApplicationAndFillVRegDetailsThenValidate() throws Throwable {
        ArrayList<String> dvlaList = fileReaderService.launchApplicationAndFillVehicleRegDetails();
        ArrayList<String> vdetailsList = fileReaderService.ReadFileData();
        for ( int i=0;i<vdetailsList.size()-1;i++){
            try {
                Assert.assertTrue(dvlaList.get(i).equalsIgnoreCase(vdetailsList.get(i)));
            }catch(Exception e){
                e.printStackTrace();
            }

        }


    }

    /**This Method is to Read The File Name , FileType,FileExtensions from the Directory in Excel sheet**/

    @Given("^Read Excel OR CSV Data From the Directory And Retreive File Contents$")
    public void readExcelORCSVDataFromTheDirectoryAndRetreiveFileContents() throws Throwable {

        ArrayList<String> vdetailsList = fileReaderService.ReadFileData();
        beans.FileReader fileRead = new beans.FileReader();

        ArrayList<beans.FileReader> fombean =  fileReaderService.listFilesFromBean(dirName);
        ArrayList<String> fileNameFromBean = new ArrayList<String>();
        for(beans.FileReader list:fombean){
            fileNameFromBean = list.getFileName();
        }
        for(String listofNames:fileNameFromBean) {
            if (listofNames.contains("csv")) {
                String fileExtension = "csv";
                System.out.println("Total CSV files are " + listofNames.length());
                System.out.println("Total CSV files are " + listofNames + "And the File Extension is" + fileExtension);
            }

            if (listofNames.contains("xlsx")) {
                String fileExtension = "xlsx";
                System.out.println("Total XLSX files are " + listofNames.length());
                System.out.println("Total XLSX files are " + listofNames + "And the File Extension is" + fileExtension);
            }

        }
    }
}
