--<View 1>
INSERT INTO MarketingSite(SiteId, SiteName, SiteLocation) VALUES (1, 'Mathematics Research', 'San Jose');

INSERT INTO PERSON (PersonId, Name, Age, Gender, Address, Phone, Email) VALUES (2048, 'Matthew McMillian', 20, 'Male', 'UT Dallas', 3335557777, 'testemail@ut.edu');
INSERT INTO EMPLOYEE (EmployeeId, Rank, Title, SupervisorId, SiteId) VALUES (2048, 'Lead Director', 'Vice President', NULL , 1);

INSERT INTO PERSON (PersonId, Name, Age, Gender, Address, Phone, Email) VALUES (2049, 'Brandon Tran', 21, 'Male', 'UT Dallas', 4448889999, 'testemail@ut.edu');
INSERT INTO EMPLOYEE (EmployeeId, Rank, Title, SupervisorId, SiteId) VALUES (2049, 'Director', 'UX Designer', 2048 , 1);

INSERT INTO SALARY (EmployeeId, TransactionNumber, PayDate, Amount) VALUES (2048, 1000, TO_DATE('2018/02/01','yyyy/mm/dd'), 10.00);
INSERT INTO SALARY (EmployeeId, TransactionNumber, PayDate, Amount) VALUES (2048, 1001, TO_DATE('2018/02/02','yyyy/mm/dd'), 40.00);
INSERT INTO SALARY (EmployeeId, TransactionNumber, PayDate, Amount) VALUES (2048, 1002, TO_DATE('2018/02/02','yyyy/mm/dd'), 240.00);

INSERT INTO SALARY (EmployeeId, TransactionNumber, PayDate, Amount) VALUES (2049, 1000, TO_DATE('2018/02/01','yyyy/mm/dd'), 30.00);
INSERT INTO SALARY (EmployeeId, TransactionNumber, PayDate, Amount) VALUES (2049, 1001, TO_DATE('2018/027/02','yyyy/mm/dd'), 80.00);
COMMIT;

--<View 2>
INSERT INTO PERSON (PersonId, Name, Age, Gender, Address, Phone, Email) VALUES (1048, 'Seong Wang', 20, 'Male', 'UT Dallas', 1112223333, 'testemail@ut.edu');
INSERT INTO POTENTIALEMPLOYEE (POTENTIALEMPLOYEEID) VALUES (1048);

INSERT INTO DEPARTMENT (DepartmentId, DepartmentName) VALUES (3, 'Mathematics');
INSERT INTO JOB (JobId, JobDescription, PostedDate, DepartmentId) VALUES (3, 'Math Research Intern', TO_DATE('2018/05/03','yyyy/mm/dd'), 3);

INSERT INTO INTERVIEW (InterviewId, InterviewerId, ApplicantId, JobId) VALUES (10, 2048, 1048, 3);
INSERT INTO INTERVIEW_GRADE (InterviewId, RoundNumber, Grade) VALUES (10, 1, 60);
INSERT INTO INTERVIEW_GRADE (InterviewId, RoundNumber, Grade) VALUES (10, 2, 70);
INSERT INTO INTERVIEW_GRADE (InterviewId, RoundNumber, Grade) VALUES (10, 3, 50);
INSERT INTO INTERVIEW_GRADE (InterviewId, RoundNumber, Grade) VALUES (10, 4, 80);
INSERT INTO INTERVIEW_GRADE (InterviewId, RoundNumber, Grade) VALUES (10, 5, 90);

INSERT INTO PERSON (PersonId, Name, Age, Gender, Address, Phone, Email) VALUES (1049, 'Kion Smith', 20, 'Male', 'UT Dallas', 3454563456, 'testemail@ut.edu');
INSERT INTO POTENTIALEMPLOYEE (POTENTIALEMPLOYEEID) VALUES (1049);

INSERT INTO INTERVIEW (InterviewId, InterviewerId, ApplicantId, JobId) VALUES (11, 2048, 1049, 3);
INSERT INTO INTERVIEW_GRADE (InterviewId, RoundNumber, Grade) VALUES (11, 1, 30);
INSERT INTO INTERVIEW_GRADE (InterviewId, RoundNumber, Grade) VALUES (11, 2, 20);
INSERT INTO INTERVIEW_GRADE (InterviewId, RoundNumber, Grade) VALUES (11, 3, 50);
INSERT INTO INTERVIEW_GRADE (InterviewId, RoundNumber, Grade) VALUES (11, 4, 60);
INSERT INTO INTERVIEW_GRADE (InterviewId, RoundNumber, Grade) VALUES (11, 5, 70);

