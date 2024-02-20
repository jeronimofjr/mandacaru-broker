CREATE TABLE IF NOT EXISTS stock(
                      id VARCHAR(255) PRIMARY KEY,
                      symbol VARCHAR(10) NOT NULL,
                      company_name VARCHAR(100) NOT NULL,
                      price NUMERIC(10) NOT NULL
);