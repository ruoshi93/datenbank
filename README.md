# Exploratory Join Ordering using Progress Feedback

     
     
The project explores the join ordering using progress feedback. It uses the IMDB data set [1] and the JOB [2], which are proposed in the article "How Good Are Query Optimizers, Really?" [3]. 

   
   
Due to the upload storage limit of Github, to run the project with the IMDB data set , the data set should be downloaded in the file "imdb" in the directory of the project. The relative path is: "./imdb" 

The download link of IMDB data set and the JOB queries can be founded in the references. 

  
  
The entrance of the program is the main method in datenbank/main/DatabaseEngine.java . 
        
            
               
               
               
###References: 

[1] http://homepages.cwi.nl/~boncz/job/imdb.tgz

[2] http://www-db.in.tum.de/~leis/qo/job.tgz

[3] V. Leis, A. Gubichev, A. Mirchev, P. Boncz, A. Kemper, and T. Neumann. “How Good Are Query Optimizers, Really?” In: Proc. VLDB Endow. 9.3 (Nov. 2015), pp. 204–215. issn: 2150-8097. doi: 10.14778/2850583.2850594.