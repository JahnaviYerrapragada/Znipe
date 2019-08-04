Znipe Twit API:

This project has API's for  four services to follow user, unfollow user , to get the followers and to fetch the User Tweets by the followers.
There is Token Generator API to generate a JWT Token.


Account: Refers to The User account on which follow/un-follow request is performed.
Followers: Refers to the user who want to follow/un-follow

URLS: 
////////////// Unfollow User, Scenario: Varysr wants to unfollow  User Sansa. ///////////////

Unfollow_User : localhost:8084/znipe/unfollow
Type: POST
Header :
       key: Authorisation
       value":"Token *Your Generated Token*"

Body:    {
      "accountID": "sansa@got.com", 
      "followersID": "varysr@got.com"
       }

////////////// Follow User ,Scenario: Sansa wants to follow the User Varysr ///////////////

Follow_User : localhost:8084/znipe/follow
Type: PUT
Header :
       key: Authorisation
       value":"Token *Your Generated Token*"
Body:    {
      "accountID": "varysr@got.com", 
      "followersID": "sansa@got.com"
       }

    
////////////// Request to fetch the Tweets of a user and replies to the tweet by the followers only///////////////  

Tweets : localhost:8084/znipe/tweets/varysr@got.com
Type: Get , path parameter is userID:varysr@got.com
Header :
       key: Authorisation
       value":"Token *Your Generated Token*"

 
////////////// Request to fetch the followers of a user ///////////////  

Followers : localhost:8084/znipe/followers/sansa@got.com
Type: Get , path parameter is userID:sansa@got.com
Header :
       key: Authorisation
       value":"Token *Your Generated Token*"



////////////// Request to fetch a token ///////////////  
Token: localhost:8084/token
Type : POST
Body: 
{
	"username": "sansa@got.com",
	"id": 1234,
	"role": "admin"
}






