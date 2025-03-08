CREATE TABLE address (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     status ENUM('ACTIVE', 'INACTIVE', 'DELETED') NOT NULL DEFAULT 'ACTIVE',
     created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     last_modified_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     created_by VARCHAR(50),
     modified_by VARCHAR(50),
     state VARCHAR(20),
     city VARCHAR(20),
     zip_code VARCHAR(20),
     street VARCHAR(50),
     house_number VARCHAR(5)
);

CREATE TABLE customer (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
      created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      last_modified_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      created_by VARCHAR(50),
      modified_by VARCHAR(50),
      customer_id VARCHAR(25) NOT NULL UNIQUE,
      name VARCHAR(25),
      surname VARCHAR(25),
      email VARCHAR(80) NOT NULL UNIQUE,
      phone VARCHAR(25),
      address_id BIGINT NOT NULL,
      password VARCHAR(100),
      gender VARCHAR(10) NOT NULL,
      CONSTRAINT fk_customer_address FOREIGN KEY (address_id) REFERENCES address(id) ON DELETE CASCADE
);