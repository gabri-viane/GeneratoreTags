/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generatoretags;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class GeneratoreTags {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Triple<String, String, String> monDI = new Triple<>("C:\\Users\\TEA\\Desktop\\Generati\\Template\\monDI.db", "C:\\Users\\TEA\\Desktop\\Generati\\listaMonDI.txt", "C:\\Users\\TEA\\Desktop\\Generati\\monDI\\DB ");
        Triple<String, String, String> monAI = new Triple<>("C:\\Users\\TEA\\Desktop\\Generati\\Template\\monAI.db", "C:\\Users\\TEA\\Desktop\\Generati\\listaMonAI.txt", "C:\\Users\\TEA\\Desktop\\Generati\\monAI\\DB ");

        List<Triple<String, String, String>> files = new ArrayList();
        files.add(monDI);
        files.add(monAI);

        files.forEach(triplet -> {
            FileTemplate ft = new FileTemplate(triplet.getLeft());
            ft.load();
            TagsLoader tl = new TagsLoader(triplet.getCenter());
            tl.load();

            tl.getData().forEach((line) -> {
                String path = triplet.getRight() + line.get("%%TAG%%") + ".db";
                try {
                    File f = new File(path);
                    f.createNewFile();
                    try (FileWriter fw = new FileWriter(f)) {
                        fw.append(ft.replaceTags(line));
                        fw.flush();
                    }
                } catch (IOException ex) {
                    System.getLogger(GeneratoreTags.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            });
        });

    }

}
