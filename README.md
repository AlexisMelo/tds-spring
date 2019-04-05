# tds-spring

demo-2 : Controllers, Entities, Repositories in Spring

demo-3 : Added VueJs, Request Ajax and Vuetifiyjs

gestion-script : Little application allowing users to save scripts/text documents and update them, while keeping an history of it. Includes Login/Logout pages, use of session variables, advanced use of JPA Repositories

# Problems with Spring

Sometimes you can't launch Spring because another device is already listening on port 8080. 
To solve this problem you can either :
  - Change the port on which Spring is supposed to be launched
  - Kill the process using port 8080 
  
For the second solution use the following commands on windows : 

  netstat -ano | findstr 8080 
  taskkill /F /pid process_pid
  
( replace process_pid with the pid you found with the first command line) 
