-- Insert sample addresses
INSERT INTO address (status, created_on, last_modified_on, created_by, modified_by, state, city, zip_code, street, house_number)
VALUES
    ('ACTIVE', NOW(), NOW(), 'admin', 'admin', 'California', 'Los Angeles', '90001', 'Sunset Blvd', '123'),
    ('ACTIVE', NOW(), NOW(), 'admin', 'admin', 'New York', 'New York City', '10001', 'Broadway', '456'),
    ('INACTIVE', NOW(), NOW(), 'admin', 'system', 'Texas', 'Houston', '77001', 'Main St', '789');

-- Insert sample customers
INSERT INTO customer (status, created_on, last_modified_on, created_by, modified_by, customer_id, name, surname, email, phone, address_id, password, gender)
VALUES
    ('ACTIVE', NOW(), NOW(), 'admin', 'admin', 'CUST001', 'John', 'Paul', 'john.paul@neo.com', '+1234567890', 1, 'hashed_password_1', 'MALE'),
    ('ACTIVE', NOW(), NOW(), 'admin', 'system', 'CUST002', 'Victoria', 'Smith', 'victoria.smith@neo.com', '+9876543210', 2, 'hashed_password_2', 'FEMALE'),
    ('DELETED', NOW(), NOW(), 'system', 'system', 'CUST003', 'Alex', 'Johnson', 'alex.johnson@neo.com', '+1122334455', 3, 'hashed_password_3', 'MALE');
