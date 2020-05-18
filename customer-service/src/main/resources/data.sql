INSERT INTO CUSTOMER_SCHEMA.customer (id,first_name, last_name) VALUES
  (100,'Aliko', 'Dangote'),
  (200,'Bill', 'Gates'),
  (300,'Folrunsho', 'Alakija');
 
INSERT INTO CUSTOMER_SCHEMA.address (address1, address2, city,state,zip,customer_id) VALUES
  ('Flat 11', 'Spring Apt', 'New York','New York','56903',100),
  ('Flat 12', 'Spring Apt', 'New York','New York','56903',200),
  ('Flat 13', 'Spring Apt', 'New York','New York','56903',300);