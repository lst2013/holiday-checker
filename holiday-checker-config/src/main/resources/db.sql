CREATE TABLE IF NOT EXISTS holiday_check_config (
  id INT AUTO_INCREMENT,
  config_key VARCHAR(20) NOT NULL,
  config_value VARCHAR(100) NOT NULL,
  CONSTRAINT holiday_check_config_pk PRIMARY KEY (id),
  CONSTRAINT holiday_check_config_uq UNIQUE (config_key)
);

INSERT INTO holiday_check_config (config_key, config_value) VALUES ('url', 'https://holidayapi.com/v1/holidays'), ('key', '7b499143-bddb-43bf-8413-45359d2a9712');