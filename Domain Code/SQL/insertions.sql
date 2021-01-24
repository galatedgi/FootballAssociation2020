USE [FootballTest]
GO

insert into Member values(19,'luzi',123456,'avi luzon',1,1)

insert into Member values(1,'omeriko',123456,'omer reichstein',1,0)
insert into Member values(2,'sancho',123456,'gal atedgi',0,0)
insert into Member values(3,'roi Nis',123456,'roi nissan',0,0)
insert into Member values(4,'roico',123456,'roei cohen',1,0)
insert into Member values(5,'wulfi',123456,'amit wulf',1,1)

insert into Member values(6,'queenA',123456,'alona barkat',1,1)
insert into Member values(7,'KingE',123456,'eli barkat',1,1)
insert into Member values(8,'mitch',123456,'mitch goldhar',1,1)
insert into Member values(9,'tabib',123456,'eli tabib',1,1)
insert into Member values(10,'moshiko',123456,'moshe hogeg',1,1)
insert into Member values(11,'yankale',123456,'yaacov sahar',1,1)
insert into Member values(12,'zino666',123456,'eli zino',1,1)

insert into Member values(13,'ogu',123456,'john ogu',1,1)
insert into Member values(14,'tn9',123456,'tony wakeme',1,1)
insert into Member values(15,'shed',123456,'niv zrihan',1,1)
insert into Member values(16,'magic24',123456,'maor melikson',1,1)
insert into Member values(17,'magicHands',123456,'dudu goresh',1,1)
insert into Member values(18,'tabah1',123456,'tal ben haim',1,1)
insert into Member values(36,'zahav',123456,'eran zehavi',1,1)
insert into Member values(20,'parsi',123456,'ofir davidzada',1,1)

insert into Member values(21,'bb',123456,'barak bachar',1,1)
insert into Member values(22,'dror shimshon',123456,'dror shimshon',1,1)
insert into Member values(23,'osc',123456,'oscar garcia',1,1)
insert into Member values(24,'elish',123456,'elisha levi',1,1)

insert into Member values(25,'elia',123456,'elianiv barda',1,1)
insert into Member values(26,'jordi101',123456,'jordi cruiyf',1,1)

insert into Member values(27,'zain1',123456,'liran liani',1,1)
insert into Member values(28,'itzhaki',123456,'otzhak ben itzhak',1,1)
insert into Member values(29,'haki',123456,'eli hakmon',1,1)

insert into Member values(30,'kavan1',123456,'jordi ehad',1,1)
insert into Member values(31,'kavan2',123456,'jordi shtaim',1,1)
insert into Member values(32,'kavan3',123456,'jordi shalosh',1,1)
insert into Member values(33,'kavan4',123456,'jordi arba',1,1)

insert into Member values(34,'var1',123456,'var mis ehad',1,1)
insert into Member values(35,'var2',123456,'var mis shtaim',1,1)

INSERT INTO [dbo].[League] VALUES('Bundes League',2,3,2,1);
INSERT INTO [dbo].[League] VALUES('La liga',1,3,1,1);
INSERT INTO [dbo].[League] VALUES('Japanika',4,3,2,0);

INSERT INTO [dbo].[League] VALUES('Japanikaasd','4',3,2,0);

Insert Into [dbo].[Season] VALUES(1999,'Bundes League',8,4,5,6);
Insert Into [dbo].[Season] VALUES(2005,'Bundes League',1,2,3,4);
insert into Season values(1999,'japanika',1,3,1,0)

insert into Stadium values('vasermil','beer sheva');
insert into Stadium values('tedi','jerusalem');
insert into Stadium values('bernabeu','madrid');
insert into Stadium values('sami ofer','haifa');
insert into Stadium values('bloomfield','tel aviv');

insert into Team values('hbs',0,'vasermil');
insert into Team values('madrid',0,'bernabeu');
insert into Team values('macabi haifa',0,'sami ofer');
insert into Team values('beitar',0,'tedi');
insert into Team values('tel aviv',0,'bloomfield');

select * from LeaguePosition
delete from TeamTM where TeamName='maccabi'
delete  from Team where TeamName='maccabi'

select * from TeamCoaches
se


insert into AssociationMember values(19)

insert into Coach values(21,3,'hbs','head coach');
insert into Coach values(23,1,'hbs','fitness coach');
insert into Coach values(22,2,'tel aviv','main coach');
insert into Coach values(24,2,'macabi haifa','head coach');

insert into TeamCoaches values('hbs',21)
insert into TeamCoaches values('hbs',23)
insert into TeamCoaches values('tel aviv',22)
insert into TeamCoaches values('macabi haifa',24)



insert into TeamManager values(25,'football manager','hbs')
insert into TeamManager values(26,'football manager','tel aviv')

insert into TMPermissions values(25,1)
insert into TMPermissions values(25,2)
insert into TMPermissions values(26,3)

delete  from TMPermissions

