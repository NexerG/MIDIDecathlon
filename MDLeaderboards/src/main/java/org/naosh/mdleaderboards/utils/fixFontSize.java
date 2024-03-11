package org.naosh.mdleaderboards.utils;

public class fixFontSize {
    public static String fixFontSize (String s, int size) { //Nuroviau iš bukkit forumo

        String ret = s.toUpperCase();

        if ( s != null ) {

            for (int i=0; i < s.length(); i++) {
                if ( s.charAt(i) == 'I' || s.charAt(i) == ' ') {
                    ret += " ";
                }
            }

            int faltaEspacos = size - s.length();
            faltaEspacos = (faltaEspacos * 2);

            for (int i=0; i < faltaEspacos; i++) {
                ret += " ";
            }
        }

        return (ret);
    }
}
