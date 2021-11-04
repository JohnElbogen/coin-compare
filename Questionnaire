**Are there any sub-optimal choices( or short cuts taken due to limited time ) in your implementation?**

Due to limited time there were several design decisions that were sub-optimal. For example, the frontend makes a GET request to the back end, which in turn sends out several api calls to different exchanged apis in order to update the price values. This could be exploited in a production environment and cause unnessesary requests given the traffic is high. Since this is a test application, there will be limited requests, so having the ability to send too many api calls is not an issue. One possible solution would be to have the backend send out requests on a time schedule (eg. once per second) controlling and limiting the max requests to outside api's (such as binance or gemini's api) Then all GET requests from the frontend can be more quickly resolved through returning whatever data is most currently active in the backend. At the moment two fetch requests are run. Another sub optimal design choice was sub-optimal project structure. This was due to wanting more simple file structure, but this design choice would not scale efficiently.

**Is any part of it over-designed? ( It is fine to over-design to showcase your skills as long as you are clear about it)**

There are some parts of this application that are over deisgned for the lsited requirements. For one, the page was designed to adjust to browser sizes using flexboxe's and applying correct css to help manage browser sizing. Also the design accounts to prevent over stretching within the browser window to keep elements relatively visible and legible. This was intentional to help showcase some considerations that would be applied for a better user experience.

**If you have to scale your solution to 100 users/second traffic what changes would you make, if any?**

The most important change was mentioned in the first question. It would be to help minimize what is actually requested of the backend server. Ideally I would localize the data that the backend fetches, and each request from the front end would just pull information directly. The backend would update itself on a cycle. Another potential change would be to update the prices every so often preventing users from manually sending requests. This implementation was something I had considered but could not add with the time constraints. 

**What are some other enhancements you would have made, if you had more time to do this implementation**

One enhancment I was looking to add initially was dynamic components which move the best price toward the top of the page. However this would ahve required the time based updating that I did not end up implementing in the back end. In addition I would want to security layers, like a maximum number of requests per user. In a more realistic scenario I think it would have been crucial to provide information and links to help users access the reccomendations.
