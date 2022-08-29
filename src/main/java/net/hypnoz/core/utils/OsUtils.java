package net.hypnoz.core.utils;

import ch.qos.logback.core.CoreConstants;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class OsUtils {
    static final int OS_TYPE_LINUX=1;
    static final int OS_TYPE_WINDOWS=2;
    static final int OS_TYPE_MAC=3;
    private static final int OS_TYPE_SCOLARIS = 4;
    private static final int OS_TYPE_OTHERS = 5;

    public static int getOsType() {
        int osType = 0;
        String osName = System.getProperty("os.name");
        String osNameMatch = osName.toLowerCase();
        if (osNameMatch.contains("linux")) {
            osType = OS_TYPE_LINUX;
        } else if (osNameMatch.contains("windows")) {
            osType = OS_TYPE_WINDOWS;
        } else if (osNameMatch.contains("solaris")
                || osNameMatch.contains("sunos")) {
            osType = OS_TYPE_SCOLARIS;
        } else if (osNameMatch.contains("mac os")
                || osNameMatch.contains("macos")
                || osNameMatch.contains("darwin")) {
            osType = OS_TYPE_LINUX;
        } else {
            osType = OS_TYPE_OTHERS;

        }
        return osType;
    }

    public static String getOsPath(String p) {
        p = p.replaceAll("\\\\", "/");
        if (getOsType() == OS_TYPE_WINDOWS) {
            if (!p.endsWith("/")) {
                p += "/";
            }
        } else {
            if (!p.endsWith("/")) {
                p += "/";
            }
            String sch = "";

            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) != '\'') {
                    sch = sch + p.charAt(i);
                }
            }
            p = sch;
        }

        return p;
    }

    public static String getOsHomeDir() {
        String p = "";
        if (getOsType() == OS_TYPE_WINDOWS) {
            p = "C:/" + HypnozCoreConstants.DEFAULT_DOC_SERVEUR_NAME + "/";
        } else {
            p = "/home/" + HypnozCoreConstants.DEFAULT_DOC_SERVEUR_NAME + "/";
        }
        return p;
    }

    public static String getTempDir() {
        return getOsHomeDir() + "/" + HypnozCoreConstants.DIR_TEMP;
    }


}
