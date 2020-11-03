
-- -----------------------------------------------------
-- Update Table Customer
-- -----------------------------------------------------

ALTER TABLE customer
    ADD createdAt datetime, ADD updateAt datetime;


-- -----------------------------------------------------
-- Update Table address
-- -----------------------------------------------------
ALTER TABLE address
    ADD createdAt datetime, ADD updateAt datetime;