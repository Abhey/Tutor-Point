/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorpoint;

import javax.swing.ImageIcon;

/**
 *
 * @author adityachaudhary
 */
public class ListEntry {
    
    public String title;
    public String imgPath;
    public ImageIcon image;
    public String id;
    
   public ListEntry(String title,String imgPath,String id)
    {
     this.title=title;
     this.imgPath=imgPath;
     this.id = id;
    }
   
   public String getTitle()
   {
       return title;
   }
   
   public ImageIcon getImage()
   {
       if(image==null)
           image=new ImageIcon(imgPath);
       
       return image;
   }
   
   public String toString()
   {
       return title;
   }
   
}