insert into TeamOwner values(6,'hbs')
insert into TeamOwner values(7,'hbs')
insert into TeamOwner values(8,'tel aviv')
insert into TeamOwner values(9,'beitar')
insert into TeamOwner values(10,'beitar')
insert into TeamOwner values(11,'macabi haifa')
insert into TeamOwner values(12,'hbs')

insert into Player values(13,7,'hbs',1993-02-02)
insert into Player values(14,8,'hbs',1993-02-02)
insert into Player values(15,6,'hbs',1993-02-02)
insert into Player values(16,10,'hbs',1993-02-02)
insert into Player values(17,1,'hbs',1993-02-02)
insert into Player values(18,7,'tel aviv',1993-02-02)
insert into Player values(36,7,'tel aviv',1993-02-02)
insert into Player values(20,7,'tel aviv',1993-02-02)
insert into Player values(37,7,'beitar',1993-02-02)



insert into BudgetReports values('hbs',1993-02-02,20,'kaha')
insert into BudgetReports values('hbs',1993-02-03,30,'ma kara')
insert into BudgetReports values('hbs',1993-02-04,-25,'lama ma kara')
insert into BudgetReports values('tel aviv',1993-02-02,1000,'kaha')

insert into TeamTO values('hbs',6)
insert into TeamTO values('hbs',7)
insert into TeamTO values('hbs',12)
insert into TeamTO values('tel aviv',8)
insert into TeamTO values('beitar',10)
insert into TeamTO values('beitar',9)
insert into TeamTO values('macabi haifa',11)

delete from TeamTO

select * from TeamTO

insert into TeamPlayers values('hbs',13)
insert into TeamPlayers values('hbs',14)
insert into TeamPlayers values('hbs',15)
insert into TeamPlayers values('hbs',16)
insert into TeamPlayers values('hbs',17)
insert into TeamPlayers values('tel aviv',18)
insert into TeamPlayers values('tel aviv',36)
insert into TeamPlayers values('tel aviv',20)

insert into TOAppointments values(12,6)
insert into TOAppointments values(12,7)
insert into TOAppointments values(9,10)

insert into Ticket values(1,'madafuck','fu',1)
insert into Ticket values(2,'madafuck2','fu2',1)
insert into Ticket values(3,'madafuck3',null,0)

insert into LeaguePosition values(1999,'japanika','hbs',3,0,0,10,1)
insert into LeaguePosition values(1999,'japanika','tel aviv',1,1,1,10,1)
insert into LeaguePosition values(1999,'japanika','beitar',0,3,0,10,1)
insert into LeaguePosition values(1999,'japanika','macabi haifa',1,1,1,10,1)

insert into FootballGame values('hbs','beitar',1993-02-02,null,null,null,null,null,null,1999,'Japanika',null)
insert into FootballGame values('hbs','tel aviv',1994-02-02,null,null,null,null,null,null,1999,'Japanika',null)
update FootballGame set GameDate='1990-12-12T11:30'


insert into TeamObservers values('hbs',1)
insert into TeamObservers values('hbs',2)
insert into TeamObservers values('hbs',3)
insert into TeamObservers values('beitar',4)
insert into TeamObservers values('tel aviv',5)

insert into GameFoulEvent values('hbs','tel aviv','1990-12-12T11:30','1990-12-12T11:45','tel aviv',18,14)
insert into GameGoalEvent values('hbs','tel aviv','1990-12-12T11:30','1990-12-12T11:47','hbs',14)
insert into GameInjuryEvent values('hbs','tel aviv','1990-12-12T11:30','1990-12-12T11:49','hbs',15)
insert into GameOffsideEvent values('hbs','tel aviv','1990-12-12T11:30','1990-12-12T11:49','hbs',15)
insert into GameRedCardEvent values('hbs','tel aviv','1990-12-12T11:30','1990-12-12T11:49','tel aviv',18)
insert into GameYellowCardEvent values('hbs','tel aviv','1990-12-12T11:30','1990-12-12T11:49','tel aviv',18)
insert into GameStartEvent values('hbs','tel aviv','1990-12-12T11:30')
insert into GameSubtitutionEvent values('hbs','tel aviv','1990-12-12T11:30','1990-12-12T11:49','hbs',18,16)
insert into GameRelocationEvent values('hbs','tel aviv','1990-12-12T11:30',null,null)
insert into GameEndEvent values('hbs','tel aviv','1990-12-12T11:30')

insert into Referee values(27,1,1)
insert into Referee values(28,1,1)
insert into Referee values(29,1,0)

insert into Referee values(30,2,1)
insert into Referee values(31,2,1)
insert into Referee values(32,2,1)
insert into Referee values(33,2,0)

insert into Referee values(34,3,1)
insert into Referee values(35,3,1)

insert into LeagueLReferee values('japanika',30)
insert into LeagueLReferee values('japanika',31)
insert into LeagueLReferee values('japanika',32)

insert into LeagueVReferee values('japanika',34)
insert into LeagueVReferee values('japanika',35)

insert into LeagueMReferee values('japanika',27)
insert into LeagueMReferee values('japanika',28)