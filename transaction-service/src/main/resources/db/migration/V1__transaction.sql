CREATE TABLE transaction (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Auto-incrementing primary key
      customer_id VARCHAR(25) NOT NULL,
      amount DECIMAL(18,2) NOT NULL,
      transaction_reference VARCHAR(50) UNIQUE NOT NULL,
      account_number VARCHAR(25) NOT NULL,
      narration TEXT,
      transaction_type VARCHAR(20) NOT NULL,  -- Adjust ENUM values based on your TransactionType enum
      fee DECIMAL(18,2),
      currency VARCHAR(10) NOT NULL,
      settle_instantly BOOLEAN NOT NULL,
      payment_method VARCHAR(20) NOT NULL,  -- Adjust based on your PaymentMethod enum
      status  VARCHAR(20) NOT NULL DEFAULT 'PENDING',
      created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      last_modified_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      created_by VARCHAR(50),
      modified_by VARCHAR(50)
);

CREATE INDEX idx_customer_id ON transaction(customer_id);
