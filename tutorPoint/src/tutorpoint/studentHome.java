package tutorpoint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.UIManager;
//import com.apple.laf.AquaLookAndFeel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adityachaudhary
 */
public class studentHome extends javax.swing.JFrame {

    /**
     * Creates new form studentHome
     */
    /* public ListEntry entry[]={
        new ListEntry("1.Maths","1.jpg"),
        new ListEntry("2.Science","2.jpg"),
        new ListEntry("3.Social","3.jpg"),
        new ListEntry("3.CS","4.jpg")
    }; */
     
    public user student;
    public String name;
    public int state; // Static removed .....
    public String courseID = ""; // Static removed .....
    
    public String courseName = ""; // Static removed .....
    public String subTopicName = ""; // Static removed .....
    
    
    public ArrayList<ListEntry> entry = new ArrayList<>() ;
     
    public studentHome(user student,String name) {
        
        initComponents();
        getContentPane().setBackground( new Color( 214 , 246 , 250 ) );
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //setBounds(( screenSize.width - 1280 ) / 2 , ( screenSize.height - 720 ) / 2 ,1280,720);
        
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); 
        
        
        this.student = student;
        this.name = name;
        
        jLabel5.setText("Hello " + name);
        
        this.fetchToWatchList();
        this.fetchNotification();
        this.fetchProgress();
        
        String query = "SELECT * FROM  courses";
        
