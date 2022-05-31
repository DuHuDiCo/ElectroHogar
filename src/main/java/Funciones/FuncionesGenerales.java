package Funciones;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class FuncionesGenerales {

    public static Date obtenerFechaServer(String formato) {        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat(formato);

        java.util.Date datObj = calendar.getTime();
        String formattedDate = sdf.format(datObj);
        java.sql.Date dateformated = fechaSQL(formattedDate, formato);

        return dateformated;
    }

    public static Date fechaSQL(String fecha, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        java.util.Date javaDate = null;
        try {
            javaDate = sdf.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
        return sqlDate;
    }
    
    public static DateTime fechaDateTime(String fecha){
        DateTimeFormatter inputFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime joda_time = inputFormat.parseDateTime(fecha);
        return joda_time;
    }
    
    
    
    

}
