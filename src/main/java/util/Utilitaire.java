package util;
import java.sql.Timestamp;
import java.util.Date;
public class Utilitaire {
    public static String castDateTimeHTML(String dater){
        return (dater.replace("T"," ")).replace("-","/");
    }
    public static double dateDifferencesInHours(String ancien,String nouveau){
        // String stockAncien = ancien.replace("T"," ")+":00.00";
        // String stockNouveau = nouveau.replace("T"," ")+":00.00";
        double dateAncien = Timestamp.valueOf(ancien).getTime();
        double dateNouveau = Timestamp.valueOf(nouveau).getTime();
        if (dateAncien>dateNouveau) {
            return ((dateAncien-dateNouveau)/3600000);
        }
        return ((dateNouveau-dateAncien)/3600000);
    }
    public static double dateDifferencesInDays(Date ancien,Date nouveau){
        double dateAncien = ancien.getTime();
        double dateNouveau = nouveau.getTime();
        if (dateAncien>dateNouveau) {
            return ((dateAncien-dateNouveau)/86400000);
        }
        return ((dateNouveau-dateAncien)/86400000);
    }
    public static double dateDifferencesInHours(Date ancien,Date nouveau){
        double dateAncien = ancien.getTime();
        double dateNouveau = nouveau.getTime();
        if (dateAncien>dateNouveau) {
            return ((dateAncien-dateNouveau)/3600000);
        }
        return ((dateNouveau-dateAncien)/3600000);
    }
    public static double min (double one, double two){
        if (one > two) {
            return two;
        }
        return one;
    }
    public static int nombreDateMiasa(String htmlMois){
        Date mois = null;
        Date stock = new Date(castDateTimeHTML(htmlMois)+"/01");
        int retour = 0;
        int test = 0;
        // Date test = new Date("2022/12/26");
        for (int i = 1; i < 32; i++) {
            mois = new Date(castDateTimeHTML(htmlMois)+"/"+i);
            if (stock.getMonth()+1 == mois.getMonth()+1) {
                if ((mois.getDay() != 2) && (mois.getDay() != 4)) {
                    retour++;
                }
                test++;
            }
        }   
        return retour;        
    }
    public static Date dateNext (Date now,int jour){
        long stockOnDay = 86400000;
        long jourInMilliSecond = stockOnDay * jour;
        return new java.util.Date(jourInMilliSecond + now.getTime());
    }
    public static double dateDifferencesInDays2(Date ancien,Date nouveau){
        double dateAncien = ancien.getTime();
        double dateNouveau = nouveau.getTime();
        int indice = 0;
        if (dateAncien < dateNouveau) {
            while (!ancien.equals(nouveau)) {
                ancien = dateNext(ancien, 1);
                if ((ancien.getDay()!=2)&&(ancien.getDay()!=4)) {
                    indice++;
                }
            }
        }
        else{
            while (!nouveau.equals(ancien)) {
                nouveau = dateNext(nouveau, 1);
                if ((nouveau.getDay()!=2)&&(nouveau.getDay()!=4)) {
                    indice++;
                }
            }
        }
        return indice;
    }
    
}
