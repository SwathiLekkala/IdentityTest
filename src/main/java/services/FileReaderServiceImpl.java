package services;

import beans.VehicleRegDetails;
import com.opencsv.CSVReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FileReaderServiceImpl {

     /**Class Variabales ***/

    WebDriver driver = null;
    public String vehiclereg,make,colour,dlvaVehicleReg,dlvaMake,dlvaColour;
    public String fileNames;
    public String fileName,fileType,fileSize;
    ArrayList<String> list = null;
    ArrayList<String> details = new ArrayList<String>();
    VehicleRegDetails detail = new VehicleRegDetails();

   static String DirPath = "";

   static {

        try{
            DirPath = "C://Users//Swathi Lekkala//Desktop//drivers";
        }catch(Exception e){

             e.printStackTrace();

        }
    }

    /**
     *
     * The Below Method is to Read the FileData from the given XLS sheet path and then Add the Data to Array list
     * to call these values in the ServiceImplTest in cucumber
     * @return
     * @throws Exception
     */
    public ArrayList<String> ReadFileData() throws Exception {

        CSVReader  reader = new CSVReader(new FileReader("C://Users//Swathi Lekkala//Desktop//drivers//VehicleDetails1.csv"));
        ArrayList<String> mylist =  new ArrayList<String>();
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            vehiclereg = nextLine[0];
            make = nextLine[1];
            colour = nextLine[2];
            mylist.add(vehiclereg);
            mylist.add(make);
            mylist.add(colour);
            detail.setVehcileReg(vehiclereg);
            details.add(vehiclereg);
        }

        return mylist;
    }
 /** Scan Configired Directory and Read Files from the File System and List the Files **/

    public ArrayList<beans.FileReader> listFilesFromBean(String dirName){

        beans.FileReader beanReader = new beans.FileReader();
        ArrayList<beans.FileReader> beanFile = new ArrayList<beans.FileReader>();
        ArrayList<String> listFilename = new ArrayList<String>();
        dirName = DirPath;
        File directory = new File(dirName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isFile()){
                listFilename.add(file.getName());
                beanReader.setFileName(listFilename);
                beanFile.add(beanReader);
            }
        }
        return beanFile;
    }

    /***
     * This Method is to launchApplicationAndFillVehicleRegDetails using DVLA Website.
     * @return
     * @throws Exception
     */
    public ArrayList<String> launchApplicationAndFillVehicleRegDetails() throws Exception{
        ArrayList<String> mylocallist =  new ArrayList<String>();

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Swathi Lekkala\\IdeaProjects\\IdentityTest\\src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.gov.uk/get-vehicle-information-from-dvla");
        driver.findElement(By.cssSelector(".gem-c-button.govuk-button.govuk-button--start")).click();
        System.out.println(details.size());

        for (int i=0;i<=details.size()-1;i++){

            Thread.sleep(7000);
            driver.findElement(By.xpath(".//input[@data-val-regex='You must enter your registration number in a valid format']")).sendKeys(details.get(i));
            driver.findElement(By.xpath("//button[@name='Continue']")).click();
            Thread.sleep(5000);
            dlvaVehicleReg = driver.findElement(By.cssSelector(".reg-mark")).getText();
            dlvaMake = driver.findElement(By.xpath("//li/span[text()='Make']//following-sibling::span")).getText();
            dlvaColour = driver.findElement(By.xpath("//li/span[text()='Colour']//following-sibling::span")).getText();
            mylocallist.add(dlvaVehicleReg);
            mylocallist.add(dlvaMake);
            mylocallist.add(dlvaColour);
            driver.findElement(By.id("Correct_False")).click();
            driver.findElement(By.xpath("//button[@name='Continue']")).click();

        }

     return mylocallist;
    }




}

