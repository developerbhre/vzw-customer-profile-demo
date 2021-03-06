DROP SCHEMA IF EXISTS EQUIPMENT_SCHEMA CASCADE;

CREATE SCHEMA EQUIPMENT_SCHEMA; 
 
CREATE TABLE EQUIPMENT_SCHEMA.equipment (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  sku_id VARCHAR(250) NOT NULL,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE EQUIPMENT_SCHEMA.customer_equipment (
   id INT AUTO_INCREMENT  PRIMARY KEY,
  customer_id INT NOT NULL,
  equipment_id INT NOT NULL,
  FOREIGN KEY(equipment_id) REFERENCES EQUIPMENT_SCHEMA.equipment(id) ON DELETE CASCADE
);