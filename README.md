# tds-spring

demo-2 : Controllers, Entities, Repositories in Spring

demo-3 : Added VueJs, Reqeuest Ajax and Vuetifiyjs

# Problems with Spring

Sometimes you can't launch Spring because another device is already listening on port 8080. 
To solve this problem you can either :
  - Change the port on which Spring is supposed to be launched
  - Kill the process using port 8080 
  
For the second solution use the following commands on windows : 

  netstat -ano | findstr 8080 
  taskkill /F /pid process_pid
  
( replace process_pid with the pid you found with the first command line) 
