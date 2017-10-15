package tutorpoint;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import tutorpoint.ListEntry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adityachaudhary
 */
public class courseRenderer extends JLabel implements ListCellRenderer {
    
    
    public courseRenderer()
    {
        setOpaque(true);
        setIconTextGap(12);
    }
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ListEntry entry=(ListEntry) value;
        setText(entry.getTitle());
        setIcon(entry.getImage());
        return this;
        
    }
    
}
