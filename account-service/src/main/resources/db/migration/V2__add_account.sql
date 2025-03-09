INSERT INTO customer_accounts (
    customer_id, account_number, account_type, balance, currency,
    is_joint_account, branch_code, status, created_on, last_modified_on,
    created_by, modified_by
) VALUES
      ('CUST001', '1234567890', 'SAVINGS', 5000.75, 'USD', 0, 'BR001', 'ACTIVE', NOW(), NOW(), 'admin', 'admin'),
      ('CUST002', '1234567891', 'CURRENT', 15000.00, 'EUR', 1, 'BR002', 'ACTIVE', NOW(), NOW(), 'admin', 'admin'),
      ('CUST003', '1234567892', 'FIXED_DEPOSIT', 250000.50, 'GBP', 0, 'BR003', 'ACTIVE', NOW(), NOW(), 'admin', 'admin'),
      ('CUST004', '1234567893', 'SAVINGS', 780.25, 'USD', 0, 'BR004', 'INACTIVE', NOW(), NOW(), 'admin', 'admin'),
      ('CUST005', '1234567894', 'CURRENT', 12000.00, 'NGN', 1, 'BR005', 'ACTIVE', NOW(), NOW(), 'admin', 'admin'),
      ('CUST001', '1234564890', 'CURRENT', 51000.75, 'USD', 0, 'BR001', 'ACTIVE', NOW(), NOW(), 'admin', 'admin'),
      ('CUST003', '1234567591', 'CURRENT', 16000.00, 'EUR', 1, 'BR002', 'ACTIVE', NOW(), NOW(), 'admin', 'admin'),
      ('CUST001', '1234567862', 'FIXED_DEPOSIT', 20000.50, 'GBP', 0, 'BR003', 'ACTIVE', NOW(), NOW(), 'admin', 'admin');
;
