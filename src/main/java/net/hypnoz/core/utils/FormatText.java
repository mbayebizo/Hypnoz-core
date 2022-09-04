package net.hypnoz.core.utils;

public class FormatText {
    public static String formatCode(String code) {
        code = code.trim().toLowerCase();
        code = code.replace(" & ", ".");
        code = code.replace("&", ".");
        code = code.replace(" ", ".");
        code = code.replace("’", ".");
        code = code.replace("'", ".");
        code = code.replace("é", "e");
        code = code.replace("è", "e");
        code = code.replace("ê", "e");
        code = code.replace("â", "a");
        code = code.replace("û", "u");
        code = code.replace("ô", "o");
        code = code.replace("ï", "i");
        return code;
    }

    public static int getOrdre(String code) {
        return  Integer.parseInt(code.replace("[^0-9]",""));
    }
}
