INSERT INTO EQUIPMENT_SCHEMA.equipment (id,sku_id, name) VALUES
  (1001,'Aw2', 'Dangote'),
  (2001,'V343', 'Gates'),
  (3001,'B343', 'Alakija');
 
INSERT INTO EQUIPMENT_SCHEMA.customer_equipment (equipment_id,customer_id) VALUES
  (1001,100),
  (2001,200),
  (3001,300);