--<View 3, 4>
INSERT INTO PRODUCT (ProductId, ProductType, ProductSize, ListPrice, Weight, ProductStyle) VALUES (1, 'Crowbar', 20, 19.99, 5.31, 'Red-Metal');
INSERT INTO PRODUCT (ProductId, ProductType, ProductSize, ListPrice, Weight, ProductStyle) VALUES (3, 'Crowbar', 20, 19.99, 5.31, 'Blue-Metal');
INSERT INTO PRODUCT (ProductId, ProductType, ProductSize, ListPrice, Weight, ProductStyle) VALUES (4, 'Crowbar', 20, 21.99, 5.31, 'Yellow-Metal');
INSERT INTO PRODUCT (ProductId, ProductType, ProductSize, ListPrice, Weight, ProductStyle) VALUES (2, 'Portal Gun', 23, 119.99, 10.31, 'Aperture White');
INSERT INTO PRODUCT (ProductId, ProductType, ProductSize, ListPrice, Weight, ProductStyle) VALUES (6, 'Textbook', 15, 120.99, 2.59, 'Statistical Analysis');
INSERT INTO PRODUCT (ProductId, ProductType, ProductSize, ListPrice, Weight, ProductStyle) VALUES (7, 'Textbook', 15, 220.99, 2.59, 'Numerical Analysis');
INSERT INTO PRODUCT (ProductId, ProductType, ProductSize, ListPrice, Weight, ProductStyle) VALUES (8, 'Textbook', 15, 260.99, 2.59, 'Database Analysis');

INSERT INTO PART (PartId, PartName, Price, VendorId) VALUES (1, 'Handle', 3.99, 1);
INSERT INTO PART (PartId, PartName,Price, VendorId) VALUES (2, 'Metal Bob', 1.99, 2);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (1, 1, 1);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (1, 1, 2);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (4, 1, 1);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (4, 1, 2);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (3, 1, 1);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (3, 1, 2);

INSERT INTO PART (PartId, PartName,Price, VendorId) VALUES (3, 'Quantum Parts', 6.99, 2);
INSERT INTO PART (PartId, PartName,Price, VendorId) VALUES (4, 'Metallic Plating', 0.99, 3);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (2, 3, 3);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (2, 4, 4);

INSERT INTO PART (PartId, PartName,Price, VendorId) VALUES (5, 'High-Quality Paper', 4.99, 2);
INSERT INTO PART (PartId, PartName,Price, VendorId) VALUES (6, 'Cardboard Cover', 0.99, 3);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (6, 1, 5);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (6, 50, 6);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (7, 1, 5);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (7, 50, 6);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (8, 1, 5);
INSERT INTO Product_Parts (ProductId, Quantity, PartId) VALUES (8, 50, 6);

--<Query 1>
INSERT INTO PERSON (PersonId, Name, Age, Gender, Address, Phone, Email) VALUES (1001, 'Hellen Cole', 30, 'Female', '999 New York City', 2819995555, 'testemail@ut.edu');
INSERT INTO POTENTIALEMPLOYEE (POTENTIALEMPLOYEEID) VALUES (1001);

INSERT INTO JOB (JobId, JobDescription, PostedDate, DepartmentId) VALUES (11111, 'Summer Computer Science Intern', TO_DATE('2018/02/01','yyyy/mm/dd'), 3);

INSERT INTO INTERVIEW (InterviewId, InterviewerId, ApplicantId, JobId) VALUES (1, 2048, 1001, 11111);
INSERT INTO INTERVIEW (InterviewId, InterviewerId, ApplicantId, JobId) VALUES (2, 2049, 1001, 11111);

--<Query 2>
INSERT INTO DEPARTMENT (DepartmentId, DepartmentName) VALUES (12, 'Marketing');
INSERT INTO JOB (JobId, JobDescription, PostedDate, DepartmentId) VALUES (86, 'Marketing Intern, Fall', TO_DATE('2011/03/03','yyyy/mm/dd'), 12);
INSERT INTO JOB (JobId, JobDescription, PostedDate, DepartmentId) VALUES (87, 'Marketing Intern, Summer', TO_DATE('2011/03/05','yyyy/mm/dd'), 12);
INSERT INTO JOB (JobId, JobDescription, PostedDate, DepartmentId) VALUES (88, 'Marketing Intern, Spring', TO_DATE('2011/03/02','yyyy/mm/dd'), 12);
INSERT INTO JOB (JobId, JobDescription, PostedDate, DepartmentId) VALUES (89, 'Marketing Intern, Winter', TO_DATE('2011/03/09','yyyy/mm/dd'), 12);
INSERT INTO JOB (JobId, JobDescription, PostedDate, DepartmentId) VALUES (90, 'On-Site Sales Intern', TO_DATE('2012/04/09','yyyy/mm/dd'), 12);

