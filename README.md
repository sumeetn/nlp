nlp
===

CV extraction

Pre-requisite
Maven 3
jdk 1.6 onwards


Run below steps to setup on local machine

git clone -b master https://github.com/sumeetn/nlp.git 
cd <project_home>/nlp/cv-ext-nlp 
mvn clean install 
mvn tomcat7:run
Browse url : localhost:8080/nlp-ui
