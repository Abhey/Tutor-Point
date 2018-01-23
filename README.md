# Tutor-Point
An online learning platform for the video tutorials on various courses provided by different tutors around the globe. Where tutors would create courses and upload videos and students would be able to watch them and learn.

# Basic Features
<UL>
   <LI> Create a student/teacher account. </LI>
   <LI> Panel for a teacher account to manage his/her content. </LI>
   <LI> Teacher account features:
<UL>
   <LI> Create a course ( group of videos ). like CS1202 or Cryptography on type </LI>
   <LI> Divide a course into subtopics. ( Analogous to chapter in the book ) </LI>
   <LI> Upload one or more videos into one subtopic. ( Like topics in the chapter ) </LI>
   <LI> Delete any video. </LI>
   <LI> Able to track statistics of each video ( Likes and comments ). </LI> </UL> </LI>
<LI> Add/Delete the comments. ( Admin must have right to delete any comment, user would have right for his comment only ) </LI>
<LI> Student account features:
  <UL>
     <LI> Able to watch any video of any course. ( Browse and watch any video ) </LI>
     <LI> Able to like and comment on any video. ( Just like youtube and facebook ) </LI>
     <LI> Search a particular video on the basis of course name. (Simple string matching ) </LI>
   </UL> </LI>
</UL>
<H1> Advanced Features </H1>
<UL>
<LI> Students can subscribe to a teacher. ( Students would get notification when a new video is uploaded by the teacher ) </LI>
   <LI> Teacher can add tags to videos. ( like add #JAVA tag to java course’s video ) </LI>
<LI> Students can search videos by tag and authors. ( Search would result video with same tag as searched ) </LI>
<LI> Student can rate a course in 5 stars. ( Student would rate course in 5 stars and that would update the average course rating ) </LI>
<LI> Sort courses based on rating. ( Order search results in increasing or decreasing rating of course ) </LI>
<LI> Student can maintain a to-watch list. ( A list to remember which video is to be watched in future) </LI>
<LI> Maintain a list of in-progress courses. ( Courses which is student has marked as still watching, so that he can easily browse those courses ) </LI>
</UL>

<H1>Note for contributors</H1>

* Download the source code from https://github.com/Abhey/Tutor-Point
* Extract the content of zip file.

( If you are comfortable using git then you can also clone the repo and skip above two steps )

* Open the project in netbeans or any other IDE you like (Project can be found under tutorPoint directory).
* Add all jar files to project ( All the dependency can be found in dependency directory ).

* Install vsftpd in your Linux distro ( execute command sudo apt-get install vsftpd for ubuntu ).
* Run the server first by executing server.java .
* When prompted for your username and password enter the your system username and system password. ( This is only needed to start ftp server in sudo mode ).

* Now you need to import database to your system.
* Download TutorPoint.sql to your system.
* Open MySql workbench in your system and import TutorPoint.sql and set database name TutorPoint.
( If you love using cli just like me, then execute the following command **mysql -u root -p TutorPoint < TutorPoint.sql** and you are all set. )
   
* To run the client side of application run Login.java file.
* Now you are good to go start contributing.

Note: Currently tutor point works only on linux based operating systems.

# Contact
1. Abhey Rana - abheyr@acm.org
