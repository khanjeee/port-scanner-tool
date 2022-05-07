# port-scanner-tool
A tool for scanning network ports via Lambda Function

 - Ceate a Maven Project 
![Screenshot from 2022-05-06 17-56-26.png](:/48f46afd55bf4d0d8e0c9bb05ea0171b)


-  Add dependency maven shade
-  run project as > maven > goals > type > package shade:shade 
-  You will see java file in target when the build is complete
-  Upload this file to s3 bucket
-  Open the lambda function associated with api gateway 
-  Goto > Code > Upload from and provide the s3 url, build will be updated 
-  userfull articles
	-  https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html
	-  https://docs.aws.amazon.com/lambda/latest/dg/java-package-eclipse.html
	
