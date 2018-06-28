package test.dhw;

import java.text.*;
import java.util.Date;

public class TestDate {
    public static void main(String args[]) {
        try{
    Format form = new SimpleDateFormat("yyyy-MM-dd");
    Date date = (Date) ((DateFormat) form).parse("1980-09-25");
    
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    String formattedDate = formatter.format(date);
    System.out.println(formattedDate);
        }catch(Exception e){
            
        }
    }
}