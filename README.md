****Automated HTTP Server Deployment on AWS cloud using Ansible****

					**Author: Hanmant Lokare, Indrajeet Vidhate**

			            	    **Guided by: Dr. Charles Border**


**Project Description**

This project was based on our course **'NSSA.713.01-02 - Enterprise Service Provisioning (NSSA7130)'** at **Rochester Institute of Technology.**

In this project, we had deployed a Java HTTP server manually and also using Ansible on Amazon Web services(AWS). 
 
**Steps:**

1.Manually deploy Server on AWS:
    	 Before automating the deployment of HTTP Server to AWS we will manually deploy the server to AWS.

1.1 Creating the User:
	Creating a user is the very critical step in our process because it is very important to have all permission to manage the server and use other services on AWS. For this, we will use a service provided by AWS called Identity and access management service (IAM). We will create a user using IAM and a group initially giving all the required rights and permission.

1.2 Creating an EC2 instance:
          AWS provide amazing service called EC2 which offers secure, resizable compute capacity in the cloud. We will create an EC2 instance of Amazon Linux AMI and configure it according to our needs. We also create an SSH connection, so that we can log in to the server whenever we want.

1.3 Preparing application based on Java:
	We have developed HTTP server in core java which will use Java's socket class to create a TCP port and listen on it. We have made use of Java's multithreaded support to serve multiple requests parallelly. The HTTP server can facilitate any file type ranging from pdf, mp3, mp4, etc. as we are making use of Java's byte streams for input and output mechanism. The HTTP connection established is of type persistent HTTP connection, this means we can serve multiple objects without been the need to re-establish the connection for each request.

1.4 Setup dependencies and libraries on the server:
       We will need git and java to clone GitHub repository and to run the java application. So, will we SSH into the machine and install git and JDK 1.8. Now we will clone our repository from GitHub and run the desired application.

1.5   Automating the above process using Ansible:
     We will do the same process of provisioning the server but this time we will use ansible to automate it. We will install the ansible on our local machine and create a host file to enter all the credential required and try to connect the server using ansible. Then we will write a playbook to install all the dependencies and libraries required to run our application. Finally, we will test our application on different browsers
After Completing the above process, we have understood the basic difference between the manual vs automated deployment for time, simplicity and less error-prone environment.

**Based on:** https://medium.com/@mglover/deploying-a-java-httpserver-to-aws-ec51388ed8e2
