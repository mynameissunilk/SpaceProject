"# SpaceProject" 
 In general, the space enthusiast community wishes an app existed that combines multiple sources of space information in one place -- and as we researched, we understood why. Not many APIs exist, and the ones that do are only good for raw or super technical data. But the cool images and interesting updates that they desire require browsing multiple websites and specific research -- which can be a lot of work if you're not motivated. This is our app where a user can find out relevant information about space missions and events from multiple news sources, access space images such as NASA's picture of the day, and see what Astronauts are up to right now via their tweets. The user can also add upcoming astronomical events to their calendar. All articles and tweets can be shared via whichever social media app the user prefers.

trello:
https://trello.com/b/MwNm8Yw1/space-projectuser 

personas:
https://docs.google.com/document/d/1GzrJTgMt4MnQybXrpbO7dC8SDx8NHbnwUgfN3sdxMhA/edit?usp=sharingresearch 

plan:
https://docs.google.com/document/d/1_W9iE-4CG7yhcS_iwNKLl_nWD_iLZvNSpmlZJKrdMpc/edit?usp=sharinginterviews and 

competitive research:
https://docs.google.com/document/d/1oaJpLaS_W0OxT0OXQRJauV273llX7SQDfU4NXeQAyBw/edit

first prototype:
https://popapp.in/projects/57b0980929de692f29355d41/preview


second prototype:
https://popapp.in/projects/57b34a83d15d26580119ecef/preview


PROBLEMS: 

SUNIL: I tried (in several ways) to POST and GET on Twitter. Amusingly, my HTTP requests always return 200 OK statements, but the GET request fails immediately after the GET request. I tried to implement different Retrfofit APIServices, and I tried to pass different POJO/"hand-made" Model objects. Every time, I would get 200 Codes on authentication and on reaching the API, but was unfortunately unable to parse that JSON. 

In addition, we planned to include a 5th API (API.NYT). I must have somehow misused my endpoints, because I was unable to properly query their database. 

Also, (Sunil stil here) It would have been to our benefit if I anticipated the limited scope of the Retrofit callback methods. It was only unfortunately late in the weewk when I realized that I could not quickly or easily access the GSON/JSON/parsed data from my onResponse()s. 



ALICE: 
Objects stored in the Arraylist that is passed to our recycleradapter get duplicated. 

ANDERS: 
We had a strange issue where some cardviews were not of a uniform size in landscape orientation. 

