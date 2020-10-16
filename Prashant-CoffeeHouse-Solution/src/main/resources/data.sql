INSERT INTO 
	CUSTOMER_MASTER (FIRST_NAME , LAST_NAME , email, PHONE , ADDRESS_LINE1 , ADDRESS_LINE2 ) 
VALUES
  	('Lokesh', 'Gupta', 'lokesh@gmail.com', '9999999999', 'JP Nagar', '6th phase'),
  	('John', 'Doe', 'john@gmail.com', '9888888888', 'Jay Nagar', '2nd phase'),
	('prashant', 'k', 'prashant@gmail.com', '9666666666', 'Banashankari', '1st phase');

INSERT INTO 
	COFFEE (NAME , DESCRIPTION , PRICE , TOTOAL_SERVINGS_AVAILABLE_FORADAY ) 
VALUES
  	('mocha', 'addition of chocolate', 50, 100),
  	('latte', 'two parts coffee and one part steamed milk', 100, 50),
	('cappuccino', 'espresso and milk', 150, 60);



INSERT INTO 
	ORDER_MASTER (COFFEE_ID , OM_CREATED_BY , OM_CREATED_ON , OM_ORDER_NUMBER , OM_QUANTITY , CUSTOMER_ID ) 
VALUES
  	(1, 'lokesh@gmail.com', GETDATE(), '123ab', 2, 1),
  	(2, 'john@gmail.com', GETDATE(), '456cd', 1, 2),
	(3, 'lokesh@gmail.com', GETDATE(), '123ab', 2, 1);


