 
INSERT INTO FLIGHT(ID,SOURCE,DESTINATION,DEPARTURE_DATE,ARRIVAL_DATE,FLIGHT_NAME,FLIGHT_NO,PRICE,CAPACITY) VALUES  
(1,'delhi','pune','29/08/20 12:00:00','29/08/20 14:00:00', 'flight1', 'F-1',3000,500),
(2,'pune','delhi','30/08/20 15:00:00','30/08/20 17:00:00', 'flight1', 'F-1',3500,100),
(3,'delhi','mumbai','29/08/20 12:00:00','29/08/20 14:00:00', 'flight2', 'F-2',4000,200),
(4,'mumbai','delhi','30/08/20 15:00:00','30/08/20 17:00:00', 'flight2', 'F-2',5000,300),
(5,'delhi','mumbai','29/08/20 08:00:00','29/08/20 10:00:00', 'flight3', 'F-3',3000,300),
(6,'mumbai','delhi','30/08/20 11:00:00','30/08/20 13:00:00', 'flight4', 'F-4',6000,300),
(7,'Banglore','delhi','30/08/20 15:00:00','30/08/20 17:00:00', 'flight6', 'F-6',5500,100);



INSERT INTO USER_TYPE(USER_TYPE_ID,USER_TYPE,DISCOUNT) VALUES  
(1,'silver', 5),
(2,'gold',10),
(3,'platinum',15);


INSERT INTO USERS(USER_ID,FIRST_NAME,LAST_NAME,MOBILE_NO,EMAIL_ID,USER_TYPE_ID) VALUES  
(1,'Arpit','Garg', '9654321011','arpitgarg@gmail.com',1),
(2,'Publicis','Sapient','7654321890','sapient@gmail.com',2),
(3,'XYZ','xyz','9898765657','xyz@gmail.com',3);
