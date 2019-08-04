INSERT INTO USERS(username, followers, following, accountID) VALUES ('Sansa_Stark',4,3,'sansa@got.com');
INSERT INTO USERS(username, followers, following, accountID) VALUES ('Yara_Greyjoy',2,3,'yara@got.com');
INSERT INTO USERS(username, followers, following, accountID) VALUES ('John_Snow',4,4,'john@got.com');
INSERT INTO USERS(username, followers, following, accountID) VALUES ('Tyrion_Lanister',3,4,'tyrion@got.com');
INSERT INTO USERS(username, followers, following, accountID) VALUES ('Varysr',3,3,'varysr@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('yara@got.com','sansa@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('john@got.com','sansa@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('tyrion@got.com','sansa@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('varysr@got.com','sansa@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('sansa@got.com','yara@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('john@got.com','yara@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('sansa@got.com','john@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('yara@got.com','john@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('tyrion@got.com','john@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('varysr@got.com','john@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('sansa@got.com','tyrion@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('john@got.com','tyrion@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('varysr@got.com','tyrion@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('yara@got.com','tyrion@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('john@got.com','varysr@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('tyrion@got.com','varysr@got.com');
INSERT INTO FOLLOWERS(followersID, accountID) VALUES ('yara@got.com','varysr@got.com');
INSERT INTO TWEETS(tweet, accountID) VALUES ('Long Live North','sansa@got.com');
INSERT INTO TWEETS(tweet, accountID) VALUES ('King of North','sansa@got.com');
INSERT INTO TWEETS(tweet, accountID) VALUES ('Women Power','yara@got.com');
INSERT INTO TWEETS(tweet, accountID) VALUES ('Winter is Coming','john@got.com');
INSERT INTO TWEETS(tweet, accountID) VALUES ('Nights Watch','john@got.com');
INSERT INTO TWEETS(tweet, accountID) VALUES ('Dragon Blade','john@got.com');
INSERT INTO TWEETS(tweet, accountID) VALUES ('Lanister Pays his Debts','tyrion@got.com');
INSERT INTO TWEETS(tweet, accountID) VALUES ('Queens Hand','tyrion@got.com');
INSERT INTO TWEETS(tweet, accountID) VALUES ('DRACARUS','varysr@got.com');
INSERT INTO TWEETS_REPLIES(tweet, accountID, tweetID) VALUES ('oops','sansa@got.com',9);
INSERT INTO TWEETS_REPLIES(tweet, accountID, tweetID) VALUES ('sorry mate','john@got.com',9);
INSERT INTO TWEETS_REPLIES(tweet, accountID, tweetID) VALUES ('i did warn you','tyrion@got.com',9);
INSERT INTO TWEETS_REPLIES(tweet, accountID, tweetID) VALUES ('Alright!!!!','yara@got.com',9);
INSERT INTO TWEETS_REPLIES(tweet, accountID, tweetID) VALUES ('Am not sure','john@got.com',1);
INSERT INTO TWEETS_REPLIES(tweet, accountID, tweetID) VALUES ('All Hail..','tyrion@got.com',1);
INSERT INTO TWEETS_REPLIES(tweet, accountID, tweetID) VALUES ('Ok It alright..','tyrion@got.com',5);
INSERT INTO TWEETS_REPLIES(tweet, accountID, tweetID) VALUES ('White Walkers...','varysr@got.com',5);









