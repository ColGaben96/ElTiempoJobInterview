package com.eltiempo.bl.utils.io;

import com.eltiempo.bl.utils.df.DateProcessor;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FileProcessor {

    private String dirExec = "./lib/execution "
            + new DateProcessor().parse2BasicDate(Timestamp.valueOf(LocalDateTime.now()))
            + "/";
    private String dirScreens = "./lib/execution "
            + new DateProcessor().parse2BasicDate(Timestamp.valueOf(LocalDateTime.now()))
            + "/screenshots/";

    public FileProcessor() {
        try {
            createDirs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeLog(String content, String dExec) throws IOException {
        File f = new File(dirExec
                + "Test executed on "
                + dExec
                + ".log");
        FileWriter fw = new FileWriter(f, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        fw.close();
    }

    public void writeScreenShot(File content, long caseID, String dTaken) throws IOException {
        FileUtils.moveFile(content, new File(dirScreens
                + "case "
                + caseID
                + " captured on "
                + dTaken
                + ".png"));
    }

    private void createDirs() throws IOException {
        if (!new File(dirExec).exists()) {
            new File(dirExec).mkdirs();
            new File(dirExec).mkdir();
        }
        if (!new File(dirScreens).exists()) {
            new File(dirScreens).mkdirs();
            new File(dirScreens).mkdir();
        }
    }
}
