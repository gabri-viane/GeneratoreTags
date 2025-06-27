/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags.gui.panels;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;

/**
 *
 * @author gabri
 */
public class FrameCreator {

    public static JInternalFrame createFrame(String title, boolean resizable, JComponent c) {
        JInternalFrame jif = new JInternalFrame(title, resizable);
        jif.setLayout(new BorderLayout());
        jif.add(c, BorderLayout.CENTER);
        return jif;
    }
}
