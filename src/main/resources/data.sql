
DROP TABLE IF EXISTS register;

CREATE TABLE register (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  value VARCHAR(30) NOT NULL,
  timestamp TIMESTAMP(3) NOT NULL
);

CREATE TABLE transfer (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  origin_register_id VARCHAR(100) NOT NULL,
  target_register_id VARCHAR(30) NOT NULL,
  timestamp TIMESTAMP(3) NOT NULL,
  amount VARCHAR(30) NOT NULL,
  archived BIT NOT NULL DEFAULT 0,
  FOREIGN KEY (origin_register_id) REFERENCES register(id),
  FOREIGN KEY (target_register_id) REFERENCES register(id)
);

ALTER TABLE register ADD CONSTRAINT name_timestamp_unique UNIQUE (name, timestamp);

INSERT INTO register (name, value, timestamp) VALUES
('wallet', '1000', CURRENT_TIMESTAMP),
('savings', '5000', CURRENT_TIMESTAMP),
('insurance policy', '0', CURRENT_TIMESTAMP),
('food expenses', '0', CURRENT_TIMESTAMP);