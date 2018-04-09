
use moviedb;

create table if not exists movies(
id	 varchar(10) not null primary key,
   title varchar(100) not null,
   year integer not null,
   director varchar(100) not null
);

create table if not exists stars(
id varchar(10) not null primary key,
   name varchar(100) not null,
   birthYear integer
);

create table if not exists stars_in_movies(
starId varchar(10) not null,
   movieId varchar(10) not null,
foreign key (starId) References stars(id),
   foreign key (movieId) references movies(id)
);

create table if not exists genres(
id integer not null auto_increment primary key,
   name varchar(32) not null
);

create table if not exists genres_in_movies(
genreId integer not null,
   movieId varchar(10) not null,
   foreign key (genreId) References genres(id),
   foreign key (movieId) references movies(id)
);

create table if not exists creditcards(
id varchar(20) not null primary key,
   firstName varchar(50) not null,
   lastName varchar(50) not null,
   epiration date not null
);

create table if not exists customers(
id integer not null auto_increment primary key,
   firstName varchar(50) not null,
   lastName varchar(50) not null,
   ccId varchar(20) not null,
   address varchar(200) not null,
   email varchar(50) not null,
   passworkd varchar(50) not null,
   foreign key (ccId) references creditcards(id)
);

create table if not exists sales(
id integer not null auto_increment primary key,
   customerId integer not null,
   movieId varchar(10) not null,
   saleDate date,
   foreign key (customerId) references customers(id),
   foreign key (movieId) references movies(id)
);

create table if not exists ratings(
movieId varchar(10) not null,
   rating float,
   numVotes integer,
   foreign key (movieId) references movies(id)
);
