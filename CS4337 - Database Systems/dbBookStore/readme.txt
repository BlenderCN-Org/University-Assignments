- Spring JPC and Spring WebApp (WAR) integrated with AngularJS

https://spring.io/guides/gs/accessing-data-jpa/
https://elearning.utdallas.edu/bbcswebdav/pid-2167759-dt-content-rid-53104254_1/courses/2188-merged-CS4347501-SE4347501/Homework%205%282%29.pdf

--drop table books cascade constraints;
create table books (
    isbn char(10),
    author varchar2(100) not null,
    title varchar2(128) not null,
    price number(7,2) not null,
    subject varchar2(30) not null,
    primary key (isbn)
);

--drop table members cascade constraints;
create table members (
    fname varchar2(20) not null,
    lname varchar2(20) not null,
    address varchar2(50) not null,
    city varchar2(30) not null,
    state varchar2(20) not null,
    zip number(5) not null,
    phone varchar2(12),
    email varchar2(40),
    userid varchar2(20),
    password varchar2(20),
    creditcardtype varchar2(10)
    check(creditcardtype in ('amex','discover','mc','visa')),
    creditcardnumber char(16),
    primary key (userid)
);

--drop table orders cascade constraints;
create table orders (
    userid varchar2(20) not null,
    ono number(5),
    received date not null,
    shipped date,
    shipAddress varchar2(50),
    shipCity varchar2(30),
    shipState varchar2(20),
    shipZip number(5),
    primary key (ono),
    foreign key (userid) references members
);

--drop table odetails cascade constraints;
create table odetails (
    ono number(5),
    isbn char(10),
    qty number(5) not null,
    price number(7,2) not null,
    primary key (ono,isbn),
    foreign key (ono) references orders,
    foreign key (isbn) references books
);

--drop table cart cascade constraints;
create table cart (
    userid varchar2(20),
    isbn char(10),
    qty number(5) not null,
    primary key (userid,isbn),
    foreign key (userid) references members,
    foreign key (isbn) references books
);


<tr>
                <th>
                    <div class="wrapper">
                        <label for="uname"><b>Username</b></label>
                        <input type="text" placeholder="Enter Username" name="uname" required>
                    </div>
                </th>
                <th>
                    <div class="wrapper">
                        <label for="psw"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="psw" required>
                    </div>
                </th>
                <th>
                    <div class="wrapper">
                        <label for="creditcardtype"><b>Credit Card Holder</b></label>
                        <input type="text" placeholder="Enter Credit Card Holder" name="creditcardtype" required>
                    </div>
                </th>
                <th>
                    <div class="wrapper">
                        <label for="creditcardnumber"><b>Credit Card Number</b></label>
                        <input type="text" placeholder="Enter Credit Card Number" name="creditcardnumber" required>
                    </div>
                </th>
            </tr>