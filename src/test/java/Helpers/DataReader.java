package Helpers;

import Base.ExcelReader;
import Models.InputDataForCheckout;
import Models.LoginData;

import java.io.IOException;

public class DataReader {
    public ExcelReader excelReader;

    public DataReader() throws IOException {
        this.excelReader = new ExcelReader("C:\\Users\\Admin\\OneDrive\\Book 4.xlsx");;
    }

    public InputDataForCheckout getInputDataForCheckout(boolean usePostalCode){
        String firstName = excelReader.getStringData("List1", 6, 0);
        String lastName = excelReader.getStringData("List1", 6, 1);
        int postalCode = usePostalCode ? excelReader.getIntegerData("List1", 6, 2) : 0;

        return new InputDataForCheckout(firstName, lastName, postalCode);
    }

    public LoginData getLoginData(String userType){
        String validUsername = "";
        String validPassword = "";
        if(userType == null || userType.isEmpty()){
            userType = System.getenv("userType") == null ? "" : System.getenv("userType") ;
        }

        switch (userType){
            case "problem_user" :
                validUsername= excelReader.getStringData("List1", 7, 0);
                validPassword = excelReader.getStringData("List1", 5, 1);
                break;
            case "performance_glitch_user" :
                validUsername= excelReader.getStringData("List1", 8, 0);
                validPassword = excelReader.getStringData("List1", 5, 1);
                break;
            case "locked_out_user" :
                validUsername= excelReader.getStringData("List1", 9, 0);
                validPassword = excelReader.getStringData("List1", 5, 1);
                break;
            default:
                validUsername= excelReader.getStringData("List1", 5, 0);
                validPassword = excelReader.getStringData("List1", 5, 1);
                break;
        }
        return new LoginData(validUsername, validPassword);
    }

}
