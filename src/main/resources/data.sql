
DROP TABLE IF EXISTS register;

CREATE TABLE register (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE,
  value VARCHAR(30) NOT NULL,
  timestamp TIMESTAMP(3) NOT NULL
);

ALTER TABLE register ADD CONSTRAINT name_timestamp_unique UNIQUE (name, timestamp);

INSERT INTO register (name, value, timestamp) VALUES
('wallet', '1000', CURRENT_TIMESTAMP),
('savings', '5000', CURRENT_TIMESTAMP),
('insurance policy', '0', CURRENT_TIMESTAMP),
('food expenses', '0', CURRENT_TIMESTAMP);