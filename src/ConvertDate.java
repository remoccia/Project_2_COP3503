import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {

    public static void main(String[] args) throws ParseException {

        //Current Format dd/MM/yyyy
        String input = "28/10/2022";

        String output = formatDate(input);

        System.out.println(output);
    }

    public static String formatDate(String dateString) throws ParseException {

        //You will need to modify the args to match the format for project 2
        SimpleDateFormat currentFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy,MM,dd");

        Date dateObject = currentFormat.parse(dateString);

        dateString = newFormat.format(dateObject);

        return dateString;
    }

}