--<Query 4>
INSERT INTO MarketingSite(SiteId, SiteName, SiteLocation) VALUES (2, 'Omega', 'Houston');
INSERT INTO PERSON (PersonId, Name, Age, Gender, Address, Phone, Email) VALUES (2050, 'Hector Quear', 27, 'Male', 'Austin', 3335557777, 'testemail@ut.edu');
INSERT INTO EMPLOYEE (EmployeeId, Rank, Title, SupervisorId, SiteId) VALUES (2050, 'Salesman', 'Sr.', 2048 , 2);

INSERT INTO PERSON (PersonId, Name, Age, Gender, Address, Phone, Email) VALUES (4001, 'John Bimbo', 42, 'Male', '999 Los Muertos', 6775663444, 'testemail@ut.edu');
INSERT INTO CUSTOMER (CustomerId, PreferredSalesman) VALUES (4001, 2049);

INSERT INTO SALE (SaleId, ProductId, CustomerId, SalesmanId, SaleTime) VALUES (301, 1, 4001, 2049, TO_DATE('2011/04/03','yyyy/mm/dd'));
INSERT INTO SALE (SaleId, ProductId, CustomerId, SalesmanId, SaleTime) VALUES (302, 1, 4001, 2049, TO_DATE('2011/03/01','yyyy/mm/dd'));

INSERT INTO SALE (SaleId, ProductId, CustomerId, SalesmanId, SaleTime) VALUES (311, 3, 4001, 2050, TO_DATE('2011/04/03','yyyy/mm/dd'));
INSERT INTO SALE (SaleId, ProductId, CustomerId, SalesmanId, SaleTime) VALUES (312, 2, 4001, 2050, TO_DATE('2011/02/01','yyyy/mm/dd'));

--<Query 6>
INSERT INTO SALE (SaleId, ProductId, CustomerId, SalesmanId, SaleTime) VALUES (612, 8, 4001, 2050, TO_DATE('2011/02/05','yyyy/mm/dd'));
INSERT INTO SALE (SaleId, ProductId, CustomerId, SalesmanId, SaleTime) VALUES (613, 7, 4001, 2050, TO_DATE('2011/02/06','yyyy/mm/dd'));

--<Query 7>
INSERT INTO JOB (JobId, JobDescription, PostedDate, DepartmentId) VALUES (101, 'Receptionist Intern', TO_DATE('2011/01/06','yyyy/mm/dd'), 12);

--<Query 8>
INSERT INTO JOB (JobId, JobDescription, PostedDate, DepartmentId) VALUES (12345, 'Busniess Insider Intern', TO_DATE('2011/01/06','yyyy/mm/dd'), 12);
INSERT INTO INTERVIEW (InterviewId, InterviewerId, ApplicantId, JobId) VALUES (83, 2048, 2049, 12345);
INSERT INTO INTERVIEW (InterviewId, InterviewerId, ApplicantId, JobId) VALUES (82, 2048, 1048, 12345);

--<Query 11>
-- Insert Matt into each department for testing
INSERT INTO DEPARTMENT_HISTORY(DepartmentId, EmployeeId) VALUES ();

--<Query 13>
-- Add person who applies for and passes all jobs, and add someone who applies for all jobs but does not pass them all

--<Query 15>
INSERT INTO VENDOR (VendorId, Name, Address, AccountNumber, CreditRating, WebURL) VALUES (2, 'Jacks Parts', '500 W Parker St', 999888777, 200, 'www.test.com');
INSERT INTO PART (PartId, PartName,Price, VendorId) VALUES (15, 'Cup', 2.99, 2);

INSERT INTO VENDOR (VendorId, Name, Address, AccountNumber, CreditRating, WebURL) VALUES (3, 'Bills Parts', '300 W Parker St', 124432134, 210, 'www.test.com');
INSERT INTO PART (PartId, PartName,Price, VendorId) VALUES (17, 'Cup', 1.99, 3);
