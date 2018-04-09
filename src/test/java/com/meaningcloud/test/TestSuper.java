package com.meaningcloud.test;

import org.junit.AfterClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TestSuper {


    protected static String MEANINGCLOUD_KEY;
    protected static List<File> filesToRemove = new LinkedList<>();


    static {
        MEANINGCLOUD_KEY = (String)System.getProperties().get("MEANINGCLOUD_KEY");
        //MEANINGCLOUD_KEY = "...";

        if (MEANINGCLOUD_KEY == null || MEANINGCLOUD_KEY.length() == 0) {
            throw new RuntimeException("Please define MEANINGCLOUD_KEY in your test (-DMEANINGCLOUD_KEY=...)");
        }
    }


    @AfterClass
    public static void cleanFiles() {
        for (File f : filesToRemove){
            f.delete();
        }
    }

    File createTemporaryFileWithContents(String contents) throws IOException {
        File f = File.createTempFile("mc-test", ".txt");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(contents.getBytes());
        fos.close();
        filesToRemove.add(f);
        return f;
    }
}