        try {
            ArrayList<ArrayList<String>> rs = student.sendQuery(query,2);
            for(int i = 0 ; i < rs.size() ; i ++){
                String content = "";
                content += rs.get(i).get(1) + "\t ( Rating: " + rs.get(i).get(3) + " )";
                String id = rs.get(i).get(0);
                entry.add(new ListEntry(content,"folder.png",id));
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
 
        DefaultListModel<ListEntry> listModel = new DefaultListModel<>();
        for(int i = 0 ; i < entry.size() ; i ++)
            listModel.addElement(entry.get(i));
        
        back.setEnabled(false);
        sortButton.setEnabled(true);
        
        state = 0;
        
        jList1.setModel(listModel);
        jList1.setCellRenderer(new courseRenderer());
        jList1.setVisibleRowCount(entry.size());
        setVisible(true);
        
        slider.setVisible(false);
        inProgress.setVisible(false);
        rateButton.setVisible(false);
        
    }
    
    public void fetchNotification(){
        
        String query = "SELECT * FROM notifications WHERE s_email = '" + student.email + "';";
        
        try{
            ArrayList<ArrayList<String>> rs = student.sendQuery(query, 2);
            
            if(rs.size() != 0){
                
                DefaultListModel<ListEntry> listModel = new DefaultListModel<>();
                for(int i = 0 ; i < rs.size() ; i ++){
                    listModel.addElement(new ListEntry(rs.get(i).get(3),"notification.png",rs.get(i).get(0)));
                }
                
                notifyList.setModel(listModel);
                notifyList.setCellRenderer(new courseRenderer());
                notifyList.setVisibleRowCount(listModel.size());
                
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
    
    public void fetchToWatchList(){
        
        String query = "SELECT * FROM to_watch WHERE email = '" + student.email + "';" ;
        try{
            ArrayList<ArrayList<String>> rs = student.sendQuery(query,2);
            
            DefaultListModel<ListEntry> listModel = new DefaultListModel<>();
            
            if(rs.size() != 0){
                
                query = "SELECT * FROM videos WHERE id IN (";

                for(int i = 0 ; i < rs.size() - 1 ; i ++){
                    query = query + rs.get(i).get(1) + ",";
                }

                query = query + rs.get(rs.size() - 1).get(1) + ")";

                rs = student.sendQuery(query, 2);

                for(int i = 0 ; i < rs.size() ; i ++){
                    listModel.addElement(new ListEntry(rs.get(i).get(1),"video.png",rs.get(i).get(0)));
                }
            
            }
            
            toWatchList.setModel(listModel);
            toWatchList.setCellRenderer(new courseRenderer());
            toWatchList.setVisibleRowCount(listModel.size());
            
        }
        catch( IOException ex){
            System.out.println(ex);
        }
    }
    
    public void fetchProgress(){
            try{
                String query = "SELECT * FROM progress WHERE s_email = '" + student.email + "';";

                ArrayList<ArrayList<String>> rs = student.sendQuery(query,2);
                if(rs.size() != 0){

                    entry.clear();
                    for(int i = 0 ; i < rs.size() ; i ++){
                        entry.add(new ListEntry(rs.get(i).get(2),"folder.png",rs.get(i).get(0)));
                    }

                    DefaultListModel<ListEntry> listModel = new DefaultListModel<>();
                    for(int i = 0 ; i < entry.size() ; i ++)
                        listModel.addElement(entry.get(i));


                    progressList.setModel(listModel);
                    progressList.setCellRenderer(new courseRenderer());
                    progressList.setVisibleRowCount(entry.size());

                }
            } catch (IOException ex) {
                System.out.println(ex);
        }
    }
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        notifyList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        progressList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        toWatchList = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        back = new javax.swing.JButton();
        searchComboBox = new javax.swing.JComboBox<>();
        slider = new javax.swing.JSlider();
        rateButton = new javax.swing.JButton();
        sortButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        inProgress = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/loupe.png"))); // NOI18N
        jButton1.setText("SEARCH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        notifyList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        notifyList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notifyListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(notifyList);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/opened-textbook.png"))); // NOI18N
        jLabel1.setText("Tutor Point");

        progressList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(progressList);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/progress-report.png"))); // NOI18N
        jLabel3.setText("In Progress Courses");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/notification.png"))); // NOI18N
        jLabel4.setText("Notifications");

        toWatchList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        toWatchList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toWatchListMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(toWatchList);

        jLabel5.setText("Hello,mate");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/home (1).png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/back.png"))); // NOI18N
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        searchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CourseName", "Tags", "Author" }));
        searchComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchComboBoxActionPerformed(evt);
            }
        });

        slider.setMajorTickSpacing(1);
        slider.setMaximum(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setValue(0);

        rateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/star.png"))); // NOI18N
        rateButton.setText("Rate this course");
        rateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rateButtonActionPerformed(evt);
            }
        });

        sortButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/sort-by-order.png"))); // NOI18N
        sortButton.setText("Sort Course By Rating");
        sortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortButtonActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/logout.png"))); // NOI18N
        jButton3.setText("Logout");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        inProgress.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/progress-report.png"))); // NOI18N
        inProgress.setText("Mark as in porgress");
        inProgress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inProgressActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/time.png"))); // NOI18N
        jLabel6.setText("To Watch List");
        jLabel6.setToolTipText("");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/rotate-to-right.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(rateButton)
                                        .addGap(32, 32, 32)
                                        .addComponent(inProgress))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(63, 63, 63)
                                .addComponent(jButton4)
                                .addGap(113, 113, 113)
                                .addComponent(jButton2)
                                .addGap(76, 76, 76)
                                .addComponent(back)
                                .addGap(126, 126, 126)
                                .addComponent(sortButton)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel6)
                                .addGap(192, 192, 192))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jButton2)
                    .addComponent(back)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sortButton)
                        .addComponent(jLabel6)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rateButton)
                                .addComponent(inProgress))
                            .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String str = searchField.getText();
        String searchPattern = (String) searchComboBox.getSelectedItem();
        str = str.toLowerCase();
                    
        entry.clear();
        
        if(searchPattern.compareTo("CourseName") == 0){
            
            ArrayList<String> courseName = new ArrayList<>();
            
            int index = 0 ,temp = 0;
            for(int i = 0 ; i < str.length() ; i ++){
                if(str.charAt(i) == ' ' || str.charAt(i) == '\t' && temp == 0){
                    courseName.add(str.substring(index,i));
                    index = i + 1;
                    temp = 1;
                }
                else if(str.charAt(i) == ' ' || str.charAt(i) == '\t' && temp == 1){
                    index = i + 1;
                    temp = 1;
                }
                else
                    temp = 0;
            }
            if(temp == 0)
                courseName.add(str.substring(index));
            
            ArrayList<String> cID = new ArrayList<>();
            
            String query = "SELECT * FROM courses WHERE name IN (";
            
            for(int k = 0 ; k < courseName.size() - 1 ; k ++){
                    query = query +"'" +courseName.get(k) + "',";
            }
            
            query = query + "'" + courseName.get(courseName.size() - 1) + "')";
            
            ArrayList<ArrayList<String> > res = null;
            try {
                res = student.sendQuery(query, 2);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            
            for(int i = 0 ; i < res.size() ; i ++){
                cID.add(res.get(i).get(0));
            }
            
           query = "SELECT * FROM videos WHERE cid IN (";
            
            if(cID.size() != 0){
                
                for(int k = 0 ; k < cID.size() - 1 ; k ++){
                        query = query +"" + cID.get(k) + ",";
                }

                query = query + "" + cID.get(cID.size() - 1) + ")";

                try {
                    res = student.sendQuery(query, 2);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                
                System.out.println(res.get(0).get(0));
                
                for(int i = 0 ; i < res.size() ; i ++){
                        entry.add(new ListEntry(res.get(i).get(1),"video.png",res.get(i).get(0)));
                }
            }
            
        }
        else if(searchPattern.compareTo("Tags") == 0){
            
            ArrayList<String> tagName = new ArrayList<>();
            int index = 0 ,temp = 0;
            for(int i = 0 ; i < str.length() ; i ++){
                if(str.charAt(i) == ' ' || str.charAt(i) == '\t' && temp == 0){
                    tagName.add(str.substring(index,i));
                    index = i + 1;
                    temp = 1;
                }
                else if(str.charAt(i) == ' ' || str.charAt(i) == '\t' && temp == 1){
                    index = i + 1;
                    temp = 1;
                }
                else
                    temp = 0;
            }
            if(temp == 0)
                tagName.add(str.substring(index));
            
            String query = "SELECT * FROM videos WHERE ";
            
            for(int k = 0 ; k < tagName.size() - 1 ; k ++){
                    query = query +"tags LIKE '%" +tagName.get(k) + "%' OR ";
            }
            
            query = query + "tags LIKE '%" + tagName.get(tagName.size() - 1) + "%'";
            
            ArrayList<ArrayList<String> > res = null;
            try {
                res = student.sendQuery(query, 2);
            } catch (IOException ex) {
                System.out.println(ex);
            }
                
            for(int i = 0 ; i < res.size() ; i ++){
                    entry.add(new ListEntry(res.get(i).get(1),"video.png",res.get(i).get(0)));
            }
            
        }
        else if(searchPattern.compareTo("Author") == 0){
            
            ArrayList<String> authorName = new ArrayList<>();
            
            int index = 0 ,temp = 0;
            for(int i = 0 ; i < str.length() ; i ++){
                if(str.charAt(i) == ' ' || str.charAt(i) == '\t' && temp == 0){
                    authorName.add(str.substring(index,i));
                    index = i + 1;
                    temp = 1;
                }
                else if(str.charAt(i) == ' ' || str.charAt(i) == '\t' && temp == 1){
                    index = i + 1;
                    temp = 1;
                }
                else
                    temp = 0;
            }
            if(temp == 0)
                authorName.add(str.substring(index));
            
            ArrayList<String> cID = new ArrayList<>();
            
            String query = "SELECT * FROM courses WHERE ";
            
            for(int k = 0 ; k < authorName.size() - 1 ; k ++){
                    query = query +"email LIKE '%" +authorName.get(k) + "%' OR ";
            }
            
            query = query + "email LIKE '%" + authorName.get(authorName.size() - 1) + "%'";
            
            ArrayList<ArrayList<String> > res = null;
            try {
                res = student.sendQuery(query, 2);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            
            for(int i = 0 ; i < res.size() ; i ++){
                cID.add(res.get(i).get(0));
            }
            
            if(cID.size() != 0){
            
                query = "SELECT * FROM videos WHERE cid IN (";

                for(int k = 0 ; k < cID.size() - 1 ; k ++){
                        query = query +"'" + cID.get(k) + "',";
                }

                query = query + "'" + cID.get(cID.size() - 1) + "')";

                try {
                    res = student.sendQuery(query, 2);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            
                for(int i = 0 ; i < res.size() ; i ++){
                    entry.add(new ListEntry(res.get(i).get(1),"video.png",res.get(i).get(0)));
                }
                
            }
            
        }
        
        back.setEnabled(true);
        sortButton.setEnabled(false);
            
        DefaultListModel<ListEntry> listModel = new DefaultListModel<>();
        for(int i = 0 ; i < entry.size() ; i ++)
            listModel.addElement(entry.get(i));
            
        jList1.setModel(listModel);
        jList1.setCellRenderer(new courseRenderer());
        jList1.setVisibleRowCount(entry.size());
            
        state = 4;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    String query = "SELECT * FROM  courses";
        
        try {
            ArrayList<ArrayList<String>> rs = student.sendQuery(query,2);
            entry.clear();
            for(int i = 0 ; i < rs.size() ; i ++){
                String content = "";
                content += rs.get(i).get(1) + "\t ( Rating: " + rs.get(i).get(3) + " )";
                String id = rs.get(i).get(0);
                    entry.add(new ListEntry(content,"folder.png",id));
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }

        DefaultListModel<ListEntry> listModel = new DefaultListModel<>();
        for(int i = 0 ; i < entry.size() ; i ++)
            listModel.addElement(entry.get(i));

        back.setEnabled(false);
        sortButton.setEnabled(true);
        courseName = "";

        jList1.setModel(listModel);
        jList1.setCellRenderer(new courseRenderer());
        jList1.setVisibleRowCount(entry.size());

        state = 0;
        
        slider.setVisible(false);
        inProgress.setVisible(false);
        rateButton.setVisible(false);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        
    }//GEN-LAST:event_jList1ValueChanged

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        if(state == 2){
            
            try{
                String query = "SELECT * FROM courses WHERE id = \'" + courseID + "\';";
                ArrayList<ArrayList<String> > res = student.sendQuery(query, 2);
            
                entry.clear();
                String subTopics = res.get(0).get(4);
                subTopicName = "";
                
                int ind = 0;
                for(int i = 0 ; i < subTopics.length() ; i ++){
                    if(subTopics.charAt(i) == '#'){
                        entry.add(new ListEntry(subTopics.substring(ind,i),"folder.png",""));
                        ind = i + 1;
                    }
                }
                
                DefaultListModel<ListEntry> listModel = new DefaultListModel<>();
                for(int i = 0 ; i < entry.size() ; i ++)
                    listModel.addElement(entry.get(i));
                
                state = 1;
                
                back.setEnabled(true);
                sortButton.setEnabled(false);
                slider.setVisible(true);
                inProgress.setVisible(true);
                
                query = "SELECT *from progress WHERE CID = " + this.courseID + " AND s_email = '" + student.email + "';";
                res = student.sendQuery(query,2);
                
                if(res.size() != 0){
                    inProgress.setEnabled(false);
                }
                else
                    inProgress.setEnabled(true);
                
                query = "SELECT * FROM ratings WHERE cid = " + this.courseID + " AND s_email = '" + student.email + "';";
                res = student.sendQuery(query,2);

                if(res.size() != 0 ){
                    int val = Integer.parseInt(res.get(0).get(2));
                    slider.setValue(val);
                }
                else
                    slider.setValue(0);
                
                rateButton.setVisible(true);
                
                jList1.setModel(listModel);
                jList1.setCellRenderer(new courseRenderer());
                jList1.setVisibleRowCount(entry.size());
                
            
            }catch (IOException ex) {
                System.out.println(ex);
            }
            
        }
        else if(state == 1 || state == 4){
            
            String query = "SELECT * FROM  courses";
        
            try {
                ArrayList<ArrayList<String>> rs = student.sendQuery(query,2);
                entry.clear();
                for(int i = 0 ; i < rs.size() ; i ++){
                    String content = "";
                    content += rs.get(i).get(1) + "\t ( Rating: " + rs.get(i).get(3) + " )";
                    String id = rs.get(i).get(0);
                    entry.add(new ListEntry(content,"folder.png",id));
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
 
            DefaultListModel<ListEntry> listModel = new DefaultListModel<>();
            for(int i = 0 ; i < entry.size() ; i ++)
                listModel.addElement(entry.get(i));
        
            back.setEnabled(false);
            sortButton.setEnabled(true);
            slider.setVisible(false);
            inProgress.setVisible(false);
            rateButton.setVisible(false);
            
            courseName = "";
            
            jList1.setModel(listModel);
            jList1.setCellRenderer(new courseRenderer());
            jList1.setVisibleRowCount(entry.size());
            
            state = 0;
            
        }
        
    }//GEN-LAST:event_backActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
                    ListEntry en = (ListEntry) jList1.getSelectedValue();
        
        if(state == 0 && en != null){
            // SubTopic State ................
            try {
                String query = "SELECT * FROM courses WHERE id = \'" + en.id + "\';";
                ArrayList<ArrayList<String> > res = student.sendQuery(query, 2);
            
                entry.clear();
                String subTopics = res.get(0).get(4);
                courseName = res.get(0).get(1);
                
                int ind = 0;
                for(int i = 0 ; i < subTopics.length() ; i ++){
                    if(subTopics.charAt(i) == '#'){
                        entry.add(new ListEntry(subTopics.substring(ind,i),"folder.png",""));
                        ind = i + 1;
                    }
                }
                
                DefaultListModel<ListEntry> listModel = new DefaultListModel<>();
                for(int i = 0 ; i < entry.size() ; i ++)
                    listModel.addElement(entry.get(i));
                
                state = 1;
                
                back.setEnabled(true);
                sortButton.setEnabled(false);
                slider.setVisible(true);
                inProgress.setVisible(true);
                
                courseID = en.id;
                
                query = "SELECT *from progress WHERE CID = " + this.courseID + " AND s_email = '" + student.email + "';";
                res = student.sendQuery(query,2);
                
                if(res.size() != 0){
                    inProgress.setEnabled(false);
                }
                else
                    inProgress.setEnabled(true);
                
                
                query = "SELECT * FROM ratings WHERE cid = " + this.courseID + " AND s_email = '" + student.email + "';";
                res = student.sendQuery(query,2);

                if(res.size() != 0 ){
                    int val = Integer.parseInt(res.get(0).get(2));
                    slider.setValue(val);
                }
                else
                    slider.setValue(0);
                
                rateButton.setVisible(true);
                
                jList1.setModel(listModel);
                jList1.setCellRenderer(new courseRenderer());
                jList1.setVisibleRowCount(entry.size());
                
            
            }catch (IOException ex) {
                System.out.println(ex);
            }
            
        }
        else if(state == 1 && en != null){
            try {
                // Video State .........
                String query = "SELECT * FROM videos WHERE cid = \'" + courseID + "\' and subtopic = \'" + en.title + "\';";
                
                ArrayList<ArrayList<String> > res = student.sendQuery(query, 2);
                subTopicName = en.title;
                
                entry.clear();
                for(int i = 0 ; i < res.size() ; i ++){
                    entry.add(new ListEntry(res.get(i).get(1),"video.png",res.get(i).get(0)));
                }
                
                DefaultListModel<ListEntry> listModel = new DefaultListModel<>();
                for(int i = 0 ; i < entry.size() ; i ++)
                    listModel.addElement(entry.get(i));
                
                state = 2;
                
                back.setEnabled(true);
                sortButton.setEnabled(false);
                slider.setVisible(false);
                rateButton.setVisible(false);
                inProgress.setVisible(false);
                
                
                jList1.setModel(listModel);
                jList1.setCellRenderer(new courseRenderer());
                jList1.setVisibleRowCount(entry.size());
                
                
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        else if(state == 2 || state == 4 && en != null){
            // Video Viewing State ...........
            String videoID = en.id;
            try {
                ArrayList<ArrayList<String>> res = student.sendQuery("$$$$",3);
                String path = res.get(0).get(0).concat(videoID);
                new player(student,path,videoID,null,this).setVisible(true);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchComboBoxActionPerformed

    private void toWatchListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toWatchListMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            try{
                ListEntry en = (ListEntry) toWatchList.getSelectedValue();
                String videoID = en.id;
                ArrayList<ArrayList<String>> res = student.sendQuery("$$$$",3);
                String path = res.get(0).get(0).concat(videoID);
                new player(student,path,videoID,null,this).setVisible(true);
            }
            catch(IOException ex){
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_toWatchListMouseClicked

    private void notifyListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notifyListMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            
            try {
                ListEntry en = (ListEntry) notifyList.getSelectedValue();
                String query = "DELETE FROM notifications where id = " + en.id + ";";
                
                ArrayList<ArrayList<String>> rs = student.sendQuery(query,1);
                
                if(rs.size() == 1 && rs.get(0).get(0).compareTo("DONE") == 0){
                    
                    this.fetchNotification();
                    
                }
                else
                    System.out.println("Error in fetching notifiactions.");
                
            } catch (IOException ex) {
                System.out.println(ex);
            }
            
        }
    }//GEN-LAST:event_notifyListMouseClicked

    private void rateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rateButtonActionPerformed
        // TODO add your handling code here:
        
        int val = slider.getValue();
        
        String query = "SELECT * FROM courses WHERE id = " + this.courseID + ";";
        
        try{
            
            ArrayList<ArrayList<String>> rs = student.sendQuery(query,2);
            
            double rating = Double.parseDouble(rs.get(0).get(3));
            int rater = Integer.parseInt(rs.get(0).get(5));
            
            query = "SELECT * FROM ratings WHERE cid = " + this.courseID + " AND s_email = '" + student.email + "';";
            rs = student.sendQuery(query,2);
            
            if(rs.size() != 0){
                
                int prevRate = Integer.parseInt(rs.get(0).get(2));
                rating = ((rating*rater) + (val - prevRate)) /(rater);
                
                query = "UPDATE courses SET rating = " + rating + ", raters = " + rater + " WHERE id = " + this.courseID + ";";
                student.sendQuery(query,1);
                query = "UPDATE ratings set rate_value = " + val + " WHERE cid = " + this.courseID + " AND s_email = '" + student.email + "';";
                student.sendQuery(query, 1);
                 
            }
                
            else{
                
                rating = ((rating*rater) + val) / (rater + 1);
                rater ++;
                
                query = "UPDATE courses SET rating = " + rating + ", raters = " + rater + " WHERE id = " + this.courseID + ";";
                student.sendQuery(query,1);
                query = "INSERT INTO ratings VALUES(" + this.courseID + ",'" + student.email + "'," + val + ");";
                student.sendQuery(query, 1);
                
            }
                
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }//GEN-LAST:event_rateButtonActionPerformed

    private void sortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortButtonActionPerformed
        // TODO add your handling code here:
        
        if(state == 0){
            
            String query = "SELECT * FROM  courses ORDER BY rating DESC";
            
            entry.clear();

            try {
                ArrayList<ArrayList<String>> rs = student.sendQuery(query,2);
                for(int i = 0 ; i < rs.size() ; i ++){
                    String content = "";
                    content += rs.get(i).get(1) + "\t ( Rating: " + rs.get(i).get(3) + " )";
                    String id = rs.get(i).get(0);
                    entry.add(new ListEntry(content,"folder.png",id));
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }

            DefaultListModel<ListEntry> listModel = new DefaultListModel<>();
            for(int i = 0 ; i < entry.size() ; i ++)
                listModel.addElement(entry.get(i));
            
            jList1.setModel(listModel);
            jList1.setCellRenderer(new courseRenderer());
            jList1.setVisibleRowCount(entry.size());
            
        }
        
    }//GEN-LAST:event_sortButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            student.s.close();
            this.dispose();
            (new Login()).setVisible(true);
        } catch (IOException ex) {
           System.out.println(ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void inProgressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inProgressActionPerformed
        try {
            // TODO add your handling code here:
            
            String query = "INSERT INTO progress values (" + this.courseID + ",'" + student.email + "','" + this.courseName + "');";
            
            student.sendQuery(query,1);
            
            this.fetchProgress();
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
       
        inProgress.setEnabled(false);
        
    }//GEN-LAST:event_inProgressActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.fetchNotification();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
             javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(studentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(studentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(studentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(studentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new studentHome(null,"Dummy User").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton inProgress;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList notifyList;
    private javax.swing.JList progressList;
    private javax.swing.JButton rateButton;
    private javax.swing.JComboBox<String> searchComboBox;
    private javax.swing.JTextField searchField;
    private javax.swing.JSlider slider;
    private javax.swing.JButton sortButton;
    private javax.swing.JList toWatchList;
    // End of variables declaration//GEN-END:variables
}
