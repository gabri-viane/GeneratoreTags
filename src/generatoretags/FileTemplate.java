/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

/**
 *
 * @author gabri
 */
public class FileTemplate {

    private String content = null;
    private final String filename;
    private File f;

    public FileTemplate(String filepath) {
        this.filename = filepath;
    }

    public void load() {
        this.f = new File(filename);
        try {
            content = new String(Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.getLogger(FileTemplate.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public String replaceTags(Map<String, String> placeholders) {
        String newStr = content;
        for (var current : placeholders.entrySet()) {
            newStr = newStr.replaceAll(current.getKey(), current.getValue());
        }
        return newStr;
    }

}
