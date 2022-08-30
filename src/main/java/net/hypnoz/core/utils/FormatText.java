package net.hypnoz.core.utils;

public class FormatText {
    public static String formatCode(String code) {
        code = code.trim().toLowerCase();
        code = code.replaceAll(" & ", ".");
        code = code.replaceAll("&", ".");
        code = code.replaceAll(" ", ".");
        code = code.replaceAll("’", ".");
        code = code.replaceAll("'", ".");
        code = code.replaceAll("é", "e");
        code = code.replaceAll("è", "e");
        code = code.replaceAll("ê", "e");
        code = code.replaceAll("â", "a");
        code = code.replaceAll("û", "u");
        code = code.replaceAll("ô", "o");
        code = code.replaceAll("ï", "i");
        return code;
    }

    public static int getOrdre(String code) {
        return  Integer.parseInt(code.replaceAll("[^0-9]",""));
    }
}
