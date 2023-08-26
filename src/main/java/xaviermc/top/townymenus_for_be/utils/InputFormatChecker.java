package xaviermc.top.townymenus_for_be.utils;

public class InputFormatChecker {
    public static boolean isValidCityName(String cityname){
        return  cityname.matches("[a-zA-Z0-9]");
    }
}
