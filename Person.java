import java.util.*;

public class Person {
    int tag;
    int monat;
    int jahr;
    String name;

    void setGeburtstag(int geburtsTag, int geburtsMonat, int geburtsJahr) {
        jahr = geburtsJahr;
        monat = geburtsMonat;
        tag = geburtsTag;
    }

    void setName(String name)
    {
        this.name = name;
    }

    int getAlter() {
        // Create a calendar object with the date of birth
        Calendar dateOfBirth = new GregorianCalendar(jahr, monat - 1, tag);
        // Create a calendar object with today's date
        Calendar today = Calendar.getInstance();
        // Get age based on year
        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        // If this year's birthday has not happened yet, subtract one from age
        if (today.get(Calendar.DAY_OF_YEAR) < dateOfBirth.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    private boolean hatNochGeburtstag()
    {
        Calendar now = Calendar.getInstance();
        Calendar birthdayThisYear = new GregorianCalendar(now.get(Calendar.YEAR), monat - 1, tag);
        if(now.before(birthdayThisYear))
            return true;
        else
            return false;
    }

    int getTageBisGeburtstag()
    {
        Calendar now = Calendar.getInstance();

        if(hatNochGeburtstag())
        {
            Calendar birthdayThisYear = new GregorianCalendar(now.get(Calendar.YEAR), monat - 1, tag);
            return birthdayThisYear.get(Calendar.DAY_OF_YEAR)-now.get(Calendar.DAY_OF_YEAR);
        }
        else
        {
            Calendar birthdayNextYear = new GregorianCalendar(now.get(Calendar.YEAR) + 1, monat - 1, tag);
            Calendar lastDayOfYear = new GregorianCalendar(now.get(Calendar.YEAR), Calendar.DECEMBER, 31);
            int tageImJahr = lastDayOfYear.get(Calendar.DAY_OF_YEAR) - now.get(Calendar.DAY_OF_YEAR);
            return tageImJahr + birthdayNextYear.get(Calendar.DAY_OF_YEAR);
        }
    }

    boolean istVolljaehrig()
    {
        if(getAlter() >= 18)
            return true;
        else
            return false;
    }

    void druckeInfo() {
        System.out.println(name + " ist am " + tag + "." + monat + "." + jahr + " geboren");
        System.out.println("und damit " + getAlter() + " Jahre alt.");
        System.out.println("\nBis zum nächsten Geburtstag muss\n" + name + " noch "
            + getTageBisGeburtstag() + " Tage warten.");
    }
}
