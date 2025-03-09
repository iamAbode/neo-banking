CREATE TABLE customer_accounts (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       customer_id VARCHAR(25) NOT NULL,
       account_number VARCHAR(20) UNIQUE NOT NULL,
       account_type VARCHAR(20) NOT NULL,
       balance DECIMAL(18,2) NOT NULL,
       currency VARCHAR(5) NOT NULL,
       is_joint_account BOOLEAN DEFAULT FALSE,
       branch_code VARCHAR(20),
       status  VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
       created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       last_modified_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
       created_by VARCHAR(50),
       modified_by VARCHAR(50)
);

CREATE INDEX idx_customer_id ON customer_accounts(customer_id);
