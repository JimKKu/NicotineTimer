CREATE TABLE smoke_timer (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     rev1 VARCHAR(100),
     rev2 VARCHAR(100),
     date VARCHAR(8),   -- yyyyMMdd
     time VARCHAR(6)    -- HHmmss
);


CREATE TABLE IF NOT EXISTS DATE_INFO (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date CHAR(14) COMMENT '日期，格式：YYYYMMDDHHmm',
    create_time CHAR(14) COMMENT '创建时间，格式：YYYYMMDDHHMMSS'
);