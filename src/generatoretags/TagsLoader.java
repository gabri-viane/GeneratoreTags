/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gabri
 */
public class TagsLoader {
    
    private final List<String> content;
    private final List<String> placeholders;
    private final List<Map<String,String>> table;
    private final String filename;
    private File f;

    public TagsLoader(String filepath) {
        this.filename = filepath;
        this.content = new ArrayList<>();
        this.table = new ArrayList<>();
        this.placeholders = new ArrayList<>();
    }

    public void load() {
        this.f = new File(filename);
        try {
            content.addAll(Files.readAllLines(f.toPath(), StandardCharsets.UTF_8));
        } catch (IOException ex) {
            System.getLogger(TagsLoader.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        //La prima linea sono la lista di tag (nomi delle colonne)
        //le colonne sono divise da tabs
        Iterator<String> iterator = content.iterator();
        String[] tmpPlchlder = iterator.next().split("\t");
        this.placeholders.addAll(Arrays.asList(tmpPlchlder));
        while(iterator.hasNext()){
            String line = iterator.next();
            String[] values = line.split("\t");
            HashMap<String,String> lineVals = new HashMap<>();
            for(int i=0;i<values.length;i++){
                lineVals.put(this.placeholders.get(i), values[i]);
            }
            this.table.add(lineVals);
        }
    }
    
    public List<Map<String,String>> getData(){
        return this.table;
    }


    
}
