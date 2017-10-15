package tutorpoint;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.DefaultListModel;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adityachaudhary
 */
public class player extends javax.swing.JFrame {

    /**
     * Creates new form player
     */
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    
    public user u;
    int videoId ;
    String tags ;
    MouseListener ml ;
    teacherHome1 t ;
    String t_email = "" ;
    public player(user u,String path,String videoId,teacherHome1 t , studentHome s ) {
       initComponents();
       setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
       this.addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
               pause.doClick();
               if(s != null)
                s.fetchToWatchList();
         }
    });
       this.u = u;
       this.t = t ;
       this.videoId = Integer.parseInt( videoId ) ;
       player.setLayout(new BorderLayout()); 
       mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
       player.add(mediaPlayerComponent,BorderLayout.CENTER);
       this.setContentPane(player);
       //player.add(tool,BorderLayout.SOUTH);
       player.add(tool1,BorderLayout.SOUTH);
       player.add(tool3,BorderLayout.EAST);
       System.out.println( videoId ) ;
       if( u.type != 1 )
       {
           jButton1.setEnabled( false ) ;
           jButton1.setVisible( false ) ;
           jButton5.setEnabled( false ) ;
           jButton5.setVisible( false ) ;
           jButton6.setEnabled( false ) ;
           jButton6.setVisible( false ) ;
       }
       else
       {
           getTeacherEmail( ) ;
           jButton4.setEnabled( false ) ;
           jButton4.setVisible( false ) ;
           jTextField2.setEnabled( false ) ;
           jTextField2.setVisible( false ) ;
           jButton2.setEnabled(false) ;
           jButton2.setVisible( false ) ;
       }
       if( getLike( ) )
           jButton1.setText("Unlike") ;
       else
           jButton1.setText("Like") ;
       getTags( ) ;
       setTags( ) ;
       if( getToWatch( ) )
           jButton5.setText("Remove from to-watch list") ;
       if( getSubscription( ) )
           jButton6.setText( "Unsubscribe") ;
       mediaPlayerComponent.getMediaPlayer().playMedia(path);
       setLikesComments( ) ;
       comments( ) ;
       //Thread t1 = new playerThread( this ) ;
       //t1.start( ) ;
    }
    
    public void getTeacherEmail( )
    {
        //Get teacher email here.
        try {
            String query = "SELECT * FROM videos WHERE id=" + videoId ;
            ArrayList<ArrayList<String>> videos = u.sendQuery( query , 2 );
            int cid = Integer.parseInt( videos.get(0).get(2) ) ;
            query = "SELECT * FROM courses WHERE id=" + cid ;
            ArrayList<ArrayList<String>> courses = u.sendQuery( query , 2 );
            t_email = courses.get(0).get(2) ;
        } catch (IOException ex) {
            Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public boolean getSubscription( )
    {
        String query = "SELECT * FROM subscriptions WHERE t_email='" + t_email + "' AND s_email='" + u.email + "'";
        try {
            ArrayList<ArrayList<String>> subscriptions = u.sendQuery( query , 2 );
            return subscriptions.size( ) > 0 ;
        } catch (IOException ex) {
            Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;
    }
    
    public boolean getToWatch( )
    {
        String query = "SELECT * FROM to_watch WHERE email = '" + u.email + "' AND video_id=" + videoId ;
        try {
            ArrayList<ArrayList<String>> to_watch = u.sendQuery( query , 2 );
            return to_watch.size( ) > 0 ;
        } catch (IOException ex) {
            Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;
    }
    
    public void setTags( )
    {
        jLabel3.setText( tags ) ;
    }
    
    public void deleted( )
    {
        try {
            String query = "SELECT * FROM videos WHERE id=" + videoId ;
            ArrayList<ArrayList<String>> videos = u.sendQuery( query , 2 );
            if( videos.size( ) == 0 )
                this.dispose( ) ;
        } catch (IOException ex) {
            Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void setLikesComments( )
    {
        try {
            String query = "SELECT * FROM videos WHERE id=" + videoId ;
            ArrayList<ArrayList<String>> videos = u.sendQuery( query , 2 );
            jLabel4.setText( videos.get(0).get(4) + " likes") ;
            jLabel5.setText( videos.get(0).get(5) + " comments") ;       
        } catch (IOException ex) {
            Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public boolean getLike( )
    {
        String query = "SELECT * FROM likes WHERE email = \"" + u.email + "\" AND video_id=" + videoId ;
        try {
            ArrayList<ArrayList<String>> likes = u.sendQuery( query , 2 );
            return likes.size( ) > 0 ;
        } catch (IOException ex) {
            Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        player = new javax.swing.JPanel();
        tool3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tool1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        play = new javax.swing.JButton();
        pause = new javax.swing.JButton();
        forward = new javax.swing.JButton();
        rewind = new javax.swing.JButton();
        volume = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        org.jdesktop.layout.GroupLayout playerLayout = new org.jdesktop.layout.GroupLayout(player);
        player.setLayout(playerLayout);
        playerLayout.setHorizontalGroup(
            playerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 735, Short.MAX_VALUE)
        );
        playerLayout.setVerticalGroup(
            playerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 477, Short.MAX_VALUE)
        );

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("Comments");

        jTextField1.setText("Enter comment here.");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/comment.png"))); // NOI18N
        jButton3.setText("Comment");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("Double click to delete");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/rotate-to-right.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel5.setText("Comments");

        org.jdesktop.layout.GroupLayout tool3Layout = new org.jdesktop.layout.GroupLayout(tool3);
        tool3.setLayout(tool3Layout);
        tool3Layout.setHorizontalGroup(
            tool3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tool3Layout.createSequentialGroup()
                .add(tool3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(tool3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jTextField1))
                    .add(tool3Layout.createSequentialGroup()
                        .add(tool3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(tool3Layout.createSequentialGroup()
                                .add(80, 80, 80)
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jLabel2))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, tool3Layout.createSequentialGroup()
                                .add(20, 20, 20)
                                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 351, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
            .add(tool3Layout.createSequentialGroup()
                .add(tool3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(tool3Layout.createSequentialGroup()
                        .add(132, 132, 132)
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(tool3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jButton3)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jButton7)
                .add(40, 40, 40))
        );
        tool3Layout.setVerticalGroup(
            tool3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tool3Layout.createSequentialGroup()
                .add(tool3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(tool3Layout.createSequentialGroup()
                        .add(9, 9, 9)
                        .add(jLabel1))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, tool3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel2)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 349, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(tool3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(tool3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jButton3)
                        .add(jLabel5))
                    .add(jButton7))
                .add(45, 45, 45))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/like.png"))); // NOI18N
        jButton1.setText("Like");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/add.png"))); // NOI18N
        jButton4.setText("Add Tag");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField2.setText("Enter tag here.");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/delete.png"))); // NOI18N
        jButton2.setText("Delete Video");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Likes");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/add.png"))); // NOI18N
        jButton5.setText("Add to to-watch list");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/subscribe.png"))); // NOI18N
        jButton6.setText("Subscribe");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/play.png"))); // NOI18N
        play.setText("Play");
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });

        pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/pause.png"))); // NOI18N
        pause.setText("Pause");
        pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseActionPerformed(evt);
            }
        });

        forward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/forward.png"))); // NOI18N
        forward.setText("Forward");
        forward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardActionPerformed(evt);
            }
        });

        rewind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorpoint/rewind.png"))); // NOI18N
        rewind.setText("Rewind");
        rewind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rewindActionPerformed(evt);
            }
        });

        volume.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                volumeStateChanged(evt);
            }
        });

        jLabel3.setText("tags");

        org.jdesktop.layout.GroupLayout tool1Layout = new org.jdesktop.layout.GroupLayout(tool1);
        tool1.setLayout(tool1Layout);
        tool1Layout.setHorizontalGroup(
            tool1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tool1Layout.createSequentialGroup()
                .add(32, 32, 32)
                .add(tool1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(tool1Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(jButton4)
                        .add(184, 184, 184))
                    .add(tool1Layout.createSequentialGroup()
                        .add(play)
                        .add(57, 57, 57)
                        .add(pause)
                        .add(75, 75, 75)
                        .add(forward)
                        .add(70, 70, 70)
                        .add(rewind)
                        .add(79, 79, 79)
                        .add(volume, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 134, Short.MAX_VALUE)
                        .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 202, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(260, 260, 260))
                    .add(tool1Layout.createSequentialGroup()
                        .add(tool1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jButton2)
                            .add(tool1Layout.createSequentialGroup()
                                .add(565, 565, 565)
                                .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(tool1Layout.createSequentialGroup()
                                .add(tool1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 638, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(tool1Layout.createSequentialGroup()
                                        .add(84, 84, 84)
                                        .add(jButton6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 171, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(jButton1)))
                                .add(74, 74, 74)
                                .add(jButton5)))
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        tool1Layout.setVerticalGroup(
            tool1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tool1Layout.createSequentialGroup()
                .add(tool1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(tool1Layout.createSequentialGroup()
                        .add(69, 69, 69)
                        .add(tool1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jButton4)
                            .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 53, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, tool1Layout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jButton6)
                        .add(18, 18, 18)))
                .add(jButton2)
                .add(29, 29, 29))
            .add(tool1Layout.createSequentialGroup()
                .addContainerGap()
                .add(tool1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(tool1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(play)
                        .add(pause)
                        .add(forward)
                        .add(rewind))
                    .add(volume, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(tool1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jButton5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel4)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(player, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(tool3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(144, 144, 144)
                        .add(tool1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(player, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(tool3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 101, Short.MAX_VALUE)
                .add(tool1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void forwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardActionPerformed
        // TODO add your handling code here:
         mediaPlayerComponent.getMediaPlayer().skip(10000);
    }//GEN-LAST:event_forwardActionPerformed

    private void rewindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rewindActionPerformed
        // TODO add your handling code here:
         mediaPlayerComponent.getMediaPlayer().skip(-10000);
    }//GEN-LAST:event_rewindActionPerformed

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        // TODO add your handling code here:
        mediaPlayerComponent.getMediaPlayer().play();
    }//GEN-LAST:event_playActionPerformed

    private void pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseActionPerformed
        // TODO add your handling code here:
        mediaPlayerComponent.getMediaPlayer().pause();
    }//GEN-LAST:event_pauseActionPerformed

    private void volumeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_volumeStateChanged
        // TODO add your handling code here:
        JSlider source=(JSlider) evt.getSource();
        mediaPlayerComponent.getMediaPlayer().setVolume(source.getValue());
    }//GEN-LAST:event_volumeStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if( getLike( ) )
        {
            try {
                String query = "DELETE FROM likes WHERE email='"+u.email+"' AND video_id="+ videoId +"";
                u.sendQuery( query , 1 );
                query = "SELECT * FROM videos WHERE id=" + videoId ;
                ArrayList<ArrayList<String>> videos = u.sendQuery( query , 2 );
                int likes = Integer.parseInt( videos.get(0).get(4) ) ;
                likes-- ;
                query = "UPDATE videos SET likes=" + likes + " WHERE id=" + videoId ;
                u.sendQuery( query , 1 ) ;
                jButton1.setText("Like") ;
                setLikesComments( ) ;
            } catch (IOException ex) {
                System.out.println("SQL Error in unliking.") ;
            }
        }
        else
        {
            try {
                String query = "INSERT INTO likes(email,video_id) VALUES('"+u.email+"', "+ videoId +")";
                u.sendQuery( query , 1 );
                query = "SELECT * FROM videos WHERE id=" + videoId ;
                ArrayList<ArrayList<String>> videos = u.sendQuery( query , 2 );
                int likes = Integer.parseInt( videos.get(0).get(4) ) ;
                likes++ ;
                query = "UPDATE videos SET likes=" + likes + " WHERE id=" + videoId ;
                u.sendQuery( query , 1 ) ;
                jButton1.setText("Unlike") ;
                setLikesComments( ) ;
            } catch (IOException ex) {
                System.out.println("SQL Error in liking.") ;
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public void comments( )
    {
        //to update the Jlist
        ArrayList<ArrayList<String>> comments;
        try {
            jList1.removeMouseListener(ml);
            String query = "SELECT * FROM comments WHERE video_id=" + videoId ;
            comments = u.sendQuery( query , 2 );
            DefaultListModel model1 = new DefaultListModel();
            for( int i = 0 ; i < comments.size( ) ; i++ )
            {
                String temp = ( comments.get(i).get(1) + " " + comments.get(i).get(2) ) ;
                model1.addElement(temp) ;
                
            }
            jList1.setModel(model1);
            
            ml = new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        int index = jList1.locationToIndex(evt.getPoint());
                        int comment = Integer.parseInt(comments.get(index).get(0)) ;
                        String cemail = comments.get(index).get(1) ; 
                        if( u.type == 2 || u.email.equals( cemail ) )
                        deleteComment( comment ) ; 
        
                    }
                } 
            };
            jList1.addMouseListener(ml) ;
        } catch (IOException ex) {
            Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public void deleteComment( int comment )
    {
        try {
            String query = "DELETE FROM comments WHERE id="+ comment ;
            u.sendQuery( query , 1 );
            query = "SELECT * FROM videos WHERE id=" + videoId ;
            ArrayList<ArrayList<String>> videos = u.sendQuery( query , 2 );
            int comments = Integer.parseInt( videos.get(0).get(5) ) ;
            comments-- ;
            query = "UPDATE videos SET comments=" + comments + " WHERE id=" + videoId ;
            u.sendQuery( query , 1 ) ;
            comments( ) ;
        } catch (IOException ex) {
            Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    public void getTags( )
    {
        String query = "SELECT * FROM videos WHERE id=" + videoId ;
        try {
            ArrayList<ArrayList<String>> videos = u.sendQuery( query , 2 );
            tags = videos.get(0).get(6) ;
            if( tags == null )
                tags = "No tags for this video yet." ;
        } catch (IOException ex) {
            Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            String comment = jTextField1.getText( ) ;
            String query = "INSERT INTO comments(email,video_id,comment) VALUES('"+u.email+"', "+ videoId +",'"+ comment +"')";
            u.sendQuery( query , 1 );
            query = "SELECT * FROM videos WHERE id=" + videoId ;
            ArrayList<ArrayList<String>> videos = u.sendQuery( query , 2 );
            int comments = Integer.parseInt( videos.get(0).get(5) ) ;
            comments++ ;
            query = "UPDATE videos SET comments=" + comments + " WHERE id=" + videoId ;
            u.sendQuery( query , 1 ) ;
            comments( ) ;
            setLikesComments( ) ;
        } catch (IOException ex) {
            Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            String newTag = jTextField2.getText( ) ;
            if( tags.equals("No tags for this video yet.") )
            {
                tags = newTag + "#" ;
            }
            else
                tags += ( newTag + "#" ) ;
            String query = "UPDATE videos SET tags='" + tags + "' WHERE id=" + videoId ;
            u.sendQuery( query , 1 ) ;
            setTags( ) ;
        } catch (IOException ex) {
            Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked

    }//GEN-LAST:event_jList1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            String query = "DELETE FROM videos WHERE id="+ videoId ;
            u.sendQuery( query , 1 );
            query = "DELETE FROM likes WHERE video_id="+ videoId ;
            u.sendQuery( query , 1 );
            query = "DELETE FROM comments WHERE video_id="+ videoId ;
            u.sendQuery( query , 1 );
            t.videos( ) ;
            query = "" + videoId ;
            u.sendQuery( query , 5 ) ;
            this.dispose( ) ;
        } catch (IOException ex) {
            Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String temp = jButton5.getText( ) ;
        if( temp.equals( "Add to to-watch list" ) )
        {
            try{
                String query = "INSERT INTO to_watch(email,video_id) VALUES('"+u.email+"',"+ videoId +")";
                u.sendQuery( query , 1 );
                jButton5.setText( "Remove from to-watch list" ) ;
            }catch (IOException ex) {
                Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            try{
                String query = "DELETE FROM to_watch WHERE email='"+u.email+"' AND video_id="+ videoId ;
                u.sendQuery( query , 1 );
                jButton5.setText( "Add to to-watch list" ) ;
            }catch (IOException ex) {
                Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String temp = jButton6.getText( ) ;
        if( temp.equals("Subscribe") )
        {
            try{
                String query = "INSERT INTO subscriptions VALUES('"+t_email+"','"+ u.email +"')";
                u.sendQuery( query , 1 );
                jButton6.setText( "Unsubscribe" ) ;
            }catch (IOException ex) {
                Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            try{
                String query = "DELETE FROM subscriptions WHERE t_email='"+t_email+"' AND s_email='"+ u.email +"'";
                u.sendQuery( query , 1 );
                jButton6.setText( "Subscribe" ) ;
            }catch (IOException ex) {
                Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        comments( ) ;
    }//GEN-LAST:event_jButton7ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //new NativeDiscovery().discover();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Just for debugging purposes ..........
                //new player(null,"",).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton forward;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton pause;
    private javax.swing.JButton play;
    private javax.swing.JPanel player;
    private javax.swing.JButton rewind;
    private javax.swing.JPanel tool1;
    private javax.swing.JPanel tool3;
    private javax.swing.JSlider volume;
    // End of variables declaration//GEN-END:variables
}

class playerThread extends Thread{
    player p ;
    public playerThread( player p ) {
        this.p = p ;
    }
    
    @Override
    public void run(){
        while( true )
        {
            p.deleted( ) ;
            p.getTags( ) ;
            p.setTags( ) ;
            p.setLikesComments( ) ;
            p.comments( ) ;
            try{
            Thread.sleep( 5000 ) ;
            } catch( Exception e )
            {
                System.out.println("Error in sleeping thread.") ;
            }
        }
    }
 
}