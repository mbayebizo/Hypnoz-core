package net.hypnoz.core.utils;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.*;

public class FilesUtils {
    static final int BUFFER = 2048;

    public static File convertMultipartFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public static boolean copier(File source, File dest) {
        try {
            // Declaration et ouverture des flux

            try (FileInputStream sourceFile = new FileInputStream(
                    source)) {

                try (FileOutputStream destinationFile = new FileOutputStream(dest)) {

                    // Lecture par segment de 0.5Mo
                    byte buffer[] = new byte[512 * 1024];
                    int nbLecture;

                    while ((nbLecture = sourceFile.read(buffer)) != -1) {
                        destinationFile.write(buffer, 0, nbLecture);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Erreur
        }

        return true; // Résultat OK
    }

    public static boolean existed(String pathDestination) {
        File f = new File(pathDestination);
        return f.exists();
    }

    public static void deleteFile(String sfile) {
        boolean resultat = true;
        File file = new File(sfile);
        if (file.exists()) {
            resultat &= file.delete();
        }

    }

    public static String getFileExtension(File tmpFichier) {
        int posPoint = tmpFichier.getName().lastIndexOf('.');
        if (0 < posPoint && posPoint <= tmpFichier.getName().length() - 2) {
            return tmpFichier.getName().substring(posPoint + 1);
        }
        return "";
    }

    /**
     * Creer un repertoire
     */
    public static void mkdir(String pathDestination) {
        File f = new File(pathDestination);
        if (!f.exists()) {
            f.mkdirs();
        }

    }

    public static boolean deleteAllFiles(String pathDestination) {
        boolean resultat = true;
        String path = pathDestination;
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile() && f.exists()) {
                f.delete();
            }
        }
        return (resultat);
    }

    public static long getTaille(String pathDestination) {
        File f = new File(pathDestination);
        return f.length();
    }

    public static long getTaille(File f) {
        return f.length();
    }

    public static long getTaille(File f, String conv) {
        long t = f.length();

        if (conv.equals("KB")) {
            t = t / 1000;
        }
        if (conv.equals("MB")) {
            t = t / 1000000;
        }
        return t;
    }


    public static String getStringTaille(File f) {
        long t = f.length();
        double r = 0;

        String s = "";
        if (t <= 1000) {
            s = t + " Bytes";
        } else if (t <= 1000000) {
            r = (t / 1000);
            s = r + " KB";
        } else if (t <= 1000000000) {
            r = (t / 1000000);
            s = r + " MB";
        }
        return s;
    }

    public static String getStringTaille(long t) {
        double r = 0;

        String s = "";
        if (t <= 1000) {
            s = t + " Bytes";
        } else if (t <= 1000000) {
            r = (t / 1000);
            s = r + " KB";
        } else if (t <= 1000000000) {
            r = (t / 1000000);
            s = r + " MB";
        }
        return s;
    }

    public static String getFileExtension(String tmpFichier) {
        // tmpFichier.getName();
        int posPoint = tmpFichier.lastIndexOf('.');
        if (0 < posPoint && posPoint <= tmpFichier.length() - 2) {
            return tmpFichier.substring(posPoint + 1);
        }
        return "";
    }

    public static String getFileName(String tmpFichier) {
        // tmpFichier.getName();
        int posPoint = tmpFichier.lastIndexOf('.');
        if (0 < posPoint && posPoint <= tmpFichier.length() - 2) {
            return tmpFichier.substring(0, posPoint);
        }
        return "";
    }

    /**
     * Return le type MIME d'un fichier
     */
    public static String getMIMEType(String pathDestination) {
        File f = new File(pathDestination);
        try {
            URL url = f.toURL();
            URLConnection connection = url.openConnection();
            return connection.getContentType();
        } catch (MalformedURLException mue) {
            return mue.getMessage();
        } catch (IOException ioe) {
            return ioe.getMessage();
        }
    }

    public static String getMIMEType(File f) {
        try {
            URL url = f.toURL();
            URLConnection connection = url.openConnection();
            return connection.getContentType();
        } catch (MalformedURLException mue) {
            return mue.getMessage();
        } catch (IOException ioe) {
            return ioe.getMessage();
        }
    }

    /**
     * Return le type d'un fichier
     */
    public static String getType(String pathDestination) {
        String[] s = getMIMEType(pathDestination).split("/");
        return s[1];
    }

    public static String getType(File f) {
        String[] s = getMIMEType(f).split("/");
        return s[1];
    }

    public static String saveJsonFile(String json, String filename, String path, boolean zip) {
        try {
            if (FilesUtils.existed(path)) {
                FileWriter writer = new FileWriter(path + filename + ".json");
                writer.write(json);
                writer.close();
                if (zip) {
                    compresser(path + filename + ".json", path, filename);
                }
            } else {
                return "LE CHEMIN SUIVANT N'EXISTE PAS : " + path;
            }
            return "-1";
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public static void compresser(String fileathSource,
                                  String pathDestination, String filename) {

        try {
            File f = new File(fileathSource);
            FileOutputStream dest = new FileOutputStream(pathDestination
                    + filename + ".zip");

            CheckedOutputStream checksum = new CheckedOutputStream(dest,
                    new Adler32());
            BufferedOutputStream buff = new BufferedOutputStream(checksum);
            ZipOutputStream out = new ZipOutputStream(buff);
            out.setMethod(ZipOutputStream.DEFLATED);
            out.setLevel(Deflater.BEST_COMPRESSION);
            byte data[] = new byte[BUFFER];
            FileInputStream fi = new FileInputStream(f);
            BufferedInputStream buffi = new BufferedInputStream(fi, BUFFER);
            ZipEntry entry = new ZipEntry(filename + "."
                    + getFileExtension(f).toLowerCase());
            out.putNextEntry(entry);
            int count;
            while ((count = buffi.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            out.closeEntry();
            buffi.close();
            out.close();
            buff.close();
            checksum.close();
            dest.close();
            deleteFile(fileathSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decompresser(final String fileName,
                                    final String folderName, final boolean deleteZipAfter)
            throws IOException {
        final File file = new File(fileName);
        final File folder = new File(folderName);
        try (ZipInputStream zis = new ZipInputStream(new BufferedInputStream(
                new FileInputStream(file.getCanonicalFile())))) {
            ZipEntry ze;
            // Parcourt tous les fichiers
            while (null != (ze = zis.getNextEntry())) {
                final File f = new File(folder.getCanonicalPath(), ze.getName());
                if (f.exists())
                    f.delete();

                // Création des dossiers
                if (ze.isDirectory()) {
                    f.mkdirs();
                    continue;
                }
                f.getParentFile().mkdirs();
                final OutputStream fos = new BufferedOutputStream(
                        new FileOutputStream(f));

                // Ecriture des fichiers
                try {
                    try {
                        final byte[] buf = new byte[8192];
                        int bytesRead;
                        while (-1 != (bytesRead = zis.read(buf)))
                            fos.write(buf, 0, bytesRead);
                    } finally {
                        fos.close();
                    }
                } catch (final IOException ioe) {
                    f.delete();
                    throw ioe;
                }
            }
        }
        if (deleteZipAfter)
            file.delete();

    }

    public static List<String> getFileNameFromDirectory(String path) {
        List<String> l = new ArrayList<String>();
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(x -> x.getFileName().toString()).toList();

            l.addAll(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return l;
    }

    public static void setReporExcelResponse(String filePath, HttpServletResponse response) {
        File dfile = new File(filePath);
        if (dfile != null) {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(dfile);
                response.setHeader("Content-Disposition", "attachment; filename=\"etat.xls\"");
                response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
                ServletOutputStream outputStream = response.getOutputStream();
                IOUtils.copy(inputStream, outputStream);

                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